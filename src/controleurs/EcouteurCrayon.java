package controleurs;

/**
 * La classe EcouteurCrayon implante les methodes correspondant
 * aux evenements souris et deplacement de la souris
 * pour dessiner des segments les uns à la suite des autres.
 */

import modele.Dessin;
import modele.Trace;
import vue.FenetreDeDessin;
import java.awt.event.MouseEvent;

public class EcouteurCrayon extends EcouteurSouris {
	private int xd, yd;
	private Trace trace;

	public EcouteurCrayon(Dessin dessin, FenetreDeDessin fenetre) {
		super(dessin, fenetre);
	}

	/* en tant que MouseListener */
	public void mousePressed(MouseEvent e) {
		xd = e.getX();
		yd = e.getY();
		vue.afficheCoordonnees(xd, yd);
		trace = new Trace(vue.epaisseur(), vue.color(), xd, yd);
		modele.ajoute(trace);
	}

	/* en tant que MouseMotionListener */
	public void mouseDragged(MouseEvent e) {
		int x, y;
		x = e.getX();
		y = e.getY();
		vue.afficheCoordonnees(x, y);
		trace.ajoute(x, y);
		vue.dessineTrait(xd, yd, x, y);
		xd = x;
		yd = y;
	}
}
