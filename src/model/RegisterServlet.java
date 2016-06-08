package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import netbank.*;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String repeatpassword=request.getParameter("repeatpassword");
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		String language=request.getParameter("language");
		String country=request.getParameter("country");
		
		// Check if username is available
		if(!password.equals(repeatpassword)) {
			request.setAttribute("message", "Passwords not matching");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("Register.jsp");
			dispatcher.forward(request, response);
			response.sendRedirect("Register.jsp");
		} else {
			String salt = Hash.getSalt();
			servle.getDb().setters("INSERT INTO \"DTUGRP04\".\"customers\" VALUES ('"+UUID.randomUUID()
			+ "','"+name+"','"+address+ "','"+Hash.SHA512(password,salt)+ "','"+salt+ "','"+country+ "');");
		}
		
		out.close(); 
	}

}
