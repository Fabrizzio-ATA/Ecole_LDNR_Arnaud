package ecole.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecole.Bean.EnseignantBean;
import ecole.BusinessLogic.InscriptionEnseignantForm;


public class InscriptionEnseignant extends HttpServlet {
	private static final long serialVersionUID = -5610039168573430172L;
	private static final String VUE_FORM = "/WEB-INF/inscriptionEnseignant.jsp";
	public static final String VAR_ENS = "enseignant";
	
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* À la réception d'une requête GET, simple affichage du formulaire */
    	InscriptionEnseignantForm display = new InscriptionEnseignantForm();
    	EnseignantBean en = display.getById(request);
    	request.setAttribute(VAR_ENS, en);
        this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
    	// Cree une form pour valider les donnees enseignants et eventuellement
    	// renvoyer les erreurs
    	InscriptionEnseignantForm form = new InscriptionEnseignantForm();
    	EnseignantBean ensBean = form.inscrireEnseignant(request);
    	request.setAttribute("form", form);
    	request.setAttribute(VAR_ENS, ensBean);
    	this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
    	
    }
}