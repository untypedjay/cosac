package swe4;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import swe4.entities.Meal;
import swe4.entities.Order;
import swe4.entities.TimeSlot;
import swe4.entities.User;

import java.time.LocalTime;

public class Main extends Application {
  TimeSlot ts1 = new TimeSlot(LocalTime.of(11, 00), LocalTime.of(11, 30), 10);
  TimeSlot ts2 = new TimeSlot(LocalTime.of(11, 30), LocalTime.of(12, 00), 10);
  TimeSlot ts3 = new TimeSlot(LocalTime.of(12, 00), LocalTime.of(12, 30), 10);
  TimeSlot ts4 = new TimeSlot(LocalTime.of(12, 30), LocalTime.of(13, 00), 10);
  ObservableList<TimeSlot> timeSlots = FXCollections.observableArrayList(ts1, ts2, ts3, ts4);

  User bill = new User("Bill", "Yard", "yard", "yard123");
  User claire = new User("Claire", "Waßer", "wasser", "wasser123");
  User rainer = new User("Rainer", "Zufall", "zufall", "zufall123");
  User martha = new User("Martha", "Pfahl", "pfahl", "pfahl123");
  User marie = new User("Marie", "Huana", "huana", "huana123");
  ObservableList<User> users = FXCollections.observableArrayList(bill, claire, rainer, martha, marie);

  Meal m1 = new Meal("Italienische Köstlichkeiten", "Spaghetti Bolognese", 640);
  Meal m2 = new Meal("Heftig Deftig", "Cordon Bleu vom Schwein mit Kartoffel und Reis", 750);
  Meal m3 = new Meal("Vegetarische Gerichte", "Gebackene Spinatpalatschinke mit Kartoffeln ", 640);
  Meal m4 = new Meal("Vegetarische Gerichte", "Faschierte Laibchen mit Kartoffelpüree und Gemüse ", 750);
  Meal m5 = new Meal("Vegetarische Gerichte", "Spaghetti mit Tomatensauce ", 640);
  ObservableList<Meal> meals = FXCollections.observableArrayList(m1, m2, m3, m4, m5);

  Order o1 = new Order(claire, m5, ts3);
  Order o2 = new Order(bill, m3, ts1);
  Order o3 = new Order(rainer, m5, ts3);
  Order o4 = new Order(claire, m5, ts4);
  Order o5 = new Order(marie, m4, ts4);
  Order o6 = new Order(bill, m2, ts2);
  Order o7 = new Order(bill, m1, ts1);
  Order o8 = new Order(martha, m2, ts3);
  ObservableList<Order> orders = FXCollections.observableArrayList(o1, o2, o3, o4, o5, o6, o7, o8);

