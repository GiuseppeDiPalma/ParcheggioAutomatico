package dipalma.parcheggioautomatico.view;

import dipalma.parcheggioautomatico.model.ChiamataPrenotazione;
import dipalma.parcheggioautomatico.model.ContextPrenotazione;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChiamataPrenotazioneController extends Application {

	@FXML
	private TextField numeroCell;
	@FXML
	private Button prenota;
	
	ContextPrenotazione context = new ContextPrenotazione(null);
	String provaMail = "";
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private void handlePrenota() {
		context = new ContextPrenotazione(new ChiamataPrenotazione());
		System.out.println("Prenotazione con SMS "+context.eseguiPrenotazione(provaMail, numeroCell.getText()));

	}


}
