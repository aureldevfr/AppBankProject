package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Client;

public class ClientDao {
	public ObservableList<Client> trouverClients(){
		ObservableList<Client> lstClients = FXCollections.observableArrayList();
		
		try {
			Connection connect = ConnectDB.initConnection();
			Statement state = connect.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM client");
			while(result.next()) {
				Client unClient = new Client(	result.getInt("idClient"), 
												result.getString("nomClient"), 
												result.getString("prenomClient"), 
												result.getString("adresseClient"), 
												result.getString("villeClient"), 
												result.getString("mailClient"), 
												result.getString("mdpClient"),
												result.getObject("dateClient", LocalDate.class), 
												result.getString("telClient"));
				lstClients.add(unClient);
				
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return lstClients;
	}
	
	public Client findClientPerId(int idClient){
		Client unClient = null;
		
		try {
			Connection connect = ConnectDB.initConnection();
			PreparedStatement stmt = connect.prepareStatement(
						"SELECT * "
					+ 	"FROM client, compte, operation "
					+ 	"WHERE client.idClient = compte.idClient "
					+ 	"AND compte.idCompte = operation.idCompte "
					+ 	"AND operation.idCompte = ? "
					+ 	"GROUP BY nomclient;");
			stmt.setInt(1, idClient);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				unClient = new Client(	result.getInt("idClient"), 
										result.getString("nomClient"), 
										result.getString("prenomClient"), 
										result.getString("adresseClient"), 
										result.getString("villeClient"), 
										result.getString("mailClient"), 
										result.getString("mdpClient"),
										result.getObject("dateClient", LocalDate.class), 
										result.getString("telClient"));
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return unClient;
	}
	
	public Client createClientDB(Client client){
		Boolean rep = false;
		Client unClient = null;
		
		try {
			Connection connect = ConnectDB.initConnection();
			PreparedStatement stmt = connect.prepareStatement(
					"INSERT INTO client ("
					+ "	idClient, "
					+ "	nomClient,"
					+ "	prenomClient,"
					+ "	adresseClient, "
					+ "	villeClient,"
					+ "	mailClient, "
					+ "	mdpClient, "
					+ "	dateClient, "
					+ "	telClient)	"
					+ "VALUES"
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setInt(1,client.getIdClient()); 
			stmt.setString(2,client.getNomClient());
			stmt.setString(3,client.getPrenomClient());
			stmt.setString(4,client.getAdresseClient()); 
			stmt.setString(5,client.getVilleClient()); 
			stmt.setString(6,client.getMailClient()); 
			stmt.setString(7,client.getMdpClient()); 
			stmt.setObject(8,client.getDateClient()); 
			stmt.setString(9,client.getTelClient());
			int result = stmt.executeUpdate();
			System.out.println("A new user was inserted successfully!");
			unClient = client;
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("WALLLLLLLLA = "+unClient.getNomClient());
		return unClient;
	}
	
	public Client modifyClientDB(Client client){
		Boolean rep = false;
		Client unClient = null;
		
		try {
			Connection connect = ConnectDB.initConnection();
			
			/*UPDATE table
			SET nom_colonne_1 = 'nouvelle valeur'
			WHERE condition*/
			
			PreparedStatement stmt = connect.prepareStatement(
					"UPDATE client SET "
					+ "	nomClient = ?,"
					+ "	prenomClient = ?,"
					+ "	adresseClient = ?, "
					+ "	villeClient = ?,"
					+ "	mailClient = ?, "
					//+ "	mdpClient = ?, "
					+ "	dateClient = ?, "
					+ "	telClient = ?	"
					+ "WHERE idClient = ?");
			stmt.setString(1,client.getNomClient());
			stmt.setString(2,client.getPrenomClient());
			stmt.setString(3,client.getAdresseClient()); 
			stmt.setString(4,client.getVilleClient()); 
			stmt.setString(5,client.getMailClient()); 
			stmt.setObject(6,client.getDateClient()); 
			stmt.setString(7,client.getTelClient());
			stmt.setInt(8,client.getIdClient());
			int result = stmt.executeUpdate();
			System.out.println("User modify");
			System.out.println("TELmodify = "+ client.getTelClient());
			unClient = client;
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("WALLLLLLLLA = "+unClient.getNomClient());
		return unClient;
	}
	
	public boolean ifClientExist(int idClient){
		Boolean rep = false;
		int res = 0;
		Client unClient = null;
		List idList = new ArrayList(); 
		
		try {
			Connection connect = ConnectDB.initConnection();
			
			/*UPDATE table
			SET nom_colonne_1 = 'nouvelle valeur'
			WHERE condition*/
			
			PreparedStatement stmt = connect.prepareStatement(
					"SELECT COUNT(1) "
					+ "FROM client "
					+ "WHERE idClient = ?");
			stmt.setInt(1,idClient);
			
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				res = result.getInt(1);
			}
			
			System.out.println("result = "+res);
			
			if(res > 0) {
				rep = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rep;
	}

}
