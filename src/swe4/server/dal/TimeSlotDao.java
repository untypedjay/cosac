package swe4.server.dal;

import javafx.collections.ObservableList;
import swe4.model.entities.TimeSlot;
import java.sql.SQLException;

public interface TimeSlotDao extends AutoCloseable {
  ObservableList<TimeSlot> getAll() throws SQLException;
  TimeSlot get(String startTime) throws SQLException;
  void store(TimeSlot timeSlot) throws SQLException;
}
