package swe4.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import swe4.model.data.UserRepository;

public class LoginView {

  public static void create(Stage stage) {
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
    inputUsername.getStyleClass().add("input");
    PasswordField inputPassword = new PasswordField();
    inputPassword.getStyleClass().add("input");
    Label loginHeader = new Label("Bei CosaC anmelden");
    loginHeader.getStyleClass().add("login-header");
    loginForm.add(loginHeader, 0, 0, 2, 1);
    loginForm.add(new Label("Benutzername:"), 0, 1);
    loginForm.add(inputUsername, 1, 1);
    loginForm.add(new Label("Passwort:"), 0, 2);
    loginForm.add(inputPassword, 1, 2);
    loginContainer.setCenter(loginForm);
    FlowPane loginButtonContainer = new FlowPane(4, 4);
    loginButtonContainer.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
    Button loginButton = new Button("Einloggen");
    loginButton.getStyleClass().add("button");
    loginButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        String username = inputUsername.getText();
        String password = inputPassword.getText();
        if (UserRepository.isValidUser(username, password)) {
          stage.setScene(AdminView.create(stage));
        } else {
          inputUsername.setText("");
          inputPassword.setText("");
          alert();
        }
      }
    });
    loginButtonContainer.getChildren().add(loginButton);
    loginContainer.setBottom(loginButtonContainer);
    loginContainer.getStyleClass().add("login");
    Scene loginScene = new Scene(loginContainer, 780, 450);

    loginScene.getStylesheets().add(LoginView.class.getResource("./styles.css").toExternalForm());
    stage.setScene(loginScene);
  }

  private static void alert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Einloggen fehlgeschlagen!");
    alert.setHeaderText("Benutzername oder Passwort inkorrekt!");
    alert.setContentText("Bitte versuchen Sie es noch einmal.");
    alert.showAndWait();
  }
}
