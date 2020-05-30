package swe4.view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import swe4.model.entities.Meal;

public class MenuTab {
  public static BorderPane create(ObservableList<Meal> meals) {
    BorderPane menuPane = new BorderPane();
    TableView<Meal> menuTable = new TableView<Meal>();

    menuTable.setItems(meals);
    TableColumn<Meal, String> mealTypeCol = new TableColumn<>("Bereich");
    mealTypeCol.setCellValueFactory(new PropertyValueFactory<>("mealType"));
    menuTable.getColumns().add(mealTypeCol);

    TableColumn<Meal, String> descriptionCol = new TableColumn<>("Bezeichnung");
    descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
    menuTable.getColumns().add(descriptionCol);

    TableColumn<Meal, Long> priceCol = new TableColumn<>("Preis");
    priceCol.setCellValueFactory(new PropertyValueFactory<>("priceInCents"));
    menuTable.getColumns().add(priceCol);

    TableColumn<Meal, String> buttonCol = new TableColumn<>("");
    buttonCol.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
    menuTable.getColumns().add(buttonCol);

    FlowPane addMealContainer = new FlowPane(4, 4);

    TextField mealType = new TextField();
    mealType.setPromptText("Bereich");
    addMealContainer.getChildren().add(mealType);

    TextField description = new TextField();
    description.setPromptText("Bezeichnung");
    addMealContainer.getChildren().add(description);

    TextField price = new TextField();
    price.setPromptText("Preis");
    addMealContainer.getChildren().add(price);

    Button addButton = new Button("Hinzufügen");
    addButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        meals.add(new Meal(mealType.getText(), description.getText(), price.getText()));
      }
    });
    addMealContainer.getChildren().add(addButton);

    menuPane.setCenter(menuTable);
    menuPane.setBottom(addMealContainer);

    return menuPane;
  }
}
