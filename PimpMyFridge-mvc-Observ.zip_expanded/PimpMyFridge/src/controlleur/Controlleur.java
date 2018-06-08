package controlleur;

import modele.Frigo;
import modele.Modele;

public class Controlleur {
	private Modele modele;

	//constructeur du controller
	//controller de liason entre la vue et le modele
	public Controlleur(Modele modele) {
		
		super();
		this.modele = modele;
	}

	public Modele getModele() {
		return modele;
	}

	public void setModele(Modele modele) {
		this.modele = modele;
	}

	// recup de la T* int, ext, hum du frigo
	public float getInterieur() {
		return modele.getFrigo().getArduino().getThermoInterieur().getMesure();
	}
	// recup de la T* ext
	public float getExterieur() {
		return modele.getFrigo().getArduino().getThermoExterieur().getMesure();

	}
	// recup de l' hum
	public float getHumidite() {
		return modele.getFrigo().getArduino().getCapteurHumidite().getMesure();
	}
	// recup du point de rozé
	public float getPointRosee() {
		return modele.getFrigo().getPointRosee();
	}

	public int getConsigne() {
		return modele.getFrigo().getConsigne();
	}
//	recuperation de l'etat du frigo
	public boolean getEtat() {
		return modele.getFrigo().isEtat();
	}
// changement de la consigne du frigo
	public void setConsigne(int value) {
		modele.getFrigo().setConsigne(value);
	}
// verification consigne atteinte
	public boolean isConsigne() {
		Frigo frigo = modele.getFrigo();
		
		return modele.seuille(frigo.getArduino().getThermoInterieur().getMesure(), frigo.getConsigne());
	}
	// verification rosée atteint
	public boolean isRosee() {
		Frigo frigo = modele.getFrigo();
		
		return modele.pointrosee(frigo.getArduino().getThermoInterieur().getMesure(), frigo.getArduino().getCapteurHumidite().getMesure());
	}

	//changement de l'etat du frigo
	public void commuter() {
		
		modele.getFrigo().commuter();
		
	}
	
	
	

}
