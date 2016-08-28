package ecole.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecole.BusinessLogic.AfficherEleveListe;


public class AfficherIndex extends HttpServlet {
	
	private static final long serialVersionUID = -2019952750468954428L;
	private static final String VIEW = "/WEB-INF/index.jsp";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficherIndex() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		this.getServletContext().getRequestDispatcher( VIEW ).forward( request, response );
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AfficherEleveListe display = new AfficherEleveListe();
    	display.chercherParNom(request);
		doGet(request, response);
	}

}