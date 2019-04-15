package dbConnect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/login")

public class dataBaseLogin extends HttpServlet{
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	  {
		
		
		String email= request.getParameter("email"); 
		String password = request.getParameter("pass");
		//String name= "orlando";
	//	String email="test@edu.com";
	//	String password="pass";
		
		 System.out.println("email : " + email + " pass "+password);
		 PreparedStatement preparedStatement = null;
		 
		 Connection connect = null;
	   
	     String QueryAddUser = "SELECT EMAIL, PASS FROM REGISTERUSER WHERE EMAIL LIKE '"+email+"' AND PASS LIKE '"+password+"';";
		
		try {
			 
			Class.forName("com.mysql.jdbc.Driver");
			connect= DriverManager.getConnection("jdbc:mysql://mydatabase.ccabptaeh7ff.us-east-2.rds.amazonaws.com:3306/myDatabase?autoReconnect=true&useSSL=false", "myDatabase","db246890" );
			
			preparedStatement = connect.prepareStatement(QueryAddUser);
			ResultSet rs= preparedStatement.executeQuery(QueryAddUser);
			
			
			
			 if (rs.next() == false) {
			        System.out.println("ResultSet in empty in Java ");
			      
			       response.setContentType("String");

				   response.getWriter().write("failed");
			        
			      } else {

			        do {
			          
			        	
			        	System.out.print("USer found");
			        	
			        	response.setContentType("String");

					    
						   response.getWriter().write("successful");
			        	
			        	
			        } while (rs.next());
			      }
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
	    response.setContentType("String");
	    
	    System.out.println("user "+email+" password  "+ password);
	    
	
	   
	   
	   
	  } 


}
