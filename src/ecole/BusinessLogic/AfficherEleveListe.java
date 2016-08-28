package ecole.BusinessLogic;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import ecole.Bean.EleveBean;
import ecole.Exception.DAOException;
import ecole.Exception.InputInvalidException;
import ecole.Exception.InputValueTooLongException;

public class AfficherEleveListe {
	public static final String VAR_LISTE = "listEleve";
	public static final String VAR_ERREUR = "msg_erreur";

	public void getAll(HttpServletRequest request){
		ArrayList<EleveBean> listEl = new ArrayList<EleveBean>();
		try {
			listEl= EleveBean.readAll();
			request.setAttribute(VAR_LISTE, listEl);
		} catch (DAOException | InputValueTooLongException | InputInvalidException e) {
			request.setAttribute(VAR_ERREUR, Util.genErrMsg(e));
		}
	}

	public void chercherParNom(HttpServletRequest request){
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		if(nom!=null && prenom!=null){

			ArrayList<EleveBean> list = new ArrayList<EleveBean>();

			try {
				list = EleveBean.selectByName(nom, prenom);
				if(list!=null) {
					request.setAttribute(VAR_LISTE, list);
				}
			} catch (DAOException | InputValueTooLongException | InputInvalidException e) {
				request.setAttribute(VAR_ERREUR, Util.genErrMsg(e));					
			}	
		}
	}

}
