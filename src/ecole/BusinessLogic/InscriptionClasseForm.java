package ecole.BusinessLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ecole.Bean.ClasseBean;
import ecole.Bean.EnseignantBean;
import ecole.Exception.DAOException;
import ecole.Exception.InputInvalidException;
import ecole.Exception.InputValueTooLongException;

public class InscriptionClasseForm {

	
	public static final String VAR_ERREUR = "msg_erreur";
	// Noms des champs de la jsp
	private final static String CHAMP_NOM = "nom";
	private final static String CHAMP_NIVEAU = "niveau";
	private final static String CHAMP_ID_ENS = "ens";




	// Attributs privés

	private Map<String, String> erreurs;
	private String rapport;

	// Constructeur par defaut
	public InscriptionClasseForm() {
		rapport = "";
		erreurs = new HashMap<String, String>();
	}

	// Accesseurs pour informer le formulaire
	public String getRapport() {
		return rapport;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	/**
	 *  Rôle : inscrire un nouvel enseignant
	 *  1) Valide les données et jette une exception si elles sont
	 *  invalides, tout en remplissant une map d'erreurs
	 *  2) Renvoie les données à la fiche
	 * @param request
	 * @return
	 */
	public ClasseBean inscrireEnseignant(HttpServletRequest request) {


		// On recupere les parametres transmis par le formulaire

		String nom = getValeurChamp( request, CHAMP_NOM );
		String niveau = getValeurChamp( request, CHAMP_NIVEAU );
		String ens = getValeurChamp( request, CHAMP_ID_ENS );
		Integer id_ens = Integer.valueOf(ens);
		if(id_ens==0) id_ens=null;




		try {
			Util.validationNom(nom);
		} catch (InputValueTooLongException | InputInvalidException e) {
			setErreur( CHAMP_NOM, e.getMessage() );
		}
		try {
			Util.validationNiveau(niveau);
		} catch (InputInvalidException | InputValueTooLongException e) {
			setErreur( CHAMP_NIVEAU, e.getMessage() );
		}
		
		boolean exist = false;
		ClasseBean cl = getById(request);
		if(cl!=null){
			exist=true;
			cl.setNom(nom);
			cl.setNiveaux(niveau);
			EnseignantBean en=null;
			try {
				if(id_ens!=null && id_ens!=0){
					en = EnseignantBean.read(id_ens);
					cl.setEnseignant(en);
				}
			} catch (DAOException | InputValueTooLongException | InputInvalidException e) {
				request.setAttribute(VAR_ERREUR, Util.genErrMsg(e));
			}
			
		}
		else{
			cl = new ClasseBean();
			cl.setNom(nom);
			cl.setNiveaux(niveau);
			EnseignantBean en;
			try {
				if(id_ens!=null){
				en = EnseignantBean.read(id_ens);
				cl.setEnseignant(en);
				}
			} catch (DAOException | InputValueTooLongException | InputInvalidException e) {
				request.setAttribute(VAR_ERREUR, Util.genErrMsg(e));
			}
			
		}
		
		
		if(erreurs.isEmpty()){

			if(exist){
				
				try {
					
					cl.update();
					rapport="La fiche de la classe a bien été mise à jour.";

				} catch (DAOException | InputValueTooLongException | InputInvalidException e) {
					request.setAttribute(VAR_ERREUR, Util.genErrMsg(e));
				}		
			}
			else{
				

				try {
					
					cl.create();
					rapport="La fiche de la classe a bien été créée.";
				} catch (DAOException | InputValueTooLongException | InputInvalidException e) {
					request.setAttribute(VAR_ERREUR, Util.genErrMsg(e));
				}


			}
		}
		else
		{
			rapport="Merci de bien vouloir vérifier les données saisies.";
		}
		return cl;
	}


	private void setErreur( String champ, String message ) {
		erreurs.put( champ, message );
	}
	/**
	 * Rôle : pas grand chose sinon éliminer les espaces avant et après
	 * @param nomParam : paramètre de la requête à valider
	 * @return : chaine nettoyée ou chaine vide au minimum
	 */
	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
		String valeur = request.getParameter( nomChamp );
		if ( valeur == null || valeur.trim().length() == 0 ) {
			return null;
		} 
		else {
			return valeur.trim();
		}
	}

	public ClasseBean getById(HttpServletRequest request){
		ClasseBean cl = null;
		String id = request.getParameter("id");
		if(id!=null && !id.isEmpty()){
			try {
				cl = ClasseBean.read((int) Integer.valueOf(id));
				
			} catch (NumberFormatException | DAOException | InputValueTooLongException | InputInvalidException e) {
				request.setAttribute(VAR_ERREUR, Util.genErrMsg(e));
			}
		}
		return cl;
	}
	
	public ArrayList<EnseignantBean> getListEns(HttpServletRequest request){

		ArrayList<EnseignantBean> listEns = new ArrayList<EnseignantBean>();
		try {
			listEns = EnseignantBean.readAll();
		} catch (DAOException | InputValueTooLongException | InputInvalidException e) {
			request.setAttribute(VAR_ERREUR, Util.genErrMsg(e));
		}
		
		return listEns;

	}
}
