package view;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.function.Predicate;

import ch.makery.appbankfx.MainApp;
import dao.ClientDao;
import dao.CompteDao;
import dao.OperationDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Client;
import model.Compte;
import model.Operation;
import model.Test;

public class AccueilController {
	
	/////////////////Client/////////////////////
	@FXML
	private TableView<Client> tbClient;
	@FXML
	private TableColumn<Client, String> clNomClient;
	@FXML
	private TableColumn<Client, String> clPrenomClient;
	@FXML
	private TableColumn<Client, String> clVilleClient;
	@FXML
	private TableColumn<Client, String> clAdresseClient;
	@FXML
	private TextField tfNomClient;
	@FXML
	private TextField tfPrenomClient;
	@FXML
	private TextField tfVilleClient;
	@FXML
	private Button btFindClient;
	@FXML
	private Button btModifyClient;
	@FXML
    private Label lbNomClient;
    @FXML
    private Label lbPrenomClient;
    @FXML
    private Label lbAdresseClient;
    @FXML
    private Label lbVilleClient;
    @FXML
    private Label lbEmailClient;
    @FXML
    private Label lbTelClient;
    @FXML
    private Label lbDateClient;
   
	//public ObservableList<Client> lstClients = FXCollections.observableArrayList();
	public ObservableList<Client> lstClients2 = FXCollections.observableArrayList();
	
	/////////////////Compte/////////////////////
	@FXML
	private TextField tfNumCompte;
	@FXML
	private Button btFindCompte;
	@FXML
	private Button btModifyCompte;
	@FXML
	private Button btNewCompte;
	@FXML
    private Label lbNumCompte;
    @FXML
    private Label lbProprioCompte;
    @FXML
    private Label lbDateCompte;
    @FXML
    private Label lbSoldeCompte;
    @FXML
    private Label lbLimiteCompte;
    
    private ObservableList<Compte> lstCompte = FXCollections.observableArrayList();
    private Compte selectedCompte = null;
	
	/////////////////Opération/////////////////////
    @FXML
	private TableView<Operation> tbOp;
	@FXML
	private TableColumn<Operation, Number> clNumOp;
	@FXML
	private TableColumn<Operation, String> clTypeOp;
	@FXML
	private TableColumn<Operation, BigDecimal> clMontantOp;
	@FXML
	private TableColumn<Operation, LocalDate> clDateOp;
	@FXML
	private TableColumn<Operation, Number> clNumCompte;
	@FXML
	private TextField tfNumCompteOp;
	//////////////////////////////
	@FXML
	private DatePicker tfDateDebut;
	@FXML
	private DatePicker tfDateFin;
	@FXML
	private Button btFindByDate;
	@FXML
	private Button btDate;
	//////////////////////////////
	@FXML
	private Button btFindOp;
	@FXML
	private Button btNewOp;
	@FXML
    private Label lbNomProprioOp;
    @FXML
    private Label lbPrenomProprioOp;
    @FXML
    private Label lbVilleProprioOp;
    @FXML
    private Label lbSoldeProprioOp;

    private ObservableList<Operation> lstOp = FXCollections.observableArrayList();
 
    private CompteDao compteDao = new CompteDao();
    private ClientDao clientDao = new ClientDao();
    private OperationDao operationDao = new OperationDao();
    
    
    // Reference to the main application.
    private MainApp mainApp;
    
    @FXML
	private void testDate() {
		System.out.println("testDate()");
		
		
		
	}

