package ecole.Data;

import ecole.Exception.InputInvalidException;

public enum Niveaux {
	PS("PS","Petite Section"),
	MS("MS","Moyenne Section"),
	GS("GS","Grande Section"),
	CP("CP","Cours préparatoire"),
	CE1("CE1","Cours élémentaire 1re année"),
	CE2("CE2"," Cours élémentaire 2e anné"),
	CM1("CM1"," Cours moyen 1re année"),
	CM2("CM2"," Cours moyen 2e année");
	
	private String labelShort;
	private String labelLong;
	
	private Niveaux(String labelShort, String labelLong){
		this.labelShort=labelShort;
		this.labelLong=labelLong;
	}

	public String getLabelShort() {
		return this.labelShort;
	}
	public String getLabelLong() {
		return this.labelLong;
	}

	public static Niveaux fromString(String str) throws InputInvalidException{
		Niveaux obj = null;
		if(str.equalsIgnoreCase("PS")) obj=Niveaux.PS;
		else if (str.equalsIgnoreCase("MS")) obj=Niveaux.MS;
		else if (str.equalsIgnoreCase("GS")) obj=Niveaux.GS;
		else if (str.equalsIgnoreCase("CP")) obj=Niveaux.CP;
		else if (str.equalsIgnoreCase("CE1")) obj=Niveaux.CE1;
		else if (str.equalsIgnoreCase("CE2")) obj=Niveaux.CE2;
		else if (str.equalsIgnoreCase("CM1")) obj=Niveaux.CM1;
		else if (str.equalsIgnoreCase("CM2")) obj=Niveaux.CM2;
		else throw new InputInvalidException("Niveaux Invalide " + str);
		return obj;
	}
}
