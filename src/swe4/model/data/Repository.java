package swe4.model.data;

import javafx.collections.ObservableList;
import swe4.model.data.dishes.*;
import swe4.model.data.orders.*;
import swe4.model.data.timeSlots.*;
import swe4.model.data.users.*;
import swe4.model.entities.Dish;
import swe4.model.entities.Order;
import swe4.model.entities.TimeSlot;
import swe4.model.entities.User;
import swe4.util.PriceUtil;

import java.time.LocalTime;

public class Repository {
  public enum RepoType {
    MOCK,
    SOCKET,
    RMI,
    DB
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
    } else if (repoType == RepoType.RMI) {
      dishRepo = new DishRepoRMIImpl();
      timeSlotRepo = new TimeSlotRepoRMIImpl();
      userRepo = new UserRepoRMIImpl();
      orderRepo = new OrderRepoRMIImpl();
    } else if (repoType == RepoType.DB) {
      dishRepo = new DishRepoDbImpl();
      timeSlotRepo = new TimeSlotRepoDbImpl();
      userRepo = new UserRepoDbImpl();
      orderRepo = new OrderRepoDbImpl();
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