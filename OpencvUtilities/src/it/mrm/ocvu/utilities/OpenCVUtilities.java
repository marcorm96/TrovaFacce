package it.mrm.ocvu.utilities;

import java.awt.image.BufferedImage;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * Classe contenente i metodi di utilità di OpenCV
 *
 */
public class OpenCVUtilities {

	/**
	 * Converte l'oggetto Mat passato in un oggetto BufferedImage
	 * @param matrix frame da convertire
	 * @return bufferedImage ottenuta dalla conversione
	 */
	public static BufferedImage matToBufferedImage(Mat matrix){		
		BufferedImage bimg = new BufferedImage(matrix.width(), matrix.height(), BufferedImage.TYPE_INT_RGB);
		if ( matrix != null ) { 
			int cols = matrix.cols();  
			int rows = matrix.rows();  
			int elemSize = (int)matrix.elemSize();  
			byte[] data = new byte[cols * rows * elemSize];  
			int type;  
			matrix.get(0, 0, data);  
			switch (matrix.channels()) {  
			case 1:  
				type = BufferedImage.TYPE_BYTE_GRAY;  
				break;  
			case 3:  
				type = BufferedImage.TYPE_3BYTE_BGR;  
				// bgr to rgb  
				byte b;  
				for(int i=0; i<data.length; i=i+3) {  
					b = data[i];  
					data[i] = data[i+2];  
					data[i+2] = b;  
				}  
				break;  
			default:  
				return null;  
			}  		        
			// Reuse existing BufferedImage if possible
			if (bimg == null || bimg.getWidth() != cols || bimg.getHeight() != rows || bimg.getType() != type) {
				bimg = new BufferedImage(cols, rows, type);
			}        
			bimg.getRaster().setDataElements(0, 0, cols, rows, data);
		}
		return bimg;  
	}  	

	/**
	 * Restituisce l'immagine ottenuta dalla conversione in scala di grigi
	 * @param immagine immagine da convertire
	 * @return l'immagine convertito
	 */
	public static Mat convertiInGrigi(Mat immagine) {
		Mat grigi=new Mat();
		Imgproc.cvtColor(immagine, grigi, Imgproc.COLOR_RGB2GRAY);
		return grigi;
	}

	/**
	 * Restituisce l'immagine ottenuta dal ridimensionamento
	 * @param immagine immagine da convertire
	 * @param width larghezza della nuova immagine
	 * @param height altezza del nuova immagine
	 * @return l'immagine convertita
	 */
	public static Mat ridimensionaImmagine(Mat immagine, int width, int height) {
		Mat immagineRid = new Mat();
		Size s=new Size(width, height);
		Imgproc.resize(immagine , immagineRid, s);
		return immagineRid;
	}

	/**
	 * Effettua il salvataggio dell'immagine passata sul disco
	 * @param immagine immagine da salvare
	 * @param path percorso in cui salvare l'immagine comprendente anche il nome del file con estensione
	 * @return true se il salvataggio è andato a buon fine
	 */
	public static boolean salvaMat(Mat immagine, String path) {		
		return Imgcodecs.imwrite(path, immagine);    			    	    	    	
	}

	/**
	 * Legge un'immagine dal disco
	 * @param path path in cui si trova l'immagine da leggere
	 * @return l'immagine letta
	 */
	public static Mat leggiImmagine(String path) {
		return Imgcodecs.imread(path);
	}
	
}
