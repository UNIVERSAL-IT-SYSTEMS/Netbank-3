package model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netbank.*;

/**
 * Servlet implementation class SearchByIDServlet
 */
@WebServlet("/SearchByIDServlet")
public class SearchByIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String id=request.getParameter("id");
		String empid=request.getParameter("empid");
		String idtype=request.getParameter("IDType");
		
		for(int i = 0; i<IDType.values().length; i++) {
			if(idtype == IDType.values()[i].toString()) {
				
			}
		}
	}
}
