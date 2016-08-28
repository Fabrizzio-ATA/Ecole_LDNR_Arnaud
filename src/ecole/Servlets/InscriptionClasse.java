package ecole.Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecole.Bean.ClasseBean;
import ecole.Bean.EnseignantBean;
import ecole.BusinessLogic.InscriptionClasseForm;
import ecole.Data.Niveaux;


/**
 * Servlet implementation class IncriptionClasse
 */

public class InscriptionClasse extends HttpServlet {
	
	private static final long serialVersionUID = 8484294393019570357L;
	private static final String VUE_FORM = "/WEB-INF/inscriptionClasse.jsp";
	public static final String VAR_LISTE = "liste_enseignant";
	public static final String VAR_CLASSE = "classe";
	public static final String VAR_NIVEAUX = "listeNiveaux";
	
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* À la réception d'une requête GET, simple affichage du formulaire */
    	
    	InscriptionClasseForm display =  new InscriptionClasseForm();
    	ClasseBean cl = display.getById(request);
    	request.setAttribute(VAR_CLASSE, cl);
    	ArrayList<EnseignantBean> listEns = display.getListEns(request);
    	request.setAttribute(VAR_LISTE, listEns);
    	Niveaux[] listNiv = Niveaux.values();
    	request.setAttribute(VAR_NIVEAUX, listNiv);
        this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	InscriptionClasseForm form =  new InscriptionClasseForm();
    	ArrayList<EnseignantBean> listEns = form.getListEns(request);
    	ClasseBean cl = form.inscrireEnseignant(request);
    	request.setAttribute(VAR_LISTE, listEns);
    	request.setAttribute("form", form);
    	request.setAttribute(VAR_CLASSE, cl);
    	Niveaux[] listNiv = Niveaux.values();
    	request.setAttribute(VAR_NIVEAUX, listNiv);
        this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
    }
   
}
