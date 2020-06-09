package swe4.model.entities;

import swe4.util.DateUtil;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
  private static final long serialVersionUID = -4610242843432448824L;
  private User customer = null;
  private Dish dish = null;
  private TimeSlot timeSlot = null;
  private Date orderTime = null;

  public Order(User customer, Dish dish, TimeSlot timeSlot) {
    this.customer = customer;
    this.dish = dish;
    this.timeSlot = timeSlot;
    this.orderTime = new Date();
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

  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    in.defaultReadObject();
  }
}
