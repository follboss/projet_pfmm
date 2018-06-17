package modele;

import java.util.Observable;

//classe des capteurs arduino heritant du pattern observable
public class Capteur extends Observable {

	protected float mesure;
	
	

	public Capteur(float mesure) {
		super();
		this.mesure = mesure;
	}

	public float getMesure() {
		return mesure;
	}

	public void setMesure(float mesure) {
		this.mesure = mesure;
	}
	
	
}
