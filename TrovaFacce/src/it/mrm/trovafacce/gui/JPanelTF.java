package it.mrm.trovafacce.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import it.mrm.trovafacce.main.MainTF;
import it.mrm.trovafacce.thread.ThreadAcquisizione;

/**
 * JPanel contenente il bottone per avviare l'acquisizione e le immagini acquisite
 *
 */
public class JPanelTF extends JPanel {	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	/**
	 * BufferedImage in cui inserire l'immagine acquisita
	 */
	private BufferedImage image;

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}	

	public JPanelTF() {	
		try {					
			JButton buttonRecording = new JButton("Avvia");
			buttonRecording.addActionListener(l -> {
				if(buttonRecording.getText().equals("Avvia")) {
					//avvio il nuovo thread
					new ThreadAcquisizione(MainTF.idCamera, MainTF.dimensioneFaccia, this).start();					
					buttonRecording.setText("Ferma");
				} else {
					System.exit(1);
				}
			});
			add(buttonRecording);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Errore durante l'acquisizione: "+e.getMessage());
		}
	}

	public void paintComponent(Graphics g){  
		super.paintComponent(g);   
		if (this.image==null) return;  
		g.drawImage(this.image,10,50,image.getWidth(),this.image.getHeight(), null);  	
	}  

}
