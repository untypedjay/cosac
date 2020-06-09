package swe4.view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import swe4.model.entities.TimeSlot;

public class TimeSlotsTab {
  private final static String TIMESTAMP_REGEX = "([0-1][0-9]|2[0-3]):[0-5][0-9]";
  private final static String ERROR_ALERT_TITLE = "Hinzufügen fehlgeschlagen!";
  public static BorderPane construct(ObservableList<TimeSlot> timeSlots) {
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
    inputStartTime.setPromptText("Startzeit (z.B. 10:30)");
    addTimeSlotContainer.getChildren().add(inputStartTime);

    TextField inputEndTime = new TextField();
    inputEndTime.setPromptText("Endzeit (z.B. 11:00)");
    addTimeSlotContainer.getChildren().add(inputEndTime);

    TextField inputMaxCustomers = new TextField();
    inputMaxCustomers.setPromptText("Maximale Kundenanzahl (z.B. 10)");
    addTimeSlotContainer.getChildren().add(inputMaxCustomers);

    Button addButton = new Button("Hinzufügen");
    addButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        if (inputStartTime.getText().isEmpty() || inputEndTime.getText().isEmpty()
                                               || inputMaxCustomers.getText().isEmpty()) {
          showErrorAlert(ERROR_ALERT_TITLE, "Unvollständige Eingabe!", "Bitte füllen Sie alle Felder aus.");
        } else if (!inputStartTime.getText().matches(TIMESTAMP_REGEX) ||
                   !inputEndTime.getText().matches(TIMESTAMP_REGEX)) {
          showErrorAlert(ERROR_ALERT_TITLE, "Falsches Zeitformat!", "Bitte verwenden Sie das gängige Uhrzeitformat.");
          inputStartTime.setText("");
          inputEndTime.setText("");
        } else if (!inputMaxCustomers.getText().matches("[0-9]*")) {
          showErrorAlert(ERROR_ALERT_TITLE, "Falsches Zahlenformat!", "Bitte verwenden Sie ein ganze positive Zahl");
          inputMaxCustomers.setText("");
        } else {
          timeSlots.add(new TimeSlot(inputStartTime.getText(), inputEndTime.getText(), inputMaxCustomers.getText()));
          inputStartTime.setText("");
          inputEndTime.setText("");
          inputMaxCustomers.setText("");
        }
      }
    });
    addTimeSlotContainer.getChildren().add(addButton);

    timeSlotPane.setCenter(timeSlotsTable);
    timeSlotPane.setBottom(addTimeSlotContainer);

    return timeSlotPane;
  }

  private static void showErrorAlert(String title, String headerText, String contentText) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);
    alert.showAndWait();
  }
}
