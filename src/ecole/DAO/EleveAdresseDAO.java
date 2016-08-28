package ecole.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ecole.Exception.DAOException;


public class EleveAdresseDAO {
	// Attribut de closse nommant la table utilisée
	private static String table = "avoir_adresse";
	private static Connection conn=DbConnect.getInstance();
	// Attributs de l'instance representant les colones de la table en BDD
	private int eleves_id;
	private int adresse_id;
	
	public EleveAdresseDAO(){
		conn=DbConnect.getInstance();
	}
	
	public EleveAdresseDAO(int eleve_id, int adresse_id){
		this.setEleves_id(eleve_id);
		this.setAdresse_id(adresse_id);
	}
	
	public int getEleves_id() {
		return eleves_id;
	}
	public void setEleves_id(int eleves_id) {
		this.eleves_id = eleves_id;
	}
	public int getAdresse_id() {
		return adresse_id;
	}
	public void setAdresse_id(int adresse_id) {
		this.adresse_id = adresse_id;
	}
	
	public static ArrayList<EleveAdresseDAO> dbSelectFromId(int eleves_id)
	{
		ArrayList<EleveAdresseDAO> retObj = new ArrayList<EleveAdresseDAO>();
		String sql= "SELECT * FROM " + table + " WHERE eleves_id=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, eleves_id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				EleveAdresseDAO obj = new EleveAdresseDAO(rs.getInt("eleves_id"), rs.getInt("adresse_id"));
				retObj.add(obj);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return retObj;	
	}
	
	public static ArrayList<EleveAdresseDAO> dbSelectAll() throws DAOException
	{
		ArrayList<EleveAdresseDAO> retObj = new ArrayList<EleveAdresseDAO>();
		String sql= "SELECT * FROM " + table;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				EleveAdresseDAO obj = new EleveAdresseDAO(rs.getInt("eleves_id"), rs.getInt("adresse_id"));
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
		String sql= "INSERT INTO " + table + " (eleves_id,adresse_id) VALUES (?, ?)";
		PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, this.getEleves_id());
				stmt.setInt(2, this.getAdresse_id());
				int status = stmt.executeUpdate();
				if(status==0){
					throw new DAOException( "Échec de la création l'adresse de l'élève, aucune ligne ajoutée dans la table." );
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
	
	public void dbDelete() throws DAOException{
		String sql= "DELETE FROM " + table + " WHERE eleves_id=? AND adresse_id=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, this.getEleves_id());
			stmt.setInt(2, this.getAdresse_id());
			int status = stmt.executeUpdate();
			if (status==0){
                throw new DAOException( "Échec de la suppression l'adresse de l'élève, aucune ligne supprimée de la table." );
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

