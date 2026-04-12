import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // SwingUtilities.invokeLater garantit que l'interface graphique
        // se lance sur le bon processus dédié à l'affichage.
        SwingUtilities.invokeLater(() -> {
            // Initialisation de la logique
            Jeu nouveauJeu = new Jeu("Joueur 1 (Blancs)", "Joueur 2 (Noirs)");

            // Lancement de la fenêtre du jeu
            new InterfaceGraphique(nouveauJeu);
        });
    }
}