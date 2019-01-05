package application;
	
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main  {
	/*@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	*/
	public static void main(String[] args) {
	//	launch(args);
		 List<String> stringList = Pattern.compile("-")
		            .splitAsStream("004-0345-56")
		            .collect(Collectors.toList());

		    stringList.forEach(s -> System.out.println(s));
	}
 
}
