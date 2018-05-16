package standalone;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import bean.LoginBean;
import model.TipologiaUtente;

public class ConfineLogin extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8347368302522006743L;
	private JPanel pannelloCentro;
	private JPanel pannelloTitolo;
	private JPanel pannelloBottom;

	private JButton pulsanteLogin;
	private JTextField campoLogin, campoPassword;

	private JProgressBar barraAvanzamento;

	private boolean loginDone;
	private LoginBean loginBean;

	public ConfineLogin() {

		super(new BorderLayout());

		loginDone = false;

		pannelloTitolo = new JPanel();
		JLabel titolo = new JLabel("<html><br><br>Bentornato. Inserisci le tue credenziali.<br></br></html>",
				SwingConstants.CENTER);
		titolo.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		pannelloTitolo.add(titolo);

		pannelloCentro = new JPanel(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		campoLogin = new JTextField(15);
		campoLogin.setPreferredSize(new Dimension(180, 60));
		pannelloCentro.add(campoLogin, gbc);

		campoPassword = new JPasswordField(15);
		campoPassword.setPreferredSize(new Dimension(180, 60));
		pannelloCentro.add(campoPassword, gbc);

		pulsanteLogin = new JButton("Login");
		pulsanteLogin.setPreferredSize(new Dimension(200, 60));
		pulsanteLogin.addActionListener(new LoginListener());
		pannelloCentro.add(pulsanteLogin, gbc);

		pannelloBottom = new JPanel(new BorderLayout());

		barraAvanzamento = new JProgressBar();
		barraAvanzamento.setIndeterminate(true);
		barraAvanzamento.setVisible(false);
		pannelloBottom.add(barraAvanzamento, BorderLayout.EAST);

		this.add(pannelloTitolo, BorderLayout.NORTH);
		this.add(pannelloCentro, BorderLayout.CENTER);
		this.add(pannelloBottom, BorderLayout.SOUTH);

	}

	private class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {

			loginBean = new LoginBean();
			loginBean.setEmail(campoLogin.getText());
			loginBean.setPassword(campoPassword.getText());

			if (loginBean.validate()) {
				pulsanteLogin.setText("Collegamento...");
				barraAvanzamento.setVisible(true);
				new AzioneLogin().execute();
			}

			else
				JOptionPane.showMessageDialog(ConfineLogin.this, "Non hai compilato correttamente i campi.");

		}

	}

	private class AzioneLogin extends SwingWorker<Boolean, Void> {

		@Override
		protected Boolean doInBackground() throws Exception {

			return loginBean.login();
		}

		@Override
		protected void done() {

			super.done();
			
			try {
				loginDone = this.get();

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}

			pulsanteLogin.setText("Login");
			barraAvanzamento.setVisible(false);

			if (loginDone) {

				if (loginBean.getTipo().equals(TipologiaUtente.CONSUMATORE))
					JOptionPane.showMessageDialog(ConfineLogin.this, "Accesso riservato ad utenti di tipo Produttore");

				if (loginBean.getTipo().equals(TipologiaUtente.PRODUTTORE)) {
					Confine.confineGlobale().setUtenteCorrente(loginBean);
					Confine.confineGlobale().mostraPannello(new ConfineGestioneOrdini());
				}

			}

			else
				JOptionPane.showMessageDialog(ConfineLogin.this,
						"Assicurati di aver inserito correttamente le tue credenziali.");

		}

	}

}
