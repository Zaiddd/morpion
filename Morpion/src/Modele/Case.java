package Modele;

import Vue.PresentationCase;

public class Case {

	public Joueur joueur;
	public PresentationCase presentation;

	/**
	 * @param morpion
	 *            morpion dans lequel se trouve la case
	 */
	public Case(Morpion morpion) {

		joueur = null;
		presentation = new PresentationCase(morpion, this);

	}

	/**
	 * 
	 * @return true si la case est libre, false si un joueur a deja joue dedans
	 */
	public boolean estLibre() {
		return joueur == null;
	}

	/**
	 * Joue un coup definitivement, met a jour la presentation.
	 * 
	 * @param joueur
	 *            joueur qui joue la case
	 */
	public void jouerDefinitivement(Joueur joueur) {

		this.joueur = joueur;

		presentation.actualiser();

	}

	/**
	 * Joue un coup provisoirement, peut etre annule.
	 * 
	 * @param joueur
	 *            joueur qui joue la case
	 */
	public void jouerProvisoirement(Joueur joueur) {
		this.joueur = joueur;
	}

	/**
	 * Annule le coup joue dans la case.
	 */
	public void annuler() {
		joueur = null;
	}

}