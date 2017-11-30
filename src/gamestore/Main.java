package gamestore;

import gamestore.model.Customer;
import gamestore.model.CustomerQueries;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    
    private Group root = new Group();
    private static Customer loggedCustomer;
    private CustomerQueries customerQueries;
    private List<Customer> userNameResults; // usernameResults
    private List<Customer> passwordResults;
    private int numberOfCustomersByUserName = 0;
    private int numberOfCustomersByPassword = 0;
    private int currentCustomerIndex;
        
    // for the use of loggedCustomer in the Checkout Page
    private static Main mainInstance;
    public Main() {
        mainInstance = this;
    }
    public static Main getInstance() {
        return mainInstance;
    }
    
    public Parent createContent() {
        gotoLogin();
        return root;
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("GameStore");
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();       
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
    
    public Customer getLoggedCustomer() {
        return loggedCustomer;
    }
    
    public boolean userLogging(String userName, String password) throws IOException{
        customerQueries = new CustomerQueries();
        userNameResults = customerQueries.getCustomerByUserName(userName);
        passwordResults = customerQueries.getCustomerByPassword(password);
        numberOfCustomersByUserName = userNameResults.size();
        numberOfCustomersByPassword = passwordResults.size();
        
        if(numberOfCustomersByUserName != 0 && numberOfCustomersByPassword != 0) {
            currentCustomerIndex = 0;
            loggedCustomer = userNameResults.get(currentCustomerIndex);
            //userName = loggedCustomer.getUserName();
            //password = loggedCustomer.getPassword();
            gotoGameStore();
            return true;
        } else {
            return false;
        }
    }
    
    void userLogout() throws Exception {
        loggedCustomer = null;
        Stage primaryStage = (Stage) root.getScene().getWindow();
        primaryStage.setMinWidth(370);
        primaryStage.setMinHeight(320);
        gotoLogin();
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
    }    
    
    public void gotoGameStore() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        BorderPane store = loader.load();
        MainController mainController = loader.getController();
        mainController.setApp(this);
        root.getChildren().clear();
        root.getChildren().add(store);
        Stage primaryStage = (Stage) root.getScene().getWindow();
        store.prefHeightProperty().bind(primaryStage.heightProperty());
        store.prefWidthProperty().bind(primaryStage.widthProperty());
        primaryStage.setMinWidth(990);
        primaryStage.setMinHeight(730);
        primaryStage.setResizable(false);
    }
    
    public void gotoRegister() {
        try {
            RegisterController registerController = (RegisterController) replaceSceneContent("Register.fxml");
            registerController.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoLogin() {
        try {
            LoginController login = (LoginController) replaceSceneContent("Login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        root.getChildren().clear();
        root.getChildren().addAll(page);
        return (Initializable) loader.getController();
    }
    
}