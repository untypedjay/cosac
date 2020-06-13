package swe4.model.data.dishes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.Dish;

import java.util.Iterator;

public class DishRepoMockImpl implements DishRepo {
  private ObservableList<Dish> dishes = FXCollections.observableArrayList();

  public DishRepoMockImpl() {
    dishes.setAll(
      new Dish("Spaghetti Bolognese","Italienische Köstlichkeiten",  640, this),
      new Dish("Cordon Bleu vom Schwein mit Kartoffel und Reis","Heftig Deftig",  750, this),
      new Dish("Gebackene Spinatpalatschinke mit Kartoffeln ","Vegetarische Gerichte",  640, this),
      new Dish("Faschierte Laibchen mit Kartoffelpüree und Gemüse ","Vegetarische Gerichte",  750, this),
      new Dish("Spaghetti mit Tomatensauce ","Vegetarische Gerichte",  640, this)
    );
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
    return true;
  }

  @Override
  public boolean saveDishes() {
    return true;
  }
}