package mybooks.root;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ResourceBundle;

import mybooks.root.model.Book;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.htmlparser.jericho.*;

public class BookImportController extends BaseController {

	 @FXML
	 private TextField title;
	 @FXML
	 private TextField author;
	 @FXML
	 private TextField year;
	 
	 private String html;

	 public StringBuilder readFile(File selectedFile){
	     StringBuilder sb = new StringBuilder(1024);
		 String curLine = "";
		 try{
		     InputStreamReader fr = new InputStreamReader(new FileInputStream(selectedFile), "UTF8");
		     BufferedReader br = new BufferedReader(fr);
		          
		     while(curLine != null){
		         curLine = br.readLine();
		         sb.append(curLine).append("\n");
		     }
		 } catch (Exception e){
		     e.getMessage();
		 }
		 return sb;
	 }
	 
	 @FXML
	 public void handleFileUpload()
	 {
		 FileChooser fileChooser = new FileChooser();
		 
         //File extension filter
         FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("(*.html)", "*.htm*");
         fileChooser.getExtensionFilters().add(extensionFilter);

         //Choose the file
         try{
                 fileChooser.setTitle("Open Html File");
                 File userFile = fileChooser.showOpenDialog(null);
                 StringBuilder sb = null;
                 sb = readFile(userFile);
                 System.out.println("HTML: " + sb.toString());
                 Source htmlSource = new Source(sb.toString());
                 Segment htmlSeg = new Segment(htmlSource, 0, htmlSource.length());
                 Renderer htmlRend = new Renderer(htmlSeg);
                 // System.out.println("TEXT: " + htmlRend.toString());
                 html = htmlRend.toString();
                 Charset charset = Charset.forName("UTF-8");
                 html = charset.decode(charset.encode(html)).toString();
         }
         catch(NullPointerException e){
                 System.out.println("Sorry, not a valid file.");
         }

	 }
	 
	 @FXML
	 public void saveBook() {
		 if (html.length() == 0)
			 return;
		 
		 DBops db = new DBops();
		 db.create(title.textProperty().get(), author.textProperty().get(), year.textProperty().get(), html);
		 
		 Stage stage = (Stage) year.getScene().getWindow();
     	 ((MainApp) this.getApp()).showListView(stage);
	 }
}
