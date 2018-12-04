package dipalma.parcheggioautomatico.view;

import java.io.IOException;
import java.sql.SQLException;

import dipalma.parcheggioautomatico.Main;
import dipalma.parcheggioautomatico.model.Localita;
import dipalma.parcheggioautomatico.model.Parcheggio;
import dipalma.parcheggioautomatico.model.Prenotazione;
import dipalma.parcheggioautomatico.model.User;
import dipalma.parcheggioautomatico.util.Edge;
import dipalma.parcheggioautomatico.util.FormFieldVerifica;
import dipalma.parcheggioautomatico.util.Graph;
import dipalma.parcheggioautomatico.util.RandomIdGenerator;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ClientePanelController {
//------------------------------------------------------	
//------------------------------------------------------
	@FXML
	private TextField nomeCliente;
	@FXML
	private TextField cognomeCliente;
	@FXML
	private TextField  targaCliente;
	@FXML
	private Button avantiButton;
	@FXML
	private Button indietroButton;
//------------------------------------------------------	
//------------------------------------------------------
	//Data e Ora Parcheggio
	@FXML
	private ComboBox<Object> categoriaVeicoli = new ComboBox<>();
	
	@FXML
	private DatePicker	dataEntrata;
	@FXML
	private ComboBox<Object> oraEntrata = new ComboBox<>();
	
	@FXML
	private DatePicker dataUscita;
	@FXML
	private ComboBox<Object> oraUscita = new ComboBox<>();
//------------------------------------------------------	
//------------------------------------------------------	
	@FXML
	private Button costoButton;
	@FXML
	private Label stampaCosto = new Label();
	
	private float costo;
//------------------------------------------------------	
//------------------------------------------------------	
	@FXML
	private ComboBox<Object> sceltaDestinazione = new ComboBox<>();
	@FXML
	private Label parcheggioDisponibile = new Label();
	@FXML
	private Button trovaParcheggio;
	@FXML
	private Label indirizzoParcheggio = new Label();
//------------------------------------------------------	
//------------------------------------------------------

	@FXML
	private RadioButton chiamata;
	@FXML
	private RadioButton sms;
	@FXML
	private RadioButton email;
	
//------------------------------------------------------	
//------------------------------------------------------
	
	private int idParcheggio;
	
	public  ClientePanelController()
	{
		
	}
	
	@FXML
	private void initialize() throws SQLException
	{
		
		ObservableList<Localita> listaLoc = Localita.visualizzaLocalita();
		
		for(int i = 0; i< listaLoc.size(); i++)
			{
				sceltaDestinazione.getItems().addAll
				(
						listaLoc.get(i).getNomeLoc()
				);
			}
		
		categoriaVeicoli.getItems().addAll
		(
				"Motoveicoli",
				"Utilitaria",
				"Suv"
		);
		
		oraEntrata.getItems().addAll
		(
				"10",
				"11",
				"12",
				"13",
				"14",
				"15",
				"16",
				"17",
				"18",
				"19",
				"20",
				"21",
				"22",
				"23"
		);
		
		oraUscita.getItems().addAll
		(
				"10",
				"11",
				"12",
				"13",
				"14",
				"15",
				"16",
				"17",
				"18",
				"19",
				"20",
				"21",
				"22",
				"23"
		);
		
		
	}
	
	@FXML
	public void inizializzaClientePanelView()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/ClienteP.fxml"));  // view/ClientePanel.fxml
			Pane ClientePanelController = (Pane) loader.load();
			Main.getPrimaryStage().setScene(new Scene(ClientePanelController));
			Main.getPrimaryStage().show();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public Edge[] edges;
	public int source;
	public String sourceString;
	
	@FXML
	private void handleTrovaParcheggio() throws IOException, SQLException
	{
		String valore = (String) sceltaDestinazione.getValue();
		
		int idLoc = Localita.associaID(valore);

		edges = Edge.caricaArchiTutti();
		Graph g = new Graph(edges);
		g.calculateShortestDistances(idLoc);
		idParcheggio = g.printResult(idLoc);
		
		String idParcheggioString = Integer.toString(idParcheggio);
		parcheggioDisponibile.setText(idParcheggioString);
		
		String indirizzoPar = Parcheggio.associaParcheggio(idParcheggio);
		indirizzoParcheggio.setText(indirizzoPar);
	}
	

	
	@FXML
	private void handleAvantiButton() throws IOException, SQLException, InterruptedException
	{
		try
		{		
			User utente = new User();
			if(FormFieldVerifica.verificaValiditaUtente(nomeCliente.getText(), cognomeCliente.getText(), targaCliente.getText()))
			{	
				utente.AggiungiUser(nomeCliente.getText(), cognomeCliente.getText(), targaCliente.getText());
				Prenotazione prenota = new Prenotazione();
				int id = RandomIdGenerator.randInt(0, 50000);
				
				int cstOraEntrata = Integer.parseInt((String) oraEntrata.getValue());
				int cstOraUscita = Integer.parseInt((String) oraUscita.getValue());
				
				String entrata = dataEntrata.getValue().toString();
				String uscita = dataUscita.getValue().toString();
				String targa = targaCliente.getText();
				String categoria = (String) categoriaVeicoli.getValue();
					
				costo = prenota.calcolaCosto(entrata, uscita, cstOraEntrata, cstOraUscita, categoria, idParcheggio);
			
				String costoC = Float.toString(costo);
				stampaCosto.setText(costoC);
				

				
				if(sms.isSelected())
				{
					FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/SmsPrenotazione.fxml"));  // view/ClientePanel.fxml
					Pane paneSMS = (Pane) loader.load();

					Scene secondScene = new Scene(paneSMS);
					
					// New window (Stage)
			        Stage newWindow = new Stage();
			        newWindow.setScene(secondScene);
			        // Specifies the modality for new window.
			        newWindow.initModality(Modality.WINDOW_MODAL);
			        
			        newWindow.show();
			        prenota.AggiungiPrenotazione(id, categoria, entrata, cstOraEntrata, uscita, cstOraUscita, costo, targa, idParcheggio);
				}
				else if(chiamata.isSelected())
				{
					
					FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/ChiamataPrenotazione.fxml"));
					Pane paneChiamata = (Pane) loader.load();

					Scene secondScene = new Scene(paneChiamata);
					
					// New window (Stage)
			        Stage newWindow = new Stage();
			        newWindow.setScene(secondScene);
			        // Specifies the modality for new window.
			        newWindow.initModality(Modality.WINDOW_MODAL);

			        newWindow.show();
			        prenota.AggiungiPrenotazione(id, categoria, entrata, cstOraEntrata, uscita, cstOraUscita, costo, targa, idParcheggio);
				}
				else if(email.isSelected())
				{
					FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/EmailPrenotazione.fxml"));
					Pane paneChiamata = (Pane) loader.load();

					Scene secondScene = new Scene(paneChiamata);
					
					// New window (Stage)
			        Stage newWindow = new Stage();
			        newWindow.setScene(secondScene);
			        // Specifies the modality for new window.
			        newWindow.initModality(Modality.WINDOW_MODAL);

			        newWindow.show();
			        prenota.AggiungiPrenotazione(id, categoria, entrata, cstOraEntrata, uscita, cstOraUscita, costo, targa, idParcheggio);
				}
				
				
				
//				TimeUnit.SECONDS.sleep(2);
//				
//				FormFieldVerifica.correttoWindow("Prenotazione riuscita", "Procedi", 
//						"Ora puoi effettuare l'accesso al parcheggio da te scelto :\n- "
//						+ "Alla fine della prenotazione paga la tua permanenza\n- "
//						+ "Tramite l'apposito bottone nel login!\n- La schermata di "
//						+ "prenotazione si chiuderà automaticamente.");
			}
			else
			{
				FormFieldVerifica.alertWarningWindow("Errore Aggiunta Utente", "Errore all'atto della creazione dell'utente", "Aggiungere Nome Cliente, Cognome Cliente, targa necessariamente!");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleCalcolaCosto() throws IOException
	{
		int cstOraEntrata = Integer.parseInt((String) oraEntrata.getValue());
		int cstOraUscita = Integer.parseInt((String) oraUscita.getValue());
		
		Prenotazione prenota = new Prenotazione();
		
		String entrata = dataEntrata.getValue().toString();
		String uscita = dataUscita.getValue().toString();
		String categoria = (String) categoriaVeicoli.getValue();
		try {
		
			costo = prenota.calcolaCosto(entrata, uscita, cstOraEntrata, cstOraUscita, categoria, idParcheggio);
			
			String costoC = Float.toString(costo);
			stampaCosto.setText(costoC);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

	@FXML
	private void handleIndietroButton() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/LoginPanel.fxml"));  // view/ClientePanel.fxml
		Pane SceltaParcheggio = (Pane) loader.load();
		Main.getPrimaryStage().setScene(new Scene(SceltaParcheggio));
		Main.getPrimaryStage().show();
	}


}