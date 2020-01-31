package Controller;

import javax.swing.JFrame;

import Modele.Morpion;

public class ApplicationMorpion {

	public static void main(String[] args) {
		Morpion morpion = new Morpion(false);
		JFrame fenetre = new JFrame("Morpion");

		fenetre.setSize(180, 200);
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setContentPane(morpion.presentation);
		fenetre.setVisible(true);
	}

}