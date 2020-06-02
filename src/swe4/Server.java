package swe4;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import swe4.model.entities.User;
import swe4.util.PasswordUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Application {
  public static void main(String[] args) {
    Application.launch();
  }

  // receiving data from client and store it in an object; called by the server and never stops
  private static void storeUsers(int port, String fileName) throws IOException, ClassNotFoundException {
    System.out.println("server, waiting for client ...");

    while (true) {
      Object[] users = null;
      try (ServerSocket server = new ServerSocket(port);
           Socket socket = server.accept();
           ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
        users = (Object[]) in.readObject();
        System.out.println("server, received: " + users); // users are now in servers RAM
      }
      try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream(fileName))) { // try-with-resources
        out.writeObject(users);
      } catch (IOException x) {
        x.printStackTrace();
      }
    }
  }

  private static ObservableList<User> activateUsers(String fileName) {
    ObservableList<User> users = null;

    try (ObjectInput in = new ObjectInputStream(new FileInputStream(fileName))) {
      users = (ObservableList<User>) in.readObject();
    } catch (ClassNotFoundException | IOException x) {
      x.printStackTrace();
    }
    return users;
  }

  @Override
  public void start(Stage stage) throws Exception {
    storeUsers(5002, "users.ser");
  }
}

