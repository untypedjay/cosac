package swe4.server;

import javafx.collections.ObservableList;
import swe4.model.entities.Dish;
import swe4.model.entities.Order;
import swe4.model.entities.TimeSlot;
import swe4.model.entities.User;

import java.rmi.Remote;

public interface RMIInterface extends Remote {
  ObservableList<Dish> loadDishes();
  boolean saveDishes(Object[] dishes);

  ObservableList<Order> loadOrders();
  boolean saveOrders(Object[] orders);

  ObservableList<TimeSlot> loadTimeSlots();
  boolean saveTimeSlots(Object[] timeSlots);

  ObservableList<User> loadUsers();
  boolean saveUsers(Object[] users);
}
