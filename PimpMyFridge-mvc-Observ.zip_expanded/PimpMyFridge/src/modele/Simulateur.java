package modele;

import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

//classe heritant du pattern observable
public class Simulateur extends Observable {

	private Frigo frigo;
	private Modele modele;
	private Arduino arduino;

	public Simulateur(Modele modele) {
		super();
		this.modele = modele;
		this.frigo = modele.getFrigo();
		this.arduino = frigo.getArduino();
	}

	//methode de simulation de donn�es
	public void simulation() {

		
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						//g�n�ration des variations des valeurs
						float humidite = (float) ThreadLocalRandom.current().nextDouble(-0.1, 0.1);
						float temperatureExt = (float) ThreadLocalRandom.current().nextDouble(-0.1, 0.1);
						float temperatureInt = 0;
						if (frigo.isEtat() && arduino.getThermoInterieur().getMesure() <= 27){
							temperatureInt = (float) ThreadLocalRandom.current().nextDouble(-0.1, 0);
						} else {
							temperatureInt = (float) ThreadLocalRandom.current().nextDouble(0, 0.05);
						}
							// MAJ des differntes donn�es en fonctions des variation g�n�r�es
						arduino.getCapteurHumidite().setMesure(arduino.getCapteurHumidite().getMesure() + humidite);
						arduino.getThermoExterieur()
								.setMesure(arduino.getThermoExterieur().getMesure() + temperatureExt);
						arduino.getThermoInterieur()
								.setMesure(arduino.getThermoInterieur().getMesure() + temperatureInt);
						// notification de l'observateur par rapport a la variations des donn�es
												
						setChanged();
						notifyObservers(null);
						
						modele.maj();
						
						Thread.sleep(1000);
					} catch (Exception e) {
						System.err.println("Erreur: Simulation impossible " + e);
					}
				}
			}
		}).start();

	}

	public Arduino getArduino() {
		return arduino;
	}

	public void setArduino(Arduino arduino) {
		this.arduino = arduino;
	}

}
