public class Main {
        public static void main(String[] args) {
        Jeu jeu = new Jeu();
        int latence = 10;

        while (true){
            jeu.maj();
            try{
                Thread.sleep(latence);
            }
            catch (Exception e) {
            }
        }
    }
}