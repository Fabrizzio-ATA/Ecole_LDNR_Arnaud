package ecole.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
	
	/*
	 *  Des constantes de classe
	 */
	// Le driver
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	// L'URL de ma DB à contacter
	private static final String DB_URL = "jdbc:mysql://82.229.232.36:3306/db_ecole_groupeB";
	/*
	 *  Mes paramètres de connexion
	 *  ATTENTION : c'est sale !
	 *  Il faut passer par des propriétés
	 */
	private static final String USER = "Guest";
	private static final String PASS = "guest";

	// L'objet Connection qui sera unique
	private static Connection conn=null;

	/*
	 * Constructeur déclaré private pour ne pas pouvoir y accéder
	 * de l'extérieur
	 */
	private DbConnect() {}
	
	/**
	 * Retourne une instance de Connection unique.
	 * En cas d'échec de connexion à la DB, l'application
	 * s'arrête là : on sort sur erreur.
	 * @return un objet Connection
	 */
	private static Connection newConn() {
		 /*
		  * Enregistrer le driver JDBC
		  * PENSEZ à ajouter la bibliothèque de connexion
		  * dans le BuildPath de votre projet
		 */
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// Pas de driver : on sort de l'application sur erreur.
			exitOnError("Driver JDBC non disponible !", e, 1);
		}

		// Ouvrir une connexion
		try {
			conn = DriverManager.getConnection(DB_URL, USER,PASS);
		} catch (SQLException e) {
			// Pas de connexion : on sort de l'application sur erreur 
			exitOnError("Connection à la base de données impossible ! (time out)", e, 2);
		}
		// On retourne la connexion
		return conn;
	}

	/**
	 * Retourne une unique instance de Connection.
	 * Si l'instance n'existe pas, on la crée.
	 * @return un objet de type Connection
	 */
	public static Connection getInstance() {
		if (conn == null) {
			conn = newConn();
		}
		return conn;
	}
	
	/**
	 * Méthode outil permettant de sortir proprement de l'application
	 * avec message d'erreur et retour de la pile d'exceptions
	 * dans la console.
	 * @param message
	 * @param e
	 * @param exitCode
	 */
	private static void exitOnError(String message, Exception e, int exitCode) {
		System.out.println(message);
		e.printStackTrace();
		System.exit(exitCode);
	}
}