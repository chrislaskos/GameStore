
package gamestore;

import gamestore.model.Customer;
import gamestore.model.Product;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class CartController {
    
    @FXML private TableView<Product> cartTable;
    @FXML private TableColumn<Product, Product> coverCol;
    @FXML private TableColumn<Product, String> titleCol;
    @FXML private TableColumn<Product, Double> priceCol;    
    @FXML private TableColumn<Product, Product> removeCol;
    @FXML private Label subtotalLabel;    
    //@FXML private Button checkoutButton;

    private MainController mainController;
    double total = 0 ;
    public Hyperlink updatedCartButton;
    public BorderPane mainBorderPaneForCheckoutUse;
    
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);
       
    public void initialize() {
        cartTable.setPlaceholder(new Label("Your shopping cart is empty..."));
    }
    
    public void setCart(MainController mainController) {
        this.mainController = mainController;
        cartTable.setItems(mainController.getCartItems());
        
        for (Product product : cartTable.getItems()) {
            total = total + product.getPrice();
        }
        String currencyPrice = currencyFormatter.format(total);
        subtotalLabel.setText(currencyPrice);    
        
        coverCol.setCellValueFactory(img -> new ReadOnlyObjectWrapper<>(img.getValue()));
        coverCol.setCellFactory(img -> new TableCell<Product, Product>(){
            private final ImageView coverImageView = new ImageView();
            @Override 
            protected void updateItem(Product product, boolean empty) {
                super.updateItem(product, empty);
                if(product == null) {
                    setGraphic(null);
                    return;
                }
                coverImageView.setImage(new Image(product.getCover()));
                coverImageView.setFitHeight(50);
                coverImageView.setFitWidth(100);
                setGraphic(coverImageView);
            }
        });
        
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceCol.setCellFactory(col -> new TableCell<Product, Double>(){
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
        
        removeCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        removeCol.setCellFactory(param -> new TableCell<Product,Product>(){
            private final Hyperlink removeFromCart = new Hyperlink("");
            @Override
            protected void updateItem(Product product, boolean empty) {
                super.updateItem(product, empty);
                if(product == null) {
                    setGraphic(null);
                    return;
                }
                HBox removeHBox = new HBox();
                TextField quantity = new TextField("1");
                quantity.setPrefWidth(35.0);
                quantity.setAlignment(Pos.CENTER_RIGHT);
                quantity.setEditable(false);
                quantity.setStyle("-fx-background-radius: 0;");
                removeHBox.setAlignment(Pos.CENTER);
                removeHBox.getChildren().addAll(quantity, removeFromCart);
                setGraphic(removeHBox);
                Image deleteIcon = new Image(getClass().getResourceAsStream("/resources/images/icons/ic_delete_black_18dp_1x.png"));
                removeFromCart.setGraphic(new ImageView(deleteIcon));
                removeFromCart.setStyle("-fx-text-fill: black;");
                removeFromCart.setOnAction(e -> {
                    getTableView().getItems().remove(product);
                    total = total - product.getPrice();
                    String currencyPrice = currencyFormatter.format(total);
                    subtotalLabel.setText(currencyPrice);
                    updatedCartButton.setText("Cart (" + String.valueOf(mainController.getCartItems().size()) + ")");
                });
            }
        });        
    }
    
    public void continueShopping(ActionEvent event) throws Exception {
        mainController.gotoStore(event);
    }
    
    public void proceedToCheckout(ActionEvent event) throws IOException {
        if(!mainController.getCartItems().isEmpty()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Checkout.fxml"));
            GridPane checkout = loader.load();
            CheckoutController checkoutController = loader.getController();
            checkoutController.setCheckoutPage(this);
            checkoutController.checkoutCartButton = updatedCartButton;
            mainBorderPaneForCheckoutUse.setCenter(checkout);
        }
    }
    
    public ObservableList<Product> getCartItemsForCheckout() {
        return cartTable.getItems();
    }
    
    public Customer getLoggedCustomer() {
        return mainController.getLoggedCustomer();
    }
       
}
