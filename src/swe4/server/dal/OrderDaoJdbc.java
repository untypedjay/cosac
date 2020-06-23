package swe4.server.dal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.Dish;
import swe4.model.entities.Order;
import swe4.model.entities.TimeSlot;
import swe4.model.entities.User;

import java.sql.*;

import static swe4.server.RMIDatabaseServer.*;

public class OrderDaoJdbc implements OrderDao {
  @Override
  public ObservableList<Order> getAll() throws SQLException {
    ObservableList<Order> orders = FXCollections.observableArrayList();

    try (Connection connection = DriverManager.getConnection(CONN, USERNAME, PASSWORD);
         PreparedStatement statement = connection.prepareStatement("select * from orders")) {

      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
//          orders.add(new Order(resultSet.getString("customer"),
//            resultSet.getString("dish"),
//            resultSet.getInt("timeSlotId"));
        }
      }
    }
    return orders;
  }

  @Override
  public void store(Order order) throws SQLException {
    try (Connection connection = DriverManager.getConnection(CONN, USERNAME, PASSWORD);
         PreparedStatement statement = connection.prepareStatement("insert into order (id, customer, dish, timeSlotId) values (?, ?, ?, ?)",
           Statement.RETURN_GENERATED_KEYS)) {
      statement.setString(1, order.getId());
      statement.setString(2, order.getCustomerName());
      statement.setString(3, order.getDishName());
      statement.setString(4, String.valueOf(order.getTimeSlot()));

      statement.executeUpdate();
    }
  }

  @Override
  public void close() throws Exception {

  }
}
