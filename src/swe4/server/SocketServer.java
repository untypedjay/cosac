package swe4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
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
        if (data.equals("data")) {
          System.out.println("server, received request for data");
          try (Socket sender = new Socket("localhost", 5003);
               ObjectOutput out = new ObjectOutputStream(sender.getOutputStream())) {
            out.writeObject(retrieveData(DataType.USER));
            out.writeObject(retrieveData(DataType.DISH));
            out.writeObject(retrieveData(DataType.ORDER));
            out.writeObject(retrieveData(DataType.TIMESLOT));
          }
          System.out.println("server, sent data");
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

  private static Object retrieveData(DataType dataType) {
    Object data = null;
    String fileName = determineFileName(dataType);

    try (ObjectInput in = new ObjectInputStream(new FileInputStream(fileName))) {
      data = in.readObject();
    } catch (ClassNotFoundException | IOException x) {
      x.printStackTrace();
    }
    if (data == null) {
      data = "";
    }
    return data;
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

