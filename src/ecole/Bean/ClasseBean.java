package ecole.Bean;

import java.util.ArrayList;

import ecole.DAO.ClasseDAO;
import ecole.Data.Niveaux;
import ecole.Exception.DAOException;
import ecole.Exception.InputInvalidException;
import ecole.Exception.InputValueTooLongException;

public class ClasseBean {
	private int id;
	private String nom;
	private String niveaux;
	private EnseignantBean enseignant;
	private ArrayList<EleveBean> listEl;
	private ClasseDAO cl;
	
	public int getId() {
		return id;
	}
	private void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getNiveaux() {
		return niveaux;
	}
	public void setNiveaux(String niveaux) {
		this.niveaux = niveaux;
	}
	public EnseignantBean getEnseignant() {
		return enseignant;
	}
	public void setEnseignant(EnseignantBean enseignant) {
		this.enseignant = enseignant;
	}
	public ArrayList<EleveBean> getListEl() {
		return listEl;
	}
	public void setListEl(ArrayList<EleveBean> listEl) {
		this.listEl = listEl;
	}
	
	
	public static ClasseBean read(int id) throws DAOException, InputValueTooLongException, InputInvalidException{
		ClasseBean cl = null;
		ClasseDAO clDAO = null;
		clDAO = ClasseDAO.dbSelectFromId(id);
		if(clDAO!=null){
			cl = new ClasseBean();
			cl.setId(clDAO.getId());
			cl.setNom(clDAO.getNom());
			cl.setNiveaux(clDAO.getNiveau().getLabelShort());
			if(clDAO.getId_enseignant()!=0){
				cl.setEnseignant(EnseignantBean.read(clDAO.getId_enseignant()));
			}
			cl.setListEl(EleveBean.selectByIdClasse(clDAO.getId()));
			cl.cl = clDAO;
		}
		return cl;
	}
	
	public static ArrayList<ClasseBean> readAll() throws DAOException, InputValueTooLongException, InputInvalidException{
		 ArrayList<ClasseBean> listCl = new ArrayList<ClasseBean>();
		 ArrayList<ClasseDAO> listDAO = null;
		 listDAO = ClasseDAO.dbSelectAll();
		 for(ClasseDAO clDAO : listDAO){
			 ClasseBean cl = new ClasseBean();
			 cl.setId(clDAO.getId());
			 cl.setNom(clDAO.getNom());
			 cl.setNiveaux(clDAO.getNiveau().getLabelShort());
			 if(clDAO.getId_enseignant()!=0){
					cl.setEnseignant(EnseignantBean.read(clDAO.getId_enseignant()));
			 }
			 cl.setListEl(EleveBean.selectByIdClasse(clDAO.getId()));
			 cl.cl = clDAO;
			 listCl.add(cl);
		 }
		 return listCl;
	}
	
	public static ClasseBean readSimple(Integer id) throws DAOException, InputValueTooLongException, InputInvalidException{
		ClasseBean cl = null;
		ClasseDAO clDAO = null;
		if(id!=null){
			clDAO = ClasseDAO.dbSelectFromId(id);
			if(clDAO!=null){
				cl = new ClasseBean();
				cl.setId(clDAO.getId());
				cl.setNom(clDAO.getNom());
				cl.setNiveaux(clDAO.getNiveau().getLabelShort());
				if(clDAO.getId_enseignant()!=0){
					cl.setEnseignant(EnseignantBean.read(clDAO.getId_enseignant()));
				}
				cl.cl = clDAO;
			}
		}
		
		return cl;
	}
	
	public static ArrayList<ClasseBean> readAllSimple() throws DAOException, InputValueTooLongException, InputInvalidException{
		 ArrayList<ClasseBean> listCl = new ArrayList<ClasseBean>();
		 ArrayList<ClasseDAO> listDAO = null;
		 listDAO = ClasseDAO.dbSelectAll();
		 for(ClasseDAO clDAO : listDAO){
			 ClasseBean cl = new ClasseBean();
			 cl.setId(clDAO.getId());
			 cl.setNom(clDAO.getNom());
			 cl.setNiveaux(clDAO.getNiveau().getLabelShort());
			 cl.setEnseignant(EnseignantBean.read(clDAO.getId_enseignant()));
			 cl.cl = clDAO;
			 listCl.add(cl);
		 }
		 return listCl;
	}
	
	public void create() throws DAOException, InputValueTooLongException, InputInvalidException{
		ClasseDAO classeDAO = null;
		if(this.enseignant!=null){
			classeDAO = new ClasseDAO(this.nom, Niveaux.fromString(this.niveaux), this.enseignant.getId());
		}
		else{
			classeDAO = new ClasseDAO(this.nom, Niveaux.fromString(this.niveaux), null);
		}	
		classeDAO.dbInsert();
		this.cl = classeDAO;
		this.id= classeDAO.getId();
	}
	
	public void update() throws DAOException, InputValueTooLongException, InputInvalidException{
		this.cl.setNom(this.nom);
		this.cl.setNiveau(Niveaux.fromString(this.niveaux));
		if(this.enseignant!=null){
			this.cl.setId_enseignant(this.enseignant.getId());
		}
		else{
			this.cl.setId_enseignant(null);
		}
		this.cl.dbUpdate();
	}
	
	public void delete() throws  DAOException, InputValueTooLongException, InputInvalidException{
		try {
			this.cl.dbDelete();
		} catch (DAOException e) {
			throw new DAOException("Impossible de supprimer cette classe car des élèves lui sont assignés", e);
		}
	}
}
