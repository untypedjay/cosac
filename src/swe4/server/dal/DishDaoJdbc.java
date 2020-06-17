package swe4.server.dal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.data.dishes.DishRepoDbImpl;
import swe4.model.entities.Dish;

import java.sql.*;

import static swe4.server.RMIDatabaseServer.*;

public class DishDaoJdbc implements DishDao {
  @Override
  public ObservableList<Dish> getAll() throws SQLException {
    ObservableList<Dish> dishes = FXCollections.observableArrayList();

    try (Connection connection = DriverManager.getConnection(CONN, USERNAME, PASSWORD);
         PreparedStatement statement = connection.prepareStatement("select * from dish")) {

      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          dishes.add(new Dish(resultSet.getString("name"),
                              resultSet.getString("section"),
                              resultSet.getInt("priceInCents"), new DishRepoDbImpl()));
        }
      }
    }
    return dishes;
  }

  @Override
  public void store(Dish dish) throws SQLException {
    try (Connection connection = DriverManager.getConnection(CONN, USERNAME, PASSWORD);
         PreparedStatement statement = connection.prepareStatement("insert into dish (name, section, priceInCents) values (?, ?, ?, ?)",
                                       Statement.RETURN_GENERATED_KEYS)) {
      statement.setString(1, dish.getName());
      statement.setString(2, dish.getSection());
      statement.setString(3, String.valueOf(dish.getPriceInCents()));

      statement.executeUpdate();
    }
  }

  @Override
  public void close() throws Exception {

  }
}
