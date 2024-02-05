package com.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")

public class UserServlet extends HttpServlet {
	  private static final String INSERT_QUERY ="INSERT INTO INFO(NAME,PASSWORD,EMAIL,MOBILE) VALUES(?,?,?,?)";
	  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	        //get PrintWriter
	        PrintWriter pw = res.getWriter();
	        //set Content type
	        res.setContentType("text/hmtl");
	        //read the form values
	        String name = req.getParameter("name");
	        String password = req.getParameter("password");
	        String email = req.getParameter("email");
	        String mobile = req.getParameter("mobile");
	        //load the jdbc driver
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        //create the connection
	        try(Connection con = DriverManager.getConnection("jdbc:mysql:///firstdb","root","Kavya@321");
	                PreparedStatement ps = con.prepareStatement(INSERT_QUERY);){
	            //set the values
	            ps.setString(1, name);
	            ps.setString(2, password);
	            ps.setString(3, email);
	            ps.setString(4, mobile);

	            //execute the query
	            int count = ps.executeUpdate();

	            if(count==0) {
	                pw.println("ACCOUNT NOT CREATED");
	            }else {
	                pw.println("ACCOUNT CREATED SUCCESFULLY");
	            }
	        }catch(SQLException se) {
	            pw.println(se.getMessage());
	            se.printStackTrace();
	        }catch(Exception e) {
	            pw.println(e.getMessage());
	            e.printStackTrace();
	        }

	        //close the stream
	        pw.close();
	    }

	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        // TODO Auto-generated method stub
	        doGet(req, resp);
	    }

}
