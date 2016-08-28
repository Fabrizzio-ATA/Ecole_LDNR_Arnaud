package ecole.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import ecole.Data.Sexe;
import ecole.Exception.DAOException;
import ecole.Exception.InputInvalidException;
import ecole.Exception.InputValueTooLongException;

public class CiviliteDAO {
	//constantes de classe
	public static final int NOM_MAX_LENGTH = 64;
	public static final int PRENOM_MAX_LENGTH = 64;
	// Attribut de classe nommant la table utilisée
	private static String table = "civilites";
	private static Connection conn=DbConnect.getInstance();
	// Attributs de l'instance representant les colones de la table en BDD
	private int id;
	private String nom;
	private String prenom;
	private Sexe sexe;
	private LocalDate dateNaissance;
	
	public CiviliteDAO(){
		conn=DbConnect.getInstance();
	}
	
	public CiviliteDAO(String nom, String prenom, Sexe sexe, LocalDate dateNaissance) throws InputValueTooLongException
	{
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setSexe(sexe);
		this.setDateNaissance(dateNaissance);
		
	}
	
	private CiviliteDAO(int id, String nom, String prenom, Sexe sexe, LocalDate dateNaissance) throws InputValueTooLongException
	{
		this.setId(id);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setSexe(sexe);
		this.setDateNaissance(dateNaissance);
		
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	// Une valeur trap Iongue est tronquée + levée exception
	public void setNom(String nom) throws InputValueTooLongException{
		if (nom.length() > CiviliteDAO.NOM_MAX_LENGTH) {
			this.nom = nom.substring(0, CiviliteDAO.NOM_MAX_LENGTH);
			throw new InputValueTooLongException("civilite.nom", CiviliteDAO.NOM_MAX_LENGTH, nom.length());
		}
		else this.nom = nom;
	}
	

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) throws InputValueTooLongException{
		if (prenom.length() > CiviliteDAO.NOM_MAX_LENGTH) {
			this.prenom = prenom.substring(0, CiviliteDAO.PRENOM_MAX_LENGTH);
			throw new InputValueTooLongException("civilite.prenom", CiviliteDAO.PRENOM_MAX_LENGTH, prenom.length());
		}
		else this.prenom = prenom;
	}

	public Sexe getSexe() {
		return sexe;
	}

	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = LocalDate.of(dateNaissance.getYear(), dateNaissance.getMonthValue(), dateNaissance.getDayOfMonth());
	}
	
	public static CiviliteDAO dbSelectFromId(int id) throws DAOException, InputValueTooLongException, InputInvalidException
	{
		CiviliteDAO obj = null;
		String sql= "SELECT * FROM " + table + " WHERE id=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// Récupération par nom de champ
				obj = new CiviliteDAO(rs.getInt("id"),
						rs.getString("nom"),
						rs.getString("prenom"),
						Sexe.fromString(rs.getString("sexe")),
						rs.getDate("date_naissance").toLocalDate());
			}
			rs.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
		return obj;
	}
	
	public static ArrayList<CiviliteDAO> dbSelectAll() throws DAOException, InputValueTooLongException, InputInvalidException
	{
		ArrayList<CiviliteDAO> retObj = new ArrayList<CiviliteDAO>();
		String sql= "SELECT * FROM " + table;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				CiviliteDAO obj = new CiviliteDAO(rs.getInt("id"),
						rs.getString("nom"),
						rs.getString("prenom"),
						Sexe.fromString(rs.getString("sexe")),
						rs.getDate("date_naissance").toLocalDate());
				retObj.add(obj);
			}
			rs.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}finally{
			
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
		return retObj;
		
	}
	
	public void dbInsert() throws DAOException
	{
		String sql= "INSERT INTO " + table + " (nom, prenom, sexe, date_naissance) VALUES (?, ?, ?, ?)";
		PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				stmt.setString(1, this.getNom());
				stmt.setString(2, this.getPrenom());
				stmt.setString(3, this.sexe.getLabelShort());
				stmt.setDate(4, java.sql.Date.valueOf(this.getDateNaissance()));
				stmt.executeUpdate();
				ResultSet rs = stmt.getGeneratedKeys();
				if(rs != null && rs.next()){
					this.setId(rs.getInt(1));
				}
				else{
					 throw new DAOException( "Échec de la création de la civilité, aucune ligne ajoutée dans la table." );
				}
				rs.close();
			} catch (SQLException e) {
				 throw new DAOException(e);
			}finally{
				try {
					stmt.close();
				} catch (SQLException e) {
					 throw new DAOException(e);
				}
			}
	}
	
	public void dbUpdate() throws DAOException{
		String sql= "UPDATE " + table + " SET nom=?, prenom=?, sexe=?, date_naissance=? WHERE id=? ";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, this.getNom());
			stmt.setString(2, this.getPrenom());
			stmt.setString(3, this.getSexe().getLabelShort());
			stmt.setDate(4, java.sql.Date.valueOf(this.getDateNaissance()));
			stmt.setInt(5, this.getId());
			int status = stmt.executeUpdate();
			if(status==0){
				throw new DAOException("Échec de la modification de la civilité, aucune ligne modifiée de la table.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
	}
	
	public void dbDelete() throws DAOException{
		String sql= "DELETE FROM " + table + " WHERE id=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, this.getId());
			// test qu'un enregistrement a été supprimé
			int status = stmt.executeUpdate();
			if (status==0){
                throw new DAOException( "Échec de la suppression de la civilité, aucune ligne supprimée de la table." );
            }
		} catch (SQLException e) {
			throw new DAOException(e);
		}finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
	}
}
