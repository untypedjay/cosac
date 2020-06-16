package swe4.server;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.Dish;
import swe4.model.entities.Order;
import swe4.model.entities.TimeSlot;
import swe4.model.entities.User;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import static swe4.util.ServerUtil.DataType.*;
import static swe4.util.ServerUtil.retrieveDataFromFile;
import static swe4.util.ServerUtil.storeDataInFile;

public class RMIServer extends UnicastRemoteObject implements RMIInterface {

  RMIServer() throws RemoteException {
    super();
  }

  public static void main(String[] args) {
    try {
      LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
      Naming.rebind("RMIServer", new RMIServer());
      System.out.println("server, ready for RMI calls...");
    } catch (RemoteException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Object[] loadDishes() {
    return retrieveDataFromFile(DISH);
  }

  @Override
  public boolean saveDishes(Object[] dishes) {
    try {
      storeDataInFile(dishes, DISH);
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public Object[] loadOrders() {
    return retrieveDataFromFile(ORDER);
  }

  @Override
  public boolean saveOrders(Object[] orders) {
    try {
      storeDataInFile(orders, ORDER);
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public Object[] loadTimeSlots() {
    return retrieveDataFromFile(TIMESLOT);
  }

  @Override
  public boolean saveTimeSlots(Object[] timeSlots) {
    try {
      storeDataInFile(timeSlots, TIMESLOT);
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public Object[] loadUsers() {
    return retrieveDataFromFile(USER);
  }

  @Override
  public boolean saveUsers(Object[] users) {
    try {
      storeDataInFile(users, USER);
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }
}
