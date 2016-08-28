package ecole.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import ecole.Exception.DAOException;

public class EleveDAO {
	// Attribut de classe nommant la table utilis�e
	private static String table = "eleves";
	private static Connection conn=DbConnect.getInstance();
	// Attributs de l'instance representant les colones de la table en BDD
	private int id;
	private int id_civ;
	private Integer id_classe;
	
	public EleveDAO(){
		conn=DbConnect.getInstance();
	}
	
	public EleveDAO(int id_civ, Integer id_classe) {
		this.setId_civ(id_civ);
		this.setId_classe(id_classe);
	}
	private EleveDAO(int id, int id_civ, Integer id_classe) {
		this.setId(id);
		this.setId_civ(id_civ);
		this.setId_classe(id_classe);
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
	public Integer getId_classe() {
		return id_classe;
	}
	public void setId_classe(Integer id_classe) {
		this.id_classe = id_classe;
	}
	
	public static EleveDAO dbSelectFromId(int id) throws DAOException
	{
		EleveDAO obj = null;
		String sql= "SELECT * FROM " + table + " WHERE id=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				obj = new EleveDAO(rs.getInt("id"),
						rs.getInt("id_civ"),
						rs.getInt("id_classe"));
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
	
	public static ArrayList<EleveDAO> dbSelectAll() throws DAOException
	{
		ArrayList<EleveDAO> listEl = new ArrayList<EleveDAO>();
		String sql= "SELECT * FROM eleves";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				EleveDAO obj = new EleveDAO(rs.getInt("id"),
							rs.getInt("id_civ"),
							rs.getInt("id_classe"));
				listEl.add(obj);
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
		return listEl;
		
	}
	
	public static ArrayList<EleveDAO> dbSelectFromIdClasse(int id_classe) throws DAOException
	{
		ArrayList<EleveDAO> listEl = new ArrayList<EleveDAO>();
		String sql= "SELECT * FROM " + table + " WHERE id_classe=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id_classe);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				EleveDAO obj = new EleveDAO(rs.getInt("id"),
							rs.getInt("id_civ"),
							rs.getInt("id_classe"));
				listEl.add(obj);
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
		return listEl;
		
	}
	
	public void dbInsert() throws DAOException
	{
		String sql= "INSERT INTO " + table + " (id_civ,id_classe) VALUES (?, ?)";
		PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				stmt.setInt(1, this.getId_civ());
				if (this.getId_classe() == null) stmt.setNull(2, Types.INTEGER);
				else stmt.setInt(2 , this.getId_classe());
				stmt.executeUpdate();
				ResultSet rs = stmt.getGeneratedKeys();
				if(rs != null && rs.next()){
					this.setId(rs.getInt(1));
				}
				else{
					 throw new DAOException( "Échec de la création de l'élève, aucune ligne ajoutée dans la table." );
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
		String sql= "UPDATE " + table + " SET id_civ=?, id_classe=? WHERE id=? ";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, this.getId_civ());
			if (this.getId_classe() == null) stmt.setNull(2, Types.INTEGER);
			else stmt.setInt(2 , this.getId_classe());
			stmt.setInt(3, this.getId());
			int status = stmt.executeUpdate();
			if(status==0){
				throw new DAOException("Échec de la modification de l'élève, aucune ligne modifiée de la table.");
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
                throw new DAOException( "Échec de la suppression de l'élève, aucune ligne supprimée de la table." );
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
