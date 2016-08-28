package ecole.Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecole.Bean.ClasseBean;
import ecole.Bean.EleveBean;
import ecole.BusinessLogic.InscriptionEleveForm;


public class InscriptionEleve extends HttpServlet {

	private static final long serialVersionUID = -6253809315292458010L;
	private static final String VUE_FORM = "/WEB-INF/inscriptionEleve.jsp";
	public  static final String VAR_ELEVE 		= "eleveBean"; 
	private static final String VAR_LIST 		= "listeclasse"; 
	
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* À la réception d'une requête GET, simple affichage du formulaire */
    	InscriptionEleveForm display = new InscriptionEleveForm();
    	EleveBean el = display.getById(request);
    	request.setAttribute(VAR_ELEVE, el);
    	ArrayList<ClasseBean> listCl = new ArrayList<ClasseBean>();
    	listCl = display.getAllClasse(request);
    	request.setAttribute( VAR_LIST, listCl);
        this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	InscriptionEleveForm form = new InscriptionEleveForm();
    	ArrayList<ClasseBean> listCl = new ArrayList<ClasseBean>();
    	listCl = form.getAllClasse(request);
    	request.setAttribute( VAR_LIST, listCl);
    	EleveBean el = form.inscrireEleve(request);
    	request.setAttribute(VAR_ELEVE, el);
    	request.setAttribute("form", form);
    	this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
    }
   
}
