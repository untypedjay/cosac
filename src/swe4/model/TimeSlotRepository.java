package swe4.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.TimeSlot;
import java.time.LocalTime;
import java.util.Iterator;

public class TimeSlotRepository {
  private static ObservableList<TimeSlot> timeSlots = FXCollections.observableArrayList();

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
    for (Iterator<TimeSlot> iterator = timeSlots.iterator(); iterator.hasNext();) {
      TimeSlot current = iterator.next();
      if (current.getStartTime().equals(startTime) && current.getEndTime().equals(endTime)) {
        iterator.remove();
      }
    }
  }

  public static void receiveTimeSlots(Object[] timeSlotObjectArray) {
      timeSlots.clear();
      for (int i = 0; i < timeSlotObjectArray.length; ++i) {
        TimeSlot timeSlot = (TimeSlot) timeSlotObjectArray[i];
        timeSlots.add(new TimeSlot(timeSlot.getStartTime(), timeSlot.getEndTime(), timeSlot.getMaximumCustomers()));
      }
    System.out.println("client, received timeSlots: " + timeSlots);
  }
}
