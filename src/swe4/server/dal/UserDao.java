package swe4.server.dal;

import javafx.collections.ObservableList;
import swe4.model.entities.User;
import java.sql.SQLException;

public interface UserDao extends AutoCloseable {
  ObservableList<User> getAll() throws SQLException;
  User get(String username) throws SQLException;
  void store(User user) throws SQLException;
}
