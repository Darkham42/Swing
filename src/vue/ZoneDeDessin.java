package vue;

import javax.swing.JPanel;

import java.awt.*;

import javax.swing.Scrollable;

import modele.*;
import modele.Rectangle; // à laisser pour qu'il n'y ai pas de conflis avec java.awt.Rectangle

@SuppressWarnings("serial")
public class ZoneDeDessin extends JPanel implements Scrollable {

	private Dessin dessin;
	private BasicStroke pinceau;
	private Color couleur;

	public ZoneDeDessin(Dessin dessin) {
		this.dessin = dessin;
		// Ajout la taille preferee pour que le JScrollPane puisse connaître la
		// taille de la zone de dessin
		this.setPreferredSize(new Dimension(1920, 1080));
	}

	/*
	 * On pourrait faire en sorte de demander les dimensions voulues par
	 * l'utilisateur
	 */

	public void choisirEpaisseur(int epaisseur) {
		pinceau = new BasicStroke(epaisseur, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND);
	}

	public void choisirCouleur(Color color) {
		this.couleur = color;
	}

	public void dessineTrait(int x1, int y1, int x2, int y2) {
		Graphics g = getGraphics();
		((Graphics2D) g).setStroke(pinceau);
		((Graphics2D) g).setColor(this.couleur);
		g.drawLine(x1, y1, x2, y2);
	}

	public void dessineGuirlande(int xd, int yd, int x, int y, int epaisseur) {
		Graphics g = getGraphics();
		((Graphics2D) g).setStroke(pinceau);
		((Graphics2D) g).setColor(this.couleur);
		g.drawLine(xd - (epaisseur + 4), yd, xd + (epaisseur + 4), yd);
		g.drawLine(xd, yd - (epaisseur + 4), xd, yd + (epaisseur + 4));
		g.drawLine(xd - (epaisseur + 4), yd - (epaisseur + 4), xd
				+ (epaisseur + 4), yd + (epaisseur + 4));
		g.drawLine(xd - (epaisseur + 4), yd + (epaisseur + 4), xd
				+ (epaisseur + 4), yd - (epaisseur + 4));
	}

	public void dessineFigure(Figure figure, Graphics g) {
	}

	public void dessineFigure(Trace trace, Graphics g) {
		choisirEpaisseur(trace.epaisseur());
		((Graphics2D) g).setStroke(pinceau);
		choisirCouleur(trace.couleur());
		((Graphics2D) g).setColor(trace.couleur());
		Point pt = trace.element(0);
		int xd, yd, x, y;
		xd = (int) (pt.getX());
		yd = (int) (pt.getY());
		for (int numPt = 1; numPt < trace.nbPoints(); numPt++) {
			pt = trace.element(numPt);
			x = (int) (pt.getX());
			y = (int) (pt.getY());
			g.drawLine(xd, yd, x, y);
			xd = x;
			yd = y;
		}
	}

	public void dessineFigure(Etoile etoile, Graphics g) {
		choisirEpaisseur(etoile.epaisseur());
		((Graphics2D) g).setStroke(pinceau);
		choisirCouleur(etoile.couleur());
		((Graphics2D) g).setColor(etoile.couleur());
		Point pt = etoile.centre();
		int xc, yc, x, y;
		xc = (int) (pt.getX());
		yc = (int) (pt.getY());
		for (int numPt = 0; numPt < etoile.nbPoints(); numPt++) {
			pt = etoile.element(numPt);
			x = (int) (pt.getX());
			y = (int) (pt.getY());
			g.drawLine(xc, yc, x, y);
		}
	}

