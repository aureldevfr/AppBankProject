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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Operation {
	
	private IntegerProperty idOperation;
	private BooleanProperty statutOperation;
	private ObjectProperty<LocalDate> dateOperation;
	private StringProperty typeOperation;
	private ObjectProperty<BigDecimal> montantOperation;
	private IntegerProperty idCompte;
	
	
	// Getters & setters
	/////////////////////idOperation////////////////////////////
	public final IntegerProperty idOperationProperty() {
	return this.idOperation;
	}
	public final int getIdOperation() {
	return this.idOperationProperty().get();
	}
	public final void setIdOperation(final int idOperation) {
	this.idOperationProperty().set(idOperation);
	}
	
	/////////////////////statutOperation////////////////////////////
	public final BooleanProperty statutOperationProperty() {
		return this.statutOperation;
	}
	public final Boolean getStatutOperation() {
		return this.statutOperationProperty().get();
	}
	public final void setStatutOperation(final Boolean statutOperation) {
		this.statutOperationProperty().set(statutOperation);
	}

	/////////////////////dateOperation////////////////////////////
	public final ObjectProperty<LocalDate> dateOperationProperty() {
		return this.dateOperation;
	}
	public final LocalDate getDateOperation() {
		return this.dateOperationProperty().get();
	}
	public final void setDateOperation(final LocalDate dateOperation) {
		this.dateOperationProperty().set(dateOperation);
	}
	

	
	/////////////////////typeOperation////////////////////////////
	public final StringProperty typeOperationProperty() {
		return this.typeOperation;
	}
	public final String getTypeOperation() {
		return this.typeOperationProperty().get();
	}
	public final void setTypeOperation(final String typeOperation) {
		this.typeOperationProperty().set(typeOperation);
	}
	
	/////////////////////montantOperation////////////////////////////
	public final ObjectProperty<BigDecimal> montantOperationProperty() {
		return this.montantOperation;
	}
	public final BigDecimal getMontantOperation() {
		return this.montantOperationProperty().get();
	}
	public final void setMontantOperation(final BigDecimal montantOperation) {
		this.montantOperationProperty().set(montantOperation);
	}
	
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

	
	// Constructeurs
	public Operation() {}
	
	public Operation(	int idOperation,
						Boolean statutOperation,
						LocalDate dateOperation,
						String typeOperation,
						BigDecimal montantOperation,
						int idCompte) {

		this.idOperation = new SimpleIntegerProperty(idOperation);
		this.statutOperation = new SimpleBooleanProperty(statutOperation);
		this.dateOperation = new SimpleObjectProperty<LocalDate>(dateOperation);
		this.typeOperation = new SimpleStringProperty(typeOperation);
		this.montantOperation = new SimpleObjectProperty<BigDecimal>(montantOperation);
		this.idCompte = new SimpleIntegerProperty(idCompte);
	}
}
