package com.snapdeal.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

public class HelloServlet extends HttpServlet {
	
	final static String dbURL = "jdbc:mysql://localhost:3306/DBNAME";
    final static String driver = "com.mysql.jdbc.Driver";
	Connection conn = null;
	Statement statemen = null;
	ResultSet rs = null;
	Object rsValue = null;
	   public int a;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
resp.setContentType("text/html; CHARSET=UTF-8");
		
		PrintWriter printr=resp.getWriter();
		
		try {
		       Class.forName(driver).newInstance();
				conn = DriverManager.getConnection(dbURL,"Username or root", "Password");
				statemen = conn.createStatement();
				rs = statemen.executeQuery("SELECT * FROM counter");
		     } catch (Exception e) {
		       System.out.print("Unable to make connection;");
		       System.out.print(e);
		     } 
		
//		try {
//			// rs=statemen.executeQuery();
//		} catch (SQLException e) {
//			System.out.println("teh result set");
//		}
		
		try {
			while(rs.next())
			{
			
			a=rs.getInt("count");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		try {
			statemen.executeUpdate("update counter set count=count+1;");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		a++;
		
		printr.println("<b>#Hits : "+a+"</b>");
		
		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
		
		resp.setContentType("text/html; CHARSET=UTF-8");
		
		PrintWriter printr=resp.getWriter();
		
		try {
		       Class.forName(driver).newInstance();
				conn = DriverManager.getConnection(dbURL,"root", "");
				statemen = conn.createStatement();
				rs = statemen.executeQuery("SELECT * FROM phones_tables");
		     } catch (Exception e) {
		       System.out.print("Unable to make connection;");
		       System.out.print(e);
		     } 
		
//		try {
//			// rs=statemen.executeQuery();
//		} catch (SQLException e) {
//			System.out.println("teh result set");
//		}
		
		try {
			if(rs!=null)
			{
			
			a=rs.getInt("count");
			}
			else
			{
				// printr.println(" the set is null ");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		try {
			statemen.executeUpdate("update counter set count=count+1 where count=a");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		a++;
		
		printr.println("<b>#Hits : "+a+"</b>");
		
		
		
	}

}


