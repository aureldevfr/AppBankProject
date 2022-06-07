package model;




import java.time.LocalDate;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Test {
	
	// Declaration des attributs d'un objet de type Client
	private IntegerProperty idClient;
	private StringProperty nomClient;
	private StringProperty prenomClient;
	private ObjectProperty<LocalDate> dateClient;
	
	// Getters & setters
	public final IntegerProperty idClientProperty() {
		return this.idClient;
	}
	public final int getIdClient() {
		return this.idClientProperty().get();
	}
	public final void setIdClient(final int idClient) {
		this.idClientProperty().set(idClient);
	}
	
	public final StringProperty nomClientProperty() {
		return this.nomClient;
	}
	public final String getNomClient() {
		return this.nomClientProperty().get();
	}
	
	public final void setNomClient(final String nomClient) {
		this.nomClientProperty().set(nomClient);
	}
	
	public final StringProperty prenomClientProperty() {
		return this.prenomClient;
	}
	public final String getPrenomClient() {
		return this.prenomClientProperty().get();
	}
	public final void setPrenomClient(final String prenomClient) {
		this.prenomClientProperty().set(prenomClient);
	}
	
	public final ObjectProperty<LocalDate> dateClientProperty() {
		return this.dateClient;
	}
	public final LocalDate getDateClient() {
		return this.dateClientProperty().get();
	}
	
	public final void setDateClient(final LocalDate dateClient) {
		this.dateClientProperty().set(dateClient);
	}
	// Constructeurs
	public Test() {
	
	}
	public Test(int idClient, String nomClient, String prenomClient, LocalDate dateClient) {

		this.idClient = new SimpleIntegerProperty(idClient);
		this.nomClient = new SimpleStringProperty(nomClient);
		this.prenomClient = new SimpleStringProperty(prenomClient);
		this.dateClient = new SimpleObjectProperty<LocalDate>(dateClient);
	}

}
