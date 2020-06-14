package swe4.model.data.timeSlots;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.TimeSlot;

import java.time.LocalTime;
import java.util.Iterator;

public class TimeSlotRepoMockImpl implements TimeSlotRepo {
  private ObservableList<TimeSlot> timeSlots = FXCollections.observableArrayList();

  public TimeSlotRepoMockImpl() {
    timeSlots.setAll(
      new TimeSlot(LocalTime.of(11, 00), LocalTime.of(11, 30), 10, this),
      new TimeSlot(LocalTime.of(11, 30), LocalTime.of(12, 00), 10, this),
      new TimeSlot(LocalTime.of(12, 00), LocalTime.of(12, 30), 10, this),
      new TimeSlot(LocalTime.of(12, 30), LocalTime.of(13, 00), 10, this),
      new TimeSlot(LocalTime.of(13, 00), LocalTime.of(13, 30), 10, this)
    );
  }

  @Override
  public ObservableList<TimeSlot> getTimeSlots() {
    return timeSlots;
  }

  @Override
  public TimeSlot getTimeSlotByTime(LocalTime start, LocalTime end) {
    for (TimeSlot timeSlot : timeSlots) {
      if (timeSlot.getStartTime().equals(start) && timeSlot.getEndTime().equals(end)) {
        return timeSlot;
      }
    }
    return null;
  }

  @Override
  public boolean addTimeSlot(TimeSlot timeSlot) {
    timeSlots.add(timeSlot);
    return true;
  }

  @Override
  public boolean deleteTimeSlot(LocalTime start, LocalTime end) {
    for (Iterator<TimeSlot> iterator = timeSlots.iterator(); iterator.hasNext();) {
      TimeSlot current = iterator.next();
      if (current.getStartTime().equals(start) && current.getEndTime().equals(end)) {
        iterator.remove();
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean updateTimeSlots() {
    return true;
  }

  @Override
  public boolean saveTimeSlots() {
    return true;
  }
}