package swe4.views.admin;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import swe4.model.data.Repository;
import swe4.util.DateUtil;
import swe4.views.*;
import java.util.Date;

public class AdminView extends BorderPane {
  private ViewController controller;
  private Repository repo;

  public AdminView(ViewController controller, Repository repo) {
    this.controller = controller;
    this.repo = repo;
    this.setTop(mainMenuButtons());
    this.setCenter(mainMenuTabs());
  }

  private HBox mainMenuButtons() {
    HBox mainMenuButtons = new HBox();
    Label lastSavedLabel = new Label("Last saved at " + DateUtil.formatTime(new Date()));
    Button saveButton = new Button("Save");
    saveButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        repo.save();
        lastSavedLabel.setText("Last saved at " + DateUtil.formatTime(new Date()));
      }
    });
    Button updateButton = new Button("Update");
    Button logoutButton = new Button("Logout");
    logoutButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
          controller.render(ViewType.LOGIN_VIEW, 780, 450);
        }
      }
    );
    mainMenuButtons.getChildren().addAll(lastSavedLabel, saveButton, updateButton, logoutButton);
    return mainMenuButtons;
  }

  private TabPane mainMenuTabs() {

    TabPane mainMenuTabs = new TabPane();

    Tab tabOrders = new Tab("Orders");
    tabOrders.setContent(new OrderTab(repo));

    Tab tabMenu = new Tab("Menu");
    tabMenu.setContent(new MenuTab(repo));

    Tab tabTimeSlots = new Tab("Time Slots");
    tabTimeSlots.setContent(new TimeSlotsTab(repo));

    Tab tabUsers = new Tab("User Management");
    tabUsers.setContent(new UsersTab(repo));

    mainMenuTabs.getTabs().addAll(tabOrders, tabMenu, tabTimeSlots, tabUsers);
    mainMenuTabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
    return mainMenuTabs;
  }
}