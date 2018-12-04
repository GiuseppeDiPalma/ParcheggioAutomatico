package dipalma.parcheggioautomatico.view;

import dipalma.parcheggioautomatico.model.ContextPrenotazione;
import dipalma.parcheggioautomatico.model.SmsPrenotazione;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class SmsPrenotazioneController extends Application {

	@FXML
	private TextField numeroCell;
	@FXML
	private Button prenota;
	ContextPrenotazione context = new ContextPrenotazione(null);
	String provaMail = "";

	@Override
	public void start(Stage primaryStage) throws Exception {

	}
	
	@FXML
	private void handlePrenota() {

		context = new ContextPrenotazione(new SmsPrenotazione());
		System.out.println("Prenotazione con SMS "+context.eseguiPrenotazione(provaMail, numeroCell.getText()));
	}
}
