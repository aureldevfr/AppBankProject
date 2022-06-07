package model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client {
	
	private IntegerProperty idClient;
	private StringProperty nomClient;
	private StringProperty prenomClient;
	private StringProperty adresseClient;
	private StringProperty villeClient;
	private StringProperty mailClient;
	private StringProperty mdpClient;
	private ObjectProperty<LocalDate> dateClient;
	private StringProperty telClient;
	
	// Getters & setters
	/////////////////////idClient////////////////////////////
	public final IntegerProperty idClientProperty() {
		System.out.println("idClientProperty = "+this.idClient);
		return this.idClient;
	}
	public final int getIdClient() {
		return this.idClientProperty().get();
	}
	public final void setIdClient(final int idClient) {
		this.idClientProperty().set(idClient);
	}
	
	/////////////////////nomClient////////////////////////////
	public final StringProperty nomClientProperty() {
		return this.nomClient;
	}
	public final String getNomClient() {
		return this.nomClientProperty().get();
	}
	public final void setNomClient(final String nomClient) {
		this.nomClientProperty().set(nomClient);
	}
	
	/////////////////////prenomClient////////////////////////////
	public final StringProperty prenomClientProperty() {
		return this.prenomClient;
	}
	public final String getPrenomClient() {
		return this.prenomClientProperty().get();
	}
	public final void setPrenomClient(final String prenomClient) {
		this.prenomClientProperty().set(prenomClient);
	}
	
	/////////////////////adresseClient////////////////////////////
	public final StringProperty adresseClientProperty() {
		return this.adresseClient;
	}
	public final String getAdresseClient() {
		return this.adresseClientProperty().get();
	}
	public final void setAdresseClient(final String adresseClient) {
		this.adresseClientProperty().set(adresseClient);
	}
	
	/////////////////////villeClient////////////////////////////
	public final StringProperty villeClientProperty() {
		return this.villeClient;
	}
	public final String getVilleClient() {
		return this.villeClientProperty().get();
	}
	public final void setVilleClient(final String villeClient) {
		this.villeClientProperty().set(villeClient);
	}
	
	/////////////////////mailClient////////////////////////////
	public final StringProperty mailClientProperty() {
		return this.mailClient;
	}
	public final String getMailClient() {
		return this.mailClientProperty().get();
	}
	public final void setMailClient(final String mailClient) {
		this.mailClientProperty().set(mailClient);
	}
	
	/////////////////////mdpClient////////////////////////////
	public final StringProperty mdpClientProperty() {
		return this.mdpClient;
	}
	public final String getMdpClient() {
		return this.mdpClientProperty().get();
	}
	public final void setMdpClient(final String mdpClient) {
		this.mdpClientProperty().set(mdpClient);
	}
	
	/////////////////////dateClient////////////////////////////
	public final ObjectProperty<LocalDate> dateClientProperty() {
		return this.dateClient;
	}
	public final LocalDate getDateClient() {
		return this.dateClientProperty().get();
	}
	public final void setDateClient(final LocalDate dateClient) {
		this.dateClientProperty().set(dateClient);
	}
	
	/////////////////////telClient////////////////////////////
	public final StringProperty telClientProperty() {
		return this.telClient;
	}
	public final String getTelClient() {
		return this.telClientProperty().get();
	}
	public final void setTelClient(final String telClient) {
		this.telClientProperty().set(telClient);
	}
	
	// Constructeurs
		public Client() {}
		
		public Client(	int  idClient,
						String  nomClient,
						String  prenomClient,
						String  adresseClient,
						String  villeClient,
						String  mailClient,
						String  mdpClient,
						LocalDate  dateClient,
						String telClient) {

			this.idClient = new SimpleIntegerProperty(idClient);
			this.nomClient = new SimpleStringProperty(nomClient);
			this.prenomClient = new SimpleStringProperty(prenomClient);
			this.adresseClient = new SimpleStringProperty(adresseClient);
			this.villeClient = new SimpleStringProperty(villeClient);
			this.mailClient = new SimpleStringProperty(mailClient);
			this.mdpClient = new SimpleStringProperty(mdpClient);
			this.dateClient = new SimpleObjectProperty<LocalDate>(dateClient);
			this.telClient = new SimpleStringProperty(telClient);
		}

}
