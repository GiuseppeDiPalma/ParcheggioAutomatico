package dipalma.parcheggioautomatico.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Localita {
	
	private String nomeLocalita;
	private int idLoc;
	
	public Localita(String nomeLocalita, int idLoc) 
	{
		this.nomeLocalita = new String(nomeLocalita);
		this.idLoc = new Integer(idLoc);
	}

	
	public void setNomeLoc(String nomeLocalita) 
	{
		this.nomeLocalita = nomeLocalita;
	}

	
	public void setID_Localita(int idLoc) 
	{
		this.idLoc = idLoc;
	}

	
	public String getNomeLoc() {

		return this.nomeLocalita;
	}

	
	public int getIdLoc() {

		return this.idLoc;
	}
	
	public static ObservableList<Localita> visualizzaLocalita() throws SQLException 
	{
		
		ObservableList <Localita> localitaLista = FXCollections.observableArrayList();
		DatabaseSingleton Database = DatabaseSingleton.getInstance();
		
		String stampaLocalita = "SELECT NOME_LOC, ID_LOCALITA FROM LOCALITA";
		ResultSet rs = Database.EseguiQuery(stampaLocalita);
		try 
		{
			while(rs.next())
			{
				localitaLista.add(new Localita(rs.getString("NOME_LOC"), rs.getInt("ID_LOCALITA")));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return localitaLista;
		
	}
	
	public static int associaID(String scelta) throws SQLException
	{
		int id = 0;
		DatabaseSingleton Database = DatabaseSingleton.getInstance();
		
		String stampaLocalita = "SELECT ID_LOCALITA FROM LOCALITA WHERE NOME_LOC = '"+scelta+"'";
		ResultSet rs = Database.EseguiQuery(stampaLocalita);
		try
		{
			while(rs.next())
			{
				id = rs.getInt(1);
			}
		}
		catch(SQLException e)
		{
			e.getStackTrace();
		}
		
		return id;
	}
	
	


}
