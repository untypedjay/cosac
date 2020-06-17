package swe4.model.data.users;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.User;
import swe4.util.PasswordUtil;

public class UserRepoSocketImpl implements UserRepo {
  private ObservableList<User> users = FXCollections.observableArrayList();

  public UserRepoSocketImpl() {
    updateUsers();
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
    //TODO
    return false;
  }

  @Override
  public boolean saveUsers() {
    //TODO
    return false;
  }
}
