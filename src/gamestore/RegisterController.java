
package gamestore;

import gamestore.model.CustomerQueries;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RegisterController implements Initializable {

    @FXML private TextField registerUserName;
    @FXML private TextField registerPassword;
    @FXML private TextField registerEmail;
    @FXML private TextField registerCreditCard;    
    @FXML private Label errorLabel;
    
    private CustomerQueries customerQueries;
    private Main application; 
    private AlertType type = AlertType.INFORMATION;
    private Stage stage;
    
    public void setAlertType(AlertType at) {
        type = at;
    }
    
    protected Alert createAlert() {
        Alert alert = new Alert(type, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.getDialogPane().setContentText(type + " You successfully registered into GameStore. Please sign in.");
        alert.getDialogPane().setHeaderText(null);
        alert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> System.out.println("The alert was approved"));
        return alert;
    }
    
    public void setApp(Main application) {
        this.application = application;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        registerUserName.setPromptText("Enter username");
        registerPassword.setPromptText("Enter password");
        registerEmail.setPromptText("example@example.com");
        registerCreditCard.setPromptText("16 digits");
    }    
    
    public void processRegister(ActionEvent event) throws IOException {
        customerQueries = new CustomerQueries();
        
        if(registerUserName.getText().trim().isEmpty() || registerPassword.getText().trim().isEmpty() || registerEmail.getText().trim().isEmpty() || registerCreditCard.getText().trim().isEmpty() || registerCreditCard.getText().length() != 16 || !(registerCreditCard.getText().matches("[0-9]+")) || !(registerEmail.getText().matches("\\b[a-z0-9._-]+@[a-z0-9.-]+\\.[a-z]{2,}\\b"))) {
            errorLabel.setText("Please enter your credentials correctly.");
        } else {
            customerQueries.addCustomer(registerUserName.getText(), registerPassword.getText(), registerEmail.getText(), registerCreditCard.getText());
            createAlert();
            application.gotoLogin();
        }
    }
    
}
