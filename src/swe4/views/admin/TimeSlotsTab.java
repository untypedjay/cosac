package swe4.views.admin;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import swe4.model.data.Repository;
import swe4.model.entities.TimeSlot;

public class TimeSlotsTab extends BorderPane {
  private final static String TIMESTAMP_REGEX = "([0-1][0-9]|2[0-3]):[0-5][0-9]";
  private final static String ERROR_ALERT_TITLE = "Adding failed!";

  public TimeSlotsTab(Repository repo) {
    TableView<TimeSlot> timeSlotsTable = new TableView<>();

    timeSlotsTable.setItems(repo.getTimeSlots());
    TableColumn<TimeSlot, String> startTimeCol = new TableColumn<>("Start");
    startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
    timeSlotsTable.getColumns().add(startTimeCol);

    TableColumn<TimeSlot, String> endTimeCol = new TableColumn<>("End");
    endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
    timeSlotsTable.getColumns().add(endTimeCol);

    TableColumn<TimeSlot, Integer> maxCustomersCol = new TableColumn<>("Maximum Customers");
    maxCustomersCol.setCellValueFactory(new PropertyValueFactory<>("maximumCustomers"));
    timeSlotsTable.getColumns().add(maxCustomersCol);

    TableColumn<TimeSlot, String> buttonCol = new TableColumn<>("");
    buttonCol.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
    timeSlotsTable.getColumns().add(buttonCol);

    FlowPane addTimeSlotContainer = new FlowPane(4, 4);

    TextField inputStartTime = new TextField();
    inputStartTime.setPromptText("Start time (e.g. 10:30)");
    addTimeSlotContainer.getChildren().add(inputStartTime);

    TextField inputEndTime = new TextField();
    inputEndTime.setPromptText("End time (e.g. 11:00)");
    addTimeSlotContainer.getChildren().add(inputEndTime);

    TextField inputMaxCustomers = new TextField();
    inputMaxCustomers.setPromptText("Maximum Customers (e.g. 10)");
    addTimeSlotContainer.getChildren().add(inputMaxCustomers);

    Button addButton = new Button("Add");
    addButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        if (inputStartTime.getText().isEmpty() || inputEndTime.getText().isEmpty()
                                               || inputMaxCustomers.getText().isEmpty()) {
          showErrorAlert(ERROR_ALERT_TITLE, "Incomplete Input!", "Please fill out all fields.");
        } else if (!inputStartTime.getText().matches(TIMESTAMP_REGEX) ||
                   !inputEndTime.getText().matches(TIMESTAMP_REGEX)) {
          showErrorAlert(ERROR_ALERT_TITLE, "Poor time formatting!", "Please use the most common time formatting.");
          inputStartTime.setText("");
          inputEndTime.setText("");
        } else if (!inputMaxCustomers.getText().matches("[0-9]*")) {
          showErrorAlert(ERROR_ALERT_TITLE, "Poor number formatting!", "Please only use positive integers.");
          inputMaxCustomers.setText("");
        } else {

          repo.addTimeSlot(inputStartTime.getText(), inputEndTime.getText(), inputMaxCustomers.getText());
          inputStartTime.setText("");
          inputEndTime.setText("");
          inputMaxCustomers.setText("");
        }
      }
    });
    addTimeSlotContainer.getChildren().add(addButton);

    this.setCenter(timeSlotsTable);
    this.setBottom(addTimeSlotContainer);
  }

  private void showErrorAlert(String title, String headerText, String contentText) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);
    alert.showAndWait();
  }
}
