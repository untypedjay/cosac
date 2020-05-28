package swe4.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import swe4.model.entities.TimeSlot;

public class TimeSlotsTab {
  public BorderPane timeSlotsTab() {
    BorderPane timeSlotPane = new BorderPane();
    TableView<TimeSlot> timeSlotsTable = new TableView<>();

    timeSlotsTable.setItems(timeSlots);
    TableColumn<TimeSlot, String> startTimeCol = new TableColumn<>("Beginn");
    startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
    timeSlotsTable.getColumns().add(startTimeCol);

    TableColumn<TimeSlot, String> endTimeCol = new TableColumn<>("Ende");
    endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
    timeSlotsTable.getColumns().add(endTimeCol);

    TableColumn<TimeSlot, Integer> maxCustomersCol = new TableColumn<>("Maximale Kundenanzahl");
    maxCustomersCol.setCellValueFactory(new PropertyValueFactory<>("maximumCustomers"));
    timeSlotsTable.getColumns().add(maxCustomersCol);

    TableColumn<TimeSlot, String> buttonCol = new TableColumn<>("");
    buttonCol.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
    timeSlotsTable.getColumns().add(buttonCol);

    FlowPane addTimeSlotContainer = new FlowPane(4, 4);

    TextField inputStartTime = new TextField();
    inputStartTime.setPromptText("Start");
    addTimeSlotContainer.getChildren().add(inputStartTime);

    TextField inputEndTime = new TextField();
    inputEndTime.setPromptText("Ende");
    addTimeSlotContainer.getChildren().add(inputEndTime);

    TextField inputMaxCustomers = new TextField();
    inputMaxCustomers.setPromptText("Maximale Kundenanzahl");
    addTimeSlotContainer.getChildren().add(inputMaxCustomers);

    Button addButton = new Button("Hinzufügen");
    addButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        timeSlots.add(new TimeSlot(inputStartTime.getText(), inputEndTime.getText(), inputMaxCustomers.getText()));
      }
    });
    addTimeSlotContainer.getChildren().add(addButton);

    timeSlotPane.setCenter(timeSlotsTable);
    timeSlotPane.setBottom(addTimeSlotContainer);

    return timeSlotPane;
  }
}
