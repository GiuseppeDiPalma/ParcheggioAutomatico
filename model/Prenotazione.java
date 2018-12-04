package dipalma.parcheggioautomatico.model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Prenotazione {
	
	private int preId;
	private String preCat;
	
	private String dataEntrata;
	private int oraEntrata;
	private String dataUscita;
	private int oraUscita;
	private float costo = (float) 0.0;
	

	public Prenotazione()
	{
		
	}
	
	
	public void setCosto(float costo) {
		this.costo = costo;
	}
	
	
	public float getCosto() {
		return this.costo;
	}
	
	
	
	public void setPre_Id(int preId) {
		this.preId = preId;
	}

	
	public void setPre_Cat(String preCat) 
	{
		this.preCat = preCat;
	}



	
	public void setOraEntrata(int oraEntrata) 
	{
		this.oraEntrata = oraEntrata;
	}
	
	
	public void setDataEntrata(String dataEntrata) 
	{
		this.dataEntrata = dataEntrata;
	}

	
	public void setOraUscita(int oraUscita) 
	{
		this.oraUscita = oraUscita;
	}
	
	
	public void setDataUscita(String dataUscita) 
	{
		this.dataUscita = dataUscita;
	}

	
	public int getPre_Id() 
	{
		return this.preId;
	}

	
	public String getPre_Cat() 
	{
		return this.preCat;
	}



	
	public int getOraEntrata() 
	{
		return this.oraEntrata;
	}
	
	
	public String getDataEntrata() 
	{
		return this.dataEntrata;
	}



	
	public int getOraUscita() 
	{
		return this.oraUscita;
	}

	
	
	public String getDataUscita() 
	{
		return this.dataUscita;
	}
	
	//questa funzione si occupa ti rimuovere una prenotazione dal database.
	public boolean rimuoviPrenotazione(String targa) throws IOException, SQLException 
	{
		
		DatabaseSingleton Database = DatabaseSingleton.getInstance();
		String rimuovi = "DELETE FROM PRENOTAZIONE WHERE M_TARGA_UTENTE = '"+targa+"'";
		Database.EseguiQuery(rimuovi);
		
		return true;
	}
	
	//permette di aggiungere una prenotazione, l'id viene generato random nella view per esigenze di test
	public  boolean AggiungiPrenotazione(int id_pren, String cat_pren, String dataEnt_pren, int oraEntrata_pren, 
			String dataUsc_pren, int oraUscita_pren, float costo, String utenteTarga, int idParcheggio) throws IOException, SQLException 
		{
			DatabaseSingleton Database = DatabaseSingleton.getInstance();

			String aggiungiPrenotazionePROVA = "INSERT INTO PRENOTAZIONE VALUES("+ ""+ id_pren +", '"+ cat_pren +"',"+ " "
					+ ""+ "TO_DATE('"+ dataEnt_pren + " " + oraEntrata_pren +"','YYYY-MM-DD HH24'), "+ "TO_DATE('"+ dataUsc_pren + 
					" " + oraUscita_pren +"','YYYY-MM-DD HH24'),"+ costo +", "+ "'"+ utenteTarga +"', "+ idParcheggio +")";
			Database.EseguiQuery(aggiungiPrenotazionePROVA);		
			return true;
		}
	
	//stampa il costo del suv
	public float costoSuv(int id_par)
	{
		float costoSuv = 0;
		DatabaseSingleton Database = DatabaseSingleton.getInstance();
		String costoAttuale="SELECT COSTO_SUV FROM PARCHEGGIO WHERE ID_PARCHEGGIO = "+id_par;
		ResultSet rs = Database.EseguiQuery(costoAttuale);
		try {
			while(rs.next())
			{
//				System.out.println("costoSuv: "+rs.getInt(1));
				costoSuv = rs.getFloat(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return costoSuv;
	}
	
	//stampo il costo dell'utilitaria
	public float costoUtilitaria(int id_par)
	{
		float costoUtilitaria = 0;
		DatabaseSingleton Database = DatabaseSingleton.getInstance();
		String costoAttuale="SELECT COSTO_UTILITARIA FROM PARCHEGGIO WHERE ID_PARCHEGGIO = "+id_par;
		ResultSet rs = Database.EseguiQuery(costoAttuale);
		try {
			while(rs.next())
			{
//				System.out.println("costoUtilitaria: "+rs.getInt(1));
				costoUtilitaria= rs.getFloat(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return costoUtilitaria;
	}
	
	//stampo il costo del motoveicolo
	public float costoMotoveicolo(int id_par)
	{
		float costoMotoveicolo = 0;
		DatabaseSingleton Database = DatabaseSingleton.getInstance();
		String costoAttuale="SELECT COSTO_MOTOVEICOLO FROM PARCHEGGIO WHERE ID_PARCHEGGIO = "+id_par;
		ResultSet rs = Database.EseguiQuery(costoAttuale);
		try {
			while(rs.next())
			{
//				System.out.println("costoMotoveicolo: "+rs.getInt(1));
				costoMotoveicolo = rs.getFloat(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return costoMotoveicolo;
	}
	
	
	//ritorna un costo in float, prendendo in considerazione la data e l'ora e il tigo veicolo
	//e convertendo calcola la differenza di tempo e stampa il costo.
	public float calcolaCosto(String dataEntrata, String dataUscita, int oraEntrata, int oraUscita, String tipoVeicolo, int id_par)
	{
		float costoSuv = costoSuv(id_par);
		float costoUtilitaria = costoUtilitaria(id_par);
		float costoMotoveicolo = costoMotoveicolo(id_par);
		
		try {
			
	            String dataE = dataEntrata+" "+oraEntrata+":00:00";
	            String dataU = dataUscita+" "+oraUscita+":00:00";

	            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            fmt.setLenient(false);
	
	            //converte le due stringhe
	            Date d1 = fmt.parse(dataE);
	            Date d2 = fmt.parse(dataU);
	
	            // calcola differenza in millisecondi
	            long millisDiff = d2.getTime() - d1.getTime();
	
	            // calcola ore e giorni
	            int hours = (int) (millisDiff / 3600000 % 24);
	            int days = (int) (millisDiff / 86400000);

	            if(tipoVeicolo =="Suv")
		    		{
		    			costo = costoSuv;
		    		}
	    		else if(tipoVeicolo == "Utilitaria")
		    		{
		    			costo = costoUtilitaria;
		    		}
	    		else if(tipoVeicolo == "Motoveicoli")
		    		{
		    			costo = costoMotoveicolo;
		    		}
	    		
	    		costo = (float) (costo + (days * 2.50) + (hours *1.50));
	            
	            
        } 
		catch (Exception e) 
		{
            System.err.println(e);
        }
		
		return costo;
	}
	
	//stampa il costo totale al cliente
	public float costoTotale(String targa)
	{
		float costoTotale = 0;
		DatabaseSingleton Database = DatabaseSingleton.getInstance();
		String costo = "SELECT COSTO_TOTALE FROM PRENOTAZIONE WHERE M_TARGA_UTENTE ='"+targa+"'";
		ResultSet rs = Database.EseguiQuery(costo);
		try {
			while(rs.next())
			{
				costoTotale = rs.getFloat(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return costoTotale;
	}
	
	
	public int parcheggioDaLiberare(String targa)
	{
		int idPar = 0;
		DatabaseSingleton Database = DatabaseSingleton.getInstance();
		String query = "SELECT M_ID_PARCHEGGIO FROM PRENOTAZIONE WHERE M_TARGA_UTENTE='"+targa+"'";
		ResultSet rs = Database.EseguiQuery(query);
		try {
			while(rs.next())
			{
				idPar = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return idPar;
	}
	
	public boolean liberaPosto(String targa)
	{
		DatabaseSingleton Database = DatabaseSingleton.getInstance();
		String rimuovi = "DELETE FROM PRENOTAZIONE WHERE TARGA='"+targa+"'";
		Database.EseguiQuery(rimuovi);
		return true;
	}
	
	
	
	public boolean pagamento(PagamentoStrategy metodoPagamento)
	{
		metodoPagamento.pagamento(costo);
		return true;
	}
	
}
