package swe4.view;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainView {
  public MainView() {
      TabPane mainMenu = new TabPane();
      Tab tabOrders = new Tab("Bestellungen");
      Tab tabMenu = new Tab("Speisekarte");
      Tab tabTimeSlots = new Tab("Zeitbereiche");
      Tab tabUsers = new Tab("Benutzerverwaltung");
      tabOrders.setContent(orderTab());
      tabMenu.setContent(menuTab());
      tabTimeSlots.setContent(timeSlotsTab());
      tabUsers.setContent(usersTab());
      mainMenu.getTabs().addAll(tabOrders, tabMenu, tabTimeSlots, tabUsers);
      mainMenu.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
      return new Scene(mainMenu, 800, 400);
  }
}
