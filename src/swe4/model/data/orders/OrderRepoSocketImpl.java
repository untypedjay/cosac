package swe4.model.data.orders;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.Order;

import java.util.Iterator;

public class OrderRepoSocketImpl implements OrderRepo {
  private ObservableList<Order> orders = FXCollections.observableArrayList();

  public OrderRepoSocketImpl() {
    updateOrders();
  }

  @Override
  public ObservableList<Order> getOrders() {
    return orders;
  }

  @Override
  public Order getOrderById(String id) {
    for (Order order : orders) {
      if (order.getId().equals(id)) {
        return order;
      }
    }
    return null;
  }

  @Override
  public boolean addOrder(Order order) {
    orders.add(order);
    return true;
  }

  @Override
  public boolean deleteOrder(String id) {
    for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {
      if (iterator.next().getId().equals(id)) {
        iterator.remove();
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean updateOrders() {
    //TODO
    return false;
  }

  @Override
  public boolean saveOrders() {
    //TODO
    return false;
  }
}
