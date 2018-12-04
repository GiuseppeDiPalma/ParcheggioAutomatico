package dipalma.parcheggioautomatico.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import dipalma.parcheggioautomatico.model.DatabaseSingleton;
import dipalma.parcheggioautomatico.model.Parcheggio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class Edge {

	private int numeroNodo;
	  private int fromNodeIndex;
	  private int toNodeIndex;
	  private int length;

public Edge(int fromNodeIndex, int toNodeIndex, int length) 
{
	 
  this.fromNodeIndex = fromNodeIndex;
  this.toNodeIndex = toNodeIndex;
  this.length = length;
}

public int getNumeroNodo()
{
	  return numeroNodo;
}

public int getFromNodeIndex() 
{
  return fromNodeIndex;
}

public int getToNodeIndex() 
{
  return toNodeIndex;
}

public int getLength() 
{
  return length;
}

// determines the neighbouring node of a supplied node, based on the two nodes connected by this edge
public int getNeighbourIndex(int nodeIndex) 
{
  if (this.fromNodeIndex == nodeIndex) 
  {
    return this.toNodeIndex;
  } 
  else 
  {
    return this.fromNodeIndex;
  }
}
	
	
	
	
	
	
	
	
	
	  
	  
	  public static Edge[] caricaArchiTutti() {
		    DatabaseSingleton Database = DatabaseSingleton.getInstance();
		    String caricaarco = "SELECT PRIMO_POSTO, SECONDO_POSTO, DISTANZA FROM ARCO";
		    ResultSet rs = Database.EseguiQuery(caricaarco);
		    
		    List<Edge> listArchi = FXCollections.observableArrayList();

		    try 
		    {	
		      while(rs.next()) 
		      {
		    	  listArchi.add(new Edge(rs.getInt(1),rs.getInt(2),rs.getInt(3)));
		      }
		    } catch (SQLException e) 
		    {
		      e.printStackTrace();
		    }

		    Edge[] array = listArchi.toArray(new Edge[listArchi.size()]);
		    
		    return array;
		    
		    
		 }
	  
	  public static Edge[] caricaArchiParcheggi() {
		  DatabaseSingleton Database = DatabaseSingleton.getInstance();
		  String caricaArco = "SELECT * FROM ARCO WHERE PRIMO_POSTO > 13 OR SECONDO_POSTO > 13";
		  ResultSet rs = Database.EseguiQuery(caricaArco);
		  
		  List<Edge> listaArchi = FXCollections.observableArrayList();
		  
		  try
		  {
			  while(rs.next())
			  {
				  listaArchi.add(new Edge(rs.getInt(1),rs.getInt(2),rs.getInt(3)));
			  }
		  }catch(SQLException e)
		  {
			  e.printStackTrace();
		  }
		  
		  Edge[] array = listaArchi.toArray(new Edge[listaArchi.size()]);
		  return array;
	  }
	  
	  public static ObservableList<Parcheggio> selezionaParcheggio() throws SQLException 
		{
			
			ObservableList <Parcheggio> parcheggi = FXCollections.observableArrayList();
			DatabaseSingleton Database = DatabaseSingleton.getInstance();
			
			String stampaLocalita = "SELECT ID_PARCHEGGIO FROM PARCHEGGIO";
			ResultSet rs = Database.EseguiQuery(stampaLocalita);
			try 
			{
				while(rs.next())
				{
//					System.out.println(rs.getInt(1));
					parcheggi.add(new Parcheggio(rs.getInt(1)));
				}
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
			return parcheggi;
			
		}
		 
}
