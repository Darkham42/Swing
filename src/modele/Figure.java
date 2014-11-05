package modele;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Point;
import java.io.DataOutputStream;
import java.io.DataInputStream;

public abstract class Figure {
	protected List<Point> lesPoints;
	protected int epaisseur;
	protected Color couleur;

	public Figure(int epaisseur, Color couleur) {
		this.epaisseur = epaisseur;
		this.couleur = couleur;
		lesPoints = new ArrayList<Point>();
	}

	public int epaisseur() {
		return epaisseur;
	}

	public Color couleur() {
		return couleur;
	}

	public void changerCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public void ajoute(int x, int y) {
		lesPoints.add(new Point(x, y));
	}

	public int nbPoints() {
		return lesPoints.size();
	}

	public Point element(int numero) {
		return lesPoints.get(numero);
	}

	public abstract void enregistreDans(DataOutputStream fichier)
			throws Exception;

	public abstract void chargeDepuis(DataInputStream fichier) throws Exception;
}
