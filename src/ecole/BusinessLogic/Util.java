package ecole.BusinessLogic;

import ecole.Exception.InputInvalidException;
import ecole.Exception.InputValueTooLongException;

public class Util {
	private static final int MAX_LENGTH_PRENOM 	= 64;
	private static final int MAX_LENGTH_NOM 	= 64;
	private static final int MAX_LENGTH_DOB 	= 10;
	private static final int MAX_LENGTH_SEXE 	= 1;
	private static final int MAX_LENGTH_VOIE 	= 64;
	private static final int MAX_LENGTH_CP 		= 5;
	private static final int MAX_LENGTH_VILLE 	= 45;
	private static final int MAX_LENGTH_CLASSE 	= 16;
	private static final int MAX_LENGTH_NIVEAU 	= 3;
	
	public static void validationPrenom( String prenom ) throws InputValueTooLongException, InputInvalidException {
		if ( prenom != null ) {
			if ( prenom.length() < 2 ) {
				throw new InputValueTooLongException( "Le prenom de l'utilisateur doit contenir au moins 2 caractères." );
			} else if ( prenom.length() >  MAX_LENGTH_PRENOM ) {
				throw new InputValueTooLongException( "Le prenom de l'utilisateur doit contenir moins de " +
														MAX_LENGTH_PRENOM + " caractères" );
			}
		
		} else {
			throw new InputInvalidException( "Merci de saisir un nom " );
		}
	}

	public static void validationNom( String nom ) throws InputValueTooLongException, InputInvalidException {
		if ( nom != null ) {
			if ( nom.length() < 2 ) {
				throw new InputValueTooLongException( "Le nom de l'utilisateur doit contenir au moins 2 caractères." );
			} else if ( nom.length() >  MAX_LENGTH_NOM ) {
				throw new InputValueTooLongException( "Le nom de l'utilisateur doit contenir moins de " +
						MAX_LENGTH_NOM + " caractères" );
			}			
		} else {
			throw new InputInvalidException( "Merci de saisir un nom " );
		}
	}

	public static void validationDoB( String dob ) throws InputInvalidException, InputValueTooLongException {
		if ( dob != null ) {
			if ( !dob.matches( "^[0-9]{4}-[0-9]{2}-[0-9]{2}$" ) ) {
				throw new InputInvalidException( "Merci de saisir une date valide. (jj/mm/aaaa)" );
			} else if ( dob.length() >  MAX_LENGTH_DOB ) {
				throw new InputValueTooLongException( "La date de naissance doit contenir moins de " +
						MAX_LENGTH_DOB + " caractères" );
			}	
		} else {
			throw new InputInvalidException( "Merci de saisir une date de naissance " );
		}
	}

	public static void validationSexe( String sexe ) throws InputInvalidException, InputValueTooLongException {
		if ( sexe != null ) {
			if ( !sexe.matches( "^[mfMFhH]$" ) ) {
				throw new InputInvalidException( "Merci de saisir un sexe valide." );
			} else if ( sexe.length() >  MAX_LENGTH_SEXE ) {
				throw new InputValueTooLongException( "Le sexe doit contenir moins de " +
						MAX_LENGTH_SEXE + " caractères" );
			}	
		} else {
			throw new InputInvalidException( "Merci de saisir un sexe " );
		}
	}

	public static void validationVoie( String voie ) throws InputInvalidException, InputValueTooLongException {
		if ( voie != null ) {
			if ( !voie.matches( "^[0-9a-zA-Z-, ']*$$" ) ) {
				throw new InputInvalidException( "Merci de saisir une voie valide." );
			} else if ( voie.length() >  MAX_LENGTH_VOIE ) {
				throw new InputValueTooLongException( "La voie doit contenir moins de " +
						MAX_LENGTH_VOIE + " caractères" );
			}	
		} else {
			throw new InputInvalidException( "Merci de saisir une voie " );
		}
	}

