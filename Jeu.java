//Imports
import java.util.Scanner;
import MG2D.*;
import MG2D.geometrie.*;
import java.awt.Font;
import java.util.Random;

public class Jeu{
    
    //CONSTANTES
    final static int largeur = 1280;
    final static int hauteur = 720;
    final static int taillePlateau = 648;

    //OBJETS
    private Joueur joueur1;
    private Joueur joueur2;
    private Plateau plateau;

    //MG2D
    private Fenetre f;
    private Clavier clavier;
    private Souris souris;
    final static Couleur couleurBg = new Couleur(117, 99, 74);
    final static Couleur couleurPlateau = new Couleur(140, 61, 44);
    final static Couleur couleurQuadrillage = new Couleur(193, 151, 97);
    private Couleur couleurJ1 = Couleur.BLANC;
    private Couleur couleurJ2 = Couleur.NOIR;
    
    //POLICES
    private static Font calibri = new Font("Calibri", Font.TYPE1_FONT, 16);
    private static Font calibriBig = new Font("Calibri", Font.TYPE1_FONT, 24);
    private static Font calibriBigger = new Font("Calibri", Font.TYPE1_FONT, 54);

    //VARIABLES
    private int tour;
    private String gagnant;
    
    //BOOLEENS
    private boolean jeuLance;
    private boolean pose1;

    /**
     * Constructeur par défaut
     */
    public Jeu(){
        joueur1 = new Joueur();
        joueur2 = new Joueur();
        plateau = new Plateau();
        jeuLance = false;
        tour = 1;
        pose1=false;
        f = new Fenetre("Jeu de pente", largeur, hauteur);
        clavier = f.getClavier();
        souris = f.getSouris();
        information();
    }

    public Plateau getPlateau(){
        return this.plateau;
    }

    public Joueur getJoueur1(){
        return this.joueur1;
    }

    public Joueur getJoueur2(){
        return this.joueur2;
    }

    public int getTour(){
        return this.tour;
    }

    /**
     * Méthode qui demande les informations des joueurs
     */
    public void information(){
        f.setBackground(couleurBg);
        f.ajouter(new Rectangle(couleurPlateau, new Point(410, 35), new Point(870, 685), true));
        Texte textePresentation = new Texte(Couleur.NOIR, new String("Bienvenue sur le jeu de pente, veuillez entrer vos informations : "), calibri, new Point(640, 665));
        Texte texteInfo1 = new Texte(Couleur.NOIR, new String("Entrez le nom des joueurs"), calibri, new Point(640, 550));
        f.ajouter(textePresentation);
        f.ajouter(texteInfo1);
        f.rafraichir();
        System.out.println("Entrer le nom du joueur blanc:");
        Scanner sc = new Scanner(System.in);
        String nomJ1 = sc.next();
        this.joueur1.setNom(nomJ1);
        this.joueur1.setCamp(1);
        System.out.println("Entrer le nom du joueur noir:");
        String nomJ2 = sc.next();
        this.joueur2.setNom(nomJ2);
        this.joueur1.setCamp(2);
        creationPlateau();
        jeuLance = true;
        sc.close();
    }

    /**
     * Méthode qui efface toutes les interfaces et fait aparaitre les plateau de jeu et les noms, nombres de pions et nombres de paires
     */
    public void creationPlateau(){
        int originex = (largeur-taillePlateau)/2;
        int originey = (hauteur-taillePlateau)/2;
        int finx = originex+taillePlateau;
        int finy = originey+taillePlateau;
        int ecart = 20;
        f.effacer();
        f.ajouter(new Rectangle(couleurPlateau, new Point(originex-ecart, originey-ecart), new Point(finx+ecart, finy+ecart), true));
        for (int i = 0;i < 19; i++) {
            for (int j = 0;j < 19;j++) {
                f.ajouter(new Rectangle(couleurQuadrillage, new Point(originex, originey+36*j), new Point(finx,  originey+36*j)));
                f.ajouter(new Rectangle(couleurQuadrillage, new Point(originex+36*i, originey), new Point(originex+36*i,  finy)));
            }
        }  
        if (tour==1) {
            Texte tourActuel = new Texte(Couleur.VERT, "C'est ton tour !",calibriBig, new Point(158, 670));
            f.ajouter(tourActuel);
        }else {
            Texte tourActuel = new Texte(Couleur.VERT, "C'est ton tour !",calibriBig, new Point(1122, 670));
            f.ajouter(tourActuel);
        }

        Texte j1 = new Texte(Couleur.BLANC, "Joueur blanc : "+joueur1.getNom(), calibriBig, new Point(158, 600));
        Texte j2 = new Texte(Couleur.NOIR, "Joueur noir : "+joueur2.getNom(), calibriBig, new Point(1122, 600));
        Texte pionJ1 = new Texte(Couleur.BLANC, "Nombre de pions : "+joueur1.getNbPion(), calibri, new Point(158, 400));
        Texte pionJ2 = new Texte(Couleur.NOIR, "Nombre de pions : "+joueur2.getNbPion(), calibri, new Point(1122, 400));
        Texte paireJ1 = new Texte(Couleur.BLANC, "Nombre de paires prises : "+joueur1.getNbPaires(), calibri, new Point(158, 200));
        Texte paireJ2 = new Texte(Couleur.NOIR, "Nombre de paires prises : "+joueur2.getNbPaires(), calibri, new Point(1122, 200));
        f.ajouter(j1);
        f.ajouter(j2);
        f.ajouter(pionJ1);
        f.ajouter(pionJ2);
        f.ajouter(paireJ1);
        f.ajouter(paireJ2);
        f.rafraichir();
    }

