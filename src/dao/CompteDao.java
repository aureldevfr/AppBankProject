package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Client;
import model.Compte;

public class CompteDao {
	public ObservableList<Compte> trouverCompte(int idCompte){
		ObservableList<Compte> lstComptes = FXCollections.observableArrayList();
		
		try {
			Connection connect = ConnectDB.initConnection();
			//Statement state = connect.createStatement();
			
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM compte WHERE idCompte = ? ");
			stmt.setInt(1, idCompte);
			ResultSet result = stmt.executeQuery();
			
			//ResultSet result = state.executeQuery("SELECT * FROM compte");
			while(result.next()) {
				Compte unClient = new Compte(	result.getInt("idCompte"), 
												result.getObject("dateCompte", LocalDate.class),
												result.getBoolean("etatCompte"), 
												result.getBigDecimal("soldeCompte"), 
												result.getBigDecimal("limiteCompte"),
												result.getInt("idClient"));
				lstComptes.add(unClient);
				
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return lstComptes;
	}
	
	public BigDecimal findSoldeCompte(int idCompte){
		BigDecimal soldeCompte = null;
		try {
			Connection connect = ConnectDB.initConnection();	
			PreparedStatement stmt = connect.prepareStatement(
						"SELECT soldeCompte "
					+ 	"FROM compte "
					+ 	"WHERE idCompte = ? ");
			stmt.setInt(1, idCompte);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				soldeCompte = result.getBigDecimal("soldeCompte");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("idCompte = "+idCompte);
		return soldeCompte;
	}
	
	public Compte createCompteDB(Compte compte){
		Boolean rep = false;
		Compte unCompte = null;
		
		try {
			Connection connect = ConnectDB.initConnection();
			PreparedStatement stmt = connect.prepareStatement(
					"INSERT INTO compte ("
					+ "	idCompte, "
					+ "	dateCompte,"
					+ "	etatCompte,"
					+ "	soldeCompte, "
					+ "	limiteCompte,"
					+ "	idClient)	"
					+ "VALUES"
					+ "(?, ?, ?, ?, ?, ?)");
			stmt.setInt(1,compte.getIdCompte()); 
			stmt.setObject(2,compte.getDateCompte());
			stmt.setBoolean(3,compte.getEtatCompte());
			stmt.setBigDecimal(4,compte.getSoldeCompte()); 
			stmt.setBigDecimal(5,compte.getLimiteCompte()); 
			stmt.setInt(6,compte.getIdClient()); 
			
			int result = stmt.executeUpdate();
			System.out.println("A new user was inserted successfully!");
			unCompte = compte;
		}catch(Exception e) {
			e.printStackTrace();
		}
		//System.out.println("WALLLLLLLLA = "+unCompte.getNomClient());
		return unCompte;
	}
	
	public Compte modifyCompteDB(Compte compte){
		Boolean rep = false;
		Compte unCompte = null;
		
		try {
			Connection connect = ConnectDB.initConnection();
			
			/*UPDATE table
			SET nom_colonne_1 = 'nouvelle valeur'
			WHERE condition*/
			
			PreparedStatement stmt = connect.prepareStatement(
					"UPDATE compte SET "
					+ "limiteCompte = ? "
					+ "WHERE idCompte = ?");
			stmt.setBigDecimal(1,compte.getLimiteCompte());
			stmt.setInt(2,compte.getIdCompte());
	
			int result = stmt.executeUpdate();
			System.out.println("User modify");
			//System.out.println("TELmodify = "+ client.getTelClient());
			unCompte = compte;
		}catch(Exception e) {
			e.printStackTrace();
		}
		//System.out.println("WALLLLLLLLA = "+unClient.getNomClient());
		return unCompte;
	}
}
