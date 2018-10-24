package it.mrm.ocvu.detectors;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

import it.mrm.ocvu.utilities.OpenCVUtilities;

/**
 * Classe che implementa l'algoritmo di face detector
 *
 */
public class FaceDetector implements Detector {
	
	/**
	 * Classificatore per identificare i volti
	 */
	private CascadeClassifier faceClassifier;
	/**
	 * Dimensione della faccia all'interno dell'immagine
	 */
	private int dimensioneFaccia;
	
	/**
	 * Costruttore
	 * @param dimensioneFaccia dimensione di un volto all'interno dell'immagine
	 * @param path percorso in cui si trova l'xml da utilizzare
	 */
	public FaceDetector(int dimensioneFaccia, String path) {						
		this.faceClassifier = new CascadeClassifier(path);
		this.dimensioneFaccia = dimensioneFaccia;		
	}
	
	@Override	
	public Mat detect(Mat frame) {
		//converto il frame in scala di grigi
		Mat gray = OpenCVUtilities.convertiInGrigi(frame);
		//equalizzo l'istogramma
		Imgproc.equalizeHist(gray, gray);
		//le operazioni effettuate servono per migliorare il risultato
		
		//se la dimensione della faccia è 0 prendo come riferimento il 20% dell'altezza del frame
		if (dimensioneFaccia == 0){
		    int height = gray.rows();
		    if (Math.round(height * 0.2f) > 0) {
		            dimensioneFaccia = Math.round(height * 0.2f);
		    }
		}
		MatOfRect facce = new MatOfRect();
		//richiamo l'algoritmo di detenction
		faceClassifier.detectMultiScale(gray, facce, 1.1, 2, 0 | Objdetect.CASCADE_SCALE_IMAGE, new Size(dimensioneFaccia, dimensioneFaccia), new Size());
		
		//disegno sul frame dei rettangoli in corrispondenza delle facce trovate
		Mat f = frame.clone();
		Rect[] facesArray = facce.toArray();
		for (int i = 0; i < facesArray.length; i++) {
		    Imgproc.rectangle(f, facesArray[i].tl(), facesArray[i].br(), new Scalar(0, 255, 0, 255), 3);
		}
		
		return f;
	}

}
