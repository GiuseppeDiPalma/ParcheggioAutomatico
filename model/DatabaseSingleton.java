package dipalma.parcheggioautomatico.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSingleton 
{
	private static DatabaseSingleton instance;
	private static String db_url;
	private static String db_user;
	private static String db_password;
	private static String driver;
	private Connection connection;
	private Statement statement;
	public Statement getStatement() {return statement;}
	
	private DatabaseSingleton()
	{
		db_url = "jdbc:oracle:thin:@localhost";
		db_user = "db_parcheggio";
		db_password = "parcheggio";
		driver = "oracle.jdbc.driver.OracleDriver";
		getConnect();
	}
	
	public static synchronized DatabaseSingleton getInstance()
	{
		if(instance == null)
		{
			instance = new DatabaseSingleton();
		}
		return instance;
	}
	
	private void getConnect()
	{
		if(connection == null || statement == null)
		{
			try 
			{
				Class.forName(driver).newInstance();
				connection = DriverManager.getConnection(db_url, db_user, db_password);
				statement = connection.createStatement();
			} 
			catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) 
			{
				e.printStackTrace();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public void ChiudiConnessione()
	{
		connection = null;
		statement = null;
	}
	
	public int EseguiQueryUpdate(String stringa)
	{
		try
		{
			return statement.executeUpdate(stringa);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public ResultSet EseguiQuery(String query)
	{
		try 
		{	
			return statement.executeQuery(query);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}
