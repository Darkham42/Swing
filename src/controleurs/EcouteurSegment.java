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
public class EcouteurSegment extends EcouteurSouris {
	private int xDebut, yDebut;
	private Segment segment;

	public EcouteurSegment(Dessin dessin, FenetreDeDessin applet) {
		super(dessin, applet);
	}

	/* en tant que MouseListener */
	public void mousePressed(MouseEvent e) {
		xDebut = e.getX();
		yDebut = e.getY();
		segment = new Segment(vue.epaisseur(), vue.color(), xDebut, yDebut);
		modele.ajoute(segment);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int xFin = e.getX();
		int yFin = e.getY();
		segment.ajoute(xFin, yFin);
		vue.dessineTrait(xDebut, yDebut, xFin, yFin);
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
