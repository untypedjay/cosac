package swe4.model.entities;

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

  public String getCustomerName() {
    return customer.getFirstName() + " " + customer.getLastName();
  }

  public String getMealName() {
    return meal.getDescription();
  }

  public String getTimeSlot() {
    return timeSlot.getStartTime() + " - " + timeSlot.getEndTime();
  }

  public LocalTime getOrderTime() {
    return orderTime;
  }
}
