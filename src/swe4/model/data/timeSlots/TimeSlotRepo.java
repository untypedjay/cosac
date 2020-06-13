package swe4.model.data;

import javafx.collections.ObservableList;
import swe4.model.entities.TimeSlot;

import java.time.LocalTime;

public interface TimeSlotRepo {
  ObservableList<TimeSlot> getTimeSlots();
  TimeSlot getTimeSlotByTime(LocalTime start, LocalTime end);
  boolean addTimeSlot(TimeSlot timeSlot);
  boolean deleteTimeSlot(LocalTime start, LocalTime end);
  boolean updateTimeSlots();
  boolean saveTimeSlots();
}
