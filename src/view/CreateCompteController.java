package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Client;
import model.Compte;
import util.UniqueIdUtil;

import java.math.BigDecimal;
import java.time.LocalDate;

import dao.ClientDao;
import dao.CompteDao;

public class CreateCompteController {
	
	@FXML
	private TextField tfIdClient;
	@FXML
	private TextField tfLimitCompte;
	@FXML
	private Button btOk;
	@FXML
	private Button btCancel;
	
	private Stage dialogStage;
    private boolean okClicked = false;
    
    private ClientDao clientDao = new ClientDao();
    private CompteDao compteDao = new CompteDao();
    private AccueilController accueil = new AccueilController();
    
    private Compte compte;
	
	@FXML
    private void initialize() {
    }
	
	public void setCompte(Compte compte) {
        this.compte = compte;
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
    		
    		int uniqueID = UniqueIdUtil.generateUniqueId();
    		
    		compte = new Compte(uniqueID,
			    				LocalDate.now(),
								true,
								new BigDecimal(0),
								new BigDecimal(tfLimitCompte.getText()),
								Integer.parseInt(tfIdClient.getText()));
    		
    		compteDao.createCompteDB(compte);
    		
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

        if (tfIdClient.getText() == null || tfIdClient.getText().length() == 0) {
            errorMessage += "Numéro Client invalide!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(tfIdClient.getText());
                if(!clientDao.ifClientExist(Integer.parseInt(tfIdClient.getText()))) {
                	errorMessage += "Client n'existe pas!\n";
                };
            } catch (NumberFormatException e) {
                errorMessage += "N° Client invalide (caractères numériques uniquement)!\n";
            }
            
        }
        
        if (tfLimitCompte.getText() == null || tfLimitCompte.getText().length() == 0) {
            errorMessage += "Limite invalide!\n";
        }else {
            // try to parse the postal code into an int.
            try {
            	new BigDecimal(tfLimitCompte.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Limite retrait invalide (caractères numériques uniquement)!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Champs Invalides");
            alert.setHeaderText("Veuillez corriger les champs invalides");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
