package dipalma.parcheggioautomatico.view;


import java.io.IOException;
import java.sql.SQLException;

import dipalma.parcheggioautomatico.Main;
import dipalma.parcheggioautomatico.util.FormFieldVerifica;
import dipalma.parcheggioautomatico.model.Amministratore;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class AmministratorePanelController  {
	
	@FXML
	private TextField nomeInput;
	@FXML
	private PasswordField psswdInput;
	@FXML
	private Button loginButton;
	@FXML
	private Button indietroButton;
	@FXML
	private Parent root;
	
	public AmministratorePanelController()
	{
		
	}
	
	@FXML
	private void initialize()
	{
		
	}
	
	@FXML
	public void inizializzaAmministratorePanelView()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/LoginAmm.fxml"));
			Pane AmministratorePanelController = (Pane) loader.load();
			
			Main.getPrimaryStage().setScene(new Scene(AmministratorePanelController));
			Main.getPrimaryStage().show();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleEseguiLogin() throws IOException, SQLException
	{
		Amministratore Amm = new Amministratore();
		
		if(FormFieldVerifica.verificaValiditaUtente(nomeInput.getText(), psswdInput.getText()))
		{
			
			if(Amm.EseguiLogin(nomeInput.getText(), psswdInput.getText()))
			{
				try
				{
					FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/AmministratoreModifica.fxml"));
					Pane AmministratoreModifica = (Pane) loader.load();
					
					Main.getPrimaryStage().setScene(new Scene(AmministratoreModifica));
					Main.getPrimaryStage().show();
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
			else
			{
				System.out.println("Errore");
			}
		}
		else
		{
			FormFieldVerifica.alertWarningWindow("Compilazione campi errata", "Campi compilati non correttamente", "Possono essersi verificati i seguenti errori:\n- ID e/o Password vuoti\n- ID non inserito correttamente");
		}
	}
	
	@FXML
	private void handleIndietro() throws IOException
	{
		root = FXMLLoader.load(Main.class.getResource("view/LoginPanel.fxml"));
		Main.getPrimaryStage().setScene(new Scene(root));
	}
	
}
