public class Dame extends Piece {
    public Dame(Couleur couleur) {
        super(couleur);
    }

    @Override
    public boolean estMouvementValide(int startX, int startY, int endX, int endY, Plateau plateau) {
        // TODO: Implémenter la logique de déplacement longue distance de la dame
        return true;
    }
}