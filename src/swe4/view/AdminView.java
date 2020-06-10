package swe4.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import swe4.model.data.UserRepository;
import swe4.util.DateUtil;
import java.io.IOException;
import java.util.Date;
import static swe4.model.data.DishRepository.getDishes;
import static swe4.model.data.OrderRepository.getOrders;
import static swe4.model.data.Repository.saveData;
import static swe4.model.data.TimeSlotRepository.getTimeSlots;

public class AdminView {

  public static Scene create(Stage stage) {
    BorderPane mainLayout = new BorderPane();
    mainLayout.setTop(mainMenuButtons(stage));
    mainLayout.setCenter(mainMenuTabs());
    Scene adminScene = new Scene(mainLayout);
    adminScene.getStylesheets().add(LoginView.class.getResource("./styles.css").toExternalForm());
    return adminScene;
  }

  private static HBox mainMenuButtons(Stage stage) {
    HBox mainMenuButtons = new HBox();
    Label lastSavedLabel = new Label("Last saved at " + DateUtil.formatTime(new Date()));
    Button saveButton = new Button("Save");
    saveButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        try {
          saveData();
        } catch (IOException e) {
          e.printStackTrace();
        }
        lastSavedLabel.setText("Last saved at " + DateUtil.formatTime(new Date()));
      }
    });
    Button updateButton = new Button("Update");
    Button logoutButton = new Button("Logout");
    logoutButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
          LoginView.create(stage);
        }
      }
    );
    mainMenuButtons.getChildren().addAll(lastSavedLabel, saveButton, updateButton, logoutButton);
    stage.setMaximized(true);
    return mainMenuButtons;
  }

  private static TabPane mainMenuTabs() {

    TabPane mainMenuTabs = new TabPane();

    Tab tabOrders = new Tab("Orders");
    tabOrders.setContent(OrderTab.create(getOrders()));

    Tab tabMenu = new Tab("Menu");
    tabMenu.setContent(MenuTab.create(getDishes()));

    Tab tabTimeSlots = new Tab("Time Slots");
    tabTimeSlots.setContent(TimeSlotsTab.construct(getTimeSlots()));

    Tab tabUsers = new Tab("User Management");
    tabUsers.setContent(UsersTab.create(UserRepository.getUsers()));

    mainMenuTabs.getTabs().addAll(tabOrders, tabMenu, tabTimeSlots, tabUsers);
    mainMenuTabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
    return mainMenuTabs;
  }
}