package ecole.BusinessLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ecole.Bean.AdresseBean;
import ecole.Bean.CiviliteBean;
import ecole.Bean.ClasseBean;
import ecole.Bean.EleveBean;
import ecole.Exception.DAOException;
import ecole.Exception.InputInvalidException;
import ecole.Exception.InputValueTooLongException;




public class InscriptionEleveForm {
	/* Des constantes */
	private static final String CHAMP_PRENOM 	= "prenom";
	private static final String CHAMP_NOM 		= "nom";
	private static final String CHAMP_DOB 		= "dob";
	private static final String CHAMP_SEXE 		= "sexe";
	private static final String CHAMP_ADR1		= "adr1";
	private static final String CHAMP_CP1 		= "cp1";
	private static final String CHAMP_VILLE1	= "ville1";
	private static final String CHAMP_ADR2		= "adr2";
	private static final String CHAMP_CP2		= "cp2";
	private static final String CHAMP_VILLE2	= "ville2";
	private static final String CHAMP_ID_CLASSE = "clas"; 


	public static final String VAR_ERREUR = "msg_erreur";
	// Attributs privés

	private Map<String, String> erreurs;
	private String rapport;

	// Constructeur par defaut
	public InscriptionEleveForm() {
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
	public EleveBean inscrireEleve(HttpServletRequest request) {


		// On recupere les parametres transmis par le formulaire
		String prenom = getValeurChamp( request, CHAMP_PRENOM );
		String nom = getValeurChamp( request, CHAMP_NOM );
		String date = getValeurChamp( request, CHAMP_DOB );
		String sexe = getValeurChamp( request, CHAMP_SEXE );
		String voie1 = getValeurChamp( request, CHAMP_ADR1 );
		String cp1 = getValeurChamp( request, CHAMP_CP1 );
		String ville1 = getValeurChamp( request, CHAMP_VILLE1 );
		String voie2 = getValeurChamp( request, CHAMP_ADR2 );
		String cp2 = getValeurChamp( request, CHAMP_CP2 );
		String ville2 = getValeurChamp( request, CHAMP_VILLE2 );
		String id_classe_str = getValeurChamp( request, CHAMP_ID_CLASSE);
		Integer id_classe = Integer.valueOf(id_classe_str);
		if(id_classe==0) id_classe = null;
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
			setErreur( CHAMP_DOB, e.getMessage() );
		}
		try {
			Util.validationSexe(sexe);
		} catch (InputInvalidException | InputValueTooLongException e) {
			setErreur( CHAMP_SEXE, e.getMessage() );
		}
		try {
			Util.validationVoie(voie1);
		} catch (InputInvalidException | InputValueTooLongException e) {
			setErreur( CHAMP_ADR1, e.getMessage() );
		}
		try {
			Util.validationVoieOp(voie2);
		} catch (InputInvalidException | InputValueTooLongException e) {
			setErreur( CHAMP_ADR2, e.getMessage() );
		}
		try {
			Util.validationCp(cp1);
		} catch (InputInvalidException | InputValueTooLongException e) {
			setErreur( CHAMP_CP1, e.getMessage() );
		}
		try {
			Util.validationCpOp(cp2);
		} catch (InputInvalidException | InputValueTooLongException e) {
			setErreur( CHAMP_CP2, e.getMessage() );
		}
		try {
			Util.validationVille(ville1);
		} catch (InputInvalidException | InputValueTooLongException e) {
			setErreur( CHAMP_VILLE1, e.getMessage() );
		}
		try {
			Util.validationVilleOp(ville2);
		} catch (InputInvalidException | InputValueTooLongException e) {
			setErreur( CHAMP_VILLE2, e.getMessage() );
		}

		boolean isAdr1Empty = voie1==null || cp1==null || ville1==null || voie1.isEmpty() || cp1.isEmpty() || ville1.isEmpty();
		boolean isAdr2Empty = voie2==null || cp2==null || ville2==null || voie2.isEmpty() || cp2.isEmpty() || ville2.isEmpty();
		boolean exist = false;
		EleveBean el =  getById(request);

		if(el!=null){
			exist = true;
			CiviliteBean civ = el.getCiv();
			civ.setNom(nom);
			civ.setPrenom(prenom);
			civ.setDate(date);
			civ.setSexe(sexe);
			el.setCiv(civ);
			el.setId_classe(id_classe);
			ArrayList<AdresseBean> listAdr = new ArrayList<AdresseBean>();
			listAdr= el.getAdr();
			if(!isAdr1Empty){
				if (listAdr.size()>0 && listAdr.get(0) != null){
					listAdr.get(0).setVoie(voie1);
					listAdr.get(0).setCp(cp1);
					listAdr.get(0).setVille(ville1);
				}
				else{
					AdresseBean adr = new AdresseBean();
					adr.setVoie(voie1);
					adr.setCp(cp1);
					adr.setVille(ville1);
					try {
						el.addAdr(adr);
					} catch (DAOException | InputValueTooLongException | InputInvalidException e) {
						request.setAttribute(VAR_ERREUR, Util.genErrMsg(e));

					}
				}
			}
			if(!isAdr2Empty){
				if (listAdr.size()>1 && listAdr.get(1) != null){
					listAdr.get(1).setVoie(voie2);
					listAdr.get(1).setCp(cp2);
					listAdr.get(1).setVille(ville2);
				}
				else{
					AdresseBean adr = new AdresseBean();
					adr.setVoie(voie2);
					adr.setCp(cp2);
					adr.setVille(ville2);
					try {
						el.addAdr(adr);
					} catch (DAOException | InputValueTooLongException | InputInvalidException e) {
						request.setAttribute(VAR_ERREUR, Util.genErrMsg(e));

					}
				}
			}
		}
		else{
			el = new EleveBean();
			CiviliteBean civ = new CiviliteBean();
			civ.setNom(nom);
			civ.setPrenom(prenom);
			civ.setDate(date);
			civ.setSexe(sexe);
			el.setCiv(civ);
			el.setId_classe(id_classe);
			ArrayList<AdresseBean> listAdr = new ArrayList<AdresseBean>();
			if(!isAdr1Empty){
				AdresseBean adr = new AdresseBean();
				adr.setVoie(voie1);
				adr.setCp(cp1);
				adr.setVille(ville1);
				listAdr.add(adr);
			}
			if(!isAdr2Empty){
				AdresseBean adr = new AdresseBean();
				adr.setVoie(voie2);
				adr.setCp(cp2);
				adr.setVille(ville2);
				listAdr.add(adr);
			}
			el.setAdr(listAdr);
		}

		if(erreurs.isEmpty()){

			if(exist){

				try {
					el.update();
					rapport="La fiche élève a bien été mise à jour.";
				} catch (DAOException | InputValueTooLongException | InputInvalidException e) {
					request.setAttribute(VAR_ERREUR, Util.genErrMsg(e));
				}
			}
			else{

				try {
					el.create();
					rapport="La fiche élèvea bien été créée.";
				} catch (DAOException | InputValueTooLongException | InputInvalidException e) {
					request.setAttribute(VAR_ERREUR, Util.genErrMsg(e));
				}

			}
		}
		else
		{
			rapport="Merci de bien vouloir vérifier les données saisies.";
		}
		return el;
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

	public EleveBean getById( HttpServletRequest request ){
		String id = request.getParameter("id");
		EleveBean el = null;
		if(id!=null && !id.isEmpty()){
			try {
				el = EleveBean.read(Integer.valueOf(id));

			} catch (NumberFormatException | DAOException | InputValueTooLongException | InputInvalidException e) {
				request.setAttribute(VAR_ERREUR, Util.genErrMsg(e));
			}
		}
		return el;
	}

	public ArrayList<ClasseBean> getAllClasse(HttpServletRequest request ){
		ArrayList<ClasseBean> listCl = new ArrayList<ClasseBean>();
		try {
			listCl = ClasseBean.readAllSimple();
		} catch (DAOException | InputValueTooLongException | InputInvalidException e) {
			request.setAttribute(VAR_ERREUR, Util.genErrMsg(e));
		}
		return listCl;
	}


}
