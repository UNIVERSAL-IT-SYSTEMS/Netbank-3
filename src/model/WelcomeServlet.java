package model;

import netbank.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WelcomeServlet
 */
@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		DatabaseGet.getAccount("cusID", ID);

		String shared = "shared";
		request.setAttribute("sharedId", shared); // add to request
		request.getSession().setAttribute("sharedId", shared); // add to session
		this.getServletConfig().getServletContext().setAttribute("sharedId", shared);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("MainMenu.jsp");
		dispatcher.forward(request, response);
		
		response.sendRedirect("MainMenu.jsp");
	}

}
