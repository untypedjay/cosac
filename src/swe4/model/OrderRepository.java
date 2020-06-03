package swe4.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.Order;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class OrderRepository {

  private static final ObservableList<Order> orders = FXCollections.observableArrayList();

  public static void loadMockOrders() {
    orders.setAll(
      new Order(UserRepository.getUsers().get(1), DishRepository.getDishes().get(4), TimeSlotRepository.getTimeSlots().get(2)),
      new Order(UserRepository.getUsers().get(2), DishRepository.getDishes().get(3), TimeSlotRepository.getTimeSlots().get(1)),
      new Order(UserRepository.getUsers().get(4), DishRepository.getDishes().get(4), TimeSlotRepository.getTimeSlots().get(2)),
      new Order(UserRepository.getUsers().get(0), DishRepository.getDishes().get(2), TimeSlotRepository.getTimeSlots().get(0)),
      new Order(UserRepository.getUsers().get(2), DishRepository.getDishes().get(0), TimeSlotRepository.getTimeSlots().get(3))
    );
  }

  public static ObservableList<Order> getOrders() {
    return orders;
  }
}
