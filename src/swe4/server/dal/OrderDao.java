package swe4.server.dal;

import javafx.collections.ObservableList;
import swe4.model.entities.Order;
import java.sql.SQLException;

public interface OrderDao extends AutoCloseable {
  ObservableList<Order> getAll() throws SQLException;
  void store(Order order) throws SQLException;
}
