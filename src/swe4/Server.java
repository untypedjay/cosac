package swe4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  private enum DataType {
    DISH, ORDER, TIMESLOT, USER
  }

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
        if (data.equals("users")) {
          System.out.println("server, received request for user data");
          sendData(DataType.USER);
        } else if (data.equals("dishes")) {
          System.out.println("server, received request for dish data");
          sendData(DataType.DISH);
        } else if (data.equals("orders")) {
          System.out.println("server, received request for order data");
          sendData(DataType.ORDER);
        } else if (data.equals("timeSlots")) {
          System.out.println("server, received request for timeSlot data");
          sendData(DataType.TIMESLOT);
        } else if (((Object[]) data)[0].toString().startsWith("user")) {
          System.out.println("server, received data");
          storeDataInFile((Object[]) data, DataType.USER);
          storeDataInFile((Object[]) in.readObject(), DataType.DISH);
          storeDataInFile((Object[]) in.readObject(), DataType.ORDER);
          storeDataInFile((Object[]) in.readObject(), DataType.TIMESLOT);
        } else {
          System.out.println("SERVER ERROR: unknown request");
        }
      }
    }
  }

  private static void storeDataInFile(Object[] data, DataType dataType) {
    String fileName = determineFileName(dataType);
    try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream(fileName))) {
      out.writeObject(data);
    } catch (IOException x) {
      x.printStackTrace();
    }
    System.out.println("server, written data to file");
  }

  private static void sendData(DataType dataType) throws IOException {
    Object[] data = null;
    String fileName = determineFileName(dataType);

    try (ObjectInput in = new ObjectInputStream(new FileInputStream(fileName))) {
      data = (Object[]) in.readObject();
    } catch (ClassNotFoundException | IOException x) {
      x.printStackTrace();
    }
    try (Socket socket = new Socket("localhost", 5003);
         ObjectOutput out = new ObjectOutputStream(socket.getOutputStream())) {
      out.writeObject(data);
      System.out.println("server, sent data to client: " + data);
    }
  }

  private static String determineFileName(DataType dataType) {
    String fileName = null;
    switch (dataType) {
      case DISH: fileName = "dishes.ser"; break;
      case ORDER: fileName = "orders.ser"; break;
      case TIMESLOT: fileName = "timeSlots.ser"; break;
      case USER: fileName = "users.ser"; break;
    }
    return fileName;
  }
}

