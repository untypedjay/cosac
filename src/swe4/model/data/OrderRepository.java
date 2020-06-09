package swe4.model.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.Order;

import static swe4.model.data.DishRepository.getDish;
import static swe4.model.data.UserRepository.getUser;

public class OrderRepository {

  private static ObservableList<Order> orders = FXCollections.observableArrayList();

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

  public static void receiveOrders(Object[] orderObjectArray) {
    orders.clear();
    for (int i = 0; i < orderObjectArray.length; ++i) {
      Order order = (Order) orderObjectArray[i];
      orders.add(new Order(order.getCustomer(), getDish(order.getDishName()), order.getTimeSlot()));
    }
    System.out.println("client, received orders: " + orders);
  }
}
