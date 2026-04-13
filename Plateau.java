public class Plateau {
    public static final int TAILLE = 10; // 10x10 pour les dames internationales
    private Case[][] grille;

    public Plateau() {
        grille = new Case[TAILLE][TAILLE];
        initialiserPlateau();
    }

    private void initialiserPlateau() {
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                grille[i][j] = new Case(i, j);
                // Placement des pions uniquement sur les cases noires
                if ((i + j) % 2 != 0) {
                    if (i < 4) grille[i][j].setPiece(new Pion(Couleur.NOIR));
                    else if (i > 5) grille[i][j].setPiece(new Pion(Couleur.BLANC));
                }
            }
        }
    }

    public Case getCase(int x, int y) { return grille[x][y]; }

    public void deplacerPiece(int startX, int startY, int endX, int endY) {
        Piece p = grille[startX][startY].getPiece();
        grille[startX][startY].setPiece(null);
        grille[endX][endY].setPiece(p);
    }
}
