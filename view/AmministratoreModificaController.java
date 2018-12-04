package dipalma.parcheggioautomatico.view;

import java.io.IOException;

import dipalma.parcheggioautomatico.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


public class AmministratoreModificaController {
	
	@FXML
	private Button logoutButton;
	@FXML
	private Parent root;
	@FXML
	private Button modificaCosto;
	@FXML
	private Button visualizzaStatistiche;
	
	
	@FXML
	private void handleModificaCosto() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/ModificaCosto.fxml"));
		Pane modificaCosto = (Pane) loader.load();
		Main.getPrimaryStage().setScene(new Scene(modificaCosto));
		Main.getPrimaryStage().show();
	}
	
	@FXML
	private void handleVisualizzaStatistiche() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/VisStats.fxml"));
		Pane visualizzaStats = (Pane) loader.load();
		Main.getPrimaryStage().setScene(new Scene(visualizzaStats));
		Main.getPrimaryStage().show();
	}
	
	@FXML
	private void handleLogout() throws IOException
	{
		root = FXMLLoader.load(Main.class.getResource("view/LoginPanel.fxml"));
		Main.getPrimaryStage().setScene(new Scene(root));
	}
}
