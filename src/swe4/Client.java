package swe4;

import javafx.application.Application;
import javafx.stage.Stage;
import swe4.model.*;
import swe4.model.entities.TimeSlot;
import swe4.view.LoginView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Client extends Application {
  public static void main(String[] args) {
    try {
      TimeUnit.SECONDS.sleep(3); // to start client and server simultaneously
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
//    UserRepository.loadMockUsers();
    try {
      Repository.loadData();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    DishRepository.loadMockDishes();
    TimeSlotRepository.loadMockTimeSlots();
    OrderRepository.loadMockOrders();
    primaryStage.setTitle("CosaC");
    LoginView.create(primaryStage);
    primaryStage.show();
    System.out.println();
  }
}
