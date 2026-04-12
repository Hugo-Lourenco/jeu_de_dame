public class Joueur {
    private String nom;
    private Couleur couleur;

    public Joueur(String nom, Couleur couleur) {
        this.nom = nom;
        this.couleur = couleur;
    }

    public Couleur getCouleur() { return couleur; }
    public String getNom() { return nom; }
}