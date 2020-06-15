package swe4.model.dal;

import javafx.collections.ObservableList;
import swe4.model.dal.dishes.DishRepo;
import swe4.model.dal.dishes.DishRepoMockImpl;
import swe4.model.dal.dishes.DishRepoSocketImpl;
import swe4.model.dal.orders.OrderRepo;
import swe4.model.dal.orders.OrderRepoMockImpl;
import swe4.model.dal.orders.OrderRepoSocketImpl;
import swe4.model.dal.timeSlots.TimeSlotRepo;
import swe4.model.dal.timeSlots.TimeSlotRepoMockImpl;
import swe4.model.dal.timeSlots.TimeSlotRepoSocketImpl;
import swe4.model.dal.users.UserRepo;
import swe4.model.dal.users.UserRepoMockImpl;
import swe4.model.dal.users.UserRepoSocketImpl;
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
}