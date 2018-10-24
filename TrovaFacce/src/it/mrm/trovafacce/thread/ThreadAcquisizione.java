package it.mrm.trovafacce.thread;

import org.opencv.core.Mat;

import it.mrm.ocvu.cam.GestoreAcquisizioni;
import it.mrm.ocvu.detectors.FaceDetector;
import it.mrm.ocvu.utilities.OpenCVUtilities;
import it.mrm.trovafacce.gui.JPanelTF;

/**
 * Thread che gestisce l'acquisizione da webcam
 *
 */
public class ThreadAcquisizione extends Thread {
		
	/**
	 * Gestore Acquisizioni da utilizzare
	 */
	private GestoreAcquisizioni ga;
	/**
	 * FaceDetector da applicare su ogni immagine catturata
	 */
	private FaceDetector fd;
	/**
	 * Contenitore per il frame che viene catturato
	 */
	private Mat frame;
	
	/**
	 * Pannello da gestire
	 */
	private JPanelTF pannello;
	
	/**
	 * Costruttore
	 * @param idCamera identificativo della camera
	 * @param dimensioneFaccia dimensione della faccia da prendere come riferimento
	 * @param pannello da gestire
	 */
	public ThreadAcquisizione(int idCamera, int dimensioneFaccia, JPanelTF pannello) {			
		ga = new GestoreAcquisizioni(idCamera);			
		fd = new FaceDetector(dimensioneFaccia, "resources/haarcascade_frontalface_alt.xml");		
		this.pannello = pannello;
	}	
	
	@Override
	public void run() {
		try {							
				while (true) {						
					frame = ga.acquisisciFrame();
					frame = OpenCVUtilities.ridimensionaImmagine(frame, 640, 480);
					frame = fd.detect(frame);
					pannello.setImage(OpenCVUtilities.matToBufferedImage(frame));
					pannello.repaint();
				}				
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
