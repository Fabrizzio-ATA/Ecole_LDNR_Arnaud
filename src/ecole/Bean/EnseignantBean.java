package ecole.Bean;

import java.util.ArrayList;

import ecole.DAO.EnseignantDAO;
import ecole.Exception.DAOException;
import ecole.Exception.InputInvalidException;
import ecole.Exception.InputValueTooLongException;

public class EnseignantBean {
	private int id;
	private CiviliteBean civ;
	private AdresseBean adr;
	private EnseignantDAO ens;
	public int getId() {
		return id;
	}
	private void setId(int id) {
		this.id = id;
	}
	public CiviliteBean getCiv() {
		return civ;
	}
	public void setCiv(CiviliteBean civ) {
		this.civ = civ;
	}
	public AdresseBean getAdr() {
		return adr;
	}
	public void setAdr(AdresseBean adr) {
		this.adr = adr;
	}
	
	public static EnseignantBean read(int id) throws DAOException, InputValueTooLongException, InputInvalidException{
		EnseignantBean ens = null;
		EnseignantDAO ensDAO = null;
		ensDAO = EnseignantDAO.dbSelectFromId(id);
		if(ensDAO!=null){
			ens = new EnseignantBean();
			CiviliteBean civ = CiviliteBean.read(ensDAO.getId_civ());
			AdresseBean adr = AdresseBean.read(ensDAO.getId_adresse());
			ens.setId(ensDAO.getId());
			ens.setCiv(civ);
			ens.setAdr(adr);
			ens.ens = ensDAO;
		}
		return ens;
	}
	
	public static ArrayList<EnseignantBean> readAll() throws DAOException, InputValueTooLongException, InputInvalidException{
		 ArrayList<EnseignantBean> listEns = new ArrayList<EnseignantBean>();
		 ArrayList<EnseignantDAO> listDAO = null;
		 listDAO = EnseignantDAO.dbSelectAll();
		 for(EnseignantDAO ensDAO : listDAO){
			 EnseignantBean ens = new  EnseignantBean();
			 CiviliteBean civ = CiviliteBean.read(ensDAO.getId_civ());
			 AdresseBean adr = AdresseBean.read(ensDAO.getId_adresse());
			 ens.setId(ensDAO.getId());
			 ens.setCiv(civ);
			 ens.setAdr(adr);
			 listEns.add(ens);
		 }
		 return listEns;
	}
	
	public void create() throws DAOException, InputValueTooLongException, InputInvalidException{
		this.civ.create();
		this.adr.create();
		EnseignantDAO ensDAO = new EnseignantDAO(this.civ.getId(), this.adr.getId());
		ensDAO.dbInsert();
		this.ens = ensDAO;
		this.id = ensDAO.getId();
	}
	
	public void update() throws DAOException, InputValueTooLongException, InputInvalidException{
		this.civ.update();
		this.adr.update();
	}
	
	public void delete() throws DAOException, InputValueTooLongException, InputInvalidException{
		try {
			this.ens.dbDelete();
		} catch (DAOException e) {
			throw new DAOException("Impossible de supprimer cet enseignant car il est assigné à une classe");
		}
		this.civ.delete();
		this.adr.delete();
	}
	
}
