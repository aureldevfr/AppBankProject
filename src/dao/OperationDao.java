package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Client;
import model.Operation;

public class OperationDao {
	public ObservableList<Operation> trouverOperation(){
		ObservableList<Operation> lstClients = FXCollections.observableArrayList();
		
		try {
			Connection connect = ConnectDB.initConnection();
			Statement state = connect.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM operation");
			while(result.next()) {
				Operation unOP = new Operation(	result.getInt("idOperation"), 
													result.getBoolean("statutOperation"),
													result.getObject("dateOperation", LocalDate.class),
													result.getString("typeOperation"), 
													result.getBigDecimal("montantOperation"), 
													result.getInt("idCompte"));
				lstClients.add(unOP);
				
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return lstClients;
	}
	
	public ObservableList<Operation> trouverOperationFilter(int numCpt,String dateDeb,String dateFin){
		ObservableList<Operation> lstClients = FXCollections.observableArrayList();
		LocalDate date1 = LocalDate.parse(dateDeb);
		LocalDate date2 = LocalDate.parse(dateFin);
		
		try {
			Connection connect = ConnectDB.initConnection();
			//Statement state = connect.createStatement();
			PreparedStatement stmt;
			if (numCpt == 0){
				stmt = connect.prepareStatement("SELECT * FROM operation WHERE idCompte = idCompte AND dateOperation BETWEEN ? AND ?");
				stmt.setObject(1,date1);
				stmt.setObject(2,date2);
			}else {
				stmt = connect.prepareStatement("SELECT * FROM operation WHERE idCompte = ? AND dateOperation BETWEEN ? AND ?");
				stmt.setInt(1,numCpt);
				stmt.setObject(2,date1);
				stmt.setObject(3,date2);
			}
			
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				Operation unClient = new Operation(	result.getInt("idOperation"), 
													result.getBoolean("statutOperation"),
													result.getObject("dateOperation", LocalDate.class),
													result.getString("typeOperation"), 
													result.getBigDecimal("montantOperation"), 
													result.getInt("idCompte"));
				lstClients.add(unClient);
				
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return lstClients;
	}
	
	public ObservableList<Operation> trouverOpCompte(int idCompte){
		ObservableList<Operation> lstOperations = FXCollections.observableArrayList();
		
		try {
			Connection connect = ConnectDB.initConnection();
			//Statement state = connect.createStatement();
			
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM operation WHERE idCompte = ? ");
			stmt.setInt(1, idCompte);
			ResultSet result = stmt.executeQuery();
			
			//ResultSet result = state.executeQuery("SELECT * FROM operation WHERE idCompte == "+idCompte+"'");
			while(result.next()) {
				Operation unOperation = new Operation(	result.getInt("idOperation"), 
													result.getBoolean("statutOperation"),
													result.getObject("dateOperation", LocalDate.class),
													result.getString("typeOperation"), 
													result.getBigDecimal("montantOperation"),
													result.getInt("idCompte"));
				lstOperations.add(unOperation);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return lstOperations;
	}
	
	public int findIdClientOp(int idCompte){
		int idClient = 0;
		try {
			Connection connect = ConnectDB.initConnection();	
			PreparedStatement stmt = connect.prepareStatement(
						"SELECT client.idClient "
					+ 	"FROM client, compte, operation "
					+ 	"WHERE client.idClient = compte.idClient "
					+ 	"AND compte.idCompte = operation.idCompte "
					+ 	"AND operation.idCompte = ? "
					+ 	"GROUP BY nomclient;");
			stmt.setInt(1, idCompte);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				idClient = result.getInt("idClient");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return idClient;
	}
}
