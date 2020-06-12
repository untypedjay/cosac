package swe4.model.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.Dish;

public class DishRepoSocketImpl implements DishRepo {
  private static final ObservableList<Dish> dishes = FXCollections.observableArrayList();

  @Override
  public ObservableList<Dish> findAll() {
    return null;
  }

  @Override
  public Dish findByName(String name) {
    return null;
  }

  @Override
  public boolean deleteDish(String name) {
    return false;
  }

  @Override
  public void receiveDishes(Object[] dishObjectArray) {
    dishes.clear();
    for (int i = 0; i < dishObjectArray.length; ++i) {
      Dish dish = (Dish) dishObjectArray[i];
      dishes.add(new Dish(dish.getName(), dish.getSection(), dish.getPriceInCents(), this));
    }
    System.out.println("client, received dishes: " + dishes);
  }
}