/**
 * 
 */
package modele;

import java.awt.Color;
import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * @author kork
 * 
 */
public class Rectangle extends Figure {
	public Rectangle() {
		super(1, Color.black);
	}

	public Rectangle(int epaisseur, Color couleur, int xDebut, int yDebut) {
		super(epaisseur, couleur);
		ajoute(xDebut, yDebut);
	}

	public void enregistreDans(DataOutputStream fichier) throws Exception {
		fichier.writeByte(5);
		// fichier.writeInt(couleur.getRGB()); // la couleur est null
		fichier.writeInt(Color.black.getRGB());
		fichier.writeInt(epaisseur);
		fichier.writeInt(lesPoints.size());
		for (Point pt : lesPoints) {
			fichier.writeInt((int) (pt.getX()));
			fichier.writeInt((int) (pt.getY()));
		}
	}

	public void chargeDepuis(DataInputStream fichier) throws Exception {
		changerCouleur(couleur = new Color((int) (fichier.readInt())));
		epaisseur = fichier.readInt();
		int nbPts = fichier.readInt();
		for (int i = 0; i < nbPts; i++) {
			int x = fichier.readInt();
			int y = fichier.readInt();
			ajoute(x, y);
		}
	}
}
