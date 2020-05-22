package swe4.entities;

import java.time.LocalTime;

public class Order {
  private User customer = null;
  private String orderItem = "";
  private TimeSlot timeSlot = null;
  private LocalTime orderTime = null;

  public Order(User customer, String orderItem, TimeSlot timeSlot) {
    this.customer = customer;
    this.orderItem = orderItem;
    this.timeSlot = timeSlot;
    this.orderTime = LocalTime.now();
  }

  public User getCustomer() {
    return customer;
  }

  public String getOrderItem() {
    return orderItem;
  }

  public TimeSlot getTimeSlot() {
    return timeSlot;
  }

  public LocalTime getOrderTime() {
    return orderTime;
  }
}
