
package gamestore;

import gamestore.model.Customer;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class AccountController implements Initializable {
    
    @FXML public TextField userTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField passwordTextField;
    @FXML private Label ccNumberLabel;
    @FXML private Label balanceLabel;
    @FXML private Label successLabel;
    
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/gamespot_db?zeroDateTimeBehavior=convertToNull";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "nbuser";    
    private Connection connection;
    private Statement statement;
    
    private Main application;
    
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);
    
    public void setApp(Main application) {
        this.application = application;
        Customer loggedCustomer = application.getLoggedCustomer();
        userTextField.setText(loggedCustomer.getUserName());
        emailTextField.setText(loggedCustomer.getEmail());
        passwordTextField.setText(loggedCustomer.getPassword());
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();

            String SELECT_BALANCE = "SELECT Balance FROM customers WHERE CustomerID = " + loggedCustomer.getCustomerID();
            ResultSet resultSet = statement.executeQuery(SELECT_BALANCE);
            while (resultSet.next()) {
                Double customerBalance = resultSet.getDouble("Balance");
                String currencyBalance = currencyFormatter.format(customerBalance);
                balanceLabel.setText(currencyBalance);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        ccNumberLabel.setText(loggedCustomer.getCCNumber());
        //balanceTextField.setText(String.valueOf(loggedCustomer.getBalance()) + " €");
        successLabel.setOpacity(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void saveProfile(ActionEvent event) {
        if(application == null) {
            animateMessage();
            return;
        }
        Customer loggedCustomer = application.getLoggedCustomer();
        
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            
            final String UPDATE_ACCOUNT = "UPDATE customers SET UserName = '" + userTextField.getText() + "', Password ='" + passwordTextField.getText() + "' WHERE CustomerID=" + loggedCustomer.getCustomerID();
            final String UPDATE_EMAIL = "UPDATE customers SET Email = '" + emailTextField.getText() + "' WHERE CustomerID=" + loggedCustomer.getCustomerID();
            statement.executeUpdate(UPDATE_ACCOUNT);
            loggedCustomer.setUserName(userTextField.getText());
            userTextField.setText(userTextField.getText());
            loggedCustomer.setPassword(passwordTextField.getText());
            passwordTextField.setText(passwordTextField.getText());
            
            if(emailTextField.getText().matches("\\b[a-z0-9._-]+@[a-z0-9.-]+\\.[a-z]{2,}\\b")) {
                statement.executeUpdate(UPDATE_EMAIL);
                loggedCustomer.setEmail(emailTextField.getText());
                emailTextField.setText(emailTextField.getText());
                successLabel.setText("Profile successfully updated!");
                animateMessage();
            } else {
                successLabel.setText("Enter a valid email address (example@example.com).");
                animateMessage();
            }
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }   
    }
    
    public void cancelButton(ActionEvent event) throws Exception {
        Main.getInstance().gotoGameStore();
    }
    
    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), successLabel);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }
}
