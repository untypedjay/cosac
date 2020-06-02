package swe4.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.User;
import swe4.util.PasswordUtil;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UserRepository {
  private static final ObservableList<User> users = FXCollections.observableArrayList();

  public static void loadMockUsers() {
    users.setAll(
      new User("Bill", "Yard", "yard", PasswordUtil.generateHash("yard123")),
      new User("admin", "admin", "admin", PasswordUtil.generateHash("admin")),
      new User("Claire", "Waßer", "wasser", PasswordUtil.generateHash("wasser123")),
      new User("Rainer", "Zufall", "zufall", PasswordUtil.generateHash("zufall123")),
      new User("Martha", "Pfahl", "pfahl", PasswordUtil.generateHash("pfahl123")),
      new User("Marie", "Huana", "huana", PasswordUtil.generateHash("huana123"))
    );
  }

  public static ObservableList<User> getUsers() {
    return users;
  }

  public static void deleteUser(String userName) {
    for (User user : users) {
      if (user.getUserName().equals(userName)) {
        users.remove(user);
      }
    }
  }

  public static void addUser(String firstName, String lastName, String userName, String password) {
    users.add(new User(firstName, lastName, userName, PasswordUtil.generateHash(password)));
  }

  public static boolean isValidUser(String userName, String password) {
    for (User user : users) {
      if (user.getUserName().equals(userName) && PasswordUtil.isValid(password, user.getPasswordHash())) {
        return true;
      }
    }
    return false;
  }

  public static void saveUsers(int port, String host) throws IOException {
    Object[] userData = new Object[users.size()];
    for (int i = 0; i < userData.length; ++i) {
      userData[i] = users.get(i);
    }
    try (Socket socket = new Socket(host, port);
         ObjectOutput out = new ObjectOutputStream(socket.getOutputStream())) {
      out.writeObject(userData);
      System.out.println("client, sent: " + userData);
    }
  }
}