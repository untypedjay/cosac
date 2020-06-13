package swe4.model.data;

import javafx.collections.ObservableList;
import swe4.model.entities.Order;

public interface OrderRepo {
  ObservableList<Order> getOrders();
  Order getOrderById(String id);
  boolean deleteOrder(String id);
  boolean updateOrders();
  boolean saveOrders();
}
