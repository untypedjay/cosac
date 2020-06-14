package swe4.model.data;

import javafx.collections.ObservableList;
import swe4.model.data.dishes.DishRepo;
import swe4.model.data.dishes.DishRepoMockImpl;
import swe4.model.data.dishes.DishRepoSocketImpl;
import swe4.model.data.orders.OrderRepo;
import swe4.model.data.orders.OrderRepoMockImpl;
import swe4.model.data.orders.OrderRepoSocketImpl;
import swe4.model.data.timeSlots.TimeSlotRepo;
import swe4.model.data.timeSlots.TimeSlotRepoMockImpl;
import swe4.model.data.timeSlots.TimeSlotRepoSocketImpl;
import swe4.model.data.users.UserRepo;
import swe4.model.data.users.UserRepoMockImpl;
import swe4.model.data.users.UserRepoSocketImpl;
import swe4.model.entities.Dish;
import swe4.model.entities.Order;
import swe4.model.entities.TimeSlot;
import swe4.model.entities.User;
import swe4.util.PriceUtil;

import java.time.LocalTime;

public class Repository {
  public enum RepoType {
    MOCK,
    SOCKET
  }

  private DishRepo dishRepo = null;
  private OrderRepo orderRepo = null;
  private TimeSlotRepo timeSlotRepo = null;
  private UserRepo userRepo = null;

  public Repository(RepoType repoType) {
    if (repoType == RepoType.MOCK) {
      dishRepo = new DishRepoMockImpl();
      timeSlotRepo = new TimeSlotRepoMockImpl();
      userRepo = new UserRepoMockImpl();
      orderRepo = new OrderRepoMockImpl(this.userRepo, this.dishRepo, this.timeSlotRepo);
    } else if (repoType == RepoType.SOCKET) {
      dishRepo = new DishRepoSocketImpl();
      timeSlotRepo = new TimeSlotRepoSocketImpl();
      userRepo = new UserRepoSocketImpl();
      orderRepo = new OrderRepoSocketImpl();
    }
  }

//  public void loadData() throws ClassNotFoundException, IOException {
//    if (repoType == RepoType.SOCKET) {
//      try (Socket socket = new Socket("localhost", 5004);
//           ObjectOutput out = new ObjectOutputStream(socket.getOutputStream())) {
//        out.writeObject("data");
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//
//      try (ServerSocket server = new ServerSocket(5003);
//           Socket socket = server.accept();
//           ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
//        receiveUsers((Object[]) in.readObject());
//        dishRepo.receiveDishes((Object[]) in.readObject());
//        receiveOrders((Object[]) in.readObject());
//        receiveTimeSlots((Object[]) in.readObject());
//      }
//    }
//
//  }

  public boolean update() {
    return dishRepo.updateDishes() && orderRepo.updateOrders() && timeSlotRepo.updateTimeSlots() && userRepo.updateUsers();
  }

  public boolean save() {
    return dishRepo.saveDishes() && orderRepo.saveOrders() && timeSlotRepo.saveTimeSlots() && userRepo.saveUsers();
  }

  public ObservableList<Dish> getDishes() {
    return dishRepo.getDishes();
  }

  public ObservableList<Order> getOrders() {
    return orderRepo.getOrders();
  }

  public ObservableList<TimeSlot> getTimeSlots() {
    return timeSlotRepo.getTimeSlots();
  }

  public ObservableList<User> getUsers() {
    return userRepo.getUsers();
  }

  public boolean addTimeSlot(String start, String end, String maxCustomers) {
    return timeSlotRepo.addTimeSlot(new TimeSlot(LocalTime.parse(start.subSequence(0, start.length())),
                                     LocalTime.parse(end.subSequence(0, end.length())),
                                     Integer.parseInt(maxCustomers),
                                     timeSlotRepo));
  }

  public boolean addUser(String firstName, String lastName, String username, String password) {
    return userRepo.addUser(new User(firstName, lastName, username, password, false, User.Role.CUSTOMER, userRepo));
  }

  public boolean addDish(String name, String section, String price) {
    return dishRepo.addDish(new Dish(name, section, PriceUtil.convertToCents(price), dishRepo));
  }

  public boolean isValidUser(String username, String password) {
    return userRepo.isValidUser(username, password);
  }

//  public void saveData() throws IOException {
//    try (Socket socket = new Socket("localhost", 5004);
//         ObjectOutput out = new ObjectOutputStream(socket.getOutputStream())) {
//      Object[] userData = new Object[getUsers().size()];
//      for (int i = 0; i < userData.length; ++i) {
//        userData[i] = getUsers().get(i);
//      }
//      out.writeObject(userData);
//      System.out.println("client, sent users to server: " + userData);
//
//      Object[] dishData = new Object[dishRepo.findAll().size()];
//      for (int i = 0; i < dishData.length; ++i) {
//        dishData[i] = dishRepo.findAll().get(i);
//      }
//      out.writeObject(dishData);
//      System.out.println("client, sent dishes to server: " + dishData);
//
//      Object[] orderData = new Object[getOrders().size()];
//      for (int i = 0; i < orderData.length; ++i) {
//        orderData[i] = getOrders().get(i);
//      }
//      out.writeObject(orderData);
//      System.out.println("client, sent orders to server: " + orderData);
//
//      Object[] timeSlotData = new Object[getTimeSlots().size()];
//      for (int i = 0; i < timeSlotData.length; ++i) {
//        timeSlotData[i] = getTimeSlots().get(i);
//      }
//      out.writeObject(timeSlotData);
//      System.out.println("client, sent timeSlots to server: " + timeSlotData);
//    }
//  }
}