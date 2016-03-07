package mybooks.root;

import java.net.URL;
import java.util.ResourceBundle;

import mybooks.root.model.Book;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BookReaderController extends BaseController {

	private StringProperty html = new SimpleStringProperty("");
	private StringProperty page = new SimpleStringProperty("");
	private StringProperty pagenum = new SimpleStringProperty("1");
	
	private int charPerPage = 2400;
	private int pageNum = 1;
	private Book book;
	
	@FXML
	private Button btn; 
	
	public String getHtml() { return html.get(); }
	public void setHtml(String value) { html.set(value); }
	
	public String getPage() { return page.get(); }
	public void setPage(String value) { page.set(value); }
	
	public String getPagenum() { return pagenum.get(); }
	public void setPagenum(String value) { pagenum.set(value); }

	public void setBook(Book book)
	{
		this.book = book;
		this.pageNum = Integer.parseInt(book.getPage());
		this.setHtml(book.getHtml());
		this.setPageContent(this.pageNum);
	}
	
	private void setPageContent (int page) {
		int from = (page - 1) * charPerPage;
		int to = page * charPerPage;
		if (to > html.length().intValue()) 
		  to = html.length().intValue();

		this.setPage(this.getHtml().substring(from, to));
		pagenum.set(Integer.toString(page));
		book.setPage(Integer.toString(page));
	}
	
	@FXML
    protected void prevPage() {
        this.pageNum --;
        if (this.pageNum < 1)
          this.pageNum = 1;
        
        this.setPageContent(this.pageNum);
    }
	
	@FXML
    protected void nextPage() {
        this.pageNum ++;
        if (this.pageNum > (html.length().intValue() / charPerPage))
          this.pageNum = (int) Math.ceil((double) html.length().intValue() / (double) charPerPage);
        this.setPageContent(this.pageNum);
    }
	
	@FXML
	public StringProperty pageProperty(){
		return page;
	}
	
	@FXML
	public StringProperty pagenumProperty() {
		return pagenum;
	}
	
	@FXML
    protected void closeReader() {
		Stage stage = (Stage) btn.getScene().getWindow();
		((MainApp) this.getApp()).closeBookReaderView(stage, this.book);
    }
}
