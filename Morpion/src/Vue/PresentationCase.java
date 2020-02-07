package Vue;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Modele.Case;
import Modele.Joueur;
import Modele.Morpion;

public class PresentationCase extends JPanel {

	private Morpion morpion;

	private Case modele;

	private JButton bouton;

	private JLabel label;

	public PresentationCase(Morpion morpion, Case modele) {

		this.morpion = morpion;
		this.modele = modele;

		bouton = new JButton();
		label = new JLabel();

		setLayout(new BorderLayout());
		add(bouton, BorderLayout.CENTER);

		bouton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evenement) {
				jouer();
			}

		});
	}

	public void jouer() {
		morpion.jouerHumain(modele);
	}

	public void actualiser() {

		Joueur joueur = modele.joueur;
		remove(bouton);

		label.setText(joueur.symbole);
		label.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(joueur.couleur);
		add(label, BorderLayout.CENTER);

		revalidate();
		repaint();

	}

}