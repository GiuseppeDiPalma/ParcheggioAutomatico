package dipalma.parcheggioautomatico.model;


import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
*	Classe Parcheggio, usata per gestire i diversi parcheggi della "città"
*
*/	

public class Parcheggio {

	public int idParcheggio;
	private int numeroPosti;
	private int costoSuv;
	private int costoUtilitaria;
	private int costoMotoveicolo;
	
	public Parcheggio()
	{
		
	}
	
	public Parcheggio(int idParcheggio)
	{
		this.idParcheggio = new Integer(idParcheggio);
	}
	
	
	public Parcheggio(int idParcheggio, int costoSuv, int costoUtilitaria, int costoMotoveicolo)
	{
		this.idParcheggio = new Integer(idParcheggio);
		this.costoSuv = new Integer(costoSuv);
		this.costoUtilitaria = new Integer(costoUtilitaria);
		this.costoMotoveicolo = new Integer(costoMotoveicolo);
	}
	


	
	public void setCostoSuv(int costoS) {
		this.costoSuv = costoS;
	}
	
	
	public void setCostoUtilitaria(int costoU) {
		this.costoMotoveicolo = costoU;
	}
	

	public void setCostoMotoveicolo(int costoM) {
		this.costoMotoveicolo = costoM;
	}

	
	public void setIdParcheggio(int idPar) {
		this.idParcheggio = idPar;
	}

	public void setNumPosti(int numPosti) {
		this.numeroPosti = numPosti;
	}
	
	
	public int getCostoSuv() {
		return this.costoSuv;
	}
	
	
	public int getCostoUtilitaria() {
		return this.costoUtilitaria;
	}
	
	
	public int getCostoMotoveicolo() {
		return this.costoMotoveicolo;
	}

	
	public int getIdParcheggio() {
		return this.idParcheggio;
	}

	
	public int getNumPosti() {
		return this.numeroPosti;
	}
	
	//Funzione usata quando, dopo aver pagato un parcheggio, viene supposto che il clienta esca, e quindi
	//un posto nel parcheggio viene liberato, aggiungendone uno a quello effettivi liberi.
	public boolean incrementaPostoParcheggio(int idPar)
	{
		
		DatabaseSingleton Database = DatabaseSingleton.getInstance();
		
		String incrementaNumPosti = "UPDATE PARCHEGGIO SET NUMERO_POSTI = NUMERO_POSTI + 1 WHERE ID_PARCHEGGIO = "+ idPar +"";
		Database.EseguiQuery(incrementaNumPosti);
		return false;
	}
	
