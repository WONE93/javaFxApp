package co.yedam.test.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class DailyMain extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("diaryInput.fxml"));
		Scene scene = new Scene(root, 600, 400);
		stage.setTitle("FXML Welcome");
		stage.setScene(scene);
		stage.show();
	}

}
