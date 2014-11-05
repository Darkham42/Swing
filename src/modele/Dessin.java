package modele;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;

public class Dessin {
	private List<Figure> contenu;
	private boolean modifie;
	private File fichierChoisi;

	public Dessin() {
		fichierChoisi = null;
		contenu = new ArrayList<Figure>();
		modifie = false;
	}

	public void ajoute(Figure figure) {
		contenu.add(figure);
		modifie = true;
	}

	public int nbFigures() {
		return contenu.size();
	}

	public Figure element(int numero) {
		return contenu.get(numero);
	}

	public void vider() {
		contenu.clear();
		modifie = false;
	}

	public boolean modifie() {
		return modifie;
	}

	public boolean enregistre() {
		return enregistreSous(fichierChoisi);
	}

	public boolean enregistreSous(File f) {
		// renvoie vrai en cas de succes, faux en cas d'echec
		fichierChoisi = f;
		try {
			DataOutputStream fichier;
			fichier = new DataOutputStream(new FileOutputStream(f));
			enregistreDans(fichier);
			fichier.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean charge(File f) {
		// renvoie vrai en cas de succes, faux en cas d'echec
		try {
			DataInputStream fichier;
			fichier = new DataInputStream(new FileInputStream(f));
			vider();
			chargeDepuis(fichier);
			fichier.close();
			fichierChoisi = f;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void enregistreDans(DataOutputStream fichier) throws Exception {
		fichier.writeInt(contenu.size());
		for (Figure fig : contenu) {
			fig.enregistreDans(fichier);
		}
		modifie = false;
	}

	private void chargeDepuis(DataInputStream fichier) throws Exception {
		int nbFig = fichier.readInt();
		for (int i = 0; i < nbFig; i++) {
			byte type = fichier.readByte();
			Figure fig;
			switch (type) {
			case 1:
				fig = new Trace();
				break;
			case 2:
				fig = new Etoile();
				break;
			case 3:
				fig = new Guirlande();
				break;
			case 4:
				fig = new Segment();
			case 5:
				fig = new Rectangle();
			default:
				fig = null;
			}
			fig.chargeDepuis(fichier);
			contenu.add(fig);
		}
		modifie = false;
	}
}
