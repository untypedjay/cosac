package swe4.model.data.dishes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.Dish;

import java.util.Iterator;

public class DishRepoSocketImpl implements DishRepo {
  private ObservableList<Dish> dishes = FXCollections.observableArrayList();

  public DishRepoSocketImpl() {
    // TODO
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
    //TODO
    return false;
  }

  @Override
  public boolean saveDishes() {
    //TODO
    return false;
  }
}



//
//  @Override
//  public void receiveDishes(Object[] dishObjectArray) {
//    dishes.clear();
//    for (int i = 0; i < dishObjectArray.length; ++i) {
//      Dish dish = (Dish) dishObjectArray[i];
//      dishes.add(new Dish(dish.getName(), dish.getSection(), dish.getPriceInCents(), this));
//    }
//    System.out.println("client, received dishes: " + dishes);
//  }