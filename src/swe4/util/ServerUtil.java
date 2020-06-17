package swe4.util;

import java.io.*;

public class ServerUtil {
  public enum DataType {
    DISH, ORDER, TIMESLOT, USER
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

  public static Object[] retrieveDataFromFile(DataType dataType) {
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
    return (Object[]) data;
  }

  public static void storeDataInFile(Object[] data, DataType dataType) throws IOException {
    String fileName = determineFileName(dataType);
    ObjectOutput out = new ObjectOutputStream(new FileOutputStream(fileName));
    out.writeObject(data);
    System.out.println("server, written data to file");
  }
}
