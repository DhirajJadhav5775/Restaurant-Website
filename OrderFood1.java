package com.servlet.OrderFood;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/OrderFood1")
public class OrderFood1 extends HttpServlet 
{
	//Create the query
	private static final String INSERT_QUERY = "insert into OrderFood(item,quantity,name,email,phone,address)values(?,?,?,?,?,?)";
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		//get PrintWriter
		PrintWriter pw = response.getWriter();
		//set Content Type
		response.setContentType("text/html");
		//read the form values
		String SelectItem = request.getParameter("item");
		String Quantity = request.getParameter("quantity");
		String YourName = request.getParameter("name");
		String YourEmail = request.getParameter("email");
		String YourPhone = request.getParameter("phone");
		String Address = request.getParameter("address");
		
		//load the jdbc driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//create the connection
		try {
			String url = "jdbc:mysql://localhost:3306/restaurant";
			String root = "root";
			String pass = "Dhiraj@5775";
			Connection connection = DriverManager.getConnection(url,root,pass);
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			ps.setString(1, SelectItem);
			ps.setString(2, Quantity);
			ps.setString(3, YourName);
			ps.setString(4, YourEmail);
			ps.setString(5, YourPhone);
			ps.setString(6, Address);
					
			int count = ps.executeUpdate();
			if(count == 0)
			{
				pw.println("Order Completed Succesfully");
			}
			else
			{
				pw.println("OOPS!. Try Again");
			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			pw.println(se.getMessage());
			se.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			pw.println(e.getMessage());
			e.printStackTrace();
		}
		
		//close the stream
		pw.close();
}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		doGet(request,response);
	}
}
