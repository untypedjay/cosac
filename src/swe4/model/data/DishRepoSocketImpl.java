package swe4.model.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.Dish;

public class DishRepoSocketImpl implements DishRepo {
  private static final ObservableList<Dish> dishes = FXCollections.observableArrayList();

  DishRepoSocketImpl(DishSource dc) {
    dishSource.receive();
  }

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
}
