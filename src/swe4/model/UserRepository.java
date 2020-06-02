package swe4.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.User;
import swe4.util.PasswordUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
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

  public static void saveUsers() throws IOException {
    Object[] userData = new Object[users.size()];
    for (int i = 0; i < userData.length; ++i) {
      userData[i] = users.get(i);
    }
    try (Socket socket = new Socket("localhost", 5004);
         ObjectOutput out = new ObjectOutputStream(socket.getOutputStream())) {
      out.writeObject(userData);
      System.out.println("client, sent users to server: " + userData);
    }
  }

  private static void receiveUsers() throws IOException, ClassNotFoundException {
    System.out.println("client, waiting for user data from server...");
    try (ServerSocket server = new ServerSocket(5003);
         Socket socket = server.accept();
         ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      Object[] userObjectArray = (Object[]) in.readObject();
      users.clear();
      for (int i = 0; i < userObjectArray.length; ++i) {
        users.add((User) userObjectArray[i]);
        System.out.println(userObjectArray[i]);
      }
    }
    System.out.println("client, received users: " + users);
  }

  public static void loadUsers() throws IOException, ClassNotFoundException {
    try (Socket socket = new Socket("localhost", 5004);
         ObjectOutput out = new ObjectOutputStream(socket.getOutputStream())) {
      out.writeObject("users");
      System.out.println("client, requested users from server");
      receiveUsers();
    }
  }
}