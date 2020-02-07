package Vue;

import java.awt.GridLayout;

import javax.swing.JPanel;

import Modele.Case;
import Modele.Morpion;

public class PresentationMorpion extends JPanel {

	public PresentationMorpion(Morpion modele) {

		setLayout(new GridLayout(3, 3));
		Case caseMorpion;

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				caseMorpion = modele.getCase(x, y);
				add(caseMorpion.presentation);
			}
		}
	}
}