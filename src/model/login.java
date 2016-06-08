package model;

import netbank.*;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.*;
import java.util.UUID;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		ResultSet res = servle.getDb().getters("SELECT * FROM \"DTUGRP04\".\"customers\" WHERE username=" + username +";" );
		if(Dao.loginValidate(res,password)) {
			UUID cusID = UUID.fromString(res.getString(1));
			
			request.setAttribute("sharedId", res); // add to request
			request.getSession().setAttribute("sharedId", res); // add to session
			this.getServletConfig().getServletContext().setAttribute("sharedId", res);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("MainMenu.jsp");
			dispatcher.forward(request, response);
		} else {
			out.print("Username or password error");
			response.sendRedirect("LoginFail.jsp");
		}
		out.close(); 
	}
}
