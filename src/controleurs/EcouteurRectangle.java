/**
 * 
 */
package controleurs;

import java.awt.event.MouseEvent;

import modele.*;
import vue.FenetreDeDessin;

/**
 * @author kork
 * 
 */
public class EcouteurRectangle extends EcouteurSouris {
	private int xDebut, yDebut;
	private Rectangle rectangle;

	public EcouteurRectangle(Dessin dessin, FenetreDeDessin applet) {
		super(dessin, applet);
	}

	/* en tant que MouseListener */
	public void mousePressed(MouseEvent e) {
		xDebut = e.getX();
		yDebut = e.getY();
		rectangle = new Rectangle(vue.epaisseur(), vue.color(), xDebut, yDebut);
		modele.ajoute(rectangle);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int xFin = e.getX();
		int yFin = e.getY();
		rectangle.ajoute(xFin, yFin);
		vue.dessineTrait(xDebut, yDebut, xDebut, yFin);
		vue.dessineTrait(xDebut, yDebut, xFin, yDebut);
		vue.dessineTrait(xDebut, yFin, xFin, yFin);
		vue.dessineTrait(xFin, yDebut, xFin, yFin);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent
	 * )
	 */

	public void mouseDragged(MouseEvent arg0) {
	}
}
