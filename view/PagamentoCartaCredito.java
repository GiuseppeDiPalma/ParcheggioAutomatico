package dipalma.parcheggioautomatico.view;

import java.io.IOException;
import java.sql.SQLException;

import dipalma.parcheggioautomatico.Main;
import dipalma.parcheggioautomatico.model.ContextPagamento;
import dipalma.parcheggioautomatico.model.PagamentoCartaCreditoClass;
import dipalma.parcheggioautomatico.model.Prenotazione;
import dipalma.parcheggioautomatico.util.FormFieldVerifica;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class PagamentoCartaCredito {
	
	@FXML
	private TextField nome;
	@FXML
	private TextField numeroCarta;
	@FXML
	private TextField codiceVerifica;
	@FXML
	private TextField dataScadenza;
	@FXML
	private Button buttonPaga;
	@FXML
	private Parent root;
	
	
	ContextPagamento context = new ContextPagamento(null);
	private String targa;
	@SuppressWarnings("unused")
	private String costoTotale;
	int idPar;
	@FXML
	private void initialize()
	{
		
	}
	
	
	public PagamentoCartaCredito()
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
	
	private String nomeconto;
	private String numero;
	private String nnver;
	private String nndat;
	
	

	@FXML
	public void handleButtonProva() throws IOException, SQLException
	{
		nomeconto = nome.getText();
		numero = numeroCarta.getText();
		nnver = codiceVerifica.getText();
		nndat =  dataScadenza.getText();
		
		context = new ContextPagamento(new PagamentoCartaCreditoClass(nomeconto, numero, nnver, nndat));
		System.out.println("targa: " + targa);
		
		Prenotazione preno = new Prenotazione();
		preno.rimuoviPrenotazione(targa);
		
		FormFieldVerifica.correttoWindow("Pagamento Effettuato", "Grazie", "Alla Prossima");
	}
	
	
	@FXML
	private void handleIndietro() throws IOException
	{
		root = FXMLLoader.load(Main.class.getResource("view/CheckOut.fxml"));
		Main.getPrimaryStage().setScene(new Scene(root));
	}

}
