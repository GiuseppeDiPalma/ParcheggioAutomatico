package dipalma.parcheggioautomatico.view;

import java.io.IOException;
import java.sql.SQLException;

import dipalma.parcheggioautomatico.Main;
import dipalma.parcheggioautomatico.model.ContextPagamento;
import dipalma.parcheggioautomatico.model.PagamentoBancomat;
import dipalma.parcheggioautomatico.model.Prenotazione;
import dipalma.parcheggioautomatico.util.FormFieldVerifica;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class PagamentoBancomatController {

	@FXML
	private TextField numeroCarta;
	@FXML
	private PasswordField pin;
	@FXML
	private Button buttonPaga;
	@FXML
	private Parent root;
	
	ContextPagamento context = new ContextPagamento(null);
	private String targa;
	@SuppressWarnings("unused")
	private int idPar;
	@SuppressWarnings("unused")
	private String costoTotale;

	@FXML
	private void initialize()
	{
		
	}
	
	public PagamentoBancomatController()
	{
		
	}
	
	public void setTargaIdPar(String targa, int idPar)
	{
		this.targa = targa;
		this.idPar = idPar;
	}
	
	public void caricaInfo() throws IOException, SQLException
	{
		Prenotazione preno = new Prenotazione();
		costoTotale = Float.toString(preno.costoTotale(targa));
	}
	
	
	@FXML
	private void handleButtonPaga() throws IOException, SQLException
	{
		
		context = new ContextPagamento(new PagamentoBancomat(numeroCarta.getText(), pin.getText()));
		FormFieldVerifica.correttoWindow("Pagamento Effettuato", "Grazie", "Alla Prossima");
		Prenotazione preno = new Prenotazione();
		preno.rimuoviPrenotazione(targa);
	}
	
	@FXML
	private void handleIndietro() throws IOException
	{
		root = FXMLLoader.load(Main.class.getResource("view/CheckOut.fxml"));
		Main.getPrimaryStage().setScene(new Scene(root));
	}
}
