package modele;

import java.awt.Color;
import java.awt.Point;
import java.io.DataOutputStream;
import java.io.DataInputStream;

public class Etoile extends Figure {
	private Point centre;

	public Etoile() {
		super(1, Color.black);
	}

	public Etoile(int epaisseur, Color couleur, int x, int y) {
		super(epaisseur, couleur);
		centre = new Point(x, y);
	}

	public Point centre() {
		return centre;
	}

	public void enregistreDans(DataOutputStream fichier) throws Exception {
		fichier.writeByte(2);
		fichier.writeInt(couleur.getRGB());
		fichier.writeInt(epaisseur);
		fichier.writeInt((int) (centre.getX()));
		fichier.writeInt((int) (centre.getY()));
		fichier.writeInt(lesPoints.size());
		for (Point pt : lesPoints) {
			fichier.writeInt((int) (pt.getX()));
			fichier.writeInt((int) (pt.getY()));
		}
	}

	public void chargeDepuis(DataInputStream fichier) throws Exception {
		changerCouleur(couleur = new Color((int) (fichier.readInt())));
		epaisseur = fichier.readInt();
		int x = fichier.readInt();
		int y = fichier.readInt();
		centre = new Point(x, y);
		int nbPts = fichier.readInt();
		for (int i = 0; i < nbPts; i++) {
			x = fichier.readInt();
			y = fichier.readInt();
			ajoute(x, y);
		}
	}
}
