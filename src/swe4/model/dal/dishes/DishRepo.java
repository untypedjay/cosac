package swe4.model.dal.dishes;

import javafx.collections.ObservableList;
import swe4.model.entities.Dish;

public interface DishRepo {
  ObservableList<Dish> getDishes();
  Dish getDishByName(String name);
  boolean addDish(Dish dish);
  boolean deleteDish(String name);
  boolean updateDishes();
  boolean saveDishes();
}
