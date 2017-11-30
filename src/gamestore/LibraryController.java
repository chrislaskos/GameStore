
package gamestore;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class LibraryController {
    
    @FXML private TilePane libraryTilePane;
    
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/gamespot_db?zeroDateTimeBehavior=convertToNull";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "nbuser";
    private Connection connection;
    private Statement statement;
    //private final Desktop desktop = Desktop.getDesktop();
    int userID = Main.getInstance().getLoggedCustomer().getCustomerID();
    
    public void initialize() {
        
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            
            String SELECT_PURCHASED_GAMES = "SELECT Title, Cover, ExeFile FROM products INNER JOIN orders ON products.`ProductID` = orders.`ProductID` AND orders.`CustomerID` = " + userID;
            ResultSet resultSet = statement.executeQuery(SELECT_PURCHASED_GAMES);
            if (!resultSet.isBeforeFirst() ) {   
                Label noGames = new Label("The games you purchase will appear in this section. You can find games in the GameSpot Store.");
                noGames.setPadding(new Insets(10, 10, 10, 10));
                libraryTilePane.getChildren().add(noGames);
            }
            
            while(resultSet.next()) {
                String productTitle = resultSet.getString("Title");
                String productCover = resultSet.getString("Cover");
                String productExe = resultSet.getString("ExeFile");
                
                Image gameImg = new Image(productCover);
                ImageView coverImageView = new ImageView(gameImg);
                coverImageView.setFitHeight(120);
                coverImageView.setFitWidth(100);
                final Tooltip tooltip = new Tooltip("Download " + productTitle);
                Hyperlink downloadGame = new Hyperlink(productTitle);
                downloadGame.setContentDisplay(ContentDisplay.TOP);
                downloadGame.setGraphic(coverImageView);
                downloadGame.getStyleClass().add("library-tiles");
                Tooltip.install(downloadGame, tooltip);
                downloadGame.setCursor(Cursor.DEFAULT);
                libraryTilePane.getChildren().add(downloadGame);
                
                downloadGame.setOnAction((ActionEvent e) -> {
                    try {
                        Desktop.getDesktop().browse(new URI(productExe));
                    } catch (URISyntaxException | IOException ex) {
                        Logger.getLogger(LibraryController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
            resultSet.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if(statement != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            } // do nothing
            try {
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            } // end finally try
        } // end try       
    }    
    
}
