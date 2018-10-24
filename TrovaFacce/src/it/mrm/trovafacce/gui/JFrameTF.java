package it.mrm.trovafacce.gui;

import javax.swing.JFrame;

/**
 * JFrame in cui verrà visualizzata l'immagine catturata
 *
 */
public class JFrameTF extends JFrame {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JFrameTF() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Trova Facce");		
		getContentPane().add(new JPanelTF());
		setSize(667, 580);
		//posiziono la finestra al centro dello schermo
		setLocationRelativeTo(null);				
		//rendo visibile la finestra
		setVisible(true);		
	}	

}
