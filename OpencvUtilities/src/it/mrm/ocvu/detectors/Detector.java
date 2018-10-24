package it.mrm.ocvu.detectors;

import org.opencv.core.Mat;

/**
 * Interfaccia da estendere nelle classi che implementano un algoritmo di detecting
 *
 */
public interface Detector {
	
	/**
	 * Implementa l'algoritmo di detecting sul frame passato
	 * @param frame frame su cui applicare l'algoritmo
	 * @return un nuovo frame contenente le modifiche effettuate dall'algoritmo
	 */
	Mat detect(Mat frame);

}
