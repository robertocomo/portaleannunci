package standalone;

import javax.swing.SwingUtilities;

/**
 * La classe di avvio dell'Applicazione Standalone.
 */
public class _Avvio {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		 SwingUtilities.invokeLater( new Runnable() {
	            public void run() {
	            	Confine.confineGlobale().mostraPannello(new ConfineLogin());;
	            }
	        });
		
		

	}

}
