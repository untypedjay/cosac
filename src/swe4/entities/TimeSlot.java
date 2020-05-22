package swe4.entities;

import javafx.scene.control.Button;

import java.time.LocalTime;

import static java.time.LocalTime.parse;

public class TimeSlot {
  private LocalTime startTime = null;
  private LocalTime endTime = null;
  private int maximumCustomers = 0;
  private Button deleteButton = null;

  public TimeSlot(LocalTime startTime, LocalTime endTime, int maximumCustomers) {
    this.startTime = startTime;
    this.endTime = endTime;
    this.maximumCustomers = maximumCustomers;
    this.deleteButton = new Button("Löschen");
  }

  public TimeSlot(String startTime, String endTime, String maximumCustomers) {
    this.startTime = parse(startTime);
    this.endTime = parse(endTime);
    this.maximumCustomers = Integer.parseInt(maximumCustomers);
    this.deleteButton = new Button("Löschen");
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalTime startTime) {
    this.startTime = startTime;
  }

  public LocalTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalTime endTime) {
    this.endTime = endTime;
  }

  public int getMaximumCustomers() {
    return maximumCustomers;
  }

  public void setMaximumCustomers(int maximumCustomers) {
    this.maximumCustomers = maximumCustomers;
  }

  public Button getDeleteButton() {
    return deleteButton;
  }

  public void setDeleteButton(Button deleteButton) {
    this.deleteButton = deleteButton;
  }
}
