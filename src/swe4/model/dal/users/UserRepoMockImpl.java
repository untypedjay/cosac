package swe4.model.dal.users;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.User;
import swe4.util.PasswordUtil;
import static swe4.model.entities.User.Role.ADMIN;
import static swe4.model.entities.User.Role.CUSTOMER;

public class UserRepoMockImpl implements UserRepo {
  private ObservableList<User> users = FXCollections.observableArrayList();

  public UserRepoMockImpl() {
    users.setAll(
      new User("Bill", "Yard", "yard", PasswordUtil.generateHash("yard123"), false, ADMIN, this),
      new User("admin", "admin", "admin", PasswordUtil.generateHash("admin"), false, ADMIN, this),
      new User("Claire", "Waßer", "wasser", PasswordUtil.generateHash("wasser123"), false, CUSTOMER, this),
      new User("Rainer", "Zufall", "zufall", PasswordUtil.generateHash("zufall123"), false, CUSTOMER, this),
      new User("Martha", "Pfahl", "pfahl", PasswordUtil.generateHash("pfahl123"), false, CUSTOMER, this),
      new User("Marie", "Huana", "huana", PasswordUtil.generateHash("huana123"), false, CUSTOMER, this)
    );
  }
  @Override
  public ObservableList<User> getUsers() {
    return users;
  }

  @Override
  public User getUserByUsername(String username) {
    for (User user : users) {
      if (user.getUsername().equals(username)) {
        return user;
      }
    }
    return null;
  }

  @Override
  public boolean addUser(User user) {
    users.add(user);
    return true;
  }

  @Override
  public boolean deleteUser(String username) {
    for (User user : users) {
      if (user.getUsername().equals(username)) {
        users.remove(user);
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isValidUser(String username, String password) {
    for (User user : users) {
      if (user.getUsername().equals(username) && PasswordUtil.isValid(password, user.getPasswordHash())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean updateUsers() {
    return true;
  }

  @Override
  public boolean saveUsers() {
    return true;
  }
}

//  public static void receiveUsers(Object[] userObjectArray) {
//    users.clear();
//    for (int i = 0; i < userObjectArray.length; ++i) {
//      User user = (User) userObjectArray[i];
//      users.add(new User(user.getFirstName(), user.getLastName(), user.getUserName(), user.getPasswordHash(), user.isLocked(), user.getRole()));
//    }
//    System.out.println("client, received users: " + users);
//  }
