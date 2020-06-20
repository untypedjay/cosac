package swe4.server.dal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.data.timeSlots.TimeSlotRepoDbImpl;
import swe4.model.entities.TimeSlot;

import java.sql.*;

import static swe4.server.RMIDatabaseServer.*;

public class TimeSlotDaoJdbc implements TimeSlotDao {
  @Override
  public ObservableList<TimeSlot> getAll() throws SQLException {
    ObservableList<TimeSlot> timeSlots = FXCollections.observableArrayList();

    try (Connection connection = DriverManager.getConnection(CONN, USERNAME, PASSWORD);
         PreparedStatement statement = connection.prepareStatement("select * from timeSlot")) {

      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          timeSlots.add(new TimeSlot(resultSet.getString("startTime"),
            resultSet.getString("endTime"),
            resultSet.getInt("maximumCustomers"), new TimeSlotRepoDbImpl()));
        }
      }
    }
    return timeSlots;
  }

  @Override
  public void store(TimeSlot timeSlot) throws SQLException {
    try (Connection connection = DriverManager.getConnection(CONN, USERNAME, PASSWORD);
         PreparedStatement statement = connection.prepareStatement("insert into timeSlot (startTime, endTime, maximumCustomers) values (?, ?, ?)",
           Statement.RETURN_GENERATED_KEYS)) {
      statement.setString(1, String.valueOf(timeSlot.getStartTime()));
      statement.setString(2, String.valueOf(timeSlot.getEndTime()));
      statement.setInt(3, timeSlot.getMaximumCustomers());

      statement.executeUpdate();
    }
  }

  @Override
  public void close() {

  }
}
