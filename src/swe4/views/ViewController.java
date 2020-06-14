package swe4.views;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import swe4.model.data.Repository;
import swe4.views.admin.AdminView;
import swe4.views.login.LoginView;

public class ViewController {
  private Stage primaryStage;
  private Repository repo = null;

  public ViewController(Stage primaryStage, Repository.RepoType repoType) {
    this.primaryStage = primaryStage;
    repo = new Repository(repoType);
  }

  public void render(ViewType view, int width, int height) {
    primaryStage.setScene(getScene(view, width, height));
    primaryStage.setTitle("CosaC");
    primaryStage.show();
  }

  private Scene getScene(ViewType view, int width, int height) {
    Pane pane = null;
    switch (view) {
      case LOGIN_VIEW: pane = new LoginView(this, repo); break;
      case ADMIN_VIEW: pane = new AdminView(this, repo); break;
    }
    Scene scene = new Scene(pane, width, height);
    scene.getStylesheets().add(LoginView.class.getResource("../styles.css").toExternalForm());
    return scene;
  }
}
