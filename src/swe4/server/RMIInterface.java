package swe4.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote {
  Object[] loadDishes() throws RemoteException;
  boolean saveDishes(Object[] dishes) throws RemoteException;

  Object[] loadOrders() throws RemoteException;
  boolean saveOrders(Object[] orders) throws RemoteException;

  Object[] loadTimeSlots() throws RemoteException;
  boolean saveTimeSlots(Object[] timeSlots) throws RemoteException;

  Object[] loadUsers() throws RemoteException;
  boolean saveUsers(Object[] users) throws RemoteException;
}
