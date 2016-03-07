package mybooks.root;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mybooks.root.model.*;

public class MainApp extends Application {

    private Stage primaryStage;
    
    @FXML
    private MenuBar menuBar;

    @FXML
    private void showImport(ActionEvent event) {
    	MenuItem node = (MenuItem) event.getSource();
    	Stage stage = (Stage) node.getParentPopup().getOwnerWindow();
        showImportView(stage);
    }
    
    @FXML
    private void showList(ActionEvent event) {
    	MenuItem node = (MenuItem) event.getSource();
    	Stage stage = (Stage) node.getParentPopup().getOwnerWindow();
        showListView(stage);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("MyBooks");

        showListView(primaryStage);
    }
    
    /**
     * Initializes arbitrary layout.
     */
    private void initLayout(Stage stage, String view, Book book) {
    	try {
        	FXMLLoader rl_loader = new FXMLLoader();
            rl_loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            BorderPane rl = (BorderPane) rl_loader.load();
        	
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/" + view + "View.fxml"));
            
            AnchorPane a_view = (AnchorPane) loader.load();
            BaseController controller = (BaseController) loader.getController();
            
            controller.setApp(this);
            if (book != null)
            {
            	((BaseController)controller).setBook(book);
            }
            
            rl.setCenter(a_view);
            Scene scene = new Scene(rl);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showImportView(Stage stage) {
    	initLayout(stage, "Import", null);
    }

    public void showListView(Stage stage) {
    	initLayout(stage, "List", null);
    }

    public void showBookDetailsView(Stage stage, Book book) {
    	initLayout(stage, "BookDetail", book);
    }
    
    public void showBookReaderView(Stage stage, Book book) {
    	initLayout(stage, "BookReader", book);	
    }
    
    public void closeBookReaderView(Stage stage, Book book) {
    	DBops db = new DBops();
    	db.update(book);
    	showListView(stage);
    }
    
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}