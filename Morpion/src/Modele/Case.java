package Modele;

import Vue.PresentationCase;

public class Case {

	public Joueur joueur;
	public PresentationCase presentation;

	public Case(Morpion morpion) {

		joueur = null;
		presentation = new PresentationCase(morpion, this);
	}

	public boolean estLibre() {
		return joueur == null;
	}

	public void jouerDefinitivement(Joueur joueur) {

		this.joueur = joueur;
		presentation.actualiser();

	}

	public void jouerProvisoirement(Joueur joueur) {
		this.joueur = joueur;
	}

	public void annuler() {
		joueur = null;
	}

}