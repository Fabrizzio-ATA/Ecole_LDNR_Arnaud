package ecole.Bean;

import java.text.Collator;
import java.util.ArrayList;

import ecole.DAO.EleveAdresseDAO;
import ecole.DAO.EleveDAO;
import ecole.Exception.DAOException;
import ecole.Exception.InputInvalidException;
import ecole.Exception.InputValueTooLongException;

public class EleveBean {
	private int id;
	private CiviliteBean civ;
	private ArrayList<AdresseBean> adr;
	private Integer id_classe;
	private EleveDAO el;
	private ArrayList<EleveAdresseDAO> elAdr;
	
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
	public ArrayList<AdresseBean> getAdr() {
		return adr;
	}
	public void setAdr(ArrayList<AdresseBean> adr) {
		this.adr = adr;
	}
	public Integer getId_classe() {
		return id_classe;
	}
	public void setId_classe(Integer id_classe) {
		this.id_classe = id_classe;
	}
	
	public static EleveBean read(int id) throws DAOException, InputValueTooLongException, InputInvalidException{
		EleveBean el = null;
		EleveDAO elDAO = null;
		elDAO = EleveDAO.dbSelectFromId(id);
		if(elDAO!=null){
			el = new EleveBean();
			CiviliteBean civ = CiviliteBean.read(elDAO.getId_civ());
			ArrayList<EleveAdresseDAO> listElAdrDAO = new ArrayList<EleveAdresseDAO>();
			ArrayList<AdresseBean> listAdr = null;
			listElAdrDAO = EleveAdresseDAO.dbSelectFromId(elDAO.getId());
			if(listElAdrDAO!=null){
				listAdr = new ArrayList<AdresseBean>();
				for(EleveAdresseDAO elAdrDAO : listElAdrDAO){
					AdresseBean adr = AdresseBean.read(elAdrDAO.getAdresse_id());
					listAdr.add(adr);
				}
			}
			el.setId(elDAO.getId());
			el.setCiv(civ);
			el.setAdr(listAdr);
			el.setId_classe(elDAO.getId_classe());
			el.el = elDAO;
			el.elAdr = listElAdrDAO;
		}
		return el;
	}
	
	public static ArrayList<EleveBean> readAll() throws DAOException, InputValueTooLongException, InputInvalidException{
		 ArrayList<EleveBean> listEl = new ArrayList<EleveBean>();
		 ArrayList<EleveDAO> listDAO = null;
		 listDAO = EleveDAO.dbSelectAll();
		 for(EleveDAO elDAO : listDAO){
			 EleveBean el = new  EleveBean();
			 CiviliteBean civ = CiviliteBean.read(elDAO.getId_civ());
			 ArrayList<EleveAdresseDAO> listElAdrDAO = new ArrayList<EleveAdresseDAO>();
			 ArrayList<AdresseBean> listAdr = null;
			 listElAdrDAO = EleveAdresseDAO.dbSelectFromId(elDAO.getId());
			 if(listElAdrDAO!=null){
				 listAdr = new ArrayList<AdresseBean>();
				 for(EleveAdresseDAO elAdrDAO : listElAdrDAO){
					 AdresseBean adr = AdresseBean.read(elAdrDAO.getAdresse_id());
					 listAdr.add(adr);
				 }
			 }
			 el.setId(elDAO.getId());
			 el.setCiv(civ);
			 el.setAdr(listAdr);
			 el.setId_classe(elDAO.getId_classe());
			 el.el = elDAO;
			 el.elAdr = listElAdrDAO;
			 listEl.add(el);
		 }
		 return listEl;
	}
	
