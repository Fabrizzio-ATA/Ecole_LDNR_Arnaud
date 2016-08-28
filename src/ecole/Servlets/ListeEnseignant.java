package ecole.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecole.BusinessLogic.AfficherEnseignantListe;

/**
 * Servlet implementation class ListeEnseignant
 */

public class ListeEnseignant extends HttpServlet {
	
	private static final long serialVersionUID = -7607149787285435952L;
	private static final String VIEW_LIST = "/WEB-INF/listeEnseignant.jsp";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeEnseignant() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AfficherEnseignantListe display = new AfficherEnseignantListe();
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
