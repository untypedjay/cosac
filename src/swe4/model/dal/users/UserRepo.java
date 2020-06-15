package swe4.model.dal.users;

import javafx.collections.ObservableList;
import swe4.model.entities.User;

public interface UserRepo {
  ObservableList<User> getUsers();
  User getUserByUsername(String username);
  boolean addUser(User user);
  boolean deleteUser(String username);
  boolean isValidUser(String username, String password);
  boolean updateUsers();
  boolean saveUsers();
}
