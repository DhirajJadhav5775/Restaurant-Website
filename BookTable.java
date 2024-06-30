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

@WebServlet("/BookTable")
public class BookTable extends HttpServlet
{
	private static final String INSERT_QUERY = "insert into BookTable(FullName,EmailAddress,PhoneNumber,BookDate,BookTime,NumberOfGuests,SpecialRequests)values(?,?,?,?,?,?,?)";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String FullName = req.getParameter("name");
		String EmailAddress = req.getParameter("email");
		String PhoneNumber = req.getParameter("phone");
		String BookDate = req.getParameter("date");
		String BookTime = req.getParameter("time");
		String NumberOfGuests = req.getParameter("guests");
		String SpecialRequest = req.getParameter("requests");
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            pw.println("Error loading database driver.");
            e.printStackTrace(pw);
            return;
        }
		
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant", "root", "Dhiraj@5775");
	             PreparedStatement ps = connection.prepareStatement(INSERT_QUERY))
		{
			ps.setString(1, FullName);
			ps.setString(2, EmailAddress);
			ps.setString(3, PhoneNumber);
			ps.setString(4, BookDate);
			ps.setString(5, BookTime);
			ps.setString(6, NumberOfGuests);
			ps.setString(7, SpecialRequest);
					
			int count = ps.executeUpdate();
			if(count == 0)
			{
				pw.println("Order Completed Succesfully");
			}
			else
			{
				pw.println("OOPS!. Try Again");
			}
		} 
		catch (SQLException se) {
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
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		doGet(req,res);
	}
}
