package com.verizon.sba3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping(value="/")
public class Sba3DevopsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sba3DevopsApplication.class, args);
	}
	
	
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public List<String> getUsers() {
		List<String> result = new ArrayList<String>();
		try
	    {
	      // create our mysql database connection
	      String myDriver = "com.mysql.jdbc.Driver";
	      String myUrl = "jdbc:mysql://localhost:3306/db1?useSSL=false";
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "admin");
	      
	      // our SQL SELECT query. 
	      // if you only need a few columns, specify them by name instead of using "*"
	      String query = "SELECT * FROM customer";

	      // create the java statement
	      Statement st = conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	        int cust_id = rs.getInt("cust_id");
	        String customerName = rs.getString("customerName");
	        String dateOfJoining = rs.getString("dateOfJoining");
	        String city = rs.getString("city");
	        String phoneNumber = rs.getString("phoneNumber");
	        
	        // print the results
	        // System.out.format("%d %s, %s, %s\n", id, firstName, lastName, email);
	        result.add(Integer.toString(cust_id)+"  "+customerName+"  "+dateOfJoining+"  "+city+"  "+phoneNumber);  
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return result;
	}
}
