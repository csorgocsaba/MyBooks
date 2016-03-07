package mybooks.root;

import java.util.Iterator;
import java.util.List;

import mybooks.root.model.Book;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BookListController extends BaseController{

    @FXML
    private TextField filterField;
    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> idColumn;
    @FXML
    private TableColumn<Book, String> yearColumn;
    @FXML
    private TableColumn<Book, String> lastColumn;
    @FXML
    private TableColumn<Book, String> pageColumn;
    
    private ObservableList<Book> masterData = FXCollections.observableArrayList();

    /**
     * Just add some sample data in the constructor.
     */
    public void bookTableController() {}
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     * 
     * Initializes the table columns and sets up sorting and filtering.
     */
    @FXML
    private void initialize() {
    	DBops db = new DBops();
    	List<Book> data = db.list();
    	
    	Iterator iterator = data.iterator();
    	while (iterator.hasNext()) {
    	  masterData.add((Book) iterator.next());  
    	}
    	
        // 0. Initialize the columns.
    	idColumn.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
     	authorColumn.setCellValueFactory(cellData -> cellData.getValue().getAuthorProperty());
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitleProperty());
        yearColumn.setCellValueFactory(cellData -> cellData.getValue().getYearProperty());
        lastColumn.setCellValueFactory(cellData -> cellData.getValue().getLastProperty());
        pageColumn.setCellValueFactory(cellData -> cellData.getValue().getPageProperty());

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Book> filteredData = new FilteredList<>(masterData, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Book -> {
                // If filter text is empty, display all Books.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every Book with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Book.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (Book.getAuthor().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (Book.getYear().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Book> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(bookTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        bookTable.setItems(sortedData);
        BookListController _this = this;
        bookTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent event) {
            	if (event.getClickCount() == 2)
            	{
            		Node node = ((Node) event.getTarget()).getParent();
            		TableRow row;

            		if (node instanceof TableRow) {
            			row = (TableRow) node;
            		} else {
            			// clicking on text part
            			row = (TableRow) node.getParent();
            		}

            		Node parent = row.getParent();
            		Stage stage = (Stage) parent.getScene().getWindow();
            		((MainApp) _this.getApp()).showBookDetailsView(stage, (Book) row.getItem());
            	}
            }
        });
    }
}
