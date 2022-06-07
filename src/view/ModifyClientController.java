package view;

import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

import dao.ClientDao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Client;
import util.DateUtil;

public class ModifyClientController {
	
	@FXML
	private TextField tfNomClient;
	@FXML
	private TextField tfPrenomClient;
	@FXML
	private TextField tfAdresseClient;
	@FXML
	private TextField tfEmailClient;
	@FXML
	private TextField tfDateClient;
	@FXML
	private TextField tfTelClient;
	@FXML
	private TextField tfVilleClient;
	@FXML
	private Button btOk;
	@FXML
	private Button btCancel;
	
	private boolean okClicked = false;
    private ClientDao clientDao = new ClientDao();
    private AccueilController accueil = new AccueilController();
    
	
    private Stage dialogStage;
    private Client client;
    private Client clientModify;
    
    
    public void test() {
    	//ObservableList<Client> vv = accueil.getPersonData();
    	//System.out.println("vv size = "+vv.size());
    	//accueil.test();
    }
    
	@FXML
    private void initialize() {
		//System.out.println("clientNOM1 = "+client.getNomClient());
    }
	
	
	public void setClient(Client client) {
        this.client = client;

        tfNomClient.setText(client.getNomClient());
        tfPrenomClient.setText(client.getPrenomClient());
        tfAdresseClient.setText(client.getAdresseClient());
        tfVilleClient.setText(client.getVilleClient());
        tfEmailClient.setText(client.getMailClient());
        tfDateClient.setText(client.getDateClient().toString());
        tfTelClient.setText(client.getTelClient());
        
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
    	System.out.println("isOkClicked()");
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
    		
    		client = new Client(		client.getIdClient(),
        								tfNomClient.getText(),
        								tfPrenomClient.getText(),
        								tfAdresseClient.getText(),
        								tfVilleClient.getText(),
        								tfEmailClient.getText(),
        								null,
        								LocalDate.parse(tfDateClient.getText()),
        								tfTelClient.getText());
        	
    		clientModify = clientDao.modifyClientDB(client);
    		
    		//accueil.showClientDetails(clientModify);
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

        if (tfNomClient.getText() == null || tfNomClient.getText().length() == 0) {
            errorMessage += "Nom invalide!\n";
        }
        if (tfPrenomClient.getText() == null || tfPrenomClient.getText().length() == 0) {
            errorMessage += "Prenom invalide!\n";
        }
        if (tfAdresseClient.getText() == null || tfAdresseClient.getText().length() == 0) {
            errorMessage += "Adresse invalide!\n";
        }
        
        if (tfVilleClient.getText() == null || tfVilleClient.getText().length() == 0) {
            errorMessage += "Ville invalide!\n";
        }

        if (tfEmailClient.getText() == null || tfEmailClient.getText().length() == 0) {
            errorMessage += "Email invalide!\n";
        }

        if (tfDateClient.getText() == null || tfDateClient.getText().length() == 0) {
            errorMessage += "Date invalide!\n";
        } else {
            if (!DateUtil.isValidFormat("yyyy-MM-dd", tfDateClient.getText(), Locale.ENGLISH)) {
                errorMessage += "Date invalide. Utiliser le format aaaa-mm-jj!\n";
            }
        }
        
        if (tfTelClient.getText() == null || tfTelClient.getText().length() == 0) {
            errorMessage += "Tel invalide!\n";
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
