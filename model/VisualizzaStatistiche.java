package dipalma.parcheggioautomatico.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
*
*	Questa classe viene usata per visualizzare le statistiche
*/
public class VisualizzaStatistiche {
	
	
	private int idPar;
	private int prenotazioni;
	private int frequenza;
	private String data;
	
	public VisualizzaStatistiche()
	{
		
	}
	
	public VisualizzaStatistiche(String data, int frequenza)
	{
		this.data = data;
		this.frequenza = frequenza;
	}
	
	public VisualizzaStatistiche(int idPar, int prenotazioni)
	{
		this.idPar = idPar;
		this.prenotazioni = prenotazioni;
	}
	
	
	public void setID_Localita(int idPar) 
	{
		this.idPar = idPar;
	}
	
	public void setPrenotazioni(int prenotazioni)
	{
		this.prenotazioni = prenotazioni;
	}
	
	public int getIdPar()
	{
		return this.idPar;
	}
	
	public int getPrenotazioni()
	{
		return this.prenotazioni;
	}
	
	public void setData(String data)
	{
		this.data = data;
	}
	
	public void setFrequenza(int frequenza)
	{
		this.frequenza = frequenza;
	}
	
	public String getData()
	{
		return this.data;
	}
	
	public int getFrequenza()
	{
		return this.frequenza;
	}
	

	public int numeroSuv()
	{
		int numero = 0;
		DatabaseSingleton Database = DatabaseSingleton.getInstance();
		String numeroSuv = "SELECT COUNT(CATEGORIA) FROM PRENOTAZIONE WHERE CATEGORIA='Suv'";
		ResultSet rs = Database.EseguiQuery(numeroSuv);

		try {
			while(rs.next())
			{
				numero = rs.getInt(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return numero;
	}
	
	public int numeroUtilitaria()
	{
		int numero = 0;
		DatabaseSingleton Database = DatabaseSingleton.getInstance();		
		String numeroUtilitaria = "SELECT COUNT(CATEGORIA) FROM PRENOTAZIONE WHERE CATEGORIA='Utilitaria'";
		ResultSet rs = Database.EseguiQuery(numeroUtilitaria);

		try {
			while(rs.next())
			{
				numero = rs.getInt(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return numero;
	}
	
	public int numeroMotoveicolo()
	{
		int numero = 0;
		DatabaseSingleton Database = DatabaseSingleton.getInstance();		
		String numeroMotoveicolo = "SELECT COUNT(CATEGORIA) FROM PRENOTAZIONE WHERE CATEGORIA='Motoveicoli'";
		ResultSet rs = Database.EseguiQuery(numeroMotoveicolo);

		try {
			
			while(rs.next())
			{
				numero = rs.getInt(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return numero;
	}
	
	
	
	
	public static ObservableList<VisualizzaStatistiche> usoParcheggio()
	{
		ObservableList<VisualizzaStatistiche> risultato = FXCollections.observableArrayList();
		
		DatabaseSingleton Database = DatabaseSingleton.getInstance();
		
		String stampa = "SELECT M_ID_PARCHEGGIO, COUNT(M_ID_PARCHEGGIO) FROM PRENOTAZIONE GROUP BY M_ID_PARCHEGGIO";
		ResultSet rs = Database.EseguiQuery(stampa);
		
		try 
		{
			while(rs.next())
			{
				risultato.add(new VisualizzaStatistiche(rs.getInt(1), rs.getInt(2)));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return risultato;
	}
	
	public static ObservableList<VisualizzaStatistiche> usoDate()
	{
		ObservableList<VisualizzaStatistiche> risultato = FXCollections.observableArrayList();
		DatabaseSingleton Database = DatabaseSingleton.getInstance();
		
		String stampa = "SELECT DATA_ENRATA, COUNT(DATA_ENRATA) AS FREQUENZA FROM PRENOTAZIONE GROUP BY DATA_ENRATA";
		ResultSet rs = Database.EseguiQuery(stampa);
		try
		{
			while(rs.next())
			{
				risultato.add(new VisualizzaStatistiche(rs.getString(1), rs.getInt(2)));
			}
		}catch(SQLException e)
		{
		 e.printStackTrace();
		}
		
		return risultato;
	}
	
	

	public static ObservableList<VisualizzaStatistiche> usoMezzi()
	{
		ObservableList<VisualizzaStatistiche> risultato = FXCollections.observableArrayList();
		
		DatabaseSingleton Database = DatabaseSingleton.getInstance();
		
		String stampa = "SELECT CATEGORIA, COUNT(CATEGORIA) FROM PRENOTAZIONE GROUP BY CATEGORIA";
		ResultSet rs = Database.EseguiQuery(stampa);
		
		try 
		{
			while(rs.next())
			{
				System.out.println("tipo: "+rs.getString(1) +" xxx "+"numero :"+ rs.getInt(2));
				risultato.add(new VisualizzaStatistiche(rs.getString(1), rs.getInt(2)));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return risultato;
	}

}
