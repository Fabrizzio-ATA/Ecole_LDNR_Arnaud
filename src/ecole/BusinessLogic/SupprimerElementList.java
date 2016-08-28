package ecole.BusinessLogic;

import javax.servlet.http.HttpServletRequest;

import ecole.Bean.ClasseBean;
import ecole.Bean.EleveBean;
import ecole.Bean.EnseignantBean;
import ecole.Exception.DAOException;
import ecole.Exception.InputInvalidException;
import ecole.Exception.InputValueTooLongException;

public class SupprimerElementList {
	public static final String VAR_ERREUR = "msg_erreur";
	
	public String delete(HttpServletRequest request){
		String StrId = request.getParameter("id");
		int id = Integer.valueOf(StrId);
		String obj = request.getParameter("obj");
		if(obj.equalsIgnoreCase("el")){
			try {
				EleveBean el = EleveBean.read(id);
				if(el!=null) el.delete();
			} catch (DAOException | InputValueTooLongException | InputInvalidException e) {
				request.setAttribute(VAR_ERREUR, Util.genErrMsg(e));
			}
		}
		if(obj.equalsIgnoreCase("en")){
			try {
				EnseignantBean en = EnseignantBean.read(id);
				if(en!=null) en.delete();
			} catch (DAOException | InputValueTooLongException | InputInvalidException e) {
				request.setAttribute(VAR_ERREUR, Util.genErrMsg(e));
			}
		}
		if(obj.equalsIgnoreCase("cl")){
			try {
				ClasseBean cl = ClasseBean.read(id);
				if(cl!=null)	cl.delete();
			} catch (DAOException | InputValueTooLongException | InputInvalidException e) {
				request.setAttribute(VAR_ERREUR, Util.genErrMsg(e));
			}
		}
		return obj;
	}
	
}
