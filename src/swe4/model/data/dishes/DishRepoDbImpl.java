package swe4.model.data.dishes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.Dish;
import swe4.server.RMIInterface;

import java.rmi.Naming;
import java.util.Iterator;

public class DishRepoDbImpl implements DishRepo {
  private static final String SERVER_URL = "rmi://127.0.0.1/DBServer";
  private ObservableList<Dish> dishes = FXCollections.observableArrayList();

  public DishRepoDbImpl() {
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
    RMIInterface server;
    try {
      server = (RMIInterface) Naming.lookup(SERVER_URL);
      dishes.clear();
      Object[] rawDishes = server.loadDishes();
      for (int i = 0; i < rawDishes.length; ++i) {
        dishes.add((Dish) rawDishes[i]);
      }
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean saveDishes() {
    RMIInterface server;
    try {
      server = (RMIInterface) Naming.lookup(SERVER_URL);
      Object[] dishData = new Object[getDishes().size()];
      for (int i = 0; i < dishData.length; ++i) {
        dishData[i] = getDishes().get(i);
      }
      server.saveDishes(dishData);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
}
