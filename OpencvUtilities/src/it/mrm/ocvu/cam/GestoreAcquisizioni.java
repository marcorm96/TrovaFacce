package it.mrm.ocvu.cam;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

/**
 * Classe contenente i metodi per la gestione delle acquisizioni
 *
 */
public class GestoreAcquisizioni {
	
	/**
	 * Camera selezionata
	 */
	private VideoCapture camera;
	
	/**
	 * Costruttore
	 * @param idCamera camera da gestire
	 */
	public GestoreAcquisizioni(int idCamera) {
		camera = new VideoCapture(idCamera);		
	}

	/**
	 * Restituisce l'immagine catturata
	 * @return immagine acquisita
	 * @throws Exception
	 */
	public Mat acquisisciFrame() throws Exception {
		//verifico che la telecamera sia aperta
		if(!camera.isOpened()){					
			camera.release();
			throw new Exception("Telecamera non aperta");					
		}
		Mat frame = new Mat();
		//leggo il frame dal disco
		if(!camera.read(frame)) {
			camera.release();
			throw new Exception("Errore nella lettura del frame");
		}
		//richiamo il garbage collector
		System.gc();
		return frame;
	}
	
}
