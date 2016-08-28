package ecole.Bean;

import java.util.ArrayList;

import ecole.DAO.AdresseDAO;
import ecole.Exception.DAOException;
import ecole.Exception.InputInvalidException;
import ecole.Exception.InputValueTooLongException;

public class AdresseBean {
	// Attributs de l'instance representant les données affichées
	private int id;
	private String voie;
	private String cp;
	private String ville;
	private AdresseDAO adr;
	
	public int getId() {
		return id;
	}
	private void setId(int id) {
		this.id = id;
	}
	public String getVoie() {
		return voie;
	}
	public void setVoie(String voie) {
		this.voie = voie;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	public static AdresseBean read(int id) throws DAOException, InputValueTooLongException, InputInvalidException{
		AdresseBean adr = null;
		AdresseDAO adrDAO = null;
		adrDAO = AdresseDAO.dbSelectFromId(id);
		if(adrDAO!=null){
			adr = new AdresseBean();
			adr.setId(adrDAO.getId());
			adr.setVoie(adrDAO.getVoie());
			adr.setCp(adrDAO.getCodePostal());
			adr.setVille(adrDAO.getVille());
			adr.adr = adrDAO;
		}
		return adr;
	}
	
	public static ArrayList<AdresseBean> readAll() throws DAOException, InputValueTooLongException, InputInvalidException{
		 ArrayList<AdresseBean> listAdr = new ArrayList<AdresseBean>();
		 ArrayList<AdresseDAO> listDAO = null;
		 listDAO = AdresseDAO.dbSelectAll();
		 for(AdresseDAO adrDAO : listDAO){
			 AdresseBean adr = new AdresseBean();
			 adr = new AdresseBean();
			 adr.setId(adrDAO.getId());
			 adr.setVoie(adrDAO.getVoie());
			 adr.setCp(adrDAO.getCodePostal());
			 adr.setVille(adrDAO.getVille());
			 adr.adr = adrDAO;
			 listAdr.add(adr);
		 }
		 return listAdr;
	}
	
	public void create() throws DAOException, InputValueTooLongException, InputInvalidException{
		AdresseDAO adrDAO = new AdresseDAO(this.voie, this.cp, this.ville);
		adrDAO.dbInsert();
		this.adr=adrDAO;
		this.id=adrDAO.getId();
	}
	
	public void update() throws DAOException, InputValueTooLongException, InputInvalidException{
		this.adr.setVoie(this.voie);
		this.adr.setCodePostal(this.cp);
		this.adr.setVille(this.ville);
		this.adr.dbUpdate();
	}
	
	public void delete() throws DAOException, InputValueTooLongException, InputInvalidException{
		this.adr.dbDelete();
	}
	
}
