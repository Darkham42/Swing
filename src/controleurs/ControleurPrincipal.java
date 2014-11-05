package controleurs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import modele.Dessin;
import vue.FenetreDeDessin;
import vue.ZoneDeDessin;

public class ControleurPrincipal implements KeyListener {
	private FenetreDeDessin fenetre;
	private ZoneDeDessin panneau;
	private EcouteurCrayon crayon;
	private EcouteurEtoile etoile;
	private EcouteurGuirlande guirlande;
	private EcouteurSouris courant;
	private EcouteurSegment segment;
	private EcouteurRectangle rectangle;

	public ControleurPrincipal(Dessin dessin, FenetreDeDessin fenetre,
			ZoneDeDessin panneau) {
		this.fenetre = fenetre;
		this.panneau = panneau;
		crayon = new EcouteurCrayon(dessin, fenetre);
		etoile = new EcouteurEtoile(dessin, fenetre);
		guirlande = new EcouteurGuirlande(dessin, fenetre);
		segment = new EcouteurSegment(dessin, fenetre);
		rectangle = new EcouteurRectangle(dessin, fenetre);
		courant = null;
		changerPourOutilCrayon();
	}

	private void changerOutil(EcouteurSouris outil) {
		panneau.removeMouseListener(courant);
		panneau.removeMouseMotionListener(courant);
		panneau.addMouseListener(outil);
		panneau.addMouseMotionListener(outil);
		courant = outil;
	}

	public void changerPourOutilCrayon() {
		changerOutil(crayon);
		fenetre.afficheOutilCrayon();
	}

	public void changerPourOutilEtoile() {
		changerOutil(etoile);
		fenetre.afficheOutilEtoile();
	}

	public void changerPourOutilGuirlande() {
		changerOutil(guirlande);
		fenetre.afficheOutilGuirlande();
	}

	public void changerPourOutilSegment() {
		changerOutil(segment);
		fenetre.afficheOutilSegment();
	}

	public void changerPourOutilRectangle() {
		changerOutil(rectangle);
		fenetre.afficheOutilRectangle();
	}

	/* en tant que KeyListener */
	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
		switch (e.getKeyChar()) {
		case 'e':
			changerPourOutilEtoile();
			break;
		case 'c':
			changerPourOutilCrayon();
			break;
		case 'g':
			changerPourOutilGuirlande();
			break;
		case 's':
			changerPourOutilSegment();
			break;
		case 'r':
			changerPourOutilRectangle();
			break;
		}
	}
}
