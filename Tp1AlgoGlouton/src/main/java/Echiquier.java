/**
 *
 * @author Maxime LEBRETON
 */


public class Echiquier implements Cloneable{
    private Cellule[][] echiquier;
    private int taille;

    public Echiquier(int taille) {
        this.taille = taille;
        echiquier = new Cellule[taille][taille];
        initialiseEchiquier();
    }
    
    @Override
    public Echiquier clone() {
        Echiquier echiquier2 = new Echiquier(taille);
        
        for (int x = 0; x < taille; x++) {
            for (int y = 0; y < taille; y++) {
                int temp = echiquier[x][y].getTypeOccupation();              
                echiquier2.modifierCellule(x, y, temp);
            }
        }       
        return echiquier2;
    }
    
    public void initialiseEchiquier() {
        
        for (int x = 0; x < taille; x++) {
            for (int y = 0; y < taille; y++) {
                echiquier[x][y] = new Cellule(x,y);
            }
        }
    } 
    
    public void modifierCellule (int x, int y, int valeur) {
        
        echiquier[x][y].setTypeOccupation(valeur);
    }
    
    public void verifierMenace(int x, int y){
        int i, j;
            for(i=0; i<taille; i++){
                if( i != x){
                    echiquier[i][y].setTypeOccupation(2);
                }
            }
            //colonne
            for(j=0; j<taille; j++){
                if( j != y) {
                    echiquier[x][j].setTypeOccupation(2);
                }
            }
            //Diagonale
            for(i=0; i<taille; i++){
                for(j=0;j<taille; j++){
                    if((i !=x ) && (j != y)){
                        if (Math.abs(x-i) == Math.abs(y-j)){
                            echiquier[i][j].setTypeOccupation(2);
                        }
                    }
                }
            }  
    }
    
    public void placerReine(int x, int y) {
        if(echiquier[x][y].getTypeOccupation() == 0){
            echiquier[x][y].setTypeOccupation(1);  
            verifierMenace(x,y);
        }
    }
    
    
    public void PremierPlacement(){
        for (int x = 0; x < taille; x++) {
            for (int y = 0; y < taille; y++) {
                if(echiquier[x][y].getTypeOccupation() == 0){
                    placerReine(x,y);
                }
            }
        }
    }
    
    public int NombreMenace(Cellule[][] test){
        int m =0;
        for (int x = 0; x < taille; x++) {
            for (int y = 0; y < taille; y++) {
                if(test[x][y].getTypeOccupation() == 2){m+= 1;}
            }
        }
        System.out.println(m);
        return m;
    }
      
    public void meilleurPlacement() {
        int MenaceMax = taille*taille;
        int X = 0;
        int Y = 0;
        
        for (int x = 0; x < taille; x++) {
            for (int y = 0; y < taille; y++) {
                if(echiquier[x][y].getTypeOccupation() == 0){
                    Echiquier CopieEchiquier = clone();
                    CopieEchiquier.placerReine(x, y);
                    
                    int temp = NombreMenace(CopieEchiquier.getEchiquier());
                    if (MenaceMax > temp){
                        X = x;
                        Y = y;
                        MenaceMax = temp;
                    }
                }
            }
        }
        placerReine(X,Y);
    }
    public boolean caseVide(){
        boolean result = false;
        for (int x = 0; x < taille; x++) {
            for (int y = 0; y < taille; y++) {
                if(echiquier[x][y].getTypeOccupation() == 0){
                    result = true;}
            }
        }
        return result;
    }
    
    public Cellule[][] getEchiquier(){
        return echiquier;
    }
    public String toString(){
        String damier = "";
        int x, y;
        for(x=0; x<taille; x++){
            for(y=0;y<taille; y++){
            if(echiquier[x][y].getTypeOccupation() == 0)
                damier += " ";
            if(echiquier[x][y].getTypeOccupation() == 1)
                damier += "R";
            if(echiquier[x][y].getTypeOccupation() == 2)
                damier += "*";
            damier += " ";
            }
        damier += "\n";
        }
        return damier;
    }

    public void afficherEchiquier(){
        System.out.println(toString());
    }
}
