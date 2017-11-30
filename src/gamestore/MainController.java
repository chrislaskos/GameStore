
package gamestore;

import gamestore.model.ImageTextCell;
import gamestore.model.Customer;
import gamestore.model.Product;
import gamestore.model.ProductQueries;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {

    private Main application;
    private Stage warningStage;
    private Stage helpStage;
    
    public void setApp(Main application) {
        this.application = application;
        Customer loggedCustomer = application.getLoggedCustomer();
    }
        
    private Product currentProduct;
    private ProductQueries productQueries;
    private List<Product> results;
    private List<Integer> alreadyPurchasedProducts;
    //private List<Integer> allProductIDs;
    private ObservableList<Product> products = FXCollections.observableArrayList();
    private ObservableList<Product> cartItems = FXCollections.observableArrayList();
    
    @FXML private BorderPane mainBorderPane;
    @FXML private ListView<Product> gamesListView;
    @FXML private ImageView firstImageView;
    @FXML private ImageView secondImageView;
    @FXML private ImageView thirdImageView;
    @FXML private ImageView fourthImageView;
    @FXML private ImageView coverImageView;
    @FXML private Label descriptionLabel;
    @FXML private Hyperlink cartButton;
    @FXML private Button addToCartButton;
    @FXML private VBox gamesVBox, imagesVBox;
    @FXML private Hyperlink videoHyperlink;
    
    int userID = Main.getInstance().getLoggedCustomer().getCustomerID();
    
    public void initialize() {
        // defines the database connection and sets the PreparedStatements
        productQueries = new ProductQueries();
        alreadyPurchasedProducts = productQueries.getAlreadyPurchasedProducts(userID);
        results = productQueries.getAllProducts();
        //numberOfEntries = results.size();

        products.addAll(results);
        
        gamesListView.setItems(products);
        
        gamesListView.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                        descriptionLabel.setText(newValue.getDescription());
                        videoHyperlink.setText(newValue.getVideo());
                        videoHyperlink.setOnAction((ActionEvent e) -> {
                            try {
                                Desktop.getDesktop().browse(new URI(videoHyperlink.getText()));
                            } catch (URISyntaxException | IOException ex) {
                                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        coverImageView.setImage(new Image(newValue.getCover()));
                        firstImageView.setImage(new Image(newValue.getImage1()));
                        secondImageView.setImage(new Image(newValue.getImage2()));
                        thirdImageView.setImage(new Image(newValue.getImage3()));
                        fourthImageView.setImage(new Image(newValue.getImage4()));
                        if(alreadyPurchasedProducts.contains(newValue.getProductID())) {
                            addToCartButton.setText("Already Purchased");
                            addToCartButton.setDisable(true);
                        } else {
                            addToCartButton.setText("Add To Cart");
                            addToCartButton.setDisable(false);
                        };
                }        
        );
        gamesListView.getSelectionModel().select(0);
        gamesListView.setCellFactory((listview) -> new ImageTextCell());
                
    }
    
    public void proccessLogout(ActionEvent event) throws Exception {
        if (application == null){
            return;
        }
        application.userLogout();
    }

    public void gotoAccount(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Account.fxml"));
        GridPane account = loader.load();
        AccountController accountController = loader.getController();
        accountController.setApp(application);
        mainBorderPane.setCenter(account);
        mainBorderPane.setRight(null);
        BorderPane.setAlignment(account, Pos.TOP_LEFT);
    }
    
    public void gotoStore(ActionEvent event) throws Exception {
        // null pointer exception
        productQueries = new ProductQueries();
        alreadyPurchasedProducts = productQueries.getAlreadyPurchasedProducts(userID);
        if(alreadyPurchasedProducts.contains(gamesListView.getSelectionModel().getSelectedItem().getProductID())) {
            addToCartButton.setText("Already Purchased");
            addToCartButton.setDisable(true);
        } else {
            addToCartButton.setText("Add To Cart");
            addToCartButton.setDisable(false);
        }
        mainBorderPane.setCenter(gamesVBox);
        mainBorderPane.setRight(imagesVBox);
    }
    
    public void gotoLibrary(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Library.fxml"));
        ScrollPane library = loader.load();
        mainBorderPane.setCenter(library);
        mainBorderPane.setRight(null);
    }
    
    public Product getSelectedProduct() {
        currentProduct = gamesListView.getSelectionModel().getSelectedItem();
        return currentProduct;
    }
    
    public void addToCartButtonClicked(ActionEvent event) throws Exception {
        
        if(cartItems.contains(getSelectedProduct())) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(warningStage);
            alert.getDialogPane().setContentText("Each digital purchase is automatically linked to your account. You cannot buy the same digital item twice.");
            alert.getDialogPane().setHeaderText(null);
            alert.showAndWait().filter(response -> response == ButtonType.OK);
        } else {
            cartItems.add(getSelectedProduct());
            cartButton.setText("Cart (" + String.valueOf(cartItems.size()) + ")");
            //System.out.println("Product Added To Cart");
        }
    }
    
    public void showCart(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Cart.fxml"));
        VBox cart = loader.load();
        CartController cartController = loader.getController();
        cartController.updatedCartButton = cartButton;
        cartController.mainBorderPaneForCheckoutUse = mainBorderPane;
        cartController.setCart(this);
        mainBorderPane.setCenter(cart);
        mainBorderPane.setRight(null);
    }
    
    public ObservableList<Product> getCartItems() {
        return cartItems;
    }
    
    public Customer getLoggedCustomer() {
        return application.getLoggedCustomer();
    }
    
    public void showHelp(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(helpStage);
        alert.getDialogPane().setContentText("Games Store is an e-store where a user can buy games digitally and then play them from the Games Library. The user can browse through the available games clicking each item on the list. Then he can add the selected game to the Shopping Cart and proceed to Payment. Each game can be purchased only once due to its linking with the user's account.");
        alert.getDialogPane().setHeaderText(null);
        alert.showAndWait().filter(response -> response == ButtonType.CLOSE);        
    }
}