	//Inverso della funzione descritta sopra, che di fatto decrementa un posto in un parcheggio 
	//all'atto della prenotazione.
	public boolean decrementaPostoParcheggio(int idPar)
	{
		DatabaseSingleton Database = DatabaseSingleton.getInstance();
		String controlloPosti = "SELECT NUMERO_POSTI FROM PARCHEGGIO WHERE ID_PARCHEGGIO = "+ idPar +"";
		ResultSet rs = Database.EseguiQuery(controlloPosti);
		try
		{
			while(rs.next())
			{
				if(rs.getInt(1) > 0)
				{
					String decrementaNumPosti = "UPDATE PARCHEGGIO SET NUMERO_POSTI = NUMERO_POSTI - 1 WHERE ID_PARCHEGGIO= "+ idPar +"";
					Database.EseguiQuery(decrementaNumPosti);
				}
				else
				{
					System.out.println("i posti in questo parcheggio sono finiti, mi dispiace!!");
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	//Una funzione usato solo per la creazione di una lista, che poi sarà utilizzata 
	//per stampare a video varie informazioni riguardanti il parcheggio.
	public static ObservableList<Parcheggio> visualizzaParcheggio() throws SQLException 
	{
		
		ObservableList <Parcheggio> localitaParcheggio = FXCollections.observableArrayList();
		DatabaseSingleton Database = DatabaseSingleton.getInstance();
		
		String stampaParcheggio = "SELECT ID_PARCHEGGIO, COSTO_SUV, COSTO_UTILITARIA, COSTO_MOTOVEICOLO FROM PARCHEGGIO";
		ResultSet rs = Database.EseguiQuery(stampaParcheggio);
		
		try 
		{
			while(rs.next())
			{
				localitaParcheggio.add(new Parcheggio(rs.getInt("ID_PARCHEGGIO"), rs.getInt("COSTO_SUV"), 
													rs.getInt("COSTO_UTILITARIA"), rs.getInt("COSTO_MOTOVEICOLO")));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return localitaParcheggio;
		
	}
	//funzione usata per modificare il costo orario della categoria suv.
	public boolean modificaCostoSuv(int idPar, double ammontare)
	{
		DatabaseSingleton Database = DatabaseSingleton.getInstance();
		String aumentoSuv = "UPDATE PARCHEGGIO SET COSTO_SUV ="+ammontare+" WHERE ID_PARCHEGGIO ="+idPar+"";
		Database.EseguiQuery(aumentoSuv);
		return true;
	}
		//funzione usata per modificare il costo orario della categoria utilitaria.
	public boolean modificaCostoUtilitaria(int idPar, double ammontare)
	{
		DatabaseSingleton Database = DatabaseSingleton.getInstance();
		String aumentoUtilitaria = "UPDATE PARCHEGGIO SET COSTO_UTILITARIA = "+ ammontare +" WHERE ID_PARCHEGGIO = "+ idPar +"";
		Database.EseguiQuery(aumentoUtilitaria);
		return false;
	}
	
	//funzione usata per modificare il costo orario della categoria motoveicolo.	
	public boolean modificaCostoMotoveicolo(int idPar, double ammontare)
	{
		DatabaseSingleton Database = DatabaseSingleton.getInstance();
		String aumentoMotoveicolo = "UPDATE PARCHEGGIO SET COSTO_MOTOVEICOLO = "+ ammontare +" WHERE ID_PARCHEGGIO = "+ idPar +"";
		Database.EseguiQuery(aumentoMotoveicolo);
		return false;
	}
	
	//una semplice interrogazione al database, che si occupa di associare id del parcheggio, al parcheggio.
	public static String associaParcheggio(int idPar) throws SQLException
	{
		String parcheggio = "";
		DatabaseSingleton Database = DatabaseSingleton.getInstance();
		
		String associaParcheggio= "SELECT INDIRIZZO FROM PARCHEGGIO JOIN POSTO ON PARCHEGGIO.ID_PARCHEGGIO=POSTO.ID_POSTO WHERE PARCHEGGIO.ID_PARCHEGGIO ="+idPar;
		ResultSet rs = Database.EseguiQuery(associaParcheggio);
		try
		{
			while(rs.next())
			{
				parcheggio = rs.getString(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return parcheggio;
	}
	
	//una fuznione che stampa il costo attuale del parcheggio
	//sempre utile per stampare informazioni a video.
	public static float costoAttuale(int idPar, String categoria) throws SQLException
	{
		float costoAttuale = 0;
		
		if(categoria == "Suv")
		{
			DatabaseSingleton Database = DatabaseSingleton.getInstance();
			
			String stampaCosto = "SELECT COSTO_SUV FROM PARCHEGGIO WHERE ID_PARCHEGGIO = "+idPar+"";
			ResultSet rs = Database.EseguiQuery(stampaCosto);
			try
			{
				while(rs.next())
				{
					costoAttuale = rs.getFloat(1);
					categoria = null;
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		else if(categoria == "Utilitaria")
		{
			DatabaseSingleton Database = DatabaseSingleton.getInstance();
			
			String stampaCosto = "SELECT COSTO_UTILITARIA FROM PARCHEGGIO WHERE ID_PARCHEGGIO = "+idPar+"";
			ResultSet rs = Database.EseguiQuery(stampaCosto);
			try
			{
				while(rs.next())
				{
					costoAttuale = rs.getFloat(1);
					categoria = null;
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		else if(categoria == "Motoveicolo")
		{
			DatabaseSingleton Database = DatabaseSingleton.getInstance();
			
			String stampaCosto = "SELECT COSTO_MOTOVEICOLO FROM PARCHEGGIO WHERE ID_PARCHEGGIO = "+idPar+"";
			ResultSet rs = Database.EseguiQuery(stampaCosto);
			try
			{
				while(rs.next())
				{
					costoAttuale = rs.getFloat(1);
					categoria = null;
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("C'è stato un problema!!");
		}
		
		return costoAttuale;
	}

}
