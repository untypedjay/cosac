package swe4.model.entities;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.time.LocalTime;

import static java.time.LocalTime.parse;
import static swe4.model.TimeSlotRepository.deleteTimeSlot;

public class TimeSlot implements EventHandler {
  private LocalTime startTime = null;
  private LocalTime endTime = null;
  private int maximumCustomers = 0;
  private Button deleteButton = null;

  public TimeSlot(LocalTime startTime, LocalTime endTime, int maximumCustomers) {
    this.startTime = startTime;
    this.endTime = endTime;
    this.maximumCustomers = maximumCustomers;
    this.deleteButton = new Button("Löschen");
    this.deleteButton.setOnAction(this::handle);
  }

  public TimeSlot(String startTime, String endTime, String maximumCustomers) {
    this.startTime = parse(startTime);
    this.endTime = parse(endTime);
    this.maximumCustomers = Integer.parseInt(maximumCustomers);
    this.deleteButton = new Button("Löschen");
    this.deleteButton.setOnAction(this::handle);
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public LocalTime getEndTime() {
    return endTime;
  }

  public long getMaximumCustomers() {
    return maximumCustomers;
  }

  public Button getDeleteButton() {
    return deleteButton;
  }

  @Override
  public void handle(Event event) {
    deleteTimeSlot(getStartTime(), getEndTime());
  }
}
