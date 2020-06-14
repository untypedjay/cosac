package swe4.model.entities;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import swe4.model.data.users.UserRepo;
import java.io.*;

import static swe4.model.entities.User.Role.ADMIN;

public class User implements Serializable {
  public enum Role {
    CUSTOMER,
    ADMIN
  }

  private String firstName;
  private String lastName;
  private String username;
  private String passwordHash;
  private boolean locked;
  private Role role;
  private transient ToggleButton lockButton;
  private transient ToggleButton roleButton;
  private transient Button deleteButton;

  public User(String fn, String ln, String un, String pwd, boolean locked, Role role, UserRepo repo) {
    this.firstName = fn;
    this.lastName = ln;
    this.username = un;
    this.passwordHash = pwd;
    this.locked = locked;
    this.role = role;
    initLockButton();
    initDeleteButton();

    this.deleteButton = new Button("Delete");
    this.deleteButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        repo.deleteUser(getUsername());
      }
    });
  }

  private void initLockButton() {
    this.lockButton = new ToggleButton();
    this.lockButton.setSelected(locked);
    if (locked) {
      lockButton.setText("Locked");
    } else {
      lockButton.setText("Lock");
    }
    this.lockButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        if (lockButton.isSelected()) {
          setLocked(true);
          lockButton.setText("Locked");
        } else {
          setLocked(false);
          lockButton.setText("Lock");
        }
      }
    });
  }

  private void initDeleteButton() {
    this.roleButton = new ToggleButton("Customer");
    if (this.role == ADMIN) {
      this.roleButton.setSelected(true);
      this.roleButton.setText("Admin");
    } else {
      this.roleButton.setSelected(false);
      this.roleButton.setText("Customer");
    }
    this.roleButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        if (roleButton.isSelected()) {
          setRole(ADMIN);
          roleButton.setText("Admin");
        } else {
          setRole(Role.CUSTOMER);
          roleButton.setText("Customer");
        }
      }
    });
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getUsername() {
    return username;
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
    sb.append(username);
    sb.append(", ");
    sb.append(passwordHash);
    sb.append(" (");
    sb.append(locked);
    sb.append(" ,");
    sb.append(role);
    sb.append(")");
    return sb.toString();
  }
}
