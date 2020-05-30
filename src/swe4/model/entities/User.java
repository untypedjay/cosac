package swe4.model.entities;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class User {
  private String firstName = "";
  private String lastName = "";
  private String userName = "";
  private String password = "";
  private Button deleteButton = null;
  private boolean locked = false;

  public User(String fn, String ln, String un, String pwd) {
    this.firstName = fn;
    this.lastName = ln;
    this.userName = un;
    this.password = pwd;
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

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Button getDeleteButton() {
    return deleteButton;
  }

  public void setDeleteButton(Button deleteButton) {
    this.deleteButton = deleteButton;
  }

  public boolean isLocked() {
    return locked;
  }

  public void lock() {
    this.locked = true;
  }

  public void unlock() {
    this.locked = false;
  }
}
