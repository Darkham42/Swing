package controleurs;

import java.awt.event.ActionEvent;

import vue.FenetreDeDessin;
import modele.Dessin;

public class EcouteurEffacer extends EcouteurOperationRisquee {
	public EcouteurEffacer(FenetreDeDessin fenetre, Dessin dessin) {
		super(fenetre, dessin, "Effacement du dessin");
	}

	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if (abandon)
			return;
		dessin.vider();
		fenetre.repaint();
	}
}
