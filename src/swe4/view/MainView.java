package swe4.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import swe4.model.DataModel;
import swe4.model.entities.Dish;
import swe4.model.entities.Order;
import swe4.model.entities.TimeSlot;
import swe4.model.entities.User;

import java.time.LocalTime;

public class MainView {

  public static Scene create() {
      TabPane mainMenu = new TabPane();
      Tab tabOrders = new Tab("Bestellungen");
      Tab tabMenu = new Tab("Speisekarte");
      Tab tabTimeSlots = new Tab("Zeitbereiche");
      Tab tabUsers = new Tab("Benutzerverwaltung");
      DataModel data = new DataModel();
      tabOrders.setContent(OrderTab.create(data.getOrders()));
      tabMenu.setContent(MenuTab.create(data.getDishes()));
      tabTimeSlots.setContent(TimeSlotsTab.construct(data.getTimeSlots()));
      tabUsers.setContent(UsersTab.create(data.getUsers()));
      mainMenu.getTabs().addAll(tabOrders, tabMenu, tabTimeSlots, tabUsers);
      mainMenu.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
      return new Scene(mainMenu, 800, 400);
  }
}