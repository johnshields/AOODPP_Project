package ie.gmit.sw;

public enum County {
	CORK			("C", "Corcaigh"), 
	CLARE			("CE", "An Clár"), 
	CAVAN			("CN", "An Cabhán"), 
	CARLOW			("CW", "Ceatharlach"),
	DUBLIN			("D", "Baile Átha Cliath"), 
	DONEGAL			("DL", "Dún na nGall"), 
	GALWAY			("G", "Gaillimh"),
	KILDARE			("KE", "Cill Dara"), 
	KILKENNY		("KK", "Cill Chainnigh"), 
	KERRY			("KY", "Ciarraí"), 
	LIMERICK		("L", "Luimneach"),
	LONGFORD		("LD", "An Longfort"), 
	LOUTH			("LH", "Lú/Lughbhaidh"), 
	LEITRIM			("LM", "Liatroim"), 
	LAOIS			("LS", "Laois"),
	MEATH			("MH", "An Mhí"), 
	MONAGHAN		("MN", "Muineachán"), 
	MAYO			("MO", "Maigh Eo"), 
	OFFALY			("OY", "Uíbh Fhailí"),
	ROSCOMMON		("RN", "Ros Comáin"), 
	SLIGO			("SO", "Sligeach"), 
	TIPPERARY		("T", "Tiobraid Árann"),
	WATERFORD		("W", "Port Láirge"), 
	WESTMEATH		("WH", "An Iarmhí"), 
	WEXFORD			("WX", "Loch Garman"),
	WICKLOW			("WW", "Cill Mhantáin");

	private final String reg;
	private final String gaeilge;

	County(String reg, String gaeilge) {
		this.reg = reg;
		this.gaeilge = gaeilge;
	}

	public String reg() {
		return reg;
	}

	public String gaeilge() {
		return gaeilge;
	}
}