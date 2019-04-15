package dbConnect;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/register")

public class dataBase extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	  {
		
		String name = request.getParameter("name");
		String email= request.getParameter("email"); 
		String password = request.getParameter("pass");
		
		 PreparedStatement preparedStatement = null;
		 
		 Connection connect = null;
	   
	     String QueryAddUser = "SELECT * FROM REGISTERUSER WHERE EMAIL LIKE '"+email+"' ;";
		
		try {
			 
			Class.forName("com.mysql.jdbc.Driver");
			connect= DriverManager.getConnection("jdbc:mysql://mydatabase.ccabptaeh7ff.us-east-2.rds.amazonaws.com:3306/myDatabase?autoReconnect=true&useSSL=false", "myDatabase","db246890" );
			
			preparedStatement = connect.prepareStatement(QueryAddUser);
			ResultSet rs= preparedStatement.executeQuery(QueryAddUser);
			
			 if (rs.next() == false) {
			        System.out.println("ResultSet is empty in Java ");
			        
			        
			        String regUser = "INSERT INTO REGISTERUSER VALUES ('"+name+"','"+email +"','"+password+"')";
			      preparedStatement= connect.prepareStatement(regUser);
			       preparedStatement.executeUpdate(regUser);
			       
			       
			       response.setContentType("String");

				    
				   response.getWriter().write("Successful");
			        
			      } else {

			        do {
			          
			        	
			        	System.out.print("USer already exists");
			        	
			        	response.setContentType("String");

					    
						   response.getWriter().write("exists");
			        	
			        	
			        } while (rs.next());
			      }
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	  //  response.setContentType("String");
	    
	   System.out.println("user "+email+" password  "+ password);
	    
	   
	   
	   
	   
	   
	   
 }
	
	

	
	
	
	
	

}
