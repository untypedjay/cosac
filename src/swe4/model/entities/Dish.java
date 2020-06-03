package swe4.model.entities;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import swe4.util.PriceUtil;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import static swe4.model.DishRepository.deleteDish;

public class Dish implements Serializable {
  private String name = "";
  private String section = "";
  private long priceInCents = 0;
  private transient Button deleteButton = null;

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

  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    in.defaultReadObject();
  }
}
