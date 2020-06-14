package swe4;

import javafx.application.Application;
import javafx.stage.Stage;
import swe4.views.ViewController;
import swe4.views.ViewType;
import java.util.concurrent.TimeUnit;
import static swe4.model.data.Repository.RepoType.MOCK;

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
    ViewController viewController = new ViewController(primaryStage, MOCK);
    viewController.render(ViewType.LOGIN_VIEW, 780, 450);
  }
}