    /**
     * Efface toute l'interface et la réaffiche avec les changements du plateau
     */
    public void rafraichirPlateau(){
        creationPlateau();
        int originex = (largeur-taillePlateau)/2;
        int originey = (hauteur-taillePlateau)/2;
        int finx = originex+taillePlateau;
        int finy = originey+taillePlateau;
        for (int i = 0;i < 19; i++) {
            for (int j = 0;j < 19;j++) {
                if (plateau.getValeur(j, i)==1){
                    f.ajouter(new Cercle(couleurJ1, new Point(originex+36*i, finy-36*j), 15, true));
                }else if (plateau.getValeur(j, i)==2){
                    f.ajouter(new Cercle(couleurJ2, new Point(originex+36*i, finy-36*j), 15, true));
                }
            }
        }
    }

    /**
     * Méthode bouclée en permanence
     */
    public void maj(){
        if (jeuLance){
            if (souris.getClicGauche()){
                Point position = souris.getPosition();
                int casex = getCaseX(position);
                int casey = getCaseY(position);
                if(pose1==false){
                    plateau.setValeur(9, 9, tour);
                    pose1=true;
                    if(tour==1) {
                        joueur1.setNbPion(joueur1.getNbPion()-1);
                        tour=2;
                    }else {
                        joueur2.setNbPion(joueur2.getNbPion()-1);
                        tour=1;
                    }
                rafraichirPlateau();
                } else if (casex>=0 && casex<=18 &&casey>=0 && casey<=18){
                    if (plateau.getValeur(casey, casex)==0){
                        if(tour==1 && joueur1.getNbPion()!=0 || tour==2 && joueur2.getNbPion()!=0){
                            plateau.setValeur(casey, casex, tour);
                            if (plateau.testGagne(casex, casey, tour)){
                                if (tour==1){
                                    ecranFin(joueur1.getNom());
                                }else{
                                    ecranFin(joueur2.getNom());
                                }
                                jeuLance=false;  
                            }
                            
                            if(tour==1) {
                                joueur1.setNbPion(joueur1.getNbPion()-1);
                                tour=2;
                            }else {
                                joueur2.setNbPion(joueur2.getNbPion()-1);
                                tour=1;
                            }
                        }
                        if (jeuLance){
                            rafraichirPlateau();
                        }
                    }
                }
            }
        }

        if (souris.getClicDroit()){
            joueur1.setNbPaires(5);
        }
        
        if (joueur1.getNbPaires()==5){
            jeuLance=false;
            ecranFin(joueur1.getNom());
        }else if (joueur2.getNbPaires()==5){
            jeuLance=false;
            ecranFin(joueur2.getNom());
        }
    }

    /**
     * Affiche l'écran de fin de la partie
     * @param gagnant
     */
    public void ecranFin(String gagnant){
        f.effacer();
        f.ajouter(new Texte(Couleur.JAUNE, "Bravo "+gagnant+", tu as gagne !", calibriBigger, new Point(largeur/2, hauteur/2)));
        f.rafraichir();
    }

    /**
     * Retourne l'index x de la case ciblée a partir d'une position
     * @param p
     * @return
     */
    public int getCaseX(Point p){
        int x = (p.getX()-296)/36;
        return x;
    }

    /**
     * Retourne l'index y de la case ciblée a partir d'une position
     * @param p
     * @return
     */
    public int getCaseY(Point p){
        int y =  Math.abs(((p.getY()-16)/36)-18);
        return y;
    }
}