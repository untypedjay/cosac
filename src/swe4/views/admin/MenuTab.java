package swe4.views.admin;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import swe4.model.data.Repository;
import swe4.model.entities.Dish;

public class MenuTab extends BorderPane {
  public MenuTab(Repository repo) {
    TableView<Dish> menuTable = new TableView<Dish>();

    menuTable.setItems(repo.getDishes());
    TableColumn<Dish, String> mealTypeCol = new TableColumn<>("Section");
    mealTypeCol.setCellValueFactory(new PropertyValueFactory<>("section"));
    menuTable.getColumns().add(mealTypeCol);

    TableColumn<Dish, String> descriptionCol = new TableColumn<>("Name");
    descriptionCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    menuTable.getColumns().add(descriptionCol);

    TableColumn<Dish, Long> priceCol = new TableColumn<>("Price");
    priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    menuTable.getColumns().add(priceCol);

    TableColumn<Dish, String> buttonCol = new TableColumn<>("");
    buttonCol.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
    menuTable.getColumns().add(buttonCol);

    FlowPane addMealContainer = new FlowPane(4, 4);

    TextField section = new TextField();
    section.setPromptText("Section (e.g. Vegetarische Gerichte)");
    addMealContainer.getChildren().add(section);

    TextField name = new TextField();
    name.setPromptText("Name (e.g. Spaghetti)");
    addMealContainer.getChildren().add(name);

    TextField price = new TextField();
    price.setPromptText("Price (e.g. 7.34)");
    addMealContainer.getChildren().add(price);

    Button addButton = new Button("Add");
    addButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        if (name.getText().isEmpty() || section.getText().isEmpty() || price.getText().isEmpty()) {
          emptyAlert();
        } else if (!price.getText().matches("[0-9.]*")) {
          invalidPriceAlert();
          price.setText("");
        } else {
          repo.addDish(name.getText(), section.getText(), price.getText());
          name.setText("");
          section.setText("");
          price.setText("");
        }
      }
    });
    addMealContainer.getChildren().add(addButton);

    this.setCenter(menuTable);
    this.setBottom(addMealContainer);
  }

  private void emptyAlert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Adding failed!");
    alert.setHeaderText("Incomplete input!");
    alert.setContentText("Please fill out all fields.");
    alert.showAndWait();
  }

  private void invalidPriceAlert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Adding failed!");
    alert.setHeaderText("Poor price formatting!");
    alert.setContentText("Please only use numbers that are separated with a dot.");
    alert.showAndWait();
  }
}
