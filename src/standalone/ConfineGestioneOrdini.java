package standalone;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import bean.OrdineBean;
import controller.GestioneAcquistoController;
import exceptions.ItemNotFoundException;


public class ConfineGestioneOrdini extends JPanel{

	private JPanel pannelloCentro;
	private JPanel pannelloTitolo;
	private JPanel pannelloRisultati;
	private JLabel titolo;
	
	
	
	private List<OrdineBean> ordini;
	private boolean found;
	private JProgressBar barraAvanzamento;
	
	public ConfineGestioneOrdini() {
		
		super(new BorderLayout());
		
		pannelloTitolo = new JPanel(new BorderLayout());
		
		
		titolo = new JLabel("<html>Bentornato " + Confine.confineGlobale().getUtenteCorrente().getNome() + ".<br></br></html>", JLabel.LEFT);	
		titolo.setPreferredSize( new Dimension(460,60));
		titolo.setFont(new Font("Segoe UI", Font.PLAIN,25));
		
		pannelloTitolo.add(titolo, BorderLayout.WEST);
		
		
		JPanel pannelloControllo = new JPanel();
		
		JButton logout = new JButton("Logout");
		logout.setPreferredSize( new Dimension(200,60));
		logout.setFont(new Font("Segoe UI", Font.PLAIN,18));
		
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				int loginDialogResult = JOptionPane.showConfirmDialog(ConfineGestioneOrdini.this, "Confermi di volerti disconnettere ?",
						"Logout", JOptionPane.YES_NO_OPTION);
				
				if (loginDialogResult == JOptionPane.YES_OPTION)
				{
					
					Confine.confineGlobale().setUtenteCorrente(null);
					JOptionPane.showMessageDialog(ConfineGestioneOrdini.this, "Stai per essere disconnesso. A presto !");
					Confine.confineGlobale().mostraPannello(new ConfineLogin());;
					
				}
	
				
				
			}});
		
		
		
		JButton ordini = new JButton("I tuoi ordini");
		ordini.setPreferredSize( new Dimension(200,60));
		ordini.setFont(new Font("Segoe UI", Font.PLAIN,16));
		
		ordini.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Confine.confineGlobale().mostraPannello(new ConfineGestioneOrdini());;
			}});
		
		pannelloControllo.add(logout);		
		pannelloControllo.add(ordini);
		pannelloTitolo.add(pannelloControllo, BorderLayout.EAST);
		
		
		this.add(pannelloTitolo,BorderLayout.NORTH);
		
		
		
		JPanel pannelloBottom = new JPanel(new BorderLayout());
		
		barraAvanzamento = new JProgressBar();
		barraAvanzamento.setIndeterminate(true);
		barraAvanzamento.setVisible(false);
		pannelloBottom.add(barraAvanzamento, BorderLayout.EAST);
		
		this.add(pannelloBottom,BorderLayout.SOUTH);
		
		
		
		found = false;
		
		barraAvanzamento.setVisible(true);
		new CercaOrdini().execute();
		
		
	}
	
	
	
	
	private void costruisciPannello() {
		
		pannelloCentro = new JPanel();
		
		
		pannelloRisultati = new JPanel(new GridLayout(ordini.size(),1));
		pannelloRisultati.setBounds(0, 0, 1280, 720);
		
		for(OrdineBean ordineBean: ordini) 
		{
			
			JPanel pannelloElemento = new JPanel(new GridBagLayout());
			
			
			GridBagConstraints gbc = new GridBagConstraints();

			gbc.fill = GridBagConstraints.HORIZONTAL ;
			
			
			JLabel idOrdine = new JLabel("Ordine ID #" + ordineBean.getIDOrder());
			idOrdine.setFont(new Font("Segoe UI", Font.BOLD,18));
			gbc.insets = new Insets(25,0,10,0);
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 1;
			gbc.ipadx = 30;
			gbc.ipady = 25;
			
			
			pannelloElemento.add(idOrdine, gbc);			
			
			
			JLabel dataOrdine = new JLabel(ordineBean.getTimestamp());
			dataOrdine.setFont(new Font("Segoe UI", Font.ITALIC,15));
			
			gbc.insets = new Insets(25,0,10,0);
			gbc.gridx = 6;
			gbc.gridy = 0;
			gbc.gridwidth = 1;
			gbc.ipadx = 30;
			gbc.ipady = 25;
			
			
			pannelloElemento.add(dataOrdine, gbc);		
			
			
			JLabel annuncio = new JLabel("Annuncio:");
			gbc.insets = new Insets(0,0,0,0);
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.ipadx = 30;
			gbc.ipady = 25;
			gbc.gridwidth = 1;
			
			pannelloElemento.add(annuncio, gbc);		
			
			
			JLabel titoloAnnuncio = new JLabel(ordineBean.getTitoloAnnuncio());
			titoloAnnuncio.setSize(15, 30);
			titoloAnnuncio.setPreferredSize( new Dimension(460,15));
			titoloAnnuncio.setFont(new Font("Segoe UI", Font.ITALIC,17));
			gbc.insets = new Insets(0,0,0,0);
			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.ipadx = 30;
			gbc.ipady = 25;
			gbc.gridwidth = 2;
			
			pannelloElemento.add(titoloAnnuncio, gbc);	
			
			
			JLabel quantit‡ = new JLabel("Quantit‡:");
			gbc.insets = new Insets(0,0,0,0);
			quantit‡.setPreferredSize( new Dimension(190,15));
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.ipadx = 30;
			gbc.ipady = 25;
			gbc.gridwidth = 1;
			
			pannelloElemento.add(quantit‡, gbc);	
			
			
			JLabel quantit‡Ordine = new JLabel("" + ordineBean.getQuantit‡());
			gbc.insets = new Insets(0,0,0,0);
			gbc.gridx = 1;
			gbc.gridy = 2;
			gbc.ipadx = 30;
			gbc.ipady = 25;
			gbc.gridwidth = 2;
			
			pannelloElemento.add(quantit‡Ordine, gbc);	
			
			
			JLabel prezzo = new JLabel("Prezzo:");
			gbc.insets = new Insets(0,0,0,0);
			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.ipadx = 30;
			gbc.ipady = 25;
			gbc.gridwidth = 1;
			
			pannelloElemento.add(prezzo, gbc);	
			
			
			JLabel prezzoOrdine = new JLabel("" + ordineBean.getPrezzo() + " Ä");
			gbc.insets = new Insets(0,0,0,0);
			gbc.gridx = 1;
			gbc.gridy = 3;
			gbc.ipadx = 30;
			gbc.ipady = 25;
			gbc.gridwidth = 2;
			
			pannelloElemento.add(prezzoOrdine, gbc);	
			
			
			JLabel totale = new JLabel("Totale:");
			totale.setFont(new Font("Segoe UI", Font.BOLD,17));
			gbc.insets = new Insets(0,0,0,0);
			gbc.gridx = 0;
			gbc.gridy = 4;
			gbc.ipadx = 30;
			gbc.ipady = 25;
			gbc.gridwidth = 1;
			
			pannelloElemento.add(totale, gbc);	
			
			
			JLabel totaleOrdine = new JLabel("" + ordineBean.getPrezzoComplessivo() + " Ä");
			totaleOrdine.setFont(new Font("Segoe UI", Font.BOLD,17));
			gbc.insets = new Insets(0,0,0,0);
			gbc.gridx = 1;
			gbc.gridy = 4;
			gbc.ipadx = 30;
			gbc.ipady = 25;
			gbc.gridwidth = 2;
			
			pannelloElemento.add(totaleOrdine, gbc);	
			
			
			
			JLabel payment = new JLabel("Payment Transaction:");
			gbc.insets = new Insets(0,0,0,0);
			gbc.gridx = 0;
			gbc.gridy = 5;
			gbc.ipadx = 30;
			gbc.ipady = 25;
			gbc.gridwidth = 1;
			
			pannelloElemento.add(payment, gbc);
			
						
			JLabel paymentTransaction = new JLabel(ordineBean.getPaymentTransaction());
			paymentTransaction.setFont(new Font("Segoe UI", Font.ITALIC,16));
			gbc.insets = new Insets(0,0,0,0);
			gbc.gridx = 1;
			gbc.gridy = 5;
			gbc.ipadx = 30;
			gbc.ipady = 25;
			gbc.gridwidth = 2;
			
			pannelloElemento.add(paymentTransaction, gbc);
		
			
			
			JLabel spedito = new JLabel("Spedito:");
			gbc.insets = new Insets(20,0,0,0);
			gbc.gridx = 0;
			gbc.gridy = 6;
			gbc.ipadx = 30;
			gbc.ipady = 25;
			gbc.gridwidth = 1;
			
			pannelloElemento.add(spedito, gbc);
			
						
			JLabel speditoOrdine = new JLabel(ordineBean.isShipped() ? ordineBean.getTracking() : "NO");
			gbc.insets = new Insets(20,0,0,0);
			gbc.gridx = 1;
			gbc.gridy = 6;
			gbc.ipadx = 30;
			gbc.ipady = 25;
			gbc.gridwidth = 2;
			
			pannelloElemento.add(speditoOrdine, gbc);
			
	
			
			JLabel spedisciA = new JLabel("Spedisci a:");
			gbc.insets = new Insets(0,0,0,0);
			gbc.gridx = 0;
			gbc.gridy = 7;
			gbc.ipadx = 30;
			gbc.ipady = 25;
			gbc.gridwidth = 1;
			
			pannelloElemento.add(spedisciA, gbc);
			
						
			JLabel indirizzo = new JLabel(ordineBean.getIndirizzoSpedizione());
			indirizzo.setPreferredSize( new Dimension(520,15));
			gbc.insets = new Insets(0,0,0,0);
			gbc.gridx = 1;
			gbc.gridy = 7;
			gbc.ipadx = 30;
			gbc.ipady = 25;
			gbc.gridwidth = 2;
			
			pannelloElemento.add(indirizzo, gbc);
			
			
			
			JButton inserisciTracking = new JButton((ordineBean.isShipped() ? "Modifica " : "Inserisci ") +  "Tracking");
			inserisciTracking.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					String tracking;
					
					if(!ordineBean.isShipped())
						 tracking = JOptionPane.showInputDialog(ConfineGestioneOrdini.this,  "Inserisci il tracking della spedizione", 
						        "Dati Spedizione", 
						        JOptionPane.WARNING_MESSAGE);
					else
						tracking = (String) JOptionPane.showInputDialog(ConfineGestioneOrdini.this, "Modifica il tracking della spedizione",
							 "Dati Spedizione", JOptionPane.WARNING_MESSAGE,null,null,ordineBean.getTracking());
					 
					 
					 
					 if(tracking !=null)
					 {
						if(tracking.trim().isEmpty())
							JOptionPane.showMessageDialog(ConfineGestioneOrdini.this, "Valore non valido. Modifica non apportata.");
						else
						{	
							if(ordineBean.inserisciTracking(tracking))
							{
								JOptionPane.showMessageDialog(ConfineGestioneOrdini.this, "Modifica apportata con successo");
								Confine.confineGlobale().mostraPannello(new ConfineGestioneOrdini());
							}
							else
								JOptionPane.showMessageDialog(ConfineGestioneOrdini.this, "Errore nel sottomettere la richiesta.");
						
						}
						
					 }
					 
					 
					 else
						 JOptionPane.showMessageDialog(ConfineGestioneOrdini.this, "Annullato.");
					 
				}});
			
			
			gbc.insets = new Insets(10,0,0,0);
			gbc.gridx = 6;
			gbc.gridy = 8;
			gbc.gridwidth = 1;
			gbc.ipadx = 0;
			gbc.ipady = 0;
			pannelloElemento.add(inserisciTracking, gbc);
			
			
			if(ordineBean.isShipped())
			{
			
						JButton eliminaTracking = new JButton("Rimuovi Tracking");
						eliminaTracking.addActionListener(new ActionListener() {
			
							@Override
							public void actionPerformed(ActionEvent arg0) {
								
								
								int dialogResult = JOptionPane.showConfirmDialog(ConfineGestioneOrdini.this, "Confermi di voler rimuovere il Tracking ?",
										"Rimuovi Spedizione", JOptionPane.YES_NO_OPTION);
								
								if (dialogResult == JOptionPane.YES_OPTION)
								{
									
									if(ordineBean.rimuoviTracking())
									{
										JOptionPane.showMessageDialog(ConfineGestioneOrdini.this, "Modifica apportata con successo");
										Confine.confineGlobale().mostraPannello(new ConfineGestioneOrdini());
									}
									else
										JOptionPane.showMessageDialog(ConfineGestioneOrdini.this, "Errore nel sottomettere la richiesta.");
									
									
									
								}
									//
								else
									 JOptionPane.showMessageDialog(ConfineGestioneOrdini.this, "Annullato.");
								
							}});
						
						
						gbc.insets = new Insets(0,0,0,0);
						gbc.gridx = 6;
						gbc.gridy = 9;
						gbc.gridwidth = 1;
						gbc.ipadx = 0;
						gbc.ipady = 0;
						pannelloElemento.add(eliminaTracking, gbc);
			
			}
			
			
			
			
			
			
			JPanel separatore = new JPanel(new BorderLayout());
			separatore.setPreferredSize( new Dimension(600,1));
			separatore.setBackground(Color.darkGray);
			gbc.insets = new Insets(15,0,15,0);
			gbc.gridx = 0;
			gbc.gridy = ordineBean.isShipped() ? 10 : 9;
			gbc.gridwidth = 7;
			gbc.ipadx = 0;
			gbc.ipady = 0;
			
			pannelloElemento.add(separatore, gbc);
			

			
			pannelloRisultati.add(pannelloElemento);
			
		}
		
		
		JScrollPane scroll = new JScrollPane(pannelloRisultati, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		scroll.getVerticalScrollBar().setUnitIncrement(16);
		
		pannelloCentro.add(scroll);
		
		
		
		this.add(scroll,BorderLayout.CENTER);
		
		Confine.confineGlobale().mostraPannello(this);
		
		
	}
	
	
	
	
	private class CercaOrdini extends SwingWorker<Boolean, Void> 
	{
		

		@Override
		protected Boolean doInBackground() throws Exception {
			
			try {
				ordini = new GestioneAcquistoController().estraiOrdiniProduttore(Confine.confineGlobale().getUtenteCorrente().getId());
				Collections.sort(ordini , new ordinaPerUltimoOrdine());
				return true;
			}
			catch (ItemNotFoundException e) {
				return false;
			}
				
		}


		@Override
		protected void done() {
			
			super.done();
						
			try {
				found = get();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			
			
			
			barraAvanzamento.setVisible(false);
			
			
			if(found) 
			{
				SwingUtilities.invokeLater( new Runnable() {
			            public void run() {
			            	costruisciPannello();
			            }
			        });
				
				
			}
			
			else
				JOptionPane.showMessageDialog(ConfineGestioneOrdini.this, "Non Ë stato trovato alcun ordine.");
			
			
			
		}
		
		
		

	}
	
	
	
	public class ordinaPerUltimoOrdine implements Comparator<OrdineBean>{
		

		@Override
        public int compare(OrdineBean ordine1, OrdineBean ordine2) {

        	Date dataOrdine1; 
			Date dataOrdine2;

        	DateFormat format = DateFormat.getDateInstance();
        	try {
				dataOrdine1 = format.parse(ordine1.getTimestamp());
				dataOrdine2 = format.parse(ordine2.getTimestamp());

			} catch (ParseException e) {
				return 0;
			}
        	
        	if(dataOrdine1.before(dataOrdine2))
				return 1;
		
        	if(dataOrdine1.after(dataOrdine2))
				return -1;
		
        	if(ordine1.getIDOrder() < ordine2.getIDOrder())
        		return 1;
        	
        	if(ordine1.getIDOrder() > ordine2.getIDOrder())
        		return -1;
        	
        	
        	return 0;
        }
	
	}
	

	
}