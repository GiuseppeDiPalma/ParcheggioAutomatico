package dipalma.parcheggioautomatico.util;


import dipalma.parcheggioautomatico.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FormFieldVerifica {

	
	public static boolean verificaValiditaUtente(String username, String psswd)
	{
		try 
		{
			if(username == null || username.length() == 0)
				return false;
			if(psswd == null || psswd.length() == 0)
				return false;

			
            	return true;
        } 
		catch (NumberFormatException e) 
		{
            return false;
        }
	}
	
	public static boolean verificaValiditaUtente(String nome, String cognome, String targa)
	{
		try
		{
			if(nome == null || nome.length() == 0)
				return false;
			if(cognome == null || cognome.length() == 0)
				return false;
			if(targa == null || targa.length() == 0)
				return false;
			
			return true;
			
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	
	
	public static void alertWarningWindow(String title, String headerText, String contentText)
	{
		Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(Main.getPrimaryStage());
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        
        alert.showAndWait();
	}
	
	public static void correttoWindow(String title, String headerText, String contentText)
	{
		Alert corretto = new Alert(AlertType.INFORMATION);
		corretto.initOwner(Main.getPrimaryStage());
		corretto.setTitle(title);
		corretto.setHeaderText(headerText);
		corretto.setContentText(contentText);
        
		corretto.showAndWait();
	}
	
}
