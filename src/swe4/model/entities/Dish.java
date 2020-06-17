package swe4.model.entities;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import swe4.model.data.dishes.DishRepo;
import swe4.util.PriceUtil;
import java.io.Serializable;

public class Dish implements Serializable {
  private static final long serialVersionUID = -287429762969025028L;
  private String name;
  private String section;
  private long priceInCents;
  private transient Button deleteButton;

  public Dish(String name, String section, long priceInCents, DishRepo repo) {
    this.name = name;
    this.section = section;
    this.priceInCents = priceInCents;
    this.deleteButton = new Button("Delete");
    this.deleteButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        repo.deleteDish(getName());
      }
    });
  }

  public String getName() {
    return name;
  }

  public long getPriceInCents() {
    return priceInCents;
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

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("dish: ");
    sb.append(name);
    sb.append(", ");
    sb.append(section);
    sb.append(", ");
    sb.append(priceInCents);
    return sb.toString();
  }
}
