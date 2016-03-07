package mybooks.root;

import mybooks.root.model.Book;
import javafx.application.Application;

public class BaseController {

	private Application app;
    
	public void setApp(Application application) {
	   	this.app = application;
	}
	   
	public Application getApp() {
	   	return this.app;
	}
	
	public void setBook(Book book) {}
}
