package ecole.BusinessLogic;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import ecole.Bean.EnseignantBean;
import ecole.Exception.DAOException;
import ecole.Exception.InputInvalidException;
import ecole.Exception.InputValueTooLongException;

public class AfficherEnseignantListe {
	public static final String VAR_LISTE = "listEns";
	public static final String VAR_ERREUR = "msg_erreur";
	
	public void getAll(HttpServletRequest request){
		ArrayList<EnseignantBean> listEns = new ArrayList<EnseignantBean>();
		try {
			listEns = EnseignantBean.readAll();
			request.setAttribute(VAR_LISTE, listEns);
		} catch (DAOException | InputValueTooLongException | InputInvalidException e) {
			request.setAttribute(VAR_ERREUR, Util.genErrMsg(e));
		}
	}
}
