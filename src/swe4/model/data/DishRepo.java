package swe4.model.data;

import javafx.collections.ObservableList;
import swe4.model.entities.Dish;

public interface DishRepo {
  ObservableList<Dish> findAll();
  Dish findByName(String name);
  boolean deleteDish(String name);
}
