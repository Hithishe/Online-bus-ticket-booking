package com.servlet.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/login")

public class LoginServlet extends HttpServlet {
	  protected  void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	        //get PrintWriter
	        PrintWriter pw = res.getWriter();
	        //set Content type
	        res.setContentType("text/hmtl");
	        //read the form values
	        String name = req.getParameter("name");
	        String password = req.getParameter("password");
	        HttpSession session = req.getSession();
	        RequestDispatcher dispatcher = null;
	        
	        
	        
	        //load the jdbc driver
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        //create the connection
	        try(Connection con = DriverManager.getConnection("jdbc:mysql:///firstdb","root","Kavya@321");
	                PreparedStatement ps = con.prepareStatement("select * from info where name = ? and password = ?");){
	            //set the values
	            ps.setString(1, name);
	            ps.setString(2, password);
	           
	           ResultSet rs=ps.executeQuery();
	           if(rs.next()) {
	        	   session.setAttribute("name",rs.getString("name"));
	        	   dispatcher = req.getRequestDispatcher("index.jsp");
	           }else {
	        	   req.setAttribute("status", "failed");
	        	   dispatcher = req.getRequestDispatcher("hello.jsp");
	           }
	           dispatcher.forward(req, res);
	          

	            
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
