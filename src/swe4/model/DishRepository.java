package swe4.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.Dish;
import swe4.model.entities.TimeSlot;

public class DishRepository {
  private static final ObservableList<Dish> dishes = FXCollections.observableArrayList();

  public static void loadMockDishes() {
    dishes.setAll(
      new Dish("Spaghetti Bolognese","Italienische Köstlichkeiten",  640),
      new Dish("Cordon Bleu vom Schwein mit Kartoffel und Reis","Heftig Deftig",  750),
      new Dish("Gebackene Spinatpalatschinke mit Kartoffeln ","Vegetarische Gerichte",  640),
      new Dish("Faschierte Laibchen mit Kartoffelpüree und Gemüse ","Vegetarische Gerichte",  750),
      new Dish("Spaghetti mit Tomatensauce ","Vegetarische Gerichte",  640)
    );
  }

  public static ObservableList<Dish> getDishes() {
    return dishes;
  }

  public static void deleteDish(String dishName) {
    for (Dish dish : dishes) {
      if (dish.getName().equals(dishName)) {
        dishes.remove(dish);
      }
    }
  }
}
