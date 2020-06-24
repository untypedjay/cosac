package swe4.server.dal;

import javafx.collections.ObservableList;
import swe4.model.entities.Dish;
import java.sql.SQLException;

public interface DishDao extends AutoCloseable {
  ObservableList<Dish> getAll() throws SQLException;
  Dish get(String name) throws SQLException;
  void store(Dish dish) throws SQLException;
}
