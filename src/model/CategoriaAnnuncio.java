package model;

public enum CategoriaAnnuncio {
	
	ABBIGLIAMENTO,
	ARREDAMENTO,
	CREATIVITÀ,
	ELETTRONICA,
	LIBRI,
	MUSICA;
	
	
	
	
	public static boolean contains(String test) {

	    for (CategoriaAnnuncio type : CategoriaAnnuncio.values()) {
	        if (	type.name().equals(test) || type.name().equals(test.toUpperCase()) || type.name().equals(test.toLowerCase())) {
	            return true;
	        }
	    }

	    return false;
	}

	
	public static String getCapitalizedName(String input) {
		
		 if (input == null || input.length() == 0) 
		        return input;
		    
		return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
	
	}
	
	
	public static CategoriaAnnuncio equals(String test) {

	    for (CategoriaAnnuncio type : CategoriaAnnuncio.values()) {
	        if (type.name().toLowerCase().equals(test.toLowerCase())) {
	            return type;
	        }
	    }

	    return null;
	}
	
	
	

}
