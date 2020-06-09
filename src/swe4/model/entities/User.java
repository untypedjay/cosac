package swe4.model.entities;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import swe4.model.data.UserRepository;
import java.io.*;

import static swe4.model.entities.User.Role.ADMIN;

public class User implements Serializable {
  private static final long serialVersionUID = -8035508999217674220L;

  public enum Role {
    CUSTOMER,
    ADMIN
  }

  private String firstName = "";
  private String lastName = "";
  private String userName = "";
  private String passwordHash = "";
  private boolean locked = false;
  private Role role = null;
  private transient ToggleButton lockButton = null;
  private transient ToggleButton roleButton = null;
  private transient Button deleteButton = null;

  public User(String fn, String ln, String un, String pwd, boolean locked, Role role) {
    this.firstName = fn;
    this.lastName = ln;
    this.userName = un;
    this.passwordHash = pwd;
    this.locked = locked;
    this.role = role;

    this.lockButton = new ToggleButton();
    this.lockButton.setSelected(locked);
    if (locked) {
      lockButton.setText("Gesperrt");
    } else {
      lockButton.setText("Sperren");
    }
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
    if (this.role == ADMIN) {
      this.roleButton.setSelected(true);
      this.roleButton.setText("Admin");
    } else {
      this.roleButton.setSelected(false);
      this.roleButton.setText("Kunde");
    }
    this.roleButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        if (roleButton.isSelected()) {
          setRole(ADMIN);
          roleButton.setText("Admin");
        } else {
          setRole(Role.CUSTOMER);
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

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("user: ");
    sb.append(firstName);
    sb.append(" ");
    sb.append(lastName);
    sb.append(", ");
    sb.append(userName);
    sb.append(", ");
    sb.append(passwordHash);
    sb.append(" (");
    sb.append(locked);
    sb.append(" ,");
    sb.append(role);
    sb.append(")");
    return sb.toString();
  }

  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    in.defaultReadObject();
  }
}
