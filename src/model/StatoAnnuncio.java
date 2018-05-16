package model;

public enum StatoAnnuncio {
	PUBLIC,
	PRIVATE,
	GONE;
	
	public static boolean contains(String test) {

	    for (StatoAnnuncio type : StatoAnnuncio.values()) {
	        if (type.name().equals(test)) {
	            return true;
	        }
	    }

	    return false;
	}
	
	
	public static StatoAnnuncio equals(String test) {

	    for (StatoAnnuncio type : StatoAnnuncio.values()) {
	        if (type.name().equals(test)) {
	            return type;
	        }
	    }

	    return null;
	}
	

}
