package view;

import java.math.BigDecimal;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CreateOperationController {
	
	@FXML
	private TextField tfIdCompte;
	@FXML
	private TextField tfTypeOp;
	@FXML
	private TextField tfMontantOp;
	@FXML
	private Button btOk;
	@FXML
	private Button btCancel;
	
	private Stage dialogStage;
    private boolean okClicked = false;
	
	@FXML
    private void initialize() {
    }
	
	/**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
    	if (isInputValid()) {

            okClicked = true;
            dialogStage.close();
        }
    }
    
    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (tfIdCompte.getText() == null || tfIdCompte.getText().length() == 0) {
            errorMessage += "Numéro Compte invalide!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(tfIdCompte.getText());
            } catch (NumberFormatException e) {
                errorMessage += "N° Compte invalide (caractères numériques uniquement)!\n";
            }
        }
        
        String credit = "credit";
        String debit = "debit";
        if (!tfTypeOp.getText().equals(credit) && !tfTypeOp.getText().equals(debit)) {
            errorMessage += "Type invalide (\"credit\" ou \"debit\" accepté)!\n";
        }
        
        if (tfMontantOp.getText() == null || tfMontantOp.getText().length() == 0) {
            errorMessage += "Montant invalide!\n";
        }else {
            // try to parse the postal code into an int.
            try {
            	new BigDecimal(tfMontantOp.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Montant invalide (caractères numériques uniquement)!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Champs Invalide");
            alert.setHeaderText("Veuillez corriger les champs invalides");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
