package CONNECTION;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.*;

import com.sun.corba.se.spi.presentation.rmi.PresentationManager.ClassData;

import java.sql.Connection;

public class MySqlConnection 
{
	
	
	private static String dbUrl = "jdbc:mysql://localhost:3306/activation_code";
	private static String dbUserName = "root";
	private static String dbPassword = "123456";
	
	public static Connection getConnection() throws SQLException
	{
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
	}
}
