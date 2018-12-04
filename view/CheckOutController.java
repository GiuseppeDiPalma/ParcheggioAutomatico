package dipalma.parcheggioautomatico.view;


import java.io.IOException;
import java.sql.SQLException;

import dipalma.parcheggioautomatico.Main;
import dipalma.parcheggioautomatico.model.Prenotazione;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class CheckOutController {
	
	@FXML
	private RadioButton cartaCredito;
	@FXML
	private RadioButton payPal;
	@FXML
	private Label costo = new Label();
	@FXML
	private Label prova = new Label();
	@FXML
	private Button paga;
	@FXML
	private TextField targaDaPagare;
	@FXML
	private Button stampaPrezzo;
	@FXML
	private Parent root;
	

	private int idPar;
	private String costoTotale;

	
	@FXML
	public void inizializzaChekOutController()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/CheckOut.fxml"));
			Pane CheckOutController = (Pane) loader.load();
			Main.getPrimaryStage().setScene(new Scene(CheckOutController));
			Main.getPrimaryStage().show();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	

	
	@FXML
	private void initialize()
	{
		 
	}
	
	
	@FXML
	public void handleStampaPrezzo() throws IOException
	{
		Prenotazione preno = new Prenotazione();
		idPar = preno.parcheggioDaLiberare(targaDaPagare.getText());
		costoTotale = Float.toString(preno.costoTotale(targaDaPagare.getText()));
		prova.setText(costoTotale);
	}

	
	
	@FXML
	public void handleMetodoPagamento() throws IOException, SQLException {
		
		if(cartaCredito.isSelected())
		{
			
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/CartaCreditoPagamento.fxml"));  // view/ClientePanel.fxml
			Pane ContantiPagamento = (Pane) loader.load();
			PagamentoCartaCredito prossimaView = new PagamentoCartaCredito();
			prossimaView.setTargaIdPar(targaDaPagare.getText(), idPar);
			prossimaView.caricaInfo();
			
			Main.getPrimaryStage().setScene(new Scene(ContantiPagamento));
			Main.getPrimaryStage().show();
		}
		
		if(payPal.isSelected())
		{
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/PayPalPagamento.fxml"));  // view/ClientePanel.fxml
			Pane PayPalPagamento = (Pane) loader.load();
			PagamentoBancomatController display = loader.getController();

			display.setTargaIdPar(targaDaPagare.getText(), idPar);
			display.caricaInfo();
			Main.getPrimaryStage().setScene(new Scene(PayPalPagamento));
			Main.getPrimaryStage().show();
		}
	}
	
	@FXML
	private void handleIndietro() throws IOException
	{
		root = FXMLLoader.load(Main.class.getResource("view/LoginPanel.fxml"));
		Main.getPrimaryStage().setScene(new Scene(root));
	}
	
}
