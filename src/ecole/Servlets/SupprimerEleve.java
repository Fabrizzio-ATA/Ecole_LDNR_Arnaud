package ecole.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecole.BusinessLogic.AfficherEleveListe;
import ecole.BusinessLogic.SupprimerElementList;

/**
 * Servlet implementation class SupprimerElement
 */

public class SupprimerEleve extends HttpServlet {
	
	private static final long serialVersionUID = -3191824400833801218L;
	private static final String VIEW_LIST = "/WEB-INF/listeEleve.jsp";   
	private static final String VIEW_CONF = "/WEB-INF/confirmationSuppression.jsp";
	/**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerEleve() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	
		this.getServletContext().getRequestDispatcher( VIEW_CONF ).forward( request, response );
	}
    
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		SupprimerElementList supr = new SupprimerElementList();
		supr.delete(request);
		AfficherEleveListe display = new AfficherEleveListe();
		display.getAll(request);
		this.getServletContext().getRequestDispatcher( VIEW_LIST ).forward( request, response );
	}

}
