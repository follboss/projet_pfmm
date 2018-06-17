package main;

import java.awt.EventQueue;

import controlleur.Controlleur;
import modele.Modele;
import modele.Simulateur;
import vue.Vue;

public class Main {
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//initialisation du modele
					Modele modele = new Modele();
					//initialisation du controller sur le modele
					Controlleur controlleur = new Controlleur(modele);
					//init de la vue sur le controller
					Vue vue = new Vue(controlleur);
					vue.getFrame().setVisible(true);
					
				
					//Simulation des données du modele
					Simulateur simulateur = new Simulateur(modele.getFrigo());
					//ajout des observateur
					simulateur.addObserver(modele);
					simulateur.addObserver(vue);
					
					// start la simulation 
					simulateur.simulation();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