  @Override
  public void start(Stage primaryStage) throws Exception {
    BorderPane loginContainer = new BorderPane();
    GridPane loginForm = new GridPane();
    ColumnConstraints col0 = new ColumnConstraints();
    col0.setHgrow(Priority.NEVER);
    ColumnConstraints col1 = new ColumnConstraints();
    col1.setHgrow(Priority.ALWAYS);
    loginForm.getColumnConstraints().addAll(col0, col1);
    loginForm.setHgap(4);
    loginForm.setVgap(4);
    TextField inputUsername = new TextField();
    PasswordField inputPassword = new PasswordField();
    loginForm.add(new Label("Bei CosaC anmelden"), 0, 0, 2, 1);
    loginForm.add(new Label("Benutzername:"), 0, 1);
    loginForm.add(inputUsername, 1, 1);
    loginForm.add(new Label("Passwort:"), 0, 2);
    loginForm.add(inputPassword, 1, 2);
    loginContainer.setCenter(loginForm);
    FlowPane loginButtonContainer = new FlowPane(4, 4);
    loginButtonContainer.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
    Button loginButton = new Button("Einloggen");
    loginButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        String username = inputUsername.getText();
        String password = inputPassword.getText();
        if (username.equals("admin")) {
          primaryStage.setScene(mainScene());
        } else {
          alert();
        }
      }
    });
    loginButtonContainer.getChildren().add(loginButton);
    loginContainer.setBottom(loginButtonContainer);
    primaryStage.setTitle("CosaC");
    primaryStage.setScene(new Scene(loginContainer, 400, 200));
    primaryStage.show();
  }

  public void alert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Einloggen fehlgeschlagen!");
    alert.setHeaderText("Benutzername oder Passwort inkorrekt!");
    alert.setContentText("Bitte versuchen Sie es noch einmal.");
    alert.showAndWait();
  }

  public Scene mainScene() {
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

  public BorderPane orderTab() {
    BorderPane orderPane = new BorderPane();
    TableView<Order> orderTable = new TableView<>();

    orderTable.setItems(orders);
    TableColumn<Order, String> customerCol = new TableColumn<>("Kunde");
    customerCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
    orderTable.getColumns().add(customerCol);

    TableColumn<Order, String> orderCol = new TableColumn<>("Bestellung");
    orderCol.setCellValueFactory(new PropertyValueFactory<>("mealName"));
    orderTable.getColumns().add(orderCol);

    TableColumn<Order, String> timeFrameCol = new TableColumn<>("Zeitfenster");
    timeFrameCol.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
    orderTable.getColumns().add(timeFrameCol);

    TableColumn<Order, String> orderTimeCol = new TableColumn<>("Bestellzeit");
    orderTimeCol.setCellValueFactory(new PropertyValueFactory<>("orderTime"));
    orderTable.getColumns().add(orderTimeCol);

    orderPane.setCenter(orderTable);

    return orderPane;
  }

  public BorderPane menuTab() {
    BorderPane menuPane = new BorderPane();
    TableView<Meal> menuTable = new TableView<Meal>();

    menuTable.setItems(meals);
    TableColumn<Meal, String> mealTypeCol = new TableColumn<>("Bereich");
    mealTypeCol.setCellValueFactory(new PropertyValueFactory<>("mealType"));
    menuTable.getColumns().add(mealTypeCol);

    TableColumn<Meal, String> descriptionCol = new TableColumn<>("Bezeichnung");
    descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
    menuTable.getColumns().add(descriptionCol);

    TableColumn<Meal, Long> priceCol = new TableColumn<>("Preis");
    priceCol.setCellValueFactory(new PropertyValueFactory<>("priceInCents"));
    menuTable.getColumns().add(priceCol);

    TableColumn<Meal, String> buttonCol = new TableColumn<>("");
    buttonCol.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
    menuTable.getColumns().add(buttonCol);

    FlowPane addMealContainer = new FlowPane(4, 4);

    TextField mealType = new TextField();
    mealType.setPromptText("Bereich");
    addMealContainer.getChildren().add(mealType);

    TextField description = new TextField();
    description.setPromptText("Bezeichnung");
    addMealContainer.getChildren().add(description);

    TextField price = new TextField();
    price.setPromptText("Preis");
    addMealContainer.getChildren().add(price);

    Button addButton = new Button("Hinzufügen");
    addButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        meals.add(new Meal(mealType.getText(), description.getText(), price.getText()));
      }
    });
    addMealContainer.getChildren().add(addButton);

    menuPane.setCenter(menuTable);
    menuPane.setBottom(addMealContainer);

    return menuPane;
  }

  public BorderPane timeSlotsTab() {
    BorderPane timeSlotPane = new BorderPane();
    TableView<TimeSlot> timeSlotsTable = new TableView<>();

    timeSlotsTable.setItems(timeSlots);
    TableColumn<TimeSlot, String> startTimeCol = new TableColumn<>("Beginn");
    startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
    timeSlotsTable.getColumns().add(startTimeCol);

    TableColumn<TimeSlot, String> endTimeCol = new TableColumn<>("Ende");
    endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
    timeSlotsTable.getColumns().add(endTimeCol);

    TableColumn<TimeSlot, Integer> maxCustomersCol = new TableColumn<>("Maximale Kundenanzahl");
    maxCustomersCol.setCellValueFactory(new PropertyValueFactory<>("maximumCustomers"));
    timeSlotsTable.getColumns().add(maxCustomersCol);

    TableColumn<TimeSlot, String> buttonCol = new TableColumn<>("");
    buttonCol.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
    timeSlotsTable.getColumns().add(buttonCol);

    FlowPane addTimeSlotContainer = new FlowPane(4, 4);

    TextField inputStartTime = new TextField();
    inputStartTime.setPromptText("Start");
    addTimeSlotContainer.getChildren().add(inputStartTime);

    TextField inputEndTime = new TextField();
    inputEndTime.setPromptText("Ende");
    addTimeSlotContainer.getChildren().add(inputEndTime);

    TextField inputMaxCustomers = new TextField();
    inputMaxCustomers.setPromptText("Maximale Kundenanzahl");
    addTimeSlotContainer.getChildren().add(inputMaxCustomers);

    Button addButton = new Button("Hinzufügen");
    addButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        timeSlots.add(new TimeSlot(inputStartTime.getText(), inputEndTime.getText(), inputMaxCustomers.getText()));
      }
    });
    addTimeSlotContainer.getChildren().add(addButton);

    timeSlotPane.setCenter(timeSlotsTable);
    timeSlotPane.setBottom(addTimeSlotContainer);

    return timeSlotPane;
  }

  public BorderPane usersTab() {
    BorderPane userPane = new BorderPane();
    TableView<User> userTable = new TableView<User>();

    userTable.setItems(users);
    TableColumn<User, String> firstNameCol = new TableColumn<User, String>("Vorname");
    firstNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
    userTable.getColumns().add(firstNameCol);

    TableColumn<User, String> lastNameCol = new TableColumn<User, String>("Nachname");
    lastNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
    userTable.getColumns().add(lastNameCol);

    TableColumn<User, String> userNameCol = new TableColumn<User, String>("Benutzername");
    userNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
    userTable.getColumns().add(userNameCol);

    TableColumn<User, String> passwordCol = new TableColumn<User, String>("Passwort");
    passwordCol.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
    userTable.getColumns().add(passwordCol);

    TableColumn<User, String> buttonCol = new TableColumn<User, String>("");
    buttonCol.setCellValueFactory(new PropertyValueFactory<User, String>("deleteButton"));
    userTable.getColumns().add(buttonCol);

    FlowPane addUserContainer = new FlowPane(4, 4);

    TextField inputFirstName = new TextField();
    inputFirstName.setPromptText("Vorname");
    addUserContainer.getChildren().add(inputFirstName);

    TextField inputLastName = new TextField();
    inputLastName.setPromptText("Nachname");
    addUserContainer.getChildren().add(inputLastName);

    TextField inputUserName = new TextField();
    inputUserName.setPromptText("Benutzername");
    addUserContainer.getChildren().add(inputUserName);

    TextField inputPassword = new TextField();
    inputPassword.setPromptText("Passwort");
    addUserContainer.getChildren().add(inputPassword);

    Button addButton = new Button("Hinzufügen");
    addButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        users.add(new User(inputFirstName.getText(), inputLastName.getText(), inputUserName.getText(), inputPassword.getText()));
      }
    });
    addUserContainer.getChildren().add(addButton);

    userPane.setCenter(userTable);
    userPane.setBottom(addUserContainer);

    return userPane;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
