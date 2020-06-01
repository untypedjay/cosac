package swe4.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.Dish;
import swe4.model.entities.Order;
import swe4.model.entities.TimeSlot;
import swe4.model.entities.User;

import java.time.LocalTime;

public class DataModel {
  private static final ObservableList<TimeSlot> timeSlots = FXCollections.observableArrayList();
  private static final ObservableList<User> users = FXCollections.observableArrayList();
  private static final ObservableList<Dish> dishes = FXCollections.observableArrayList();
  private static final ObservableList<Order> orders = FXCollections.observableArrayList();

  public static void loadMockData() {
    timeSlots.setAll(
      new TimeSlot(LocalTime.of(11, 00), LocalTime.of(11, 30), 10),
      new TimeSlot(LocalTime.of(11, 30), LocalTime.of(12, 00), 10),
      new TimeSlot(LocalTime.of(12, 00), LocalTime.of(12, 30), 10),
      new TimeSlot(LocalTime.of(12, 30), LocalTime.of(13, 00), 10)
    );

    users.setAll(
      new User("Bill", "Yard", "yard", "yard123"),
      new User("Claire", "Waßer", "wasser", "wasser123"),
      new User("Rainer", "Zufall", "zufall", "zufall123"),
      new User("Martha", "Pfahl", "pfahl", "pfahl123"),
      new User("Marie", "Huana", "huana", "huana123")
    );

    dishes.setAll(
      new Dish("Spaghetti Bolognese","Italienische Köstlichkeiten",  640),
      new Dish("Cordon Bleu vom Schwein mit Kartoffel und Reis","Heftig Deftig",  750),
      new Dish("Gebackene Spinatpalatschinke mit Kartoffeln ","Vegetarische Gerichte",  640),
      new Dish("Faschierte Laibchen mit Kartoffelpüree und Gemüse ","Vegetarische Gerichte",  750),
      new Dish("Spaghetti mit Tomatensauce ","Vegetarische Gerichte",  640)
    );

    orders.setAll(
      new Order(users.get(1), dishes.get(4), timeSlots.get(2)),
      new Order(users.get(2), dishes.get(3), timeSlots.get(1)),
      new Order(users.get(4), dishes.get(4), timeSlots.get(2)),
      new Order(users.get(0), dishes.get(2), timeSlots.get(0)),
      new Order(users.get(2), dishes.get(0), timeSlots.get(3))
    );
  }

  public static void deleteUser(String userName) {
    for (User user : users) {
      if (user.getUserName().equals(userName)) {
        users.remove(user);
      }
    }
  }

  public static ObservableList<TimeSlot> getTimeSlots() {
    return timeSlots;
  }

  public static ObservableList<User> getUsers() {
    return users;
  }

  public static ObservableList<Dish> getDishes() {
    return dishes;
  }

  public static ObservableList<Order> getOrders() {
    return orders;
  }
}
