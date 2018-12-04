package dipalma.parcheggioautomatico.view;

import dipalma.parcheggioautomatico.model.ContextPrenotazione;
import dipalma.parcheggioautomatico.model.WebPrenotazione;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EmailPrenotazioneController extends Application {

	@FXML
	private TextField emailField;
	@FXML
	private Button prenota;
	
	ContextPrenotazione context = new ContextPrenotazione(null);
	String provaNumeroCell = "";
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
	}
	
	@FXML
	private void handlePrenota() {
        context = new ContextPrenotazione(new WebPrenotazione());
        System.out.println("Prenotazione con Email: "+context.eseguiPrenotazione(emailField.getText(), provaNumeroCell));
	}
}
