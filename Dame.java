public class Dame extends Piece {

    public Dame(Couleur couleur) {
        super(couleur);
    }

    @Override
    public boolean estMouvementValide(int startX, int startY, int endX, int endY, Plateau plateau) {

        //  vérifier que le mouvement se fait en diagonale
        int diffX = Math.abs(endX - startX);
        int diffY = Math.abs(endY - startY);

        if (diffX != diffY || diffX == 0) {
            return false;
        }

        // vérifier que la case de destination est vide
        if (!plateau.getCase(endX, endY).estVide()) {
            return false;
        }

        // Déterminer le sens du déplacement
        int stepX = (endX - startX) > 0 ? 1 : -1;
        int stepY = (endY - startY) > 0 ? 1 : -1;

        //  Parcourir toute les cases situées entre le départ et l'arrivée
        int currentX = startX + stepX;
        int currentY = startY + stepY;
        int piecesAdversesSautees = 0;

        while (currentX != endX && currentY != endY) {
            Case caseActuelle = plateau.getCase(currentX, currentY);

            if (!caseActuelle.estVide()) {
                Piece pieceRencontree = caseActuelle.getPiece();

                // Si la pièce rencontrée est de la même couleur= chemin est bloqué
                if (pieceRencontree.getCouleur() == this.couleur) {
                    return false;
                } else {
                    // C'est une pièce adverse
                    piecesAdversesSautees++;
                }
            }
            // On avance d'une case sur la diagonale
            currentX += stepX;
            currentY += stepY;
        }

        // La dame ne peut sauter par-dessus plusieurs pièces d'un coup sur la même diagonale
        if (piecesAdversesSautees > 1) {
            return false;
        }

        // Si tout est bon
        return true;
    }
}