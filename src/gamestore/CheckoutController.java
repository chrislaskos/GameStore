
package gamestore;

import gamestore.model.Customer;
import gamestore.model.Product;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CheckoutController {
    
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/gamespot_db?zeroDateTimeBehavior=convertToNull";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "nbuser";
    private AlertType type = AlertType.INFORMATION;
    private Stage stage;
    public Hyperlink checkoutCartButton;
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);
    
    public void setAlertType(AlertType at) {
        type = at;
    }
    
    protected Alert createInfoAlert() {
        Alert alert = new Alert(type, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.getDialogPane().setContentText("You can now play the games you bought from the GameStore Library.");
        alert.getDialogPane().setHeaderText("Your purchase has been successfull.");
        alert.showAndWait()
                .filter(response -> response == ButtonType.OK);
        return alert;
    }
    
    protected Alert createErrorAlert() {
        Alert alert = new Alert(type, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.getDialogPane().setContentText("Update your funds and try again...");
        alert.getDialogPane().setHeaderText("Your balance is not enough.");
        alert.showAndWait()
                .filter(response -> response == ButtonType.OK);
        return alert;
    }
    
    @FXML private Label checkoutCCNumber;
    @FXML private Label checkoutBalance;
    @FXML private Label checkoutUserName;
    @FXML private Label checkoutTotal;
    
    @FXML TableView<Product> checkoutTable;
    @FXML private TableColumn<Product, String> checkoutTitleCol;
    @FXML private TableColumn<Product, Double> checkoutPriceCol;
    
    public void setCheckoutPage(CartController cartController) {
        checkoutTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        checkoutPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        //checkoutPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        checkoutPriceCol.setCellFactory(col -> new TableCell<Product, Double>(){
            @Override
            public void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if(empty) {
                    setGraphic(null);
                } else {
                    String currencyPrice = currencyFormatter.format(price);
                    Label priceLabel = new Label(currencyPrice);
                    setGraphic(priceLabel);
                }
            }
        });
        checkoutTable.setItems(cartController.getCartItemsForCheckout());
        
        Double orderTotal = cartController.total;
        String currencyTotal = currencyFormatter.format(orderTotal);
        checkoutTotal.setText(currencyTotal);
        
        Customer loggedCustomer = cartController.getLoggedCustomer();
        checkoutCCNumber.setText(loggedCustomer.getCCNumber());
        checkoutUserName.setText(loggedCustomer.getUserName());
        
        Double userBalance = loggedCustomer.getBalance();
        String currencyBalance = currencyFormatter.format(userBalance);
        checkoutBalance.setText(currencyBalance);
    }
    
    public void initialize() {
    }
    
    public void purchaseButtonClicked(ActionEvent event) throws SQLException, ParseException, IOException {
          
        DecimalFormat decimalFormat = new DecimalFormat("#");
        double total = decimalFormat.parse(checkoutTotal.getText()).doubleValue();
        double balance = decimalFormat.parse(checkoutBalance.getText()).doubleValue();
        
        String customerAccount = checkoutUserName.getText();
        // information & error dialog
        if( total <= balance) {
            // info dialog and substract the amount from database
            balance = balance - total;
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            final String UPDATE_QUERY = "UPDATE customers SET Balance = " + balance + " WHERE UserName = '" + customerAccount + "'";
            statement.executeUpdate(UPDATE_QUERY);
            checkoutBalance.setText(String.valueOf(balance));
            int userID = Main.getInstance().getLoggedCustomer().getCustomerID();
            Customer loggedCustomer = Main.getInstance().getLoggedCustomer();
            loggedCustomer.setBalance(balance);       
            
            for(Product product : checkoutTable.getItems()) {
                final String INSERT_QUERY = "INSERT INTO orders (ProductID, CustomerID) VALUES ('" + product.getProductID() + "','" + userID + "')";
                statement.executeUpdate(INSERT_QUERY);
            }
            
            setAlertType(AlertType.INFORMATION);
            createInfoAlert();
            checkoutTable.getItems().clear();
            checkoutCartButton.setText("Cart");
            // implement exit back to store
            Main.getInstance().gotoGameStore();
        } else {
            // error dialog and do nothing
            setAlertType(AlertType.ERROR);
            createErrorAlert();
        }
    }  
}