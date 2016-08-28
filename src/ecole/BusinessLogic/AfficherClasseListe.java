package ecole.BusinessLogic;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import ecole.Bean.ClasseBean;
import ecole.Exception.DAOException;
import ecole.Exception.InputInvalidException;
import ecole.Exception.InputValueTooLongException;

public class AfficherClasseListe {
	public static final String VAR_LISTE = "listClasse";
	public static final String VAR_ERREUR = "msg_erreur";
	
	public void getAll(HttpServletRequest request){
		ArrayList<ClasseBean> listCl = new ArrayList<ClasseBean>();
		try {
			listCl = ClasseBean.readAll();
			request.setAttribute(VAR_LISTE, listCl);
		} catch (DAOException | InputValueTooLongException | InputInvalidException e) {
			request.setAttribute(VAR_ERREUR, Util.genErrMsg(e));
		}
	}
}
