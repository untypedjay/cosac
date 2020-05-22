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

public class Main extends Application {

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
    alert.setContentText("Bitte probieren Sie es noch einmal.");
    alert.showAndWait();
  }

  public Scene mainScene() {
    TabPane mainMenu = new TabPane();
    Tab tabOrders = new Tab("Bestellungen");
    Tab tabMenu = new Tab("Speisekarte");
    Tab tabTimeSlots = new Tab("Zeitbereiche");
    Tab tabUsers = new Tab("Benutzerverwaltung");
    tabUsers.setContent(usersTab());
    mainMenu.getTabs().addAll(tabOrders, tabMenu, tabTimeSlots, tabUsers);
    mainMenu.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
    return new Scene(mainMenu, 1000, 800);
  }

  public BorderPane usersTab() {
    BorderPane userPane = new BorderPane();
    TableView<User> userTable = new TableView<User>();
    ObservableList<User> users = FXCollections.observableArrayList(
      new User("Bill", "Yard", "yard", "yard123"),
      new User("Clair", "Waßer", "wasser", "wasser123"),
      new User("Rainer", "Zufall", "zufall", "zufall123"),
      new User("Martha", "Pfahl", "pfahl", "pfahl123"),
      new User("Marie", "Huana", "huana", "huana123")
    );
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
    addUserContainer.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
    Button loginButton = new Button("Hinzufügen");
    addUserContainer.getChildren().add(loginButton);

    TextField inputPassword = new TextField();
    inputPassword.setPromptText("Passwort");
    addUserContainer.getChildren().add(inputPassword);

    TextField inputUserName = new TextField();
    inputUserName.setPromptText("Benutzername");
    addUserContainer.getChildren().add(inputUserName);

    TextField inputLastName = new TextField();
    inputLastName.setPromptText("Nachname");
    addUserContainer.getChildren().add(inputLastName);

    TextField inputFirstName = new TextField();
    inputFirstName.setPromptText("Vorname");
    addUserContainer.getChildren().add(inputFirstName);

    userPane.setCenter(userTable);
    userPane.setBottom(addUserContainer);

    return userPane;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
