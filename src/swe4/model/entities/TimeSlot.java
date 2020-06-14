package swe4.model.entities;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import swe4.model.data.timeSlots.TimeSlotRepo;

import java.io.Serializable;
import java.time.LocalTime;

import static java.time.LocalTime.parse;

public class TimeSlot implements Serializable {
  private LocalTime startTime;
  private LocalTime endTime;
  private int maximumCustomers;
  private transient Button deleteButton;

  public TimeSlot(LocalTime startTime, LocalTime endTime, int maximumCustomers, TimeSlotRepo repo) {
    this.startTime = startTime;
    this.endTime = endTime;
    this.maximumCustomers = maximumCustomers;
    this.deleteButton = new Button("Löschen");
    this.deleteButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        repo.deleteTimeSlot(startTime, endTime);
      }
    });
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public LocalTime getEndTime() {
    return endTime;
  }

  public int getMaximumCustomers() {
    return maximumCustomers;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("timeSlot: ");
    sb.append(getStartTime());
    sb.append(" - ");
    sb.append(getEndTime());
    sb.append(" (");
    sb.append(getMaximumCustomers());
    sb.append(")");
    return sb.toString();
  }
}
