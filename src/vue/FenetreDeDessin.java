package vue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controleurs.ControleurPrincipal;
import controleurs.EcouteurEffacer;
import controleurs.EcouteurOuvrir;
import controleurs.EcouteurDeFin;
import javax.swing.JScrollPane;
import modele.Dessin;

@SuppressWarnings("serial")
public class FenetreDeDessin extends JFrame {

	private ZoneDeDessin zoneDeDessin;
	private JScrollPane scrollPane;
	private Dessin dessin;
	private BarreDEtat barreDEtat;
	private ControleurPrincipal controleur;
	private JMenuItem itemEnreg;
	private JRadioButtonMenuItem itemCrayon, itemEtoile, itemGuirlande,
			itemSegment, itemRectangle;
	private int epaisseur;
	private Color color;

	private void constructionBarreDeMenus() {
		JMenuBar barreDeMenus = new JMenuBar();
		JMenu menu;
		JMenuItem item;
		barreDeMenus.add(menu = new JMenu("Gribouille"));
		item = new JMenuItem("A propos");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				APropos.affiche();
			}
		});
		menu.add(item);
		menu.addSeparator();
		item = new JMenuItem("Quitter");
		item.addActionListener(new EcouteurDeFin(this, dessin));
		menu.add(item);

		barreDeMenus.add(menu = new JMenu("Dessin"));
		item = new JMenuItem("Ouvrir...");
		item.addActionListener(new EcouteurOuvrir(this, dessin));
		menu.add(item);
		itemEnreg = new JMenuItem("Enregistrer");
		itemEnreg.setEnabled(false);
		itemEnreg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enregistrer();
			}
		});
		menu.add(itemEnreg);
		item = new JMenuItem("Enregistrer sous...");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enregistrerSous();
			}
		});
		menu.add(item);
		menu.addSeparator();
		item = new JMenuItem("Effacer");
		item.addActionListener(new EcouteurEffacer(this, dessin));
		menu.add(item);

		barreDeMenus.add(menu = new JMenu("Outil"));
		ButtonGroup groupe = new ButtonGroup();
		item = itemCrayon = new JRadioButtonMenuItem("Pinceau", true);
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.changerPourOutilCrayon();
				Toolkit tk = Toolkit.getDefaultToolkit();
				Image img = tk.getImage("assets/brush.gif");
				setCursor(tk.createCustomCursor(img, new Point(0, 28),
						"pinceau"));
			}
		});
		menu.add(item);
		groupe.add(item);

		item = itemEtoile = new JRadioButtonMenuItem("Etoile");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.changerPourOutilEtoile();
				Toolkit tk = Toolkit.getDefaultToolkit();
				Image img = tk.getImage("assets/star.gif");
				setCursor(tk.createCustomCursor(img, new Point(5, 15), "star"));
			}
		});
		menu.add(item);
		groupe.add(item);

		item = itemGuirlande = new JRadioButtonMenuItem("Guirlande");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.changerPourOutilGuirlande();
				Toolkit tk = Toolkit.getDefaultToolkit();
				Image img = tk.getImage("assets/christmas-tree.gif");
				setCursor(tk.createCustomCursor(img, new Point(31, 15),
						"christmas-tree"));
			}
		});
		menu.add(item);
		groupe.add(item);

		item = itemSegment = new JRadioButtonMenuItem("Segment");
		item.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controleur.changerPourOutilSegment();
				Toolkit tk = Toolkit.getDefaultToolkit();
				Image img = tk.getImage("assets/pencil.gif");
				setCursor(tk.createCustomCursor(img, new Point(0, 28),
						"crayon"));
			}
		});
		menu.add(item);
		groupe.add(item);

		item = itemRectangle = new JRadioButtonMenuItem("Rectangle");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.changerPourOutilCrayon();
				Toolkit tk = Toolkit.getDefaultToolkit();
				Image img = tk.getImage("assets/pencil.gif");
				setCursor(tk.createCustomCursor(img, new Point(0, 28),
						"pinceau"));
			}
		});
		menu.add(item);
		groupe.add(item);

		barreDeMenus.add(menu = new JMenu("Pinceau"));
		item = new JMenuItem("Epaisseur et Couleur");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (DlgEpaisseur.reponseValide()) {
					choisirEpaisseur(DlgEpaisseur.epaisseurChoisie());
					choisirCouleur(DlgEpaisseur.couleurChoisie());
				}
			}
		});
		menu.add(item);

		setJMenuBar(barreDeMenus);
	}

	public FenetreDeDessin() {
		dessin = new Dessin();
		barreDEtat = new BarreDEtat();
		scrollPane = new JScrollPane();
		zoneDeDessin = new ZoneDeDessin(dessin);

		setTitle("Gribouille");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);

		constructionBarreDeMenus();

		setLayout(new BorderLayout());

		// On place la zone de dessin dans le JscrollPane
		scrollPane.setViewportView(zoneDeDessin);
		// et on place le JScrollPane dans la JFrame
		add(scrollPane, BorderLayout.CENTER);
		add(barreDEtat, BorderLayout.SOUTH);

		addWindowListener(new EcouteurDeFin(this, dessin));
		controleur = new ControleurPrincipal(dessin, this, zoneDeDessin);
		addKeyListener(controleur);

		choisirEpaisseur(1);
		setVisible(true);
	}

	public void dessineTrait(int x1, int y1, int x2, int y2) {
		zoneDeDessin.dessineTrait(x1, y1, x2, y2);
	}

	public void dessineGuirlande(int xd, int yd, int x, int y, int epaisseur) {
		zoneDeDessin.dessineGuirlande(xd, yd, x, y, epaisseur);
	}

	public void afficheCoordonnees(int x, int y) {
		barreDEtat.afficheCoordonnees(x, y);
	}

	private void afficheOutil(JRadioButtonMenuItem item) {
		item.setSelected(true);
		barreDEtat.afficheOutil(item.getText());
	}

	public void afficheOutilCrayon() {
		afficheOutil(itemCrayon);
	}

	public void afficheOutilEtoile() {
		afficheOutil(itemEtoile);
	}

	public void afficheOutilGuirlande() {
		afficheOutil(itemGuirlande);
	}

	public void afficheOutilSegment() {
		afficheOutil(itemSegment);
	}

	public void afficheOutilRectangle() {
		afficheOutil(itemRectangle);
	}

	public void afficheCouleur(String couleur) {
		afficheCouleur(couleur);
	}

	public void activeOptionEnregistrer(boolean actif) {
		itemEnreg.setEnabled(actif);
	}

	public void choisirEpaisseur(int epaisseur) {
		zoneDeDessin.choisirEpaisseur(this.epaisseur = epaisseur);
		barreDEtat.afficheEpaisseur(epaisseur);
	}

	public void choisirCouleur(Color color) {
		zoneDeDessin.choisirCouleur(this.color = color);
		barreDEtat.afficheCouleur(color.getRed(), color.getGreen(),
				color.getBlue());
	}

	public int epaisseur() {
		return epaisseur;
	}

	public Color color() {
		return color;
	}

	public boolean abandonApresEnregistrementEventuel(String titre) {
		switch (JOptionPane
				.showConfirmDialog(
						this,
						"L'operation fait perdre les modifications non enregistrees !\nVoulez-voulez-vous enregistrer avant ?",
						titre, JOptionPane.YES_NO_CANCEL_OPTION)) {
		case JOptionPane.YES_OPTION:
			return !enregistrerSous();
		case JOptionPane.NO_OPTION:
			break;
		default:
			return true;
		}
		return false;
	}

	public boolean enregistrerSous() {
		JFileChooser dlgFichier = new JFileChooser();
		switch (dlgFichier.showSaveDialog(this)) {
		case JFileChooser.CANCEL_OPTION:
			return false;
		case JFileChooser.APPROVE_OPTION:
			if (dessin.enregistreSous(dlgFichier.getSelectedFile())) {
				activeOptionEnregistrer(true);
				return true;
			} else {
				JOptionPane
						.showMessageDialog(
								this,
								"Un probleme est survenu pendant l'enregistrement du dessin.\nLe dessin n'a pas ete enregistre.",
								"Dessin non enregistre",
								JOptionPane.ERROR_MESSAGE);
				return false;
			}
		case JFileChooser.ERROR_OPTION:
			JOptionPane
					.showMessageDialog(
							this,
							"Un probleme est survenu lors du choix de fichier pour l'enregistrement du dessin.\nLe dessin n'a pas ete enregistre.",
							"Dessin non enregistre", JOptionPane.ERROR_MESSAGE);
			return false;
		default:
			return false;
		}
	}

	public void enregistrer() {
		if (!dessin.enregistre()) {
			JOptionPane
					.showMessageDialog(
							this,
							"Un probleme est survenu pendant l'enregistrement du dessin.\nLe dessin n'a pas ete enregistre.",
							"Dessin non enregistre", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void ouvrir() {
		JFileChooser dlgFichier = new JFileChooser();
		switch (dlgFichier.showOpenDialog(this)) {
		case JFileChooser.CANCEL_OPTION:
			break;
		case JFileChooser.APPROVE_OPTION:
			if (dessin.charge(dlgFichier.getSelectedFile())) {
				repaint();
				activeOptionEnregistrer(true);
			} else {
				JOptionPane
						.showMessageDialog(
								this,
								"Un probleme est survenu pendant la lecture du dessin.\nLe dessin n'a pas ete ouvert.",
								"Dessin non ouvert", JOptionPane.ERROR_MESSAGE);
			}
			break;
		case JFileChooser.ERROR_OPTION:
			JOptionPane
					.showMessageDialog(
							this,
							"Un probleme est survenu lors du choix de fichier pour l'ouverture d'un dessin.\nLe dessin n'a pas ete ouvert.",
							"Dessin non ouvert", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
}
