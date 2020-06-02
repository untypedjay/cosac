package swe4.model.entities;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import swe4.model.DataModel;
import swe4.model.UserRepository;

import java.io.*;

public class User implements Serializable {
  private String firstName = "";
  private String lastName = "";
  private String userName = "";
  private String passwordHash = "";
  private boolean locked = false;
  private boolean admin = false;
  private transient ToggleButton lockButton = null;
  private transient ToggleButton roleButton = null;
  private transient Button deleteButton = null;

  public User(String fn, String ln, String un, String pwd) {
    this.firstName = fn;
    this.lastName = ln;
    this.userName = un;
    this.passwordHash = pwd;

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
        UserRepository.deleteUser(getUserName());
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

  public String getPasswordHash() {
    return passwordHash;
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

//  @Override
//  public void validateObject() throws InvalidObjectException {
//    if (address == null) {
//      address = new Address(1234, "<unknown>", "<unknown>");
//    }
//  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(firstName);
    sb.append(" ");
    sb.append(lastName);
    sb.append(", ");
    sb.append(userName);
    sb.append(", ");
    sb.append(passwordHash);
    return sb.toString();
  }

  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
//    in.registerValidation((ObjectInputValidation) this, 0);
    in.defaultReadObject();
  }
}
