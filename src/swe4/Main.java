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
import javafx.scene.layout.*;
import javafx.stage.Stage;
import swe4.model.entities.Meal;
import swe4.model.entities.Order;
import swe4.model.entities.TimeSlot;
import swe4.model.entities.User;

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

  public static void main(String[] args) {
    launch(args);
  }
}
