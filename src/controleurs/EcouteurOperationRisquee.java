package controleurs;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import vue.FenetreDeDessin;
import modele.Dessin;

public class EcouteurOperationRisquee implements ActionListener, // pour les
																	// options
																	// de menu
																	// declenchant
																	// des
																	// operations
																	// qui font
																	// perdre
																	// les
																	// donnees
		WindowListener // pour la gestion de la case de fermeture de la fenêtre
// de l'application
{
	protected FenetreDeDessin fenetre;
	protected Dessin dessin;
	protected boolean abandon;
	protected String nomOperation;

	public EcouteurOperationRisquee(FenetreDeDessin fenetre, Dessin dessin,
			String nomOperation) {
		this.fenetre = fenetre;
		this.dessin = dessin;
		this.nomOperation = nomOperation;
	}

	// en tant que ActionListener
	public void actionPerformed(ActionEvent e) { // remarque : on ne sert pas de
													// l'objet e
		abandon = false;
		if (dessin.modifie()) {
			if (fenetre.abandonApresEnregistrementEventuel(nomOperation))
				abandon = true;
		}
	}

	// en tant que WindowListener
	public void windowActivated(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowOpened(WindowEvent e) {
	}
}