	public static ArrayList<EleveBean> selectByIdClasse(int id_classe) throws DAOException, InputValueTooLongException, InputInvalidException{
		 ArrayList<EleveBean> listEl = new ArrayList<EleveBean>();
		 ArrayList<EleveDAO> listDAO = null;
		 listDAO = EleveDAO.dbSelectFromIdClasse(id_classe);
		 for(EleveDAO elDAO : listDAO){
			 EleveBean el = new  EleveBean();
			 CiviliteBean civ = CiviliteBean.read(elDAO.getId_civ());
			 ArrayList<EleveAdresseDAO> listElAdrDAO = new ArrayList<EleveAdresseDAO>();
			 ArrayList<AdresseBean> listAdr = null;
			 listElAdrDAO = EleveAdresseDAO.dbSelectFromId(elDAO.getId());
			 if(listElAdrDAO!=null){
				 listAdr = new ArrayList<AdresseBean>();
				 for(EleveAdresseDAO elAdrDAO : listElAdrDAO){
					 AdresseBean adr = AdresseBean.read(elAdrDAO.getAdresse_id());
					 listAdr.add(adr);
				 }
			 }
			 el.setId(elDAO.getId());
			 el.setCiv(civ);
			 el.setAdr(listAdr);
			 el.setId_classe(elDAO.getId_classe());
			 el.el = elDAO;
			 el.elAdr = listElAdrDAO;
			 listEl.add(el);
		 }
		 return listEl;
	}
	
	public static ArrayList<EleveBean> selectByName(String nom, String prenom) throws DAOException, InputValueTooLongException, InputInvalidException{
		 ArrayList<EleveBean> listEl = new ArrayList<EleveBean>();
		 ArrayList<EleveDAO> listDAO = null;
		 listDAO = EleveDAO.dbSelectAll();
		 final Collator instance = Collator.getInstance();
		 instance.setStrength(Collator.NO_DECOMPOSITION);
		 for(EleveDAO elDAO : listDAO){
			 EleveBean el = new  EleveBean();
			 CiviliteBean civ = CiviliteBean.read(elDAO.getId_civ());
			 if(instance.compare(civ.getNom(), nom) == 0 && instance.compare(civ.getPrenom(), prenom) == 0){
				 el.setId(elDAO.getId());
				 el.setCiv(civ);
				 el.setId_classe(elDAO.getId_classe());
				 el.el = elDAO;
				 listEl.add(el);
			 } 
		 }
		 return listEl;
	}
	
	public void create() throws DAOException, InputValueTooLongException, InputInvalidException{
		this.civ.create();
		
		EleveDAO elDAO = new EleveDAO(this.civ.getId(), this.id_classe);
		elDAO.dbInsert();
		ArrayList <EleveAdresseDAO> listElAdr = new ArrayList<EleveAdresseDAO>();
		for(AdresseBean a : this.adr){
			a.create();
			EleveAdresseDAO elAdr = new EleveAdresseDAO(elDAO.getId(),a.getId());
			elAdr.dbInsert();
			listElAdr.add(elAdr);
		}
		
		this.el = elDAO;
		this.id = elDAO.getId();
		this.elAdr = listElAdr;
	}
	
	public void update() throws DAOException, InputValueTooLongException, InputInvalidException{
		this.civ.update();
		for(AdresseBean a : this.adr){
			a.update();
		}
		this.el.setId_civ(this.civ.getId());
		this.el.setId_classe(this.getId_classe());
		this.el.dbUpdate();
	}
	
	public void delete() throws DAOException, InputValueTooLongException, InputInvalidException{
		try {
			for(EleveAdresseDAO elAdr : this.elAdr){
				elAdr.dbDelete();
			}
			this.el.dbDelete();
			this.civ.delete();
			for(AdresseBean a : this.adr){
				a.delete();
			}
		} catch (DAOException e) {
			throw new DAOException("Impossible de supprimer cet élève car il est assigné à une classe");
		}
		
	}
	
	public void addAdr(AdresseBean adr) throws DAOException, InputValueTooLongException, InputInvalidException{
		adr.create();
		this.adr.add(adr);
		EleveAdresseDAO elAdr = new EleveAdresseDAO(this.id, adr.getId());
		elAdr.dbInsert();
		this.elAdr.add(elAdr);
	}
	
	public void removeAdr(AdresseBean adr) throws DAOException, InputValueTooLongException, InputInvalidException{
		for(AdresseBean a : this.adr){
			if(a.getId()== adr.getId()) a.delete();
		}
		for(EleveAdresseDAO elAdr : this.elAdr){
			if(elAdr.getAdresse_id()==adr.getId()) elAdr.dbDelete();
		}
	}
	
	public String getNomClasse(){
		ClasseBean cl = new ClasseBean();
		String ret = "";
		try {
			if(this.id_classe!=null && this.id_classe!=0){
				cl = ClasseBean.readSimple(this.id_classe);
				ret = cl.getNom();
			}	
		} catch (DAOException | InputValueTooLongException | InputInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
}
