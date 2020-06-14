package swe4.model.entities;

import swe4.util.DateUtil;
import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
  private String id = null;
  private User customer;
  private Dish dish;
  private TimeSlot timeSlot;
  private Date orderTime;

  public Order(User customer, Dish dish, TimeSlot timeSlot) {
    this.customer = customer;
    this.dish = dish;
    this.timeSlot = timeSlot;
    this.orderTime = new Date();
  }

  public String getId() {
    return id;
  }

  public User getCustomer() {
    return customer;
  }

  public String getDishName() {
    return dish.getName();
  }

  public TimeSlot getTimeSlot() {
    return timeSlot;
  }

  public String getTimeSlotString() {
    return timeSlot.getStartTime() + " - " + timeSlot.getEndTime();
  }

  public String getOrderTimeString() {
    return DateUtil.formatTime(orderTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("order: ");
    sb.append(customer.getFirstName());
    sb.append(" ");
    sb.append(customer.getLastName());
    sb.append(", ");
    sb.append(getDishName());
    sb.append(", ");
    sb.append(getTimeSlotString());
    sb.append(", ");
    return sb.toString();
  }
}
