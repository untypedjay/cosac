package swe4.model.dal.orders;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.Order;
import swe4.server.RMIInterface;

import java.rmi.Naming;
import java.util.Iterator;

public class OrderRepoRMIImpl implements OrderRepo {
  private static final String SERVER_URL = "rmi://127.0.0.1/RMIServer";
  private ObservableList<Order> orders = FXCollections.observableArrayList();

  public OrderRepoRMIImpl() {
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
    RMIInterface server;
    try {
      server = (RMIInterface) Naming.lookup(SERVER_URL);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    orders.clear();
    orders = server.loadOrders();
    return true;
  }

  @Override
  public boolean saveOrders() {
    RMIInterface server;
    try {
      server = (RMIInterface) Naming.lookup(SERVER_URL);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    Object[] orderData = new Object[getOrders().size()];
    for (int i = 0; i < orderData.length; ++i) {
      orderData[i] = getOrders().get(i);
    }
    server.saveOrders(orderData);
    return true;
  }
}