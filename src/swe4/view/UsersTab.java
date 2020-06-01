package swe4.view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import swe4.model.DataModel;
import swe4.model.entities.User;

public class UsersTab {
  public static BorderPane create(ObservableList<User> users) {
    BorderPane userPane = new BorderPane();
    TableView<User> userTable = new TableView<>();

    userTable.setItems(users);
    TableColumn<User, String> firstNameCol = new TableColumn<User, String>("Vorname");
    firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    userTable.getColumns().add(firstNameCol);

    TableColumn<User, String> lastNameCol = new TableColumn<User, String>("Nachname");
    lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    userTable.getColumns().add(lastNameCol);

    TableColumn<User, String> userNameCol = new TableColumn<User, String>("Benutzername");
    userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
    userTable.getColumns().add(userNameCol);

    TableColumn<User, String> passwordCol = new TableColumn<User, String>("Passwort");
    passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
    userTable.getColumns().add(passwordCol);

    TableColumn<User, String> lockButtonCol = new TableColumn<>("");
    lockButtonCol.setCellValueFactory(new PropertyValueFactory<>("lockButton"));
    userTable.getColumns().add(lockButtonCol);

    TableColumn<User, String> roleButtonCol = new TableColumn<>("");
    roleButtonCol.setCellValueFactory(new PropertyValueFactory<>("roleButton"));
    userTable.getColumns().add(roleButtonCol);

    TableColumn<User, String> deleteButtonCol = new TableColumn<User, String>("");
    deleteButtonCol.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
    userTable.getColumns().add(deleteButtonCol);

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
      public void handle(ActionEvent event) {
        if (inputFirstName.getText().isEmpty() || inputLastName.getText().isEmpty()
          || inputUserName.getText().isEmpty() || inputPassword.getText().isEmpty()) {
          emptyAlert();
        } else {
          DataModel.addUser(inputFirstName.getText(), inputLastName.getText(), inputUserName.getText(), inputPassword.getText());
          inputFirstName.setText("");
          inputLastName.setText("");
          inputUserName.setText("");
          inputPassword.setText("");
        }
      }
    });
    addUserContainer.getChildren().add(addButton);

    userPane.setCenter(userTable);
    userPane.setBottom(addUserContainer);

    return userPane;
  }

  private static void emptyAlert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Hinzufügen fehlgeschlagen!");
    alert.setHeaderText("Unvollständige Eingabe!");
    alert.setContentText("Bitte füllen Sie alle Felder aus.");
    alert.showAndWait();
  }
}
