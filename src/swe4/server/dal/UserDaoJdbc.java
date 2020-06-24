package swe4.server.dal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.data.users.UserRepoDbImpl;
import swe4.model.entities.User;
import java.sql.*;
import static swe4.server.RMIDatabaseServer.*;

public class UserDaoJdbc implements UserDao {
  @Override
  public ObservableList<User> getAll() throws SQLException {
    ObservableList<User> users = FXCollections.observableArrayList();

    try (Connection connection = DriverManager.getConnection(CONN, USERNAME, PASSWORD);
         PreparedStatement statement = connection.prepareStatement("select * from user")) {

      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          User.Role role = null;
          if (resultSet.getString("role") == "ADMIN") {
            role = User.Role.ADMIN;
          } else {
            role = User.Role.CUSTOMER;
          }
          users.add(new User(resultSet.getString("firstName"),
            resultSet.getString("lastName"),
            resultSet.getString("username"),
            resultSet.getString("passwordHash"),
            resultSet.getBoolean("locked"),
            role,
            new UserRepoDbImpl()));
        }
      }
    }
    return users;
  }

  @Override
  public User get(String username) throws SQLException {
    try (Connection connection = DriverManager.getConnection(CONN, USERNAME, PASSWORD);
         PreparedStatement statement = connection.prepareStatement("select * from user where username like ?")) {
      statement.setObject(0, username);
      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          return new User(resultSet.getString("firstName"),
                          resultSet.getString("lastName"),
                          resultSet.getString("username"),
                          resultSet.getString("passwordHash"),
                          resultSet.getBoolean("locked"),
                          resultSet.getString("role"),
                          new UserRepoDbImpl()));
        }
      }
      return null;
    }
  }


  @Override
  public void store(User user) throws SQLException {
    try (Connection connection = DriverManager.getConnection(CONN, USERNAME, PASSWORD);
         PreparedStatement statement = connection.prepareStatement("insert into user (username, firstName, lastName, passwordHash, role, locked) values (?, ?, ?, ?, ?, ?)",
           Statement.RETURN_GENERATED_KEYS)) {
      statement.setString(1, user.getUsername());
      statement.setString(2, user.getFirstName());
      statement.setString(3, user.getLastName());
      statement.setString(4, user.getPasswordHash());
      statement.setString(5, String.valueOf(user.getRole()));
      statement.setBoolean(6, user.isLocked());

      statement.executeUpdate();
    }
  }

  @Override
  public void close() {

  }
}
