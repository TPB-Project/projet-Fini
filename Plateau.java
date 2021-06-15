public class Plateau{

    private int[][] tab;

    /**
     * Constructeur par défaut sans paramètres
     * Initialise le tableau a 0
     */
    public Plateau(){
        tab = new int[19][19];
        for (int i = 0;i < 19; i++) {
            for (int j = 0;j < 19;j++) {
                tab[i][j] = 0;
            }
        }
    }

    /**
     * Méthode de print par défaut
     * @return
     */
    public String toString(){
        String text = "";
        for (int i = 0;i < 19; i++) {
            for (int j = 0;j < 19;j++) {
                text+=tab[i][j] + " ";
            }
            text+="\n";
        }
        return text;
    }

    public int getValeur(int a, int b){
        return this.tab[a][b];
    }

    public void setValeur(int a, int b, int v){
        this.tab[a][b] = v;
    }

    public boolean testGagne(int x, int y, int tour){
        boolean win=false;
        int aligneHorizontal=0;
        int aligneVertical=0;
        for(int i=0;i<=17;i++){
            if (tab[y][i]==tour){
                aligneHorizontal+=1;
                if (aligneHorizontal>=5){
                    win=true;
                }
            }else if(tab[y][i]!=tour){
                aligneHorizontal=0;
            }

            if (tab[i][x]==tour && win==false){
                aligneVertical+=1;
                if (aligneVertical>=5){
                    win=true;
                }
            }else if(tab[i][x]!=tour){
                aligneVertical=0;
            }
        }
        
        if (win==false){
            int startH=(x>y) ? x-y : 0;
            int startV=(x>y) ? 0 : y-x;
            int longueur=(18-startH>18-startV) ? 18-startV : 18-startH;
            int aligneDiago=0;
            int u = startH;
            int v = startV;
            while (u<=startH+longueur){
                if (tab[v][u]==tour){
                    aligneDiago+=1;
                    if (aligneDiago>=5){
                        win=true;
                    }
                }else if (tab[v][u]!=tour){
                    aligneDiago=0;
                }
                u+=1;
                v+=1;
            }
        }

        /*
        if (win==false){
            int startH=(x+y<=19) ? y+x : 18;
            int startV=(x+y<=19) ? 0 : (18-x)-(18-y);
            int longueur=(x+y<=18) ? startV : 18-startH;
            System.out.println(startH+" "+startV);
            int aligneDiago=0;
            int u = startH;
            int v = startV;
            while (u<=startH+longueur && v>= startV-longueur){
                if (tab[v][u]==tour){
                    aligneDiago+=1;
                    if (aligneDiago>=5){
                        win=true;
                    }
                }else if (tab[v][u]!=tour){
                    aligneDiago=0;
                }
                u+=1;
                v-=1;
            }
        }
        */
        
        return win;
    }
}