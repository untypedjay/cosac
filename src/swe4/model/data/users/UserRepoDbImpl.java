package swe4.model.data.users;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swe4.model.entities.User;

public class UserRepoDbImpl implements UserRepo {
  private static final String SERVER_URL = "rmi://127.0.0.1/DBServer";
  private ObservableList<User> users = FXCollections.observableArrayList();

  public UserRepoDbImpl() {
    updateUsers();
  }

  @Override
  public ObservableList<User> getUsers() {
    return null;
  }

  @Override
  public User getUserByUsername(String username) {
    return null;
  }

  @Override
  public boolean addUser(User user) {
    return false;
  }

  @Override
  public boolean deleteUser(String username) {
    return false;
  }

  @Override
  public boolean isValidUser(String username, String password) {
    return false;
  }

  @Override
  public boolean updateUsers() {
    return false;
  }

  @Override
  public boolean saveUsers() {
    return false;
  }
}
