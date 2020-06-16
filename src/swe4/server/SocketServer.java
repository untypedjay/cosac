package swe4.server;

import swe4.util.ServerUtil;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import static swe4.util.ServerUtil.*;

public class SocketServer {
  public static void main(String[] args) {
    try {
      processRequest();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static void processRequest() throws IOException, ClassNotFoundException {
    System.out.println("server, waiting for requests...");
    while (true) {
      try (ServerSocket server = new ServerSocket(5004);
           Socket socket = server.accept();
           ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
        Object data = in.readObject();
        if (data.equals("data")) {
          System.out.println("server, received request for data");
          try (Socket sender = new Socket("localhost", 5003);
               ObjectOutput out = new ObjectOutputStream(sender.getOutputStream())) {
            out.writeObject(retrieveDataFromFile(ServerUtil.DataType.USER));
            out.writeObject(retrieveDataFromFile(ServerUtil.DataType.DISH));
            out.writeObject(retrieveDataFromFile(ServerUtil.DataType.ORDER));
            out.writeObject(retrieveDataFromFile(ServerUtil.DataType.TIMESLOT));
          }
          System.out.println("server, sent data");
        } else if (((Object[]) data)[0].toString().startsWith("user")) {
          System.out.println("server, received data");
          storeDataInFile((Object[]) data, ServerUtil.DataType.USER);
          storeDataInFile((Object[]) in.readObject(), ServerUtil.DataType.DISH);
          storeDataInFile((Object[]) in.readObject(), ServerUtil.DataType.ORDER);
          storeDataInFile((Object[]) in.readObject(), ServerUtil.DataType.TIMESLOT);
        } else {
          System.out.println("SERVER ERROR: unknown request");
        }
      }
    }
  }
}

