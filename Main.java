package dipalma.parcheggioautomatico;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Giuseppe Di Palma
 * @since 25/02/2018
 * @version 0.1
 * */

public class Main extends Application {
	
	
	private static Stage window;
	private static Scene scene;
	
	@Override
	public void start(Stage primaryStage) 
	{
		window = primaryStage;
		window.setTitle("Parcheggio Automatico");
		window.getIcons().add(new Image("file:resources/parkingIcon.png"));
		inizializzaParcheggioAutomatico();
	}
	
	public static void inizializzaParcheggioAutomatico()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/LoginPanel.fxml"));
			VBox loginPanelController = (VBox) loader.load();
			
			window.setScene(new Scene(loginPanelController));
			window.show();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static Stage getPrimaryStage()
	{
		return window;
	}
	
	public static Scene getPrimaryScene()
	{
		return scene;
	}

	public static void main(String[] args) {
		launch(args);
	}
}