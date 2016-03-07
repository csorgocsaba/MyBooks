package mybooks.root;
import java.sql.Connection;  
 import java.sql.DriverManager;  
 import java.sql.SQLException;  
 public class DBconn {  
   private static Connection conn;  
   private static String url = "jdbc:mysql://localhost:3306/reader?useUnicode=no&characterEncoding=latin1";  
   private static String user = "root";//Username of database  
   private static String pass = "vxcl0q";//Password of database  
   public static Connection connect() throws SQLException{  
     try{  
       Class.forName("com.mysql.jdbc.Driver").newInstance();  
     }catch(ClassNotFoundException cnfe){  
       System.err.println("Error: "+cnfe.getMessage());  
     }catch(InstantiationException ie){  
       System.err.println("Error: "+ie.getMessage());  
     }catch(IllegalAccessException iae){  
       System.err.println("Error: "+iae.getMessage());  
     }  
     conn = DriverManager.getConnection(url,user,pass);  
     System.err.println("Connected");       
     return conn;  
   }  
   public static Connection getConnection() throws SQLException, ClassNotFoundException{  
     if(conn !=null && !conn.isClosed())  
       return conn;  
     connect();  
     return conn;  
   }
 }  