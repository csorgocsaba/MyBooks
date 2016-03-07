package mybooks.root;

import java.net.URL;
import java.util.ResourceBundle;

import mybooks.root.model.Book;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BookDetailController extends BaseController {

	private StringProperty id = new SimpleStringProperty("");
	private StringProperty title = new SimpleStringProperty("");
	private StringProperty author = new SimpleStringProperty("");
	private StringProperty year = new SimpleStringProperty("");
    private Book book;
    
    @FXML
    private Button btn; 
	
	public String getId() { return id.get(); }
	public void setId(String value) { id.set(value); }
	
	public String getTitle() { return title.get(); }
	public void setTitle(String value) { title.set(value); }
	
	public String getAuthor() { return author.get(); }
	public void setAuthor(String value) { author.set(value); }
	
	public String getYear() { return year.get(); }
	public void setYear(String value) { year.set(value); }
	
	public void setBook(Book book)
	{
		this.setId(Integer.toString(book.getId()));
		this.setTitle(book.getTitle());
		this.setAuthor(book.getAuthor());
		this.setYear(book.getYear());
		this.book = book;
	}
	
	@FXML
	public void readBook() {
		 Stage stage = (Stage) btn.getScene().getWindow();
    	 ((MainApp) this.getApp()).showBookReaderView(stage, this.book);
	}
}
