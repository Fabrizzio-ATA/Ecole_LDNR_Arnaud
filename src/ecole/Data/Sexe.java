package ecole.Data;

import ecole.Exception.InputInvalidException;

public enum Sexe {
	H("H", "Homme"), F("F", "Femme");
	
	private String labelShort;
	private String labelLong;
	
	private Sexe(String labelShort, String labelLong){
		this.labelShort = labelShort;
		this.labelLong = labelLong;
	}
	
	public String getLabelShort() {
		return this.labelShort;
	}
	public String getLabelLong() {
		return this.labelLong;
	}

	public static Sexe fromString(String str) throws InputInvalidException{
		Sexe obj = null;
		if (str.equalsIgnoreCase("M") || str.equalsIgnoreCase("Masculin") || str.equalsIgnoreCase("H") || str.equalsIgnoreCase("Homme")) {
			obj = Sexe.H;
		}
		else if (str.equalsIgnoreCase("F") || str.equalsIgnoreCase("Féminin") || str.equalsIgnoreCase("Feminin") || str.equalsIgnoreCase("Femme")){
			obj = Sexe.F;
		}
		else {
				throw new InputInvalidException("Sexe inconnu " + str);
		}
		return obj;
	}
}