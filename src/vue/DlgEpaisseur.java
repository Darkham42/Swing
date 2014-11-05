package vue;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class DlgEpaisseur extends JDialog {
	private static DlgEpaisseur uniqueInstance = null;
	private JSlider jgeValeur;
	private JButton btValider, btAnnuler, btCouleur;
	private boolean valide;
	protected JColorChooser couleurChoisie;
	private Color couleur;

	private DlgEpaisseur() {
		super((Frame) null, "Epaisseur de pinceau et couleur", true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				actionBtAnnuler();
			}
		});
		setSize(450, 300);
		setResizable(false);
		setLayout(new GridLayout(3, 1)); // 3 lignes de 1 colonne
		JPanel zoneDeSaisie = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,
				10));
		zoneDeSaisie.setBorder(BorderFactory
				.createTitledBorder("Choix Epaisseur"));
		zoneDeSaisie.add(new JLabel("Valeur :"));
		jgeValeur = new JSlider(1, 9, 1);
		jgeValeur.setMinorTickSpacing(1);
		jgeValeur.setMajorTickSpacing(1);
		jgeValeur.setPaintTicks(true);
		jgeValeur.setPaintLabels(true);
		jgeValeur.setFont(new Font("Serif", Font.PLAIN, 12));
		zoneDeSaisie.add(jgeValeur);
		add(zoneDeSaisie);

		btCouleur = new JButton("Choix de la couleur");
		btCouleur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				couleur = JColorChooser.showDialog(null,
						"Choix de la couleur du pinceau", couleur);
			}
		});

		JPanel choixCouleur = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,
				10));
		choixCouleur.setBorder(BorderFactory
				.createTitledBorder("Choix Couleur"));
		btCouleur.setPreferredSize(new Dimension(170, 40));
		choixCouleur.add(btCouleur);
		add(choixCouleur);

		btValider = new JButton("Valider");
		btValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionBtValider();
			}
		});

		btAnnuler = new JButton("Annuler");
		btAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionBtAnnuler();
			}
		});
		JPanel bandeDeBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER,
				10, 5));
		bandeDeBoutons.add(btValider);
		bandeDeBoutons.add(btAnnuler);
		add(bandeDeBoutons);
	}

	private void actionBtValider() {
		valide = true;
		setVisible(false);
	}

	private void actionBtAnnuler() {
		valide = false;
		setVisible(false);
	}

	public static boolean reponseValide() { // FenetreDeDessin uneVue) {
		if (uniqueInstance == null)
			uniqueInstance = new DlgEpaisseur();
		uniqueInstance.setLocationRelativeTo(null);
		uniqueInstance.jgeValeur.requestFocus();
		uniqueInstance.setVisible(true);
		return uniqueInstance.valide;
	}

	public static int epaisseurChoisie() {
		return uniqueInstance.jgeValeur.getValue();
	}

	public static Color couleurChoisie() {
		return uniqueInstance.couleur;
	}
}
