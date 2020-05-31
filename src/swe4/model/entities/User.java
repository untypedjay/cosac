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
  private boolean admin = false;
  private ToggleButton lockButton = null;
  private ToggleButton roleButton = null;
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
          setLocked(true);
          lockButton.setText("Gesperrt");
        } else {
          setLocked(false);
          lockButton.setText("Sperren");
        }
      }
    });

    this.roleButton = new ToggleButton("Kunde");
    this.roleButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        if (roleButton.isSelected()) {
          setAdmin(true);
          roleButton.setText("Administrator");
        } else {
          setAdmin(false);
          roleButton.setText("Kunde");
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

  public ToggleButton getRoleButton() {
    return roleButton;
  }

  public Button getDeleteButton() {
    return deleteButton;
  }

  public boolean isLocked() {
    return locked;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }
}
