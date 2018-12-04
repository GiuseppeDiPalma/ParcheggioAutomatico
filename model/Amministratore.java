package dipalma.parcheggioautomatico.model;

import java.io.IOException;
import java.sql.SQLException;

public class Amministratore {

	private String id_amministratore;
	private String psswd_amministratore;
	
	public void setID(String id_amministratore)
	{
		this.id_amministratore = id_amministratore;
	}
	
	public void setPSSWD(String psswd_amministratore)
	{
		this.psswd_amministratore = psswd_amministratore;
	}
	
	public String getID()
	{
		return this.id_amministratore;
	}
	
	public String getPSSWD()
	{
		return this.psswd_amministratore;
	}
	
	public Amministratore()
	{
		super();
	}
	
	
	public boolean EseguiLogin(String nomeAmministratore, String passwdAmministratore) throws IOException, SQLException 
	{
		if(nomeAmministratore.equals("root") && passwdAmministratore.equals("root"))
			return true;
		else
			return false;
	}
	
}