package swe4.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.TimeSlot;

import java.time.LocalTime;

public class TimeSlotRepository {
  private static final ObservableList<TimeSlot> timeSlots = FXCollections.observableArrayList();

  public static void loadMockTimeSlots() {
    timeSlots.setAll(
      new TimeSlot(LocalTime.of(11, 00), LocalTime.of(11, 30), 10),
      new TimeSlot(LocalTime.of(11, 30), LocalTime.of(12, 00), 10),
      new TimeSlot(LocalTime.of(12, 00), LocalTime.of(12, 30), 10),
      new TimeSlot(LocalTime.of(12, 30), LocalTime.of(13, 00), 10)
    );
  }

  public static ObservableList<TimeSlot> getTimeSlots() {
    return timeSlots;
  }

  public static void deleteTimeSlot(LocalTime startTime, LocalTime endTime) {
    for (TimeSlot timeSlot : timeSlots) {
      if (timeSlot.getStartTime().equals(startTime) && timeSlot.getEndTime().equals(endTime)) {
        timeSlots.remove(timeSlot);
      }
    }
  }
}
