package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class ConnectDB {
	private static Connection connect = null;
	private String url = "jdbc:mysql://localhost:3306/appbank";
	private String user = "root";
	private String passwd = "";
	
	public ConnectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver O.K.");
			connect = DriverManager.getConnection(url, user, passwd);
			
			System.out.println("Connexion OK");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection initConnection() {
		if(connect==null) {
			new ConnectDB();
		}
		return connect;
	}
	
	//Close Connection
    public static void closeConnection() throws SQLException {
        try {
            if (connect != null && !connect.isClosed()) {
            	connect.close();
            }
        } catch (Exception e){
           throw e;
        }
    }
    
	
}
