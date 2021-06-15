public class Joueur{

    private String nom;
    private int camp;
    private int nbPion;
    private int nbPaires;

    /**
     * Constructeur par défaut sans paramètres
     */
    public Joueur(){
        this.nom = new String("Joueur");
        this.setCamp(1);
        this.setNbPion(50);
        this.setNbPaires(0);
    }

    /**
     * Constructeur par paramètres
     * @param nom
     * @param camp
     */
    public Joueur(String nom, int camp){
        this.nom = new String(nom);
        this.setCamp(camp);
        this.setNbPion(50);
        this.setNbPaires(0);
    }

    public String getNom(){
        return this.nom;
    }

    public int getCamp() {
        return camp;
    }

    public int getNbPion() {
        return nbPion;
    }

    public int getNbPaires() {
        return nbPaires;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public void setCamp(int camp) {
        this.camp = camp;
    }

    public void setNbPion(int nbPion) {
        this.nbPion = nbPion;
    }

    public void setNbPaires(int nbPaires) {
        this.nbPaires = nbPaires;
    }
}

    

    