public abstract class Piece {
    protected Couleur couleur;

    public Piece(Couleur couleur) {
        this.couleur = couleur;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    // Méthode abstraite que le Pion et la Dame devront coder obligatoirement
    public abstract boolean estMouvementValide(int startX, int startY, int endX, int endY, Plateau plateau);

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + couleur;
    }
}
