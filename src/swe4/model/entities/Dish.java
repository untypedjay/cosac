package swe4.model.entities;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import swe4.util.PriceUtil;

import static swe4.model.DishRepository.deleteDish;

public class Dish {
  private String name = "";
  private String section = "";
  private long priceInCents = 0;
  private Button deleteButton = null;

  public Dish(String name, String section, long priceInCents) {
    this.name = name;
    this.section = section;
    this.priceInCents = priceInCents;
    this.deleteButton = new Button("Löschen");
    this.deleteButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        deleteDish(getName());
      }
    });
  }

  public String getName() {
    return name;
  }

  public String getPrice() {
    return PriceUtil.formatPrice(priceInCents);
  }

  public String getSection() {
    return section;
  }

  public Button getDeleteButton() {
    return deleteButton;
  }
}
