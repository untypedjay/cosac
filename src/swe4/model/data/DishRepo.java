package swe4.model.data;

import javafx.collections.ObservableList;
import swe4.model.entities.Dish;

public interface DishRepo {
  ObservableList<Dish> getDishes();
  Dish getDishByName(String name);
  boolean deleteDish(String name);
  boolean updateDishes();
  boolean saveDishes();
}
