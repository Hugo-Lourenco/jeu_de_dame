 import java.util.ArrayList;
import java.util.List;

public class Jeu {
    private Plateau plateau;
    private Joueur j1, j2;
    private Joueur joueurCourant;
    private List<String> historiqueCoups; // Pour l'historique demandé dans le PDF

    public Jeu(String nomJ1, String nomJ2) {
        this.plateau = new Plateau();
        this.j1 = new Joueur(nomJ1, Couleur.BLANC);
        this.j2 = new Joueur(nomJ2, Couleur.NOIR);
        this.joueurCourant = j1; // Les blancs commencent (convention)
        this.historiqueCoups = new ArrayList<>();
    }

    public Plateau getPlateau() { return plateau; }
    public Joueur getJoueurCourant() { return joueurCourant; }
    public List<String> getHistorique() { return historiqueCoups; }

    public boolean tenterDeplacement(int startX, int startY, int endX, int endY) {
        Case caseDepart = plateau.getCase(startX, startY);

        // Vérifie si la case est vide ou si la pièce n'appartient pas au joueur courant
        if (caseDepart.estVide() || caseDepart.getPiece().getCouleur() != joueurCourant.getCouleur()) {
            return false;
        }

        Piece piece = caseDepart.getPiece();
        // Vérifie si le mouvement est autorisé par les règles de la pièce
        if (piece.estMouvementValide(startX, startY, endX, endY, plateau)) {
            plateau.deplacerPiece(startX, startY, endX, endY);
            historiqueCoups.add(joueurCourant.getNom() + " : (" + startX + "," + startY + ") -> (" + endX + "," + endY + ")");
            changerTour();
            return true;
        }
        return false;
    }

    private void changerTour() {
        joueurCourant = (joueurCourant == j1) ? j2 : j1;
    }
}
