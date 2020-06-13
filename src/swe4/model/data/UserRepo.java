package swe4.model.data;

import javafx.collections.ObservableList;
import swe4.model.entities.User;

public interface UserRepo {
  ObservableList<User> getUsers();
  User getUserByUsername(String username);
  boolean addUser(User user);
  boolean deleteUser(String username);
  boolean updateUsers();
  boolean saveUsers();
}
