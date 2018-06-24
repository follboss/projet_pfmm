package modele;

import static java.lang.Math.pow;

import java.util.Observable;
import java.util.Observer;

public class Modele extends Observable{
	private Frigo frigo;

	public Modele() {
		super();
		Thermometre thermoInterieur = new Thermometre(20);
		Thermometre thermoExterieur = new Thermometre(20);
		CapteurHumidite capteurHumidite = new CapteurHumidite(30);
		Arduino arduino = new Arduino(thermoInterieur, thermoExterieur, capteurHumidite);
		setFrigo(new Frigo(true, 7, 0, arduino));
		frigo.allumer();
	}

	public void maj() {
				// allumage et instinction du frigo en fonction de l'atteinte de la consigne ou de l'atteinte du point de rosé
		if(pointrosee(frigo.getArduino().getThermoInterieur().getMesure(), frigo.getArduino().getCapteurHumidite().getMesure()) || seuille(frigo.getArduino().getThermoInterieur().getMesure(), frigo.getConsigne())) {
			frigo.eteindre();
		}
		else {
			frigo.allumer();
		}
		setChanged();
		notifyObservers();
	}

	public Frigo getFrigo() {
		return frigo;
	}

	public void setFrigo(Frigo frigo) {
		this.frigo = frigo;
	}

	// si le point de rosée est atteint (temperature trop grande
	public boolean pointrosee(float temperature, float humidite) {
		float rosee = (float) (pow((double) (humidite / 100), 0.125) * (112 + (0.9 * temperature)) + (0.1 * temperature)
				- 112);
		this.frigo.setPointRosee(rosee);
		// temperature trop basse, exctinction du frigo
		if (temperature <= rosee) {
			//System.out.println("frigo eteint");
			return true;
		} else {

			return false;
		}

	}

	// Si la temperature dépasse le seuille en moins ou en plus -> true
	public boolean seuille(float temperature, int consigne) {
		if (temperature > consigne -1 && temperature < consigne + 1 ) {
			return true;
		}

		return false;
	}


}
