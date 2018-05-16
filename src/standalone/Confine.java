package standalone;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import bean.LoginBean;

/**
 * The Singleton Class Confine.
 */
public class Confine extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -860117513131001352L;

	private final static Confine confine = new Confine();

	private JPanel pannello;
	private LoginBean utenteCorrente;

	/**
	 * Early Initialization of confine.
	 */
	private Confine() {
		this.setSize(1280, 720);
		this.setTitle("Portale Annunci");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Confine globale.
	 *
	 * @return the unique istance of Confine
	 */
	public static Confine confineGlobale() {
		return Confine.confine;
	}

	public void mostraPannello(JPanel pnl) {
		if (pannello != null)
			this.getContentPane().remove(pannello);

		pannello = pnl;
		this.getContentPane().add(pannello, BorderLayout.CENTER);
		this.setVisible(true);
	}

	public LoginBean getUtenteCorrente() {
		return utenteCorrente;
	}

	public void setUtenteCorrente(LoginBean utenteCorrente) {
		this.utenteCorrente = utenteCorrente;
	}

}