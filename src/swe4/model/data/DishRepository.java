package swe4.model.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.Dish;
import java.util.Iterator;

public class DishRepository {
  private static final ObservableList<Dish> dishes = FXCollections.observableArrayList();



  public static ObservableList<Dish> getDishes() {
    return dishes;
  }

  public static Dish getDish(String name) {
    for (Dish dish : dishes) {
      if (dish.getName().equals(name)) {
        return dish;
      }
    }
    return null;
  }

  public static void deleteDish(String dishName) {
    for (Iterator<Dish> iterator = dishes.iterator(); iterator.hasNext();) {
      if (iterator.next().getName().equals(dishName)) {
        iterator.remove();
      }
    }
  }


  public static void receiveDishes(Object[] dishObjectArray) {
      dishes.clear();
      for (int i = 0; i < dishObjectArray.length; ++i) {
        Dish dish = (Dish) dishObjectArray[i];
        dishes.add(new Dish(dish.getName(), dish.getSection(), dish.getPriceInCents()));
      }
    System.out.println("client, received dishes: " + dishes);
  }
}
