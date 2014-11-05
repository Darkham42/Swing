package vue;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.GridLayout;

@SuppressWarnings("serial")
public class BarreDEtat extends JPanel {
	private JLabel position, outil, couleur, epaisseur;

	public BarreDEtat() {
		setLayout(new GridLayout(1, 4));
		add(position = new JLabel());
		add(outil = new JLabel());
		add(couleur = new JLabel());
		add(epaisseur = new JLabel());
	}

	public void afficheCoordonnees(int x, int y) {
		position.setText("( " + x + ", " + y + " )");
	}

	public void afficheOutil(String nomOutil) {
		outil.setText("Outil : " + nomOutil);
	}

	public void afficheEpaisseur(int epais) {
		epaisseur.setText("Epaisseur : " + epais);
	}

	public void afficheCouleur(int r, int g, int b) {
		couleur.setText("Couleur : (" + r + "," + g + "," + b + ")");
	}
}
