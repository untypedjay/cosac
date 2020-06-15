package swe4.model.dal.timeSlots;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.TimeSlot;
import java.time.LocalTime;
import java.util.Iterator;

public class TimeSlotRepoSocketImpl implements TimeSlotRepo {
  private ObservableList<TimeSlot> timeSlots = FXCollections.observableArrayList();

  public TimeSlotRepoSocketImpl() {
    updateTimeSlots();
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
    // TODO
    return false;
  }

  @Override
  public boolean saveTimeSlots() {
    // TODO
    return false;
  }
}

//  public static void receiveTimeSlots(Object[] timeSlotObjectArray) {
//    timeSlots.clear();
//    for (int i = 0; i < timeSlotObjectArray.length; ++i) {
//      TimeSlot timeSlot = (TimeSlot) timeSlotObjectArray[i];
//      timeSlots.add(new TimeSlot(timeSlot.getStartTime(), timeSlot.getEndTime(), timeSlot.getMaximumCustomers()));
//    }
//    System.out.println("client, received timeSlots: " + timeSlots);
//  }