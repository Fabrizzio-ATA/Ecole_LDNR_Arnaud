package ecole.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecole.BusinessLogic.AfficherEleveListe;

/**
 * Servlet implementation class ListeEleve
 */
@WebServlet("/ListeEleve")
public class ListeEleve extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW_LIST = "/WEB-INF/listeEleve.jsp";   
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListeEleve() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AfficherEleveListe display = new AfficherEleveListe();
		display.getAll(request);
		this.getServletContext().getRequestDispatcher( VIEW_LIST ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
