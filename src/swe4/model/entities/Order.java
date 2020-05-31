package swe4.model.entities;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Order {
  private User customer = null;
  private Dish dish = null;
  private TimeSlot timeSlot = null;
  private LocalTime orderTime = null;

  public Order(User customer, Dish dish, TimeSlot timeSlot) {
    this.customer = customer;
    this.dish = dish;
    this.timeSlot = timeSlot;
    this.orderTime = LocalTime.now();
  }

  public String getCustomerName() {
    return customer.getFirstName() + " " + customer.getLastName();
  }

  public String getDishName() {
    return dish.getName();
  }

  public String getTimeSlotString() {
    return timeSlot.getStartTime() + " - " + timeSlot.getEndTime();
  }

  public String getOrderTimeString() {
    return orderTime.format(DateTimeFormatter.ISO_TIME);
  }
}