	public AccueilController() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        tbClient.setItems(mainApp.getPersonData());
    }
	
	@FXML
    private void initialize() {
		
		/////////////////Client/////////////////////
    	// Initialize the person table with the two columns.
		clNomClient.setCellValueFactory(cellData -> cellData.getValue().nomClientProperty());
		clPrenomClient.setCellValueFactory(cellData -> cellData.getValue().prenomClientProperty());
		clVilleClient.setCellValueFactory(cellData -> cellData.getValue().villeClientProperty());
		clAdresseClient.setCellValueFactory(cellData -> cellData.getValue().adresseClientProperty());
		// Clear Client details.
		showClientDetails(null);
	    // Listen for selection changes and show the Client details when changed.
	    tbClient.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> showClientDetails(newValue));
	    
	    
	    /////////////////Compte/////////////////////
		// Clear Compte details.
		showCompteDetails(null);
	    // Listen for selection changes and show the Compte details when changed.
	    /*tbCompte.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> showCompteDetails(newValue));*/
	    
		
	    /////////////////Opération/////////////////////
	    // Initialize the person table with the two columns.
		clNumCompte.setCellValueFactory(cellData -> cellData.getValue().idCompteProperty());
	    clNumOp.setCellValueFactory(cellData -> cellData.getValue().idOperationProperty());
	    clTypeOp.setCellValueFactory(cellData -> cellData.getValue().typeOperationProperty());
	    clMontantOp.setCellValueFactory(cellData -> cellData.getValue().montantOperationProperty());
	    clDateOp.setCellValueFactory(cellData -> cellData.getValue().dateOperationProperty());
		// Clear Opération details.
		showOperationDetails(0);
	    // Listen for selection changes and show the Opération details when changed.
	    /*tbOp.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> showOperationDetails(newValue));*/
    }
	
	
	
	/////////////////Client/////////////////////
	
	private void showClientDetails(Client person) {
	    if (person != null) {
	        // Fill the labels with info from the person object.
	    	lbNomClient.setText(person.getNomClient());
	    	lbPrenomClient.setText(person.getPrenomClient());
	    	lbAdresseClient.setText(person.getAdresseClient());
	    	lbVilleClient.setText(person.getVilleClient());
	    	lbEmailClient.setText(person.getMailClient());
	    	lbTelClient.setText(person.getTelClient());
	    	lbDateClient.setText(person.getDateClient().toString());
	       
	        // TODO: We need a way to convert the birthday into a String!
	        // birthdayLabel.setText(...);
	    } else {
	        // Person is null, remove all the text.
	    	lbNomClient.setText("");
	    	lbPrenomClient.setText("");
	    	lbAdresseClient.setText("");
	    	lbVilleClient.setText("");
	    	lbEmailClient.setText("");
	    	lbTelClient.setText("");
	    	lbDateClient.setText("");
	    }
	}
	
	/*public void fillTabClient() {
		ClientDao cldao = new ClientDao();
		lstClients = cldao.trouverClients();
		tbClient.setItems(lstClients);
		System.out.println("lstClients size = "+lstClients.size());
		
	}*/
	
	
	public void test() {
		//lstClients.add(new Client());
		//System.out.println("lstClients size = "+lstClients.size());
	}
	
	
	
	public void addClientList(Client client) {
		
		System.out.println("client = "+client);
		System.out.println("client = "+client.getMailClient());
		//lstClients.add(client);
		//System.out.println("lstClients size = "+this.lstClients.size());
	}
	
	
	@FXML
	private void clickFindButton() {
		System.out.println("clickFindButton()");
		
		String nom = tfNomClient.getText();
		String prenom = tfPrenomClient.getText();
		String ville = tfVilleClient.getText();
		
		nom = nom.replaceAll("\\s", "");
		prenom = prenom.replaceAll("\\s", "");
		
		if( nom == "") {
			System.out.println("rien dedans");
		}
		
		System.out.println("nom = ["+ nom+"]");
		System.out.println("prenom = ["+ prenom+"]");
		
		//Création du filtre
		FilteredList<Client> items = new FilteredList<>(mainApp.getPersonData());
		tbClient.setItems(items);
		
		 if( nom == "" && prenom == "" && ville == "") {
			 items.setPredicate(null);
		 }else {
			 Predicate<Client> containsNom = i -> i.getNomClient().contains(tfNomClient.getText().toUpperCase());
			 Predicate<Client> containsPrenom = i -> i.getPrenomClient().contains(tfPrenomClient.getText());
			 Predicate<Client> containsVille = i -> i.getVilleClient().contains(tfVilleClient.getText().toUpperCase());
			 Predicate<Client> filter = containsNom.and(containsPrenom).and(containsVille);
			 
			 items.setPredicate(filter);
		 }
		 
	}
	
	@FXML
	private void createClient() {
		System.out.println("createClient()");
		
		Client client = new Client();
	    boolean okClicked = mainApp.displayCreateClientWindow(client);
	    if (okClicked) {
	    	System.out.println("Ca clique OK");
	        mainApp.getPersonData().add(client);
	        System.out.println("okClicked client = "+ client);
	        
	        mainApp.loadClientdata();
			tbClient.setItems(mainApp.getPersonData());
	        
	    }
		
	}
	
	@FXML
	private void modifyClient() {
		System.out.println("modifyClient()");
		
		//boolean okClicked = mainApp.displayModifyClientWindow();
		
		Client client = tbClient.getSelectionModel().getSelectedItem();
	    if (client != null) {
	        boolean okClicked = mainApp.displayModifyClientWindow(client);
	        if (okClicked) {
	        	
	        	mainApp.loadClientdata();
				tbClient.setItems(mainApp.getPersonData());
				
	        	showClientDetails(client);
	        }

	    } else {
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainApp.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Person Selected");
	        alert.setContentText("Please select a person in the table.");

	        alert.showAndWait();
	    }
		
	}
	
	/////////////////Compte/////////////////////
	private void showCompteDetails(Compte compte) {
	    if (compte != null) {
	        // Fill the labels with info from the person object.
	    	lbNumCompte.setText(Integer.toString(compte.getIdCompte()));
	    	lbProprioCompte.setText(Integer.toString(compte.getIdClient()));
	    	lbDateCompte.setText(compte.getDateCompte().toString());
	    	lbSoldeCompte.setText(compte.getSoldeCompte().toString());
	    	lbLimiteCompte.setText(compte.getLimiteCompte().toString());
	    	
	       
	        // TODO: We need a way to convert the birthday into a String!
	        // birthdayLabel.setText(...);
	    } else {
	        // Person is null, remove all the text.
	    	lbNumCompte.setText("");
	    	lbProprioCompte.setText("");
	    	lbDateCompte.setText("");
	    	lbSoldeCompte.setText("");
	    	lbLimiteCompte.setText("");
	    }
	}
	
	public void fillListCompte(int idClient) {
		/*Compte currentCompte = new Compte();
		CompteDao cldao = new CompteDao();*/
		lstCompte = compteDao.trouverCompte(idClient);
	}
	
	@FXML
	private void findCompte() {
		System.out.println("findCompte()");
		
		String num = tfNumCompte.getText();
		
		System.out.println("num = ["+ num+"]");
		
		//Trouver Compte
		if( num != "") {
			fillListCompte(getIntFromTextField(tfNumCompte));
			showCompteDetails(lstCompte.get(0));
		}
		
	}
	
	@FXML
	private void createCompte() {
		System.out.println("createCompte()");
		
		Compte compte = new Compte();
		boolean okClicked = mainApp.displayCreateCompteWindow(compte);
		 if (okClicked) {
		    	System.out.println("Ca clique OK");
		    	lstCompte.add(compte);
		        System.out.println("okClicked compte = "+ compte);
		        
		        /*mainApp.loadClientdata();
				tbClient.setItems(mainApp.getPersonData());*/
		        
		    }
		
		
	}
	
	@FXML
	private void modifyCompte() {
		System.out.println("modifyCompte()");
		
		Compte compte = lstCompte.get(0);
	    if (compte != null) {
	        boolean okClicked = mainApp.displayModifyCompteWindow(compte);
	        if (okClicked) {
	        	
	        	
	        }

	    } else {
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainApp.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Compte Selected");
	        alert.setContentText("Please select a compte in research.");

	        alert.showAndWait();
	    }
		
	}
	
	/*@FXML
	private void modifyClient() {
		System.out.println("modifyClient()");
		
		//boolean okClicked = mainApp.displayModifyClientWindow();
		
		Client client = tbClient.getSelectionModel().getSelectedItem();
	    if (client != null) {
	        boolean okClicked = mainApp.displayModifyClientWindow(client);
	        if (okClicked) {
	        	
	        	mainApp.loadClientdata();
				tbClient.setItems(mainApp.getPersonData());
				
	        	showClientDetails(client);
	        }

	    } else {
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainApp.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Person Selected");
	        alert.setContentText("Please select a person in the table.");

	        alert.showAndWait();
	    }
		
	}*/
	
	/////////////////Opération/////////////////////
	
	public void fillTabOperation() {
		OperationDao cldao = new OperationDao();
		lstOp = cldao.trouverOperation();
		tbOp.setItems(lstOp);
		System.out.println("lstClients size = "+lstOp.size());
	}
	
	private void showOperationDetails(int idCompte) {
	    if (idCompte != 0) {
	    	Client client = clientDao.findClientPerId(idCompte);
	        // Fill the labels with info from the person object.
	    	//operationDao.trouverProprioOp(compte.getIdCompte())
	    	lbNomProprioOp.setText(client.getNomClient());
	    	lbPrenomProprioOp.setText(client.getPrenomClient());
	    	lbVilleProprioOp.setText(client.getVilleClient());
	    	lbSoldeProprioOp.setText(compteDao.findSoldeCompte(idCompte).toString());
	       
	        // TODO: We need a way to convert the birthday into a String!
	        // birthdayLabel.setText(...);
	    } else {
	        // Person is null, remove all the text.
	    	lbNomProprioOp.setText("");
	    	lbPrenomProprioOp.setText("");
	    	lbVilleProprioOp.setText("");
	    	lbSoldeProprioOp.setText("");
	    }
	}
	
	public void fillTabOperation2(int idCompte) {
		OperationDao cldao = new OperationDao();
		lstOp = cldao.trouverOpCompte(idCompte);
		tbOp.setItems(lstOp);
		System.out.println("lstOp = "+ lstOp);
	}
	
	
	
	@FXML
	private void createOperation() {
		System.out.println("createOperation()");
		
		boolean okClicked = mainApp.displayCreateOperationWindow();
		
	}
	
	@FXML
	private void findByDate() {
		System.out.println("findByDate()");
		//trouverOperationFilter
		fillTabOperation();
		
	}
	
	@FXML
	private void findOperation() {
		System.out.println("findOperation()");
		
		int num = 0;
		if(tfNumCompteOp.getText() == null || tfNumCompteOp.getText() == "") {
			num = 0;
		}else {
			num = Integer.parseInt(tfNumCompteOp.getText());
		}
		
		//String dateDebut = tfDateDebut.getText();
		//String dateFin = tfDateFin.getText();
		
		LocalDate dateDebut = tfDateDebut.getValue();
		LocalDate dateFin = tfDateFin.getValue();
		
		/*int num = 0;
		String dateDebut = tfDateDebut.getText();
		String dateFin = tfDateFin.getText();*/
		
		 /*num = 1;
		 dateDebut = "2015-05-01";
		 dateFin = "2018-12-25";*/
		System.out.println("num ="+num);
		System.out.println("tfDateDebut ="+dateDebut+' '+dateDebut.getClass());
		System.out.println("tfDateFin ="+dateFin+' '+dateFin.getClass());
		
		lstOp = operationDao.trouverOperationFilter(num,dateDebut.toString(),dateFin.toString());
		tbOp.setItems(lstOp);
		
		//Création du filtre
				/*FilteredList<Operation> items = new FilteredList<>(lstOp);
				tbOp.setItems(items);
				
				 if( num == "" && dateDebut == "" && dateFin == "") {
					 items.setPredicate(null);
				 }else {
					 Predicate<Operation> containsNumCpt = i -> i.getIdCompte().contains(parseInt(tfNumCompteOp.getText()));
					 //Predicate<Operation> containsPrenom = i -> i.getPrenomClient().contains(tfPrenomClient.getText());
					 //Predicate<Operation> containsVille = i -> i.getVilleClient().contains(tfVilleClient.getText().toUpperCase());
					 Predicate<Operation> filter = containsNumCpt;//.and(containsPrenom).and(containsVille);
					 
					 items.setPredicate(filter);
				 }*/
		
		/*System.out.println("num = ["+ num+"]");
		if( num != "") {
			fillTabOperation(getIntFromTextField(tfNumCompteOp));
			clNumOp.setCellValueFactory(cellData -> cellData.getValue().idOperationProperty());
		    clTypeOp.setCellValueFactory(cellData -> cellData.getValue().typeOperationProperty());
		    clMontantOp.setCellValueFactory(cellData -> cellData.getValue().montantOperationProperty());
		    clDateOp.setCellValueFactory(cellData -> cellData.getValue().dateOperationProperty());
		    showOperationDetails(Integer.parseInt(num));
		 }*/
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public  static  int getIntFromTextField( TextField textField) {
        String text = textField.getText();
       return  Integer.parseInt(text);
   } 

}
