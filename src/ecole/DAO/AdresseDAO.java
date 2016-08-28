package ecole.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ecole.Exception.DAOException;
import ecole.Exception.InputInvalidException;
import ecole.Exception.InputValueTooLongException;

public class AdresseDAO {
	//constantes de classe
	public static final int VOIE_MAX_LENGTH = 64;
	public static final int VILLE_MAX_LENGTH = 45;
	// Attribut de classe nommant la table utilisée
	private static String table = "adresse";
	private static Connection conn=DbConnect.getInstance();
	// Attributs de l'instance representant les colones de la table en BDD
	private int id;
	private String voie;
	private String codePostal;
	private String ville;
	
	public AdresseDAO(){
		//super();
	}
	
	public AdresseDAO(String voie, String codePostal, String ville) throws InputValueTooLongException, InputInvalidException
	{
		this.setVoie(voie);
		this.setCodePostal(codePostal);
		this.setVille(ville);
	}
	
	private AdresseDAO(int id, String voie, String codePostal, String ville) throws InputValueTooLongException, InputInvalidException
	{
		this.setId(id);
		this.setVoie(voie);
		this.setCodePostal(codePostal);
		this.setVille(ville);
	}
	
	public int getId() {
		return id;
	}
	private void setId(int id) {
		this.id = id;
	}
	public String getVoie() {
		return voie;
	}
	public void setVoie(String voie) throws InputValueTooLongException{
		if (voie.length() > AdresseDAO.VOIE_MAX_LENGTH) {
			this.voie = voie.substring(0, AdresseDAO.VOIE_MAX_LENGTH);
			throw new InputValueTooLongException("adresse.voie", AdresseDAO.VOIE_MAX_LENGTH, voie.length());
		}
		else this.voie = voie;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) throws InputInvalidException{
		if(!AdresseDAO.isCodePostal(codePostal)){
			throw new InputInvalidException("Code Postal invalide : " + codePostal);
		}
		else this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) throws InputValueTooLongException{
		if (ville.length() > AdresseDAO.VILLE_MAX_LENGTH) {
			this.ville = ville.substring(0, AdresseDAO.VILLE_MAX_LENGTH);
			throw new InputValueTooLongException("adresse.ville", AdresseDAO.VILLE_MAX_LENGTH, ville.length());
		}
		else this.ville = ville;
	}
	
	public static boolean isCodePostal(String codePostal) {
		boolean ret = true;

		/* On utilise une expression r�guli�re simple :
		 * - la chaine doit commencer (^) par un chiFFre [0-9]
		 * - il doit y avoir entre 4 et 5 a la suite {4, 5}
		 * - la chaine se Finit la ($)
		 */
		if ( !codePostal.matches("^[0-9]{4,5}$")) ret = false;

		/* Si la chaine est valide, on v�rifie son contenu
		 * apr�s l'avoir transform� en valeur enti�re
		 */
		else {
			int cp = Integer.parseInt(codePostal);
			if ( (cp < 1000) || (cp > 97999)) ret = false;
		}
		return ret;
	}

	public static AdresseDAO dbSelectFromId(int id) throws DAOException, InputValueTooLongException, InputInvalidException
	{
		AdresseDAO obj = null;
		String sql= "SELECT * FROM " + table + " WHERE id=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			// TODO utiliser rs.first()
			while(rs.next()){
				obj = new AdresseDAO(rs.getInt("id"),
						rs.getString("voie"),
						rs.getString("code_postal"),
						rs.getString("ville"));
			}
			rs.close();
		} catch (SQLException  e) {
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
	
	public static ArrayList<AdresseDAO> dbSelectAll() throws DAOException, InputValueTooLongException, InputInvalidException
	{
		ArrayList<AdresseDAO> retObj = new ArrayList<AdresseDAO>() ;
		String sql= "SELECT * FROM " + table;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				AdresseDAO obj = new AdresseDAO(rs.getInt("id"),
						rs.getString("voie"),
						rs.getString("code_postal"),
						rs.getString("ville"));
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
		String sql= "INSERT INTO " + table + " (voie,code_postal,ville) VALUES (?, ?, ?)";
		PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				stmt.setString(1, this.getVoie());
				stmt.setString(2, this.getCodePostal());
				stmt.setString(3, this.getVille());
				int status = stmt.executeUpdate();
				if (status== 0) {
	                throw new DAOException( "Échec de la création de l'adresse, aucune ligne ajoutée dans la table." );
				}
				ResultSet rs = stmt.getGeneratedKeys();
				if ((rs != null) && (rs.next())) {
					this.setId(rs.getInt(1));
				}
				else{
					throw new DAOException( "Échec de la création de l'adresse en base, aucun ID auto-généré retourné." );
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
	}
	
	public void dbUpdate() throws DAOException{
		String sql= "UPDATE " + table + " SET voie=?, code_postal=?, ville=? WHERE id=? ";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, this.getVoie());
			stmt.setString(2, this.getCodePostal());
			stmt.setString(3, this.getVille());
			stmt.setInt(4, this.getId());
			// Succes si le retour est egal a 1
			// mais note : il peut retourner 0 s'il n'y a rien à changer
			int status = stmt.executeUpdate();
			if(status==0){
				throw new DAOException("Échec de la modification de l'adresse, aucune ligne modifiée de la table.");
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
			// On teste qu'un enregistrement a �t� supprim�
			int status = stmt.executeUpdate();
			if (status==0){
                throw new DAOException( "Échec de la suppression de l'adresse, aucune ligne supprimée de la table." );
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
