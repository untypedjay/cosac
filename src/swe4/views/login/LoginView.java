package swe4.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import swe4.model.data.Repository;

public class LoginView extends BorderPane {

  public LoginView(ViewController controller, Repository repo) {
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
    Label loginHeader = new Label("Welcome to CosaC");
    loginHeader.getStyleClass().add("login-header");
    loginForm.add(loginHeader, 0, 0, 2, 1);
    loginForm.add(new Label("Username:"), 0, 1);
    loginForm.add(inputUsername, 1, 1);
    loginForm.add(new Label("Password:"), 0, 2);
    loginForm.add(inputPassword, 1, 2);
    this.setCenter(loginForm);
    FlowPane loginButtonContainer = new FlowPane(4, 4);
    loginButtonContainer.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
    Button loginButton = new Button("Login");
    loginButton.getStyleClass().add("button");
    loginButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        String username = inputUsername.getText();
        String password = inputPassword.getText();
        if (repo.isValidUser(username, password)) {
          controller.render(ViewType.ADMIN_VIEW, 780, 450);
        } else {
          inputUsername.setText("");
          inputPassword.setText("");
          alert();
        }
      }
    });
    loginButtonContainer.getChildren().add(loginButton);
    this.setBottom(loginButtonContainer);
    this.getStyleClass().add("login");
  }

  private void alert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Login failed!");
    alert.setHeaderText("Incorrect username or password!");
    alert.setContentText("Please try again.");
    alert.showAndWait();
  }
}
