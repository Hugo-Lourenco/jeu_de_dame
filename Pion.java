public class Pion extends Piece {
    public Pion(Couleur couleur) {
        super(couleur);
    }

    @Override
    public boolean estMouvementValide(int startX, int startY, int endX, int endY, Plateau plateau) {
        // Vérifier que le mouvement n'est pas nul
        if (startX == endX && startY == endY) {
            return false;
        }

        // Vérifier que les coordonnées sont dans les limites de la grille
        if (endX < 0 || endX >= Plateau.TAILLE || endY < 0 || endY >= Plateau.TAILLE) {
            return false;
        }

        // Vérifier que la case destination est vide
        if (!plateau.getCase(endX, endY).estVide()) {
            return false;
        }

        // Vérifier que le mouvement est en diagonale (1 case diagonalement)
        int diffX = Math.abs(endX - startX);
        int diffY = Math.abs(endY - startY);
        if (diffX != 1 || diffY != 1) {
            return false;
        }

        // Vérifier la direction selon la couleur du pion
        if (couleur == Couleur.BLANC) {
            // Les pions blancs se déplacent vers le haut (X décroît)
            return endX < startX;
        } else {
            // Les pions noirs se déplacent vers le bas (X croît)
            return endX > startX;
        }
    }
}