	public static void validationCp( String cp ) throws InputInvalidException, InputValueTooLongException {
		if ( cp != null ) {
			if ( !cp.matches( "^[0-9]{4,5}$" ) ) {
				throw new InputInvalidException( "Merci de saisir un code postale valide." );
			} else if ( cp.length() >  MAX_LENGTH_CP ) {
				throw new InputValueTooLongException( "Le code postale doit contenir moins de " +
						MAX_LENGTH_CP + " caractères" );
			}	
		} else {
			throw new InputInvalidException( "Merci de saisir un code postale " );
		}
	}

	public static void validationVille( String ville ) throws InputInvalidException, InputValueTooLongException {
		if ( ville != null ) {
			if ( ville.length() >  MAX_LENGTH_VILLE) {
				throw new InputValueTooLongException( "Le nom de la ville doit contenir moins de " +
						MAX_LENGTH_VILLE + " caractères" );
			}	
		} else {
			throw new InputInvalidException( "Merci de saisir une ville " );
		}
	}
	public static void validationVoieOp( String voie ) throws InputInvalidException, InputValueTooLongException {
		if ( voie != null ) {
			if ( !voie.matches( "^[0-9a-zA-Z-, ']*$$" ) ) {
				throw new InputInvalidException( "Merci de saisir une voie valide." );
			} else if ( voie.length() >  MAX_LENGTH_VOIE ) {
				throw new InputValueTooLongException( "La voie doit contenir moins de " +
						MAX_LENGTH_VOIE + " caractères" );
			}	
		}
	}

	public static void validationCpOp( String cp ) throws InputInvalidException, InputValueTooLongException {
		if ( cp != null ) {
			if ( !cp.matches( "^[0-9]{4,5}$" ) ) {
				throw new InputInvalidException( "Merci de saisir un code postale valide." );
			} else if ( cp.length() >  MAX_LENGTH_CP ) {
				throw new InputValueTooLongException( "Le code postale doit contenir moins de " +
						MAX_LENGTH_CP + " caractères" );
			}	
		}
	}

	public static void validationVilleOp( String ville ) throws InputInvalidException, InputValueTooLongException {
		if ( ville != null ) {
			if ( ville.length() >  MAX_LENGTH_VILLE) {
				throw new InputValueTooLongException( "Le nom de la ville doit contenir moins de " +
						MAX_LENGTH_VILLE + " caractères" );
			}	
		} 
	}

	public static void validationClasse( String classe ) throws InputInvalidException, InputValueTooLongException {
		if ( classe != null ) {
			if ( !classe.matches( "^[A-Z]{2}[0-9][a-z]$" ) ) {
				throw new InputInvalidException( "Merci de saisir une classe valide." );
			} else if ( classe.length() >  MAX_LENGTH_CLASSE ) {
				throw new InputValueTooLongException( "Le nom de la classe doit contenir moins de " +
						MAX_LENGTH_CLASSE + " caractères" );
			}	
		} else {
			throw new InputInvalidException( "Merci de saisir un nom de classe " );
		}
	}
	public static void validationNiveau( String niveau ) throws InputInvalidException, InputValueTooLongException {
		if ( niveau != null ) {
			if ( !niveau.matches( "[A-Z]{2}[0-9]{0,1}" ) ) {
				throw new InputInvalidException( "Merci de saisir un niveau de classe valide." );
			} else if ( niveau.length() >  MAX_LENGTH_NIVEAU ) {
				throw new InputValueTooLongException( "Le niveau de la classe doit contenir moins de " +
						MAX_LENGTH_NIVEAU + " caractères" );
			}	
		} else {
			throw new InputInvalidException( "Merci de saisir un niveau de classe " );
		}
	}
	public static void validationId_Ens(Integer id) 
	{
		
	}
	
	public static String genErrMsg(Exception e){
		String msg_erreur = "Une erreur est survenue durant la connexion avec la base de donnée : <br>" 
				+ e.getMessage()
				+ "<br> Merci de bien vouloir réessayer plus tard.";
		return msg_erreur;
	}

}
