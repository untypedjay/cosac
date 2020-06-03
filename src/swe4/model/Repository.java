package swe4.model;

import javax.imageio.IIOException;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static swe4.model.UserRepository.receiveUsers;

public class Repository {
  public static void loadData() throws IIOException, ClassNotFoundException {
    try {
      try (Socket socket = new Socket("localhost", 5004);
           ObjectOutput out = new ObjectOutputStream(socket.getOutputStream())) {
        out.writeObject("users");
        System.out.println("client, requested users from server");
        receiveUsers();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void saveData() {

  }
}
