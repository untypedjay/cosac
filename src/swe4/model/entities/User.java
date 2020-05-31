package swe4.model.entities;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

public class User {
  private String firstName = "";
  private String lastName = "";
  private String userName = "";
  private String password = "";
  private boolean locked = false;
  private ToggleButton lockButton = null;
  private Button deleteButton = null;

  public User(String fn, String ln, String un, String pwd) {
    this.firstName = fn;
    this.lastName = ln;
    this.userName = un;
    this.password = pwd;

    this.lockButton = new ToggleButton("Sperren");
    this.lockButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        if (lockButton.isSelected()) {
          locked = true;
          lockButton.setText("Gesperrt");
        } else {
          locked = false;
          lockButton.setText("Sperren");
        }
      }
    });

    this.deleteButton = new Button("Löschen");
    this.deleteButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        System.out.println(getFirstName());
      }
    });
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }

  public ToggleButton getLockButton() {
    return lockButton;
  }

  public Button getDeleteButton() {
    return deleteButton;
  }
}
