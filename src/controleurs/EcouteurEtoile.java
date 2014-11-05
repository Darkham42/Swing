package controleurs;

/**
 * La classe EcouteurEtoile implante les methodes correspondant
 * aux evenements souris et deplacement de la souris
 * pour dessiner des segments ayant tous une extremite en commun.
 */

import modele.Dessin;
import modele.Etoile;
import vue.FenetreDeDessin;
import java.awt.event.MouseEvent;

public class EcouteurEtoile extends EcouteurSouris {
	private int xc, yc;
	private Etoile etoile;

	public EcouteurEtoile(Dessin dessin, FenetreDeDessin applet) {
		super(dessin, applet);
	}

	/* en tant que MouseListener */
	public void mousePressed(MouseEvent e) {
		xc = e.getX();
		yc = e.getY();
		etoile = new Etoile(vue.epaisseur(), vue.color(), xc, yc);
		modele.ajoute(etoile);
	}

	/* en tant que MouseMotionListener */
	public void mouseDragged(MouseEvent e) {
		int x, y;
		x = e.getX();
		y = e.getY();
		etoile.ajoute(x, y);
		vue.dessineTrait(xc, yc, x, y);
	}
}
