package model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Compte {
	private IntegerProperty idCompte;
	private ObjectProperty<LocalDate> dateCompte;
	private BooleanProperty etatCompte;
	private ObjectProperty<BigDecimal> soldeCompte;
	private ObjectProperty<BigDecimal> limiteCompte;
	private IntegerProperty idClient;
	
	// Getters & setters
	/////////////////////idCompte////////////////////////////
	public final IntegerProperty idCompteProperty() {
		return this.idCompte;
	}
	public final int getIdCompte() {
		return this.idCompteProperty().get();
	}
	public final void setIdCompte(final int idCompte) {
		this.idCompteProperty().set(idCompte);
	}
	
	/////////////////////dateCompte////////////////////////////
	public final ObjectProperty<LocalDate> dateCompteProperty() {
		return this.dateCompte;
	}
	public final LocalDate getDateCompte() {
		return this.dateCompteProperty().get();
	}
	public final void setDateCompte(final LocalDate dateCompte) {
		this.dateCompteProperty().set(dateCompte);
	}
	
	/////////////////////etatCompte////////////////////////////
	public final BooleanProperty etatCompteProperty() {
		return this.etatCompte;
	}
	public final Boolean getEtatCompte() {
		return this.etatCompteProperty().get();
	}
	public final void setEtatCompte(final Boolean etatCompte) {
		this.etatCompteProperty().set(etatCompte);
	}
	
	/////////////////////soldeCompte////////////////////////////
	public final ObjectProperty<BigDecimal> soldeCompteProperty() {
		return this.soldeCompte;
	}
	public final BigDecimal getSoldeCompte() {
		return this.soldeCompteProperty().get();
	}
	public final void setSoldeCompte(final BigDecimal soldeCompte) {
		this.soldeCompteProperty().set(soldeCompte);
	}
	
	/////////////////////limiteCompte////////////////////////////
	public final ObjectProperty<BigDecimal> limiteCompteProperty() {
		return this.limiteCompte;
	}
	public final BigDecimal getLimiteCompte() {
		return this.limiteCompteProperty().get();
	}
	public final void setLimiteCompte(final BigDecimal limiteCompte) {
		this.limiteCompteProperty().set(limiteCompte);
	}
	
	/////////////////////idClient////////////////////////////
	public final IntegerProperty idClientProperty() {
		return this.idClient;
	}
	public final int getIdClient() {
		return this.idClientProperty().get();
	}
	public final void setIdClient(final int idClient) {
		this.idClientProperty().set(idClient);
	}
	
	// Constructeurs
	public Compte() {}
	
	public Compte(	int idCompte,
						LocalDate dateCompte,
						Boolean etatCompte,
						BigDecimal soldeCompte,
						BigDecimal limiteCompte,
						int idClient) {

		this.idCompte = new SimpleIntegerProperty(idCompte);
		this.dateCompte = new SimpleObjectProperty<LocalDate>(dateCompte);
		this.etatCompte = new SimpleBooleanProperty(etatCompte);
		this.soldeCompte = new SimpleObjectProperty<BigDecimal>(soldeCompte);
		this.limiteCompte = new SimpleObjectProperty<BigDecimal>(limiteCompte);
		this.idClient = new SimpleIntegerProperty(idClient);
	}
}
