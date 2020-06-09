package swe4.model.entities;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalTime;

import static java.time.LocalTime.parse;
import static swe4.model.data.TimeSlotRepository.deleteTimeSlot;

public class TimeSlot implements EventHandler, Serializable {
  private static final long serialVersionUID = -87331671154138999L;
  private LocalTime startTime;
  private LocalTime endTime;
  private int maximumCustomers;
  private transient Button deleteButton;

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

  public int getMaximumCustomers() {
    return maximumCustomers;
  }

  public Button getDeleteButton() {
    return deleteButton;
  }

  @Override
  public void handle(Event event) {
    deleteTimeSlot(getStartTime(), getEndTime());
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

  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    in.defaultReadObject();
  }
}
