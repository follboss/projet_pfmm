package modele;

//classe de la carte arduino
public class Arduino {

	//capateur temp int, ext, humidité
	private Thermometre thermoInterieur;
	private Thermometre thermoExterieur;
	private CapteurHumidite capteurHumidite;

	//construteur de la classe arduino
	public Arduino(Thermometre thermoInterieur, Thermometre thermoExterieur, CapteurHumidite capteurHumidite) {
		super();
		this.thermoInterieur = thermoInterieur;
		this.thermoExterieur = thermoExterieur;
		this.capteurHumidite = capteurHumidite;
	}

	public Thermometre getThermoInterieur() {
		return thermoInterieur;
	}

	public void setThermoInterieur(Thermometre thermoInterieur) {
		this.thermoInterieur = thermoInterieur;
	}

	public Thermometre getThermoExterieur() {
		return thermoExterieur;
	}

	public void setThermoExterieur(Thermometre thermoExterieur) {
		this.thermoExterieur = thermoExterieur;
	}

	public CapteurHumidite getCapteurHumidite() {
		return capteurHumidite;
	}

	public void setCapteurHumidite(CapteurHumidite capteurHumidite) {
		this.capteurHumidite = capteurHumidite;
	}

}
