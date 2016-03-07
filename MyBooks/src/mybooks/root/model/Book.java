package mybooks.root.model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
	
	private StringProperty id;
	private StringProperty title;
	private StringProperty author;
	private StringProperty year;
	private StringProperty html;
	private StringProperty page;
	private StringProperty last_read;
	
	public Book(int id, String title, String author, String year, String html, String last_read, String page){
		this.id = new SimpleStringProperty(Integer.toString(id));
		this.title = new SimpleStringProperty(title);
		this.author = new SimpleStringProperty(author);
		this.year = new SimpleStringProperty(year);
		this.html = new SimpleStringProperty(html);
		this.last_read = new SimpleStringProperty(last_read);
		this.page = new SimpleStringProperty(page);
	}
	
	public int getId() {
		return Integer.parseInt(id.get());
	}
	
	public StringProperty getIdProperty() {
		return id;
	}
	
	public String getTitle() {
		return title.get();
	}
	
	public StringProperty getTitleProperty() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title.set(title);
	}
	
	public String getAuthor() {
		return author.get();
	}
	
	public StringProperty getAuthorProperty() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author.set(author);
	}

	public StringProperty getYearProperty() {
		return year;
	}
	
	public String getYear() {
		return year.get();
	}
	
	public void setYear(String year) {
		this.year.set(year + "-01-01");
	}

	public String getHtml() {
		return html.get();
	}
	
	public void setHtml(String html) {
		this.html.set(html);
	}

	public StringProperty getHtmlProperty() {
		return html;
	}
	
	public String getLast() {
		return last_read.get();
	}
	
	public void setLast(String last) {
		this.last_read.set(last);
	}

	public StringProperty getLastProperty() {
		return last_read;
	}
	
	public String getPage() {
		return page.get();
	}
	
	public void setPage(String page) {
		this.page.set(page);
	}

	public StringProperty getPageProperty() {
		return page;
	}
	
}
