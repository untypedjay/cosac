package swe4.model.data.orders;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.data.dishes.DishRepo;
import swe4.model.data.timeSlots.TimeSlotRepo;
import swe4.model.data.users.UserRepo;
import swe4.model.entities.Order;

import java.util.Iterator;

public class OrderRepoMockImpl implements OrderRepo {
  private ObservableList<Order> orders = FXCollections.observableArrayList();
  private UserRepo userRepo;
  private DishRepo dishRepo;
  private TimeSlotRepo timeSlotRepo;

  public OrderRepoMockImpl(UserRepo userRepo, DishRepo dishRepo, TimeSlotRepo timeSlotRepo) {
    this.userRepo = userRepo;
    this.dishRepo = dishRepo;
    this.timeSlotRepo = timeSlotRepo;

    orders.setAll(
      new Order(userRepo.getUsers().get(1), dishRepo.getDishes().get(4), timeSlotRepo.getTimeSlots().get(2)),
      new Order(userRepo.getUsers().get(2), dishRepo.getDishes().get(3), timeSlotRepo.getTimeSlots().get(1)),
      new Order(userRepo.getUsers().get(4), dishRepo.getDishes().get(4), timeSlotRepo.getTimeSlots().get(2)),
      new Order(userRepo.getUsers().get(0), dishRepo.getDishes().get(2), timeSlotRepo.getTimeSlots().get(0)),
      new Order(userRepo.getUsers().get(2), dishRepo.getDishes().get(0), timeSlotRepo.getTimeSlots().get(3))
    );
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
    return true;
  }

  @Override
  public boolean saveOrders() {
    return true;
  }
}

//  public void receiveOrders(Object[] orderObjectArray) {
//    orders.clear();
//    for (int i = 0; i < orderObjectArray.length; ++i) {
//      Order order = (Order) orderObjectArray[i];
//      orders.add(new Order(order.getCustomer(), dishRepo.findByName(order.getDishName()), order.getTimeSlot()));
//    }
//    System.out.println("client, received orders: " + orders);
//  }