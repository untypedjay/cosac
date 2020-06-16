package swe4.model.dal.timeSlots;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.Dish;
import swe4.model.entities.TimeSlot;
import swe4.server.RMIInterface;

import java.rmi.Naming;
import java.time.LocalTime;
import java.util.Iterator;

public class TimeSlotRepoRMIImpl implements TimeSlotRepo {
  private static final String SERVER_URL = "rmi://127.0.0.1/RMIServer";
  private ObservableList<TimeSlot> timeSlots = FXCollections.observableArrayList();

  public TimeSlotRepoRMIImpl() {
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
    RMIInterface server;
    try {
      server = (RMIInterface) Naming.lookup(SERVER_URL);
      timeSlots.clear();
      Object[] rawTimeSlots = server.loadTimeSlots();
      for (int i = 0; i < rawTimeSlots.length; ++i) {
        timeSlots.add((TimeSlot) rawTimeSlots[i]);
      }
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean saveTimeSlots() {
    RMIInterface server;
    try {
      server = (RMIInterface) Naming.lookup(SERVER_URL);
      Object[] timeSlotData = new Object[getTimeSlots().size()];
      for (int i = 0; i < timeSlotData.length; ++i) {
        timeSlotData[i] = getTimeSlots().get(i);
      }
      server.saveTimeSlots(timeSlotData);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
}
