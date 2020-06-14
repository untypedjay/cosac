package swe4.views.admin;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import swe4.model.data.Repository;
import swe4.model.entities.Order;

public class OrderTab extends BorderPane {
  public OrderTab(Repository repo) {
    TableView<Order> orderTable = new TableView<>();

    orderTable.setItems(repo.getOrders());
    TableColumn<Order, String> customerCol = new TableColumn<>("Customer");
    customerCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
    orderTable.getColumns().add(customerCol);

    TableColumn<Order, String> orderCol = new TableColumn<>("Order");
    orderCol.setCellValueFactory(new PropertyValueFactory<>("dishName"));
    orderTable.getColumns().add(orderCol);

    TableColumn<Order, String> timeFrameCol = new TableColumn<>("Time Slot");
    timeFrameCol.setCellValueFactory(new PropertyValueFactory<>("timeSlotString"));
    orderTable.getColumns().add(timeFrameCol);

    TableColumn<Order, String> orderTimeCol = new TableColumn<>("Order Time");
    orderTimeCol.setCellValueFactory(new PropertyValueFactory<>("orderTimeString"));
    orderTable.getColumns().add(orderTimeCol);

    this.setCenter(orderTable);
  }
}
