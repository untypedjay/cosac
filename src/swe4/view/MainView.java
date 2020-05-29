package swe4.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import swe4.model.entities.Meal;
import swe4.model.entities.Order;
import swe4.model.entities.TimeSlot;
import swe4.model.entities.User;

import java.time.LocalTime;

public class MainView {
    private static TimeSlot ts1 = new TimeSlot(LocalTime.of(11, 00), LocalTime.of(11, 30), 10);
    private static TimeSlot ts2 = new TimeSlot(LocalTime.of(11, 30), LocalTime.of(12, 00), 10);
    private static TimeSlot ts3 = new TimeSlot(LocalTime.of(12, 00), LocalTime.of(12, 30), 10);
    private static TimeSlot ts4 = new TimeSlot(LocalTime.of(12, 30), LocalTime.of(13, 00), 10);
    private static ObservableList<TimeSlot> timeSlots = FXCollections.observableArrayList(ts1, ts2, ts3, ts4);

    private static User bill = new User("Bill", "Yard", "yard", "yard123");
    private static User claire = new User("Claire", "Waßer", "wasser", "wasser123");
    private static User rainer = new User("Rainer", "Zufall", "zufall", "zufall123");
    private static User martha = new User("Martha", "Pfahl", "pfahl", "pfahl123");
    private static User marie = new User("Marie", "Huana", "huana", "huana123");
    private static ObservableList<User> users = FXCollections.observableArrayList(bill, claire, rainer, martha, marie);

    private static Meal m1 = new Meal("Italienische Köstlichkeiten", "Spaghetti Bolognese", 640);
    private static Meal m2 = new Meal("Heftig Deftig", "Cordon Bleu vom Schwein mit Kartoffel und Reis", 750);
    private static Meal m3 = new Meal("Vegetarische Gerichte", "Gebackene Spinatpalatschinke mit Kartoffeln ", 640);
    private static Meal m4 = new Meal("Vegetarische Gerichte", "Faschierte Laibchen mit Kartoffelpüree und Gemüse ", 750);
    private static Meal m5 = new Meal("Vegetarische Gerichte", "Spaghetti mit Tomatensauce ", 640);
    private static ObservableList<Meal> meals = FXCollections.observableArrayList(m1, m2, m3, m4, m5);

    private static Order o1 = new Order(claire, m5, ts3);
    private static Order o2 = new Order(bill, m3, ts1);
    private static Order o3 = new Order(rainer, m5, ts3);
    private static Order o4 = new Order(claire, m5, ts4);
    private static Order o5 = new Order(marie, m4, ts4);
    private static Order o6 = new Order(bill, m2, ts2);
    private static Order o7 = new Order(bill, m1, ts1);
    private static Order o8 = new Order(martha, m2, ts3);
    private static ObservableList<Order> orders = FXCollections.observableArrayList(o1, o2, o3, o4, o5, o6, o7, o8);

  public static Scene construct() {
      TabPane mainMenu = new TabPane();
      Tab tabOrders = new Tab("Bestellungen");
      Tab tabMenu = new Tab("Speisekarte");
      Tab tabTimeSlots = new Tab("Zeitbereiche");
      Tab tabUsers = new Tab("Benutzerverwaltung");
      tabOrders.setContent(OrderTab.construct(orders));
      tabMenu.setContent(MenuTab.construct(meals));
      tabTimeSlots.setContent(TimeSlotsTab.construct(timeSlots));
      tabUsers.setContent(UsersTab.construct(users));
      mainMenu.getTabs().addAll(tabOrders, tabMenu, tabTimeSlots, tabUsers);
      mainMenu.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
      return new Scene(mainMenu, 800, 400);
  }
}