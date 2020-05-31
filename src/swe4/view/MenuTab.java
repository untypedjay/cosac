package swe4.view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import swe4.model.entities.Dish;
import swe4.util.PriceUtil;

public class MenuTab {
  public static BorderPane create(ObservableList<Dish> dishes) {
    BorderPane menuPane = new BorderPane();
    TableView<Dish> menuTable = new TableView<Dish>();

    menuTable.setItems(dishes);
    TableColumn<Dish, String> mealTypeCol = new TableColumn<>("Bereich");
    mealTypeCol.setCellValueFactory(new PropertyValueFactory<>("section"));
    menuTable.getColumns().add(mealTypeCol);

    TableColumn<Dish, String> descriptionCol = new TableColumn<>("Bezeichnung");
    descriptionCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    menuTable.getColumns().add(descriptionCol);

    TableColumn<Dish, Long> priceCol = new TableColumn<>("Preis");
    priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    menuTable.getColumns().add(priceCol);

    TableColumn<Dish, String> buttonCol = new TableColumn<>("");
    buttonCol.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
    menuTable.getColumns().add(buttonCol);

    FlowPane addMealContainer = new FlowPane(4, 4);

    TextField section = new TextField();
    section.setPromptText("Bereich (z.B. Vegetarische Gerichte)");
    addMealContainer.getChildren().add(section);

    TextField name = new TextField();
    name.setPromptText("Bezeichnung (z.B. Spaghetti)");
    addMealContainer.getChildren().add(name);

    TextField price = new TextField();
    price.setPromptText("Preis (z.B. 7.34)");
    addMealContainer.getChildren().add(price);

    Button addButton = new Button("Hinzufügen");
    addButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        if (name.getText().isEmpty() || section.getText().isEmpty() || price.getText().isEmpty()) {
          emptyAlert();
        } else if (!price.getText().matches("[0-9.]*")) {
          invalidPriceAlert();
          price.setText("");
        } else {
          dishes.add(new Dish(name.getText(), section.getText(), PriceUtil.convertToCents(price.getText())));
          name.setText("");
          section.setText("");
          price.setText("");
        }
      }
    });
    addMealContainer.getChildren().add(addButton);

    menuPane.setCenter(menuTable);
    menuPane.setBottom(addMealContainer);

    return menuPane;
  }

  private static void emptyAlert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Hinzufügen fehlgeschlagen!");
    alert.setHeaderText("Unvollständige Eingabe!");
    alert.setContentText("Bitte füllen Sie alle Felder aus.");
    alert.showAndWait();
  }

  private static void invalidPriceAlert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Hinzufügen fehlgeschlagen!");
    alert.setHeaderText("Falsches Preisformat!");
    alert.setContentText("Bitte verwenden Sie nur Zahlen getrennt von einem Punkt.");
    alert.showAndWait();
  }
}
