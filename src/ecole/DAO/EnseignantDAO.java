package ecole.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ecole.Exception.DAOException;


public class EnseignantDAO {
	// Attribut de classe nommant la table utilis�e
	private static String table = "enseignants";
	private static Connection conn=DbConnect.getInstance();
	// Attributs de l'instance representant les colones de la table en BDD
	private int id;
	private int id_civ;
	private int id_adresse;
	
	public EnseignantDAO(){
		
	}
	
	public EnseignantDAO(int id_civ, int id_adresse){
		this.setId_civ(id_civ);
		this.setId_adresse(id_adresse);
	}
	
	private EnseignantDAO(int id, int id_civ, int id_adresse){
		this.setId(id);
		this.setId_civ(id_civ);
		this.setId_adresse(id_adresse);
	}
	
	public int getId() {
		return id;
	}
	private void setId(int id) {
		this.id = id;
	}
	public int getId_civ() {
		return id_civ;
	}
	public void setId_civ(int id_civ) {
		this.id_civ = id_civ;
	}
	public int getId_adresse() {
		return id_adresse;
	}
	public void setId_adresse(int id_adresse) {
		this.id_adresse = id_adresse;
	}
	
	public static EnseignantDAO dbSelectFromId(int id) throws DAOException
	{
		EnseignantDAO obj = null;
		String sql= "SELECT * FROM " + table + " WHERE id=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				obj = new EnseignantDAO(rs.getInt("id"),
						rs.getInt("id_civ"),
						rs.getInt("id_adresse"));
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
		return obj;
	}
	
	public static ArrayList<EnseignantDAO> dbSelectAll() throws DAOException
	{
		ArrayList<EnseignantDAO> retObj = new ArrayList<EnseignantDAO>();	
		String sql= "SELECT * FROM enseignants";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				EnseignantDAO obj = new EnseignantDAO(rs.getInt("id"),
						rs.getInt("id_civ"),
						rs.getInt("id_adresse"));
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
		String sql= "INSERT INTO " + table + " (id_civ,id_adresse) VALUES (?, ?)";
		PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				stmt.setInt(1, this.getId_civ());
				stmt.setInt(2, this.getId_adresse());
				stmt.executeUpdate();
				ResultSet rs = stmt.getGeneratedKeys();
				if(rs != null && rs.next()){
					this.setId(rs.getInt(1));
				}
				else{
					 throw new DAOException( "Échec de la création de l'enseignant, aucune ligne ajoutée dans la table." );
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
		String sql= "UPDATE " + table + " SET id_civ=?, id_adresse=? WHERE id=? ";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, this.getId_civ());
			stmt.setInt(2, this.getId_adresse());
			stmt.setInt(3, this.getId());
			int status = stmt.executeUpdate();
			if(status==0){
				throw new DAOException("Échec de la modification de l'enseignant, aucune ligne modifiée de la table.");
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
			int status = stmt.executeUpdate();
			if (status==0){
                throw new DAOException( "Échec de la suppression de l'enseignant, aucune ligne supprimée de la table." );
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
