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

  private static void storeUsers(Object[] users) {
    try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("users.ser"))) { // try-with-resources
      out.writeObject(users);
    } catch (IOException x) {
      x.printStackTrace();
    }
    System.out.println("server, written user data to users.ser");
  }

  private static void sendUsers() throws IOException {
    Object[] users = null;

    try (ObjectInput in = new ObjectInputStream(new FileInputStream("users.ser"))) {
      users = (Object[]) in.readObject();
    } catch (ClassNotFoundException | IOException x) {
      x.printStackTrace();
    }
    try (Socket socket = new Socket("localhost", 5003);
         ObjectOutput out = new ObjectOutputStream(socket.getOutputStream())) {
      out.writeObject(users);
      System.out.println("server, sent users to client: " + users);
    }
  }

  private static void processRequest() throws IOException, ClassNotFoundException {
    System.out.println("server, waiting for requests...");
    while (true) {
      try (ServerSocket server = new ServerSocket(5004);
           Socket socket = server.accept();
           ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
           Object data = in.readObject();
        if (data.equals("users")) {
          System.out.println("server, received request for user data");
          sendUsers();
        } else {
          System.out.println("server, received user data: " + data);
          storeUsers((Object[]) data);
        }
      }
    }
  }

  @Override
  public void start(Stage stage) throws Exception {
    processRequest();
  }
}

