package com.thinkInJava.interface09;

interface InstrumentInterFace{
	int INTValue = 0; //Ä¬ÈÏÊÇstatic final
	void play(); // Ä¬ÈÏpublic  
	void adjust();
}

class WindInterFace implements InstrumentInterFace{
	@Override
	public void adjust() {
		System.out.println("windinterFace adjusting"); 
	}

	@Override
	public void play() {
		System.out.println("windinterFace play");
	}
	
}

class Percussion implements InstrumentInterFace{

	@Override
	public void adjust() {
		System.out.println("Percussion adjust");
	}

	@Override
	public void play() {
		System.out.println("Percussion play");
	}
	
}


public class Music5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		InstrumentInterFace[] i = {new Percussion(),new WindInterFace()};
		for(InstrumentInterFace thisInst : i){
			thisInst.play();
		}
	}

}
