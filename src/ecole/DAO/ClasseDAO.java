package ecole.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import ecole.Data.Niveaux;
import ecole.Exception.DAOException;
import ecole.Exception.InputInvalidException;
import ecole.Exception.InputValueTooLongException;


public class ClasseDAO {
	// Des constantes de classe
	public static final int NOM_MAX_LENGTH = 16;
	// Attribut de closse nommant la table utilisée
	private static String table = "classes";
	private static Connection conn=DbConnect.getInstance();
	// Attributs de l'instance representant les colones de la table en BDD
	private int id;
	private String nom;
	private Niveaux niveau;
	private Integer id_enseignant;
	
	public ClasseDAO(){
		conn=DbConnect.getInstance();
	}
	
	public ClasseDAO(String nom, Niveaux niveau, Integer id_enseignant) throws InputValueTooLongException{
		this.setNom(nom);
		this.setNiveau(niveau);
		this.setId_enseignant(id_enseignant);
	}
	
	private ClasseDAO(int id, String nom, Niveaux niveau, Integer id_enseignant) throws InputValueTooLongException{
		this.setId(id);
		this.setNom(nom);
		this.setNiveau(niveau);
		this.setId_enseignant(id_enseignant);
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
	public void setNom(String nom) throws InputValueTooLongException{
		if (nom.length() > ClasseDAO.NOM_MAX_LENGTH) {
			this.nom = nom.substring(0, ClasseDAO.NOM_MAX_LENGTH);
			throw new InputValueTooLongException("classe.nom", ClasseDAO.NOM_MAX_LENGTH, nom.length());
		}
		else this.nom = nom;
	}
	public Niveaux getNiveau() {
		return niveau;
	}
	public void setNiveau(Niveaux niveau) {
		this.niveau = niveau;
	}
	public Integer getId_enseignant() {
		return id_enseignant;
	}
	public void setId_enseignant(Integer id_enseignant) {
		this.id_enseignant = id_enseignant;
	}
	
	public static ClasseDAO dbSelectFromId(int id) throws DAOException, InputValueTooLongException, InputInvalidException
	{
		ClasseDAO obj = null;
		String sql= "SELECT * FROM " + table + " WHERE id=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				obj = new ClasseDAO(rs.getInt("id"),
						rs.getString("nom"),
						Niveaux.fromString(rs.getString("niveaux")),
						rs.getInt("id_enseignant"));
			}
			rs.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally{
			
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
		return obj;
	}
	
	public static ArrayList<ClasseDAO> dbSelectAll() throws DAOException, InputValueTooLongException, InputInvalidException
	{
		ArrayList<ClasseDAO> retObj = new ArrayList<ClasseDAO>();
		String sql= "SELECT * FROM " + table;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				ClasseDAO obj = new ClasseDAO(rs.getInt("id"),
						rs.getString("nom"),
						Niveaux.fromString(rs.getString("niveaux")),
						rs.getInt("id_enseignant"));
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
		String sql= "INSERT INTO " + table + " (nom, niveaux, id_enseignant) VALUES (?, ?, ?)";
		PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				stmt.setString(1, this.getNom());
				stmt.setString(2, this.getNiveau().getLabelShort());
				if (this.getId_enseignant() == null) stmt.setNull(3, Types.INTEGER);
				else stmt.setInt(3 , this.getId_enseignant());
				stmt.executeUpdate();
				ResultSet rs = stmt.getGeneratedKeys();
				if(rs != null && rs.next()){
					this.setId(rs.getInt(1));
				}
				else{
					 throw new DAOException( "Échec de la création de la classe, aucune ligne ajoutée dans la table." );
				}
				rs.close();
			} catch (SQLException e) {
				 throw new DAOException("Integer in int",e);
			}finally{
				try {
					stmt.close();
				} catch (SQLException e) {
					 throw new DAOException(e);
				}
			}
	}
	
	public void dbUpdate() throws DAOException{
		String sql= "UPDATE " + table + " SET nom=?, niveaux=?, id_enseignant=? WHERE id=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, this.getNom());
			stmt.setString(2, this.getNiveau().getLabelShort());
			if (this.getId_enseignant() == null) stmt.setNull(3, Types.INTEGER);
			else stmt.setInt(3 , this.getId_enseignant());
			stmt.setInt(4, this.getId());
			int status = stmt.executeUpdate();
			if(status==0){
				throw new DAOException("Échec de la modification de la classe, aucune ligne modifiée de la table.");
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
			// On teste qu'un enregistrement a été supprimé
			int status = stmt.executeUpdate();
			if (status==0){
                throw new DAOException( "Échec de la suppression de la classe, aucune ligne supprimée de la table." );
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