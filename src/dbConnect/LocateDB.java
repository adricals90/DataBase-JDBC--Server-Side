package dbConnect;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LocateDB
 */
@WebServlet("/LocateDB")
public class LocateDB extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email= request.getParameter("email");
		String Url = request.getParameter("URL");
		String latutud = request.getParameter("Latitude");
		String longitud = request.getParameter("Longitude");

		 System.out.println("email : " + email);
		 PreparedStatement preparedStatement = null;
		 Connection connect = null;

	     String QueryAddEmail = "SELECT Holograms.URL, Holograms.Latitiude, Holograms.Longitude FROM Tables INNER JOIN Holograms ON Holograms.email=REGISTERUSER.email";

		try {

			Class.forName("com.mysql.jdbc.Driver");
			connect= DriverManager.getConnection("jdbc:mysql://mydatabase.ccabptaeh7ff.us-east-2.rds.amazonaws.com:3306/myDatabase?autoReconnect=true&useSSL=false", "myDatabase","db246890" );

			preparedStatement = connect.prepareStatement(QueryAddEmail);
			ResultSet rs= preparedStatement.executeQuery(QueryAddEmail);

			 if (rs.next() == false) {
			        System.out.println("ResultSet is empty for eclipse");
			       response.setContentType("String");
				   response.getWriter().write("failed");
			      } else {
			        do {
			        	System.out.print("EMAIL found");
			        	response.setContentType("String");
						   response.getWriter().write("true");
    		 			} while (rs.next());
			      }

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    response.setContentType("String");
	    System.out.println("user "+email);
	    System.out.println("url" + Url + "longitude" + longitud + "latitude" + latutud);

	  }
}

