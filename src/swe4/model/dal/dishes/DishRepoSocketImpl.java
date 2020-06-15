package swe4.model.dal.dishes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.Dish;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;

public class DishRepoSocketImpl implements DishRepo {
  private ObservableList<Dish> dishes = FXCollections.observableArrayList();

  public DishRepoSocketImpl() {
    updateDishes();
  }

  @Override
  public ObservableList<Dish> getDishes() {
    return dishes;
  }

  @Override
  public Dish getDishByName(String name) {
    for (Dish dish : dishes) {
      if (dish.getName().equals(name)) {
        return dish;
      }
    }
    return null;
  }

  @Override
  public boolean addDish(Dish dish) {
    dishes.add(dish);
    return true;
  }

  @Override
  public boolean deleteDish(String name) {
    for (Iterator<Dish> iterator = dishes.iterator(); iterator.hasNext();) {
      if (iterator.next().getName().equals(name)) {
        iterator.remove();
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean updateDishes() {
    try (Socket socket = new Socket("localhost", 5004);
         ObjectOutput out = new ObjectOutputStream(socket.getOutputStream())) {
      out.writeObject("data");
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }

    try (ServerSocket server = new ServerSocket(5003);
         Socket socket = server.accept();
         ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      dishes.clear();
      Object[] dishObjectArray = (Object[]) in.readObject();
      for (int i = 0; i < dishObjectArray.length; ++i) {
        Dish dish = (Dish) dishObjectArray[i];
        dishes.add(new Dish(dish.getName(), dish.getSection(), dish.getPriceInCents(), this));
      }
      System.out.println("client, received dishes: " + dishes);
      return true;
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean saveDishes() {
    try (Socket socket = new Socket("localhost", 5004);
         ObjectOutput out = new ObjectOutputStream(socket.getOutputStream())) {
      Object[] dishData = new Object[getDishes().size()];
      for (int i = 0; i < dishData.length; ++i) {
        dishData[i] = getDishes().get(i);
      }
      out.writeObject(dishData);
      System.out.println("client, sent dishes to server: " + dishData);
      return true;
    } catch (UnknownHostException e) {
      e.printStackTrace();
      return false;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }
}