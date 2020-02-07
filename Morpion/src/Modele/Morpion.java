package Modele;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import Vue.PresentationMorpion;

public class Morpion {

	private static final Random RANDOM = new Random();

	private Case[][] grille;

	private Joueur humain;

	private Joueur ordinateur;

	private int nombreCasesJouees;

	public PresentationMorpion presentation;

	public Morpion(boolean ordinateurCommence) {

		grille = new Case[3][3];

		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				grille[x][y] = new Case(this);

			}

		}
		humain = new Joueur("X", Color.BLUE);
		ordinateur = new Joueur("O", Color.RED);

		nombreCasesJouees = 0;
		presentation = new PresentationMorpion(this);

		if (ordinateurCommence) {
			jouerOrdinateur();
		}

	}

	public Case getCase(int x, int y) {
		return grille[x][y];
	}

	public void jouerHumain(Case caseJouee) {

		if (!partieFinie()) {
			caseJouee.jouerDefinitivement(humain);
			nombreCasesJouees++;

			if (!partieFinie()) {
				jouerOrdinateur();
			}

		}

	}

	private boolean partieFinie() {
		return getVainqueur() != null || nombreCasesJouees == 9;
	}

	private void jouerOrdinateur() {

		Case meilleureCase = getMeilleureCase();
		meilleureCase.jouerDefinitivement(ordinateur);
		nombreCasesJouees++;

	}

	private int evaluation(Joueur joueur) {

		Joueur vainqueur = getVainqueur();

		int evaluation;

		if (vainqueur == joueur) {
			evaluation = 1;
		} else if (vainqueur == null) {
			evaluation = 0;
		} else {
			evaluation = -1;
		}

		return evaluation;

	}

	private Joueur getAdversaire(Joueur joueur) {

		Joueur adversaire;

		if (joueur == ordinateur) {
			adversaire = humain;
		} else {
			adversaire = ordinateur;
		}

		return adversaire;

	}

	private Case getMeilleureCase() {

		Case caseTestee;
		List<Case> meilleuresCases = new LinkedList<Case>();

		int evaluationCaseTestee;
		int evaluationMeilleuresCases = Integer.MIN_VALUE;

		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				caseTestee = grille[x][y];

				if (caseTestee.estLibre()) {
					caseTestee.jouerProvisoirement(ordinateur);
					nombreCasesJouees++;

					evaluationCaseTestee = negamax(ordinateur);

					if (evaluationCaseTestee > evaluationMeilleuresCases) {
						meilleuresCases.clear();
						meilleuresCases.add(caseTestee);
						evaluationMeilleuresCases = evaluationCaseTestee;

					} else if (evaluationCaseTestee == evaluationMeilleuresCases) {
						meilleuresCases.add(caseTestee);
					}
					caseTestee.annuler();
					nombreCasesJouees--;

				}

			}

		}
		Case meilleureCase = meilleuresCases.get(RANDOM.nextInt(meilleuresCases
				.size()));

		return meilleureCase;

	}

	private int negamax(Joueur joueur) {

		int negamax;

		if (partieFinie()) {
			negamax = evaluation(joueur);
		} else {
			Joueur adversaire = getAdversaire(joueur);
			Case caseTestee;

			int negamaxCase;
			int negamaxAdversaire = Integer.MIN_VALUE;

			for (int x = 0; x < 3; x++) {

				for (int y = 0; y < 3; y++) {

					caseTestee = grille[x][y];

					if (caseTestee.estLibre()) {

						caseTestee.jouerProvisoirement(adversaire);
						nombreCasesJouees++;

						negamaxCase = negamax(adversaire);

						caseTestee.annuler();
						nombreCasesJouees--;

						if (negamaxCase > negamaxAdversaire) {
							negamaxAdversaire = negamaxCase;
						}

					}

				}

			}
			negamax = -negamaxAdversaire;
		}
		return negamax;
	}

	private Joueur getVainqueur() {

		Joueur joueur;

		for (int x = 0; x < 3; x++) {
			joueur = grille[x][0].joueur;

			if (joueur != null && grille[x][1].joueur == joueur
					&& grille[x][2].joueur == joueur) {
				return joueur;
			}
		}
		for (int y = 0; y < 3; y++) {
			joueur = grille[0][y].joueur;
			if (joueur != null && grille[1][y].joueur == joueur
					&& grille[2][y].joueur == joueur) {
				return joueur;
			}
		}
		joueur = grille[0][0].joueur;

		if (joueur != null && grille[1][1].joueur == joueur
				&& grille[2][2].joueur == joueur) {
			return joueur;

		}
		joueur = grille[0][2].joueur;

		if (joueur != null && grille[1][1].joueur == joueur
				&& grille[2][0].joueur == joueur) {
			return joueur;
		}
		return null;
	}

}