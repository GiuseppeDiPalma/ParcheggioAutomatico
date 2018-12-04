package dipalma.parcheggioautomatico.model;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Giuseppe Di Palma
 * @since 25/02/2018
 * @version 0.1
 * */

public class User {
	private String userNome;
	private String userCognome;
	private String userTarga;
	
	public User()
	{
		
	}

	
	public void setNome(String userNome) 
	{
		this.userNome = userNome;
	}

	
	public void setCognome(String userCognome) 
	{
		this.userCognome = userCognome;
		
	}
	
	public void setTarga(String userTarga) 
	{
		this.userTarga = userTarga;
	}
	
	
	
	public String getNome() 
	{
		return this.userNome;
	}
	
	
	public String getCognome() 
	{
		return this.userCognome;
	}
	
	public String getTarga() 
	{
		return this.userTarga;
	}

	
	public boolean AggiungiUser(String user_nome, String user_cognome, String user_targa) throws IOException, SQLException 
	{
		DatabaseSingleton Database = DatabaseSingleton.getInstance();

		String aggiungiUser = "INSERT INTO UTENTE VALUES('"+ user_targa +"', '"+ user_nome +"', '"+ user_cognome +"')";
		Database.EseguiQuery(aggiungiUser);		
		return true;
	}
	
	public boolean EliminaUser(String user_Targa) throws IOException, SQLException
	{
		DatabaseSingleton Database = DatabaseSingleton.getInstance();

		String eliminaUser = "DELETE FROM UTENTE WHERE TARGA = '"+user_Targa+"'";
		Database.EseguiQuery(eliminaUser);		
		
		return true;
	}
	
}
