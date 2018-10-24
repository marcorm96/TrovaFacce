package it.mrm.trovafacce.main;

import org.opencv.core.Core;

import it.mrm.trovafacce.gui.JFrameTF;

public class MainTF {

	public static int idCamera = 0; 
	public static int dimensioneFaccia = 50;
	
	public static void main(String[] args) {		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		if(args.length != 0) {
			idCamera = Integer.parseInt(args[0]);
			dimensioneFaccia = Integer.parseInt(args[1]);
		}
		
		new JFrameTF();		
	}

}
