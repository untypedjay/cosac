package swe4.model.entities;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Order {
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
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    return timeFormat.format(orderTime);
  }
}
