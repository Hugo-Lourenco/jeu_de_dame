import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InterfaceGraphique extends JFrame {
    private Jeu jeu;
    private JPanel panneauPlateau;
    private DefaultListModel<String> modeleHistorique;
    private JList<String> listeHistorique;
    private JLabel labelTour;

    private int selectedX = -1;
    private int selectedY = -1;

    public InterfaceGraphique(Jeu jeu) {
        this.jeu = jeu;
        setTitle("Jeu de Dames");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initialiserComposants();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initialiserComposants() {
        // Panel historique et info
        JPanel panelDroit = new JPanel(new BorderLayout());
        panelDroit.setPreferredSize(new Dimension(250, 600));

        labelTour = new JLabel("Au tour de : " + jeu.getJoueurCourant().getNom());
        labelTour.setFont(new Font("Arial", Font.BOLD, 16));
        labelTour.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        modeleHistorique = new DefaultListModel<>();
        listeHistorique = new JList<>(modeleHistorique);
        JScrollPane scrollHistorique = new JScrollPane(listeHistorique);
        scrollHistorique.setBorder(BorderFactory.createTitledBorder("Historique des coups"));

        panelDroit.add(labelTour, BorderLayout.NORTH);
        panelDroit.add(scrollHistorique, BorderLayout.CENTER);
        add(panelDroit, BorderLayout.EAST);

        //Panel plateau
        panneauPlateau = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dessinerPlateau(g);
            }
        };
        panneauPlateau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gererClicSouris(e.getX(), e.getY());
            }
        });
        add(panneauPlateau, BorderLayout.CENTER);
    }

    private void dessinerPlateau(Graphics g) {
        int largeurCase = panneauPlateau.getWidth() / Plateau.TAILLE;
        int hauteurCase = panneauPlateau.getHeight() / Plateau.TAILLE;

        for (int i = 0; i < Plateau.TAILLE; i++) {
            for (int j = 0; j < Plateau.TAILLE; j++) {
                // Damier
                if ((i + j) % 2 == 0) g.setColor(Color.WHITE);
                else g.setColor(Color.DARK_GRAY);
                g.fillRect(j * largeurCase, i * hauteurCase, largeurCase, hauteurCase);

                // Surbrillance case sélectionnée
                if (i == selectedX && j == selectedY) {
                    g.setColor(new Color(255, 255, 0, 100)); // Jaune transparent
                    g.fillRect(j * largeurCase, i * hauteurCase, largeurCase, hauteurCase);
                }

                // Pièces
                Case c = jeu.getPlateau().getCase(i, j);
                if (!c.estVide()) {
                    if (c.getPiece().getCouleur() == Couleur.BLANC) g.setColor(Color.WHITE);
                    else g.setColor(Color.BLACK);

                    g.fillOval(j * largeurCase + 5, i * hauteurCase + 5, largeurCase - 10, hauteurCase - 10);

                    // Marqueur si c'est une dame
                    if (c.getPiece() instanceof Dame) {
                        g.setColor(Color.RED);
                        g.drawOval(j * largeurCase + 15, i * hauteurCase + 15, largeurCase - 30, hauteurCase - 30);
                    }
                }
            }
        }
    }

    private void gererClicSouris(int mouseX, int mouseY) {
        int largeurCase = panneauPlateau.getWidth() / Plateau.TAILLE;
        int hauteurCase = panneauPlateau.getHeight() / Plateau.TAILLE;
        int y = mouseX / largeurCase; // Inversion X/Y graphique vs Matrice
        int x = mouseY / hauteurCase;

        if (selectedX == -1) {
            // Sélectionner une pièce
            if (!jeu.getPlateau().getCase(x, y).estVide()) {
                selectedX = x;
                selectedY = y;
            }
        } else {
            // Déplacer la pièce sélectionnée
            boolean success = jeu.tenterDeplacement(selectedX, selectedY, x, y);
            if (success) {
                mettreAJourInterface();
            } else {
                JOptionPane.showMessageDialog(this, "Mouvement invalide !");
            }
            selectedX = -1;
            selectedY = -1;
        }
        panneauPlateau.repaint();
    }

    private void mettreAJourInterface() {
        labelTour.setText("Au tour de : " + jeu.getJoueurCourant().getNom());
        modeleHistorique.clear();
        for (String coup : jeu.getHistorique()) {
            modeleHistorique.addElement(coup);
        }
    }
}
