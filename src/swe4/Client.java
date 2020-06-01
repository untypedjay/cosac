package swe4;

import javafx.application.Application;
import javafx.stage.Stage;
import swe4.view.LoginView;

public class Client extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("CosaC");
    LoginView.create(primaryStage);
    primaryStage.show();
    System.out.println();
  }
}
