package swe4.entities;

import java.time.LocalTime;

public class TimeSlot {
  public LocalTime startTime = null;
  public LocalTime endTime = null;
  public int maximumCustomers = 0;

  public TimeSlot(LocalTime startTime, LocalTime endTime) {
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public TimeSlot(LocalTime startTime, LocalTime endTime, int maximumCustomers) {
    this.startTime = startTime;
    this.endTime = endTime;
    this.maximumCustomers = maximumCustomers;
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
}
