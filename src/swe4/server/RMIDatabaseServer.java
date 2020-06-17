package swe4.server;

import swe4.model.entities.Dish;
import swe4.model.entities.Order;
import swe4.model.entities.TimeSlot;
import swe4.model.entities.User;
import swe4.server.dal.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class RMIDatabaseServer implements RMIInterface {
  public static final String USERNAME = "root";
  public static final String PASSWORD = "";
  public static final String CONN = "jdbc:mysql://localhost:3306/cosac";
  private DishDao dishDao;
  private OrderDao orderDao;
  private TimeSlotDao timeSlotDao;
  private UserDao userDao;

  RMIDatabaseServer() throws RemoteException {
    super();
    dishDao = new DishDaoJdbc();
    orderDao = new OrderDaoJdbc();
    timeSlotDao = new TimeSlotDaoJdbc();
    userDao = new UserDaoJdbc();
  }

  public static void main(String[] args) {
    try {
      LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
      Naming.rebind("RMIServer", new RMIFileServer());
      System.out.println("server, ready for RMI calls...");
    } catch (RemoteException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Object[] loadDishes() throws RemoteException {
    try {
      return dishDao.getAll().toArray();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean saveDishes(Object[] dishes) throws RemoteException {
    for (int i = 0; i < dishes.length; ++i) {
      try {
        dishDao.store((Dish) dishes[i]);
      } catch (SQLException throwables) {
        throwables.printStackTrace();
        return false;
      }
    }
    return true;
  }

  @Override
  public Object[] loadOrders() throws RemoteException {
    try {
      return orderDao.getAll().toArray();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean saveOrders(Object[] orders) throws RemoteException {
    for (int i = 0; i < orders.length; ++i) {
      try {
        orderDao.store((Order) orders[i]);
      } catch (SQLException throwables) {
        throwables.printStackTrace();
        return false;
      }
    }
    return true;
  }

  @Override
  public Object[] loadTimeSlots() throws RemoteException {
    try {
      return timeSlotDao.getAll().toArray();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean saveTimeSlots(Object[] timeSlots) throws RemoteException {
    for (int i = 0; i < timeSlots.length; ++i) {
      try {
        timeSlotDao.store((TimeSlot) timeSlots[i]);
      } catch (SQLException throwables) {
        throwables.printStackTrace();
        return false;
      }
    }
    return true;
  }

  @Override
  public Object[] loadUsers() throws RemoteException {
    try {
      return userDao.getAll().toArray();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean saveUsers(Object[] users) throws RemoteException {
    for (int i = 0; i < users.length; ++i) {
      try {
        userDao.store((User) users[i]);
      } catch (SQLException throwables) {
        throwables.printStackTrace();
        return false;
      }
    }
    return true;
  }
}