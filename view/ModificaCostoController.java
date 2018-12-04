package dipalma.parcheggioautomatico.view;

import java.io.IOException;
import java.sql.SQLException;

import dipalma.parcheggioautomatico.Main;
import dipalma.parcheggioautomatico.model.Parcheggio;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class ModificaCostoController {

	@FXML
	private ComboBox<Object> sceltaParcheggio = new ComboBox<>();
	@FXML
	private Button invio;
	@FXML
	private RadioButton suv;
	@FXML
	private RadioButton utilitaria;
	@FXML
	private RadioButton motoveicolo;
	@FXML
	private TextField costoAumento;
	
	@FXML
	private Label prezzoAttuale = new Label();
	@FXML
	private Button stampaPrezzo;
	
	
	@FXML
	private Parent root;
	
	
	public double ammontare =  0.0;
	
	public ModificaCostoController()
	{
		
	}
	
	@FXML
	private void initialize() throws SQLException
	{
		ObservableList<Parcheggio> listaPar = Parcheggio.visualizzaParcheggio();
				
				for(int i = 0; i< listaPar.size(); i++)
					{
						sceltaParcheggio.getItems().addAll
						(
								listaPar.get(i).getIdParcheggio()
						);
					}
	}
	

	@FXML
	public void handleInvio() throws IOException
	{

		int idPar = (int) sceltaParcheggio.getValue();
		ammontare =  Double.parseDouble(costoAumento.getText());
		
		Parcheggio par = new Parcheggio();
		
		if(suv.isSelected())
		{	
			par.modificaCostoSuv(idPar, ammontare);
		}
		
		if(utilitaria.isSelected())
		{
			par.modificaCostoUtilitaria(idPar, ammontare);
		}
		
		if(motoveicolo.isSelected())
		{
			par.modificaCostoMotoveicolo(idPar, ammontare);		
		}
		
	}
	private float costoAttuale;
	
	@FXML
	public void handleStampaPrezzoAttuale() throws IOException, SQLException
	{
		int idPar = (int) sceltaParcheggio.getValue();
		
		if(suv.isSelected())
		{	
			costoAttuale = Parcheggio.costoAttuale(idPar, "Suv");
			String costoDaStampare = Float.toString(costoAttuale);
			prezzoAttuale.setText(costoDaStampare);
		}
		
		if(utilitaria.isSelected())
		{
			costoAttuale = Parcheggio.costoAttuale(idPar, "Utilitaria");
			String costoDaStampare = Float.toString(costoAttuale);
			prezzoAttuale.setText(costoDaStampare);
		}
		
		if(motoveicolo.isSelected())
		{
			costoAttuale = Parcheggio.costoAttuale(idPar, "Motoveicolo");
			String costoDaStampare = Float.toString(costoAttuale);
			prezzoAttuale.setText(costoDaStampare);
		}
	}
	

	@FXML
	private void handleIndietro() throws IOException
	{
		root = FXMLLoader.load(Main.class.getResource("view/AmministratoreModifica.fxml"));
		Main.getPrimaryStage().setScene(new Scene(root));
	}

}
