package controleurs;

/**
 * La classe EcouteurGuirlande implante les methodes correspondant
 * aux evenements souris et deplacement de la souris
 * pour dessiner des segments les uns à la suite des autres.
 */

import modele.*;
import vue.FenetreDeDessin;

import java.awt.event.MouseEvent;

public class EcouteurGuirlande extends EcouteurSouris {
	private int xd, yd;
	private Guirlande guirlande;

	public EcouteurGuirlande(Dessin dessin, FenetreDeDessin fenetre) {
		super(dessin, fenetre);
	}

	/* en tant que MouseListener */
	public void mousePressed(MouseEvent e) {
		xd = e.getX();
		yd = e.getY();
		vue.afficheCoordonnees(xd, yd);
		guirlande = new Guirlande(vue.epaisseur(), vue.color(), xd, yd);
		modele.ajoute(guirlande);
	}

	/* en tant que MouseMotionListener */
	public void mouseDragged(MouseEvent e) {
		int x, y;
		x = e.getX();
		y = e.getY();
		vue.afficheCoordonnees(x, y);
		if (Math.sqrt(Math.pow(xd - x, 2) + Math.pow(yd - y, 2)) > 10) {
			guirlande.ajoute(x, y);
			vue.dessineGuirlande(xd, yd, x, y, vue.epaisseur());
			xd = x;
			yd = y;
		}
	}
}
