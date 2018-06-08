package modele;

public class Frigo{
	
	private boolean etat;
	private boolean etatManuel;
	private int consigne;
	private float pointRosee;
	private Arduino arduino;
	
	
	

	public Frigo(boolean etat, int consigne, float pointRosee, Arduino arduino) {
		super();
		this.etat = etat;
		this.etatManuel = etat;
		this.consigne = consigne;
		this.pointRosee = pointRosee;
		this.arduino = arduino;
	}



// raviable d'état du frigo (automatique)
	public boolean isEtat() {
		return etat;
	}




	public void setEtat(boolean etat) {
		this.etat = etat;
	}




	public int getConsigne() {
		return consigne;
	}




	public void setConsigne(int consigne) {
		this.consigne = consigne;
	}




	public float getPointRosee() {
		return pointRosee;
	}




	public void setPointRosee(float pointRosee) {
		this.pointRosee = pointRosee;
	}




	public Arduino getArduino() {
		return arduino;
	}




	public void setArduino(Arduino arduino) {
		this.arduino = arduino;
	}

	// alumage automatique du frigo
	public void allumer() {
		if(isEtatManuel()) {
			this.setEtat(true);
		}
		else {
			this.setEtat(false);
		}
		
	}
	// instinction automatique du frigo
	public void eteindre() {
		this.setEtat(false);
	}
	
	//commutation d'etat manuel du frigo
	public void commuter() {
		
		this.setEtatManuel(!this.isEtatManuel());
		this.setEtat(isEtatManuel());
	}



// variable d'état manuel (on/off sur ihm)
	public boolean isEtatManuel() {
		return etatManuel;
	}




	public void setEtatManuel(boolean etatManuel) {
		this.etatManuel = etatManuel;
	}

}
