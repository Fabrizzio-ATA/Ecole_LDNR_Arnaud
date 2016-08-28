package ecole.BusinessLogic;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ecole.Bean.AdresseBean;
import ecole.Bean.CiviliteBean;
import ecole.Bean.EnseignantBean;
import ecole.Exception.DAOException;
import ecole.Exception.InputInvalidException;
import ecole.Exception.InputValueTooLongException;

public class InscriptionEnseignantForm {

	// Noms des champs de la jsp
	private final static String CHAMP_NOM = "nom";
	private final static String CHAMP_PRENOM = "prenom";
	private final static String CHAMP_SEXE = "sexe";
	private final static String CHAMP_DATE = "dob";
	private final static String CHAMP_VOIE = "voie";
	private final static String CHAMP_CP = "cp";
	private final static String CHAMP_VILLE = "ville";
	public static final String VAR_ERREUR = "msg_erreur";
	

	// Attributs privés

	private Map<String, String> erreurs;
	private String rapport;

	// Constructeur par defaut
	public InscriptionEnseignantForm() {
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
	public EnseignantBean inscrireEnseignant(HttpServletRequest request) {


		// On recupere les parametres transmis par le formulaire
		String prenom = getValeurChamp( request, CHAMP_PRENOM );
		String nom = getValeurChamp( request, CHAMP_NOM );
		String date = getValeurChamp( request, CHAMP_DATE );
		String sexe = getValeurChamp( request, CHAMP_SEXE );
		String voie = getValeurChamp( request, CHAMP_VOIE );
		String cp = getValeurChamp( request, CHAMP_CP );
		String ville = getValeurChamp( request, CHAMP_VILLE );

		try {
			Util.validationPrenom(prenom);
		} catch (InputValueTooLongException | InputInvalidException e) {
			setErreur( CHAMP_PRENOM, e.getMessage() );
		}
		try {
			Util.validationNom(nom);
		} catch (InputValueTooLongException | InputInvalidException e) {
			setErreur( CHAMP_NOM, e.getMessage() );
		}
		try {
			Util.validationDoB(date);
		} catch (InputInvalidException | InputValueTooLongException e) {
			setErreur( CHAMP_DATE, e.getMessage() );
		}
		try {
			Util.validationSexe(sexe);
		} catch (InputInvalidException | InputValueTooLongException e) {
			setErreur( CHAMP_SEXE, e.getMessage() );
		}
		try {
			Util.validationVoie(voie);
		} catch (InputInvalidException | InputValueTooLongException e) {
			setErreur( CHAMP_VOIE, e.getMessage() );
		}
		try {
			Util.validationCp(cp);
		} catch (InputInvalidException | InputValueTooLongException e) {
			setErreur( CHAMP_CP, e.getMessage() );
		}
		try {
			Util.validationVille(ville);
		} catch (InputInvalidException | InputValueTooLongException e) {
			setErreur( CHAMP_VILLE, e.getMessage() );
		}
		
		boolean exist = false;
		EnseignantBean en =  getById(request);
		if(en!=null){
			CiviliteBean civ = en.getCiv();
			civ.setNom(nom);
			civ.setPrenom(prenom);
			civ.setDate(date);
			civ.setSexe(sexe);
			en.setCiv(civ);
			AdresseBean adr = en.getAdr();
			adr.setVoie(voie);
			adr.setCp(cp);
			adr.setVille(ville);
			en.setAdr(adr);
			exist = true;
		}
		else{
			en = new EnseignantBean();
			CiviliteBean civ = new CiviliteBean();
			civ.setNom(nom);
			civ.setPrenom(prenom);
			civ.setDate(date);
			civ.setSexe(sexe);
			en.setCiv(civ);
			AdresseBean adr = new AdresseBean();
			adr.setVoie(voie);
			adr.setCp(cp);
			adr.setVille(ville);
			en.setAdr(adr);
		}
		if(erreurs.isEmpty()){
			
			if(exist){
				try {
					en.update();
					rapport="La fiche enseignant a bien été mise à jour.";
				} catch (DAOException | InputValueTooLongException | InputInvalidException e) {
					request.setAttribute(VAR_ERREUR, Util.genErrMsg(e));
				}
			}
			else{
			
				
				try {
					en.create();
					rapport="La fiche enseignant a bien été créée.";
				} catch (DAOException | InputValueTooLongException | InputInvalidException e) {
					request.setAttribute(VAR_ERREUR, Util.genErrMsg(e));
				}
			}
			
		}
		else
		{
			rapport="Merci de bien vouloir vérifier les données saisies.";
		}
		return en;
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

	public EnseignantBean getById(HttpServletRequest request){
		String id = request.getParameter("id");
		EnseignantBean en = null;
		if(id!=null && !id.isEmpty()){
			try {
				en = EnseignantBean.read(Integer.valueOf(id));
				
			} catch (NumberFormatException | DAOException | InputValueTooLongException | InputInvalidException e) {
				request.setAttribute(VAR_ERREUR, Util.genErrMsg(e));
			}
		}
		return en;
	}

}