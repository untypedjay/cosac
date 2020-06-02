package swe4.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.Dish;
import swe4.model.entities.Order;
import swe4.model.entities.TimeSlot;
import swe4.model.entities.User;
import swe4.util.PasswordUtil;

import java.time.LocalTime;

public class DataModel {
  private static final ObservableList<TimeSlot> timeSlots = FXCollections.observableArrayList();
  private static final ObservableList<Dish> dishes = FXCollections.observableArrayList();
  private static final ObservableList<Order> orders = FXCollections.observableArrayList();

  public static void loadMockData() {
    timeSlots.setAll(
      new TimeSlot(LocalTime.of(11, 00), LocalTime.of(11, 30), 10),
      new TimeSlot(LocalTime.of(11, 30), LocalTime.of(12, 00), 10),
      new TimeSlot(LocalTime.of(12, 00), LocalTime.of(12, 30), 10),
      new TimeSlot(LocalTime.of(12, 30), LocalTime.of(13, 00), 10)
    );

    dishes.setAll(
      new Dish("Spaghetti Bolognese","Italienische Köstlichkeiten",  640),
      new Dish("Cordon Bleu vom Schwein mit Kartoffel und Reis","Heftig Deftig",  750),
      new Dish("Gebackene Spinatpalatschinke mit Kartoffeln ","Vegetarische Gerichte",  640),
      new Dish("Faschierte Laibchen mit Kartoffelpüree und Gemüse ","Vegetarische Gerichte",  750),
      new Dish("Spaghetti mit Tomatensauce ","Vegetarische Gerichte",  640)
    );

    orders.setAll(
      new Order(UserRepository.getUsers().get(1), dishes.get(4), timeSlots.get(2)),
      new Order(UserRepository.getUsers().get(2), dishes.get(3), timeSlots.get(1)),
      new Order(UserRepository.getUsers().get(4), dishes.get(4), timeSlots.get(2)),
      new Order(UserRepository.getUsers().get(0), dishes.get(2), timeSlots.get(0)),
      new Order(UserRepository.getUsers().get(2), dishes.get(0), timeSlots.get(3))
    );
  }

  public static void deleteDish(String dishName) {
    for (Dish dish : dishes) {
      if (dish.getName().equals(dishName)) {
        dishes.remove(dish);
      }
    }
  }

  public static void deleteTimeSlot(LocalTime startTime, LocalTime endTime) {
    for (TimeSlot timeSlot : timeSlots) {
      if (timeSlot.getStartTime().equals(startTime) && timeSlot.getEndTime().equals(endTime)) {
        timeSlots.remove(timeSlot);
      }
    }
  }

  public static ObservableList<TimeSlot> getTimeSlots() {
    return timeSlots;
  }



  public static ObservableList<Dish> getDishes() {
    return dishes;
  }

  public static ObservableList<Order> getOrders() {
    return orders;
  }
}
