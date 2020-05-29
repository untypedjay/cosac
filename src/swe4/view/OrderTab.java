package swe4.view;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import swe4.model.entities.Order;

public class OrderTab {
  public static BorderPane construct(ObservableList<Order> orders) {
    BorderPane orderPane = new BorderPane();
    TableView<Order> orderTable = new TableView<>();

    orderTable.setItems(orders);
    TableColumn<Order, String> customerCol = new TableColumn<>("Kunde");
    customerCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
    orderTable.getColumns().add(customerCol);

    TableColumn<Order, String> orderCol = new TableColumn<>("Bestellung");
    orderCol.setCellValueFactory(new PropertyValueFactory<>("mealName"));
    orderTable.getColumns().add(orderCol);

    TableColumn<Order, String> timeFrameCol = new TableColumn<>("Zeitfenster");
    timeFrameCol.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
    orderTable.getColumns().add(timeFrameCol);

    TableColumn<Order, String> orderTimeCol = new TableColumn<>("Bestellzeit");
    orderTimeCol.setCellValueFactory(new PropertyValueFactory<>("orderTime"));
    orderTable.getColumns().add(orderTimeCol);

    orderPane.setCenter(orderTable);

    return orderPane;
  }
}
