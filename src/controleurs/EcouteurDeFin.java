package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import vue.FenetreDeDessin;
import modele.Dessin;

public class EcouteurDeFin extends EcouteurOperationRisquee {
	public EcouteurDeFin(FenetreDeDessin fenetre, Dessin dessin) {
		super(fenetre, dessin, "Fermerture de l'application Gribouille");
	}

	protected void quitter() {
		super.actionPerformed(null); // on ne se sert pas de l'objet evenement
										// dans la classe de base
		if (!abandon) {
			System.exit(0);
		}
	}

	public void actionPerformed(ActionEvent e) {
		quitter();
	}

	public void windowClosing(WindowEvent e) {
		quitter();
	}
}