	public void dessineFigure(Guirlande guirlande, Graphics g) {
		choisirEpaisseur(guirlande.epaisseur());
		((Graphics2D) g).setStroke(pinceau);
		((Graphics2D) g).setColor(this.couleur);
		choisirCouleur(guirlande.couleur());
		((Graphics2D) g).setColor(guirlande.couleur());
		Point pt = guirlande.element(0);
		int xd, yd, x, y;
		xd = (int) (pt.getX());
		yd = (int) (pt.getY());
		for (int numPt = 1; numPt < guirlande.nbPoints(); numPt++) {
			pt = guirlande.element(numPt);
			x = (int) (pt.getX());
			y = (int) (pt.getY());

			g.drawLine(xd - (guirlande.epaisseur() + 4), yd,
					xd + (guirlande.epaisseur() + 4), yd);
			g.drawLine(xd, yd - (guirlande.epaisseur() + 4), xd, yd
					+ (guirlande.epaisseur() + 4));
			g.drawLine(xd - (guirlande.epaisseur() + 4),
					yd - (guirlande.epaisseur() + 4),
					xd + (guirlande.epaisseur() + 4),
					yd + (guirlande.epaisseur() + 4));
			g.drawLine(xd - (guirlande.epaisseur() + 4),
					yd + (guirlande.epaisseur() + 4),
					xd + (guirlande.epaisseur() + 4),
					yd - (guirlande.epaisseur() + 4));

			xd = x;
			yd = y;
		}
	}

	public void dessineFigure(Segment segment, Graphics g) {
		choisirEpaisseur(segment.epaisseur());
		((Graphics2D) g).setStroke(pinceau);
		((Graphics2D) g).setColor(this.couleur);
		choisirCouleur(segment.couleur());
		((Graphics2D) g).setColor(segment.couleur());

		Point pt = segment.element(0);
		int xDebut, yDebut, xFin, yFin;
		xDebut = (int) (pt.getX());
		yDebut = (int) (pt.getY());

		pt = segment.element(segment.nbPoints() - 1);
		xFin = (int) (pt.getX());
		yFin = (int) (pt.getY());
		g.drawLine(xDebut, yDebut, xFin, yFin);
	}

	public void dessineFigure(Rectangle rectangle, Graphics g) {
		choisirEpaisseur(rectangle.epaisseur());
		((Graphics2D) g).setStroke(pinceau);
		((Graphics2D) g).setColor(this.couleur);
		choisirCouleur(rectangle.couleur());
		((Graphics2D) g).setColor(rectangle.couleur());

		Point pt = rectangle.element(0);
		int xDebut, yDebut, xFin, yFin;
		xDebut = (int) (pt.getX());
		yDebut = (int) (pt.getY());

		pt = rectangle.element(rectangle.nbPoints() - 1);
		xFin = (int) (pt.getX());
		yFin = (int) (pt.getY());
		g.drawLine(xDebut, yDebut, xDebut, yFin);
		g.drawLine(xDebut, yDebut, xFin, yDebut);
		g.drawLine(xFin, yDebut, xFin, yFin);
		g.drawLine(xDebut, yFin, xFin, yFin);
	}

	public void paint(Graphics g) {
		Figure f;
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.black);
		for (int numFig = 0; numFig < dessin.nbFigures(); numFig++) {
			f = dessin.element(numFig);
			if (f instanceof Trace) {
				dessineFigure((Trace) f, g);
			}
			if (f instanceof Etoile) {
				dessineFigure((Etoile) f, g);
			}
			if (f instanceof Guirlande) {
				dessineFigure((Guirlande) f, g);
			}
			if (f instanceof Segment) {
				dessineFigure((Segment) f, g); // utilise dessineFigure(Figure)
												// au lieu de
												// dessineFigure(Segment)
			}
			if (f instanceof Rectangle) {
				dessineFigure((Rectangle) f, g);
			}
		}
	}

	public Dimension getPreferredScrollableViewportSize() {
		return null;
	}

	public int getScrollableBlockIncrement(java.awt.Rectangle arg0, int arg1,
			int arg2) {
		return 0;
	}

	public boolean getScrollableTracksViewportHeight() {
		return false;
	}

	public boolean getScrollableTracksViewportWidth() {
		return false;
	}

	public int getScrollableUnitIncrement(java.awt.Rectangle arg0, int arg1,
			int arg2) {
		return 0;
	}
}
