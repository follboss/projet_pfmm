package modele;

import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

//classe heritant du pattern observable
public class Simulateur extends Observable {

	private Frigo frigo;
	private Arduino arduino;

	public Simulateur(Frigo frigo) {
		super();
		this.frigo = frigo;
		this.arduino = frigo.getArduino();
	}

	//methode de simulation de données
	public void simulation() {

		
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						//génération des variations des valeurs
						float humidite = (float) ThreadLocalRandom.current().nextDouble(-0.1, 0.1);
						float temperatureExt = (float) ThreadLocalRandom.current().nextDouble(-0.1, 0.1);
						float temperatureInt = 0;
						if (frigo.isEtat() && arduino.getThermoInterieur().getMesure() <= 27){
							temperatureInt = (float) ThreadLocalRandom.current().nextDouble(-0.1, 0);
						} else {
							temperatureInt = (float) ThreadLocalRandom.current().nextDouble(0, 0.05);
						}
							// MAJ des differntes données en fonctions des variation générées
						arduino.getCapteurHumidite().setMesure(arduino.getCapteurHumidite().getMesure() + humidite);
						arduino.getThermoExterieur()
								.setMesure(arduino.getThermoExterieur().getMesure() + temperatureExt);
						arduino.getThermoInterieur()
								.setMesure(arduino.getThermoInterieur().getMesure() + temperatureInt);
						// notification de l'observateur par rapport a la variations des données
												
						setChanged();
						notifyObservers(null);
						
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
