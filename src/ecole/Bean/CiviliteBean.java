package ecole.Bean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import ecole.DAO.CiviliteDAO;
import ecole.Data.Sexe;
import ecole.Exception.DAOException;
import ecole.Exception.InputInvalidException;
import ecole.Exception.InputValueTooLongException;

public class CiviliteBean implements Comparable<CiviliteBean> {
	private  static final DateTimeFormatter DATE_FORMAT_FORM = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private  static final DateTimeFormatter DATE_FORMAT_DISPLAY = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	// Attributs de l'instance representant les données affichées
	private int id;
	private String nom;
	private String prenom;
	private String sexe;
	private String date;
	private CiviliteDAO civ;
	
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDateDisplay(){
		return civ.getDateNaissance().format(DATE_FORMAT_DISPLAY);
	}
	
	public static CiviliteBean read(int id) throws DAOException, InputValueTooLongException, InputInvalidException{
		CiviliteBean civ = null;
		CiviliteDAO civDAO = null;
		civDAO = CiviliteDAO.dbSelectFromId(id);
		if(civDAO!=null){
			civ = new CiviliteBean();
			civ.setId(civDAO.getId());
			civ.setNom(civDAO.getNom());
			civ.setPrenom(civDAO.getPrenom());
			civ.setSexe(civDAO.getSexe().getLabelShort());
			civ.setDate(civDAO.getDateNaissance().format(DATE_FORMAT_FORM));
			civ.civ = civDAO;
		}
		return civ;
	}
	
	public static ArrayList<CiviliteBean> readAll() throws DAOException, InputValueTooLongException, InputInvalidException{
		 ArrayList<CiviliteBean> listCiv = new ArrayList<CiviliteBean>();
		 ArrayList<CiviliteDAO> listDAO = null;
		 listDAO = CiviliteDAO.dbSelectAll();
		 for(CiviliteDAO civDAO : listDAO){
			 CiviliteBean civ = new CiviliteBean();
			 civ.setId(civDAO.getId());
			 civ.setNom(civDAO.getNom());
			 civ.setPrenom(civDAO.getPrenom());
			 civ.setSexe(civDAO.getSexe().getLabelShort());
			 civ.setDate(civDAO.getDateNaissance().format(DATE_FORMAT_FORM));
			 civ.civ = civDAO;
			 listCiv.add(civ);
		 }
		 return listCiv;
	}
	
	public void create() throws DAOException, InputValueTooLongException, InputInvalidException{
		CiviliteDAO civDAO = new CiviliteDAO(this.nom, this.prenom, Sexe.fromString(this.sexe), LocalDate.parse(this.date, DATE_FORMAT_FORM));
		civDAO.dbInsert();
		this.civ = civDAO;
		this.id=civDAO.getId();
	}
	
	public void update() throws DAOException, InputValueTooLongException, InputInvalidException{
		this.civ.setNom(this.nom);
		this.civ.setPrenom(this.prenom);
		this.civ.setSexe(Sexe.fromString(this.sexe));
		this.civ.setDateNaissance( LocalDate.parse(this.date, DATE_FORMAT_FORM));
		this.civ.dbUpdate();
	}
	
	public void delete() throws DAOException, InputValueTooLongException, InputInvalidException{
		this.civ.dbDelete();
	}
	
	public int compareTo(CiviliteBean C){
	    final int EQUAL = 0;
	    
	    if(this==C) return EQUAL;
	    
	    int comp= this.getNom().compareTo(C.getNom());
	    if(comp!=EQUAL) return comp;
	    comp=this.getPrenom().compareTo(C.getPrenom());
	    if(comp!=EQUAL) return comp;
	    comp=LocalDate.parse(this.getDate(), DATE_FORMAT_FORM).compareTo(LocalDate.parse(C.getDate(), DATE_FORMAT_FORM));
	    if(comp!=EQUAL) return comp;
	    return EQUAL;	
	    }
}
