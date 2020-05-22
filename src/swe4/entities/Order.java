package swe4.entities;

import java.time.LocalTime;

public class Order {
  private User customer = null;
  private Meal meal = null;
  private TimeSlot timeSlot = null;
  private LocalTime orderTime = null;

  public Order(User customer, Meal meal, TimeSlot timeSlot) {
    this.customer = customer;
    this.meal = meal;
    this.timeSlot = timeSlot;
    this.orderTime = LocalTime.now();
  }

  public User getCustomer() {
    return customer;
  }

  public Meal getMeal() {
    return meal;
  }

  public TimeSlot getTimeSlot() {
    return timeSlot;
  }

  public LocalTime getOrderTime() {
    return orderTime;
  }
}
