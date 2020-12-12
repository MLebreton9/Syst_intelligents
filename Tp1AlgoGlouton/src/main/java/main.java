/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ml318464
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Echiquier test=new Echiquier(8);
        test.placerReine(0, 0);
        /*Cellule[][] test2 = test.clone();
        String damier ="";
        int x, y;
        for(x=0; x<8; x++){
            for(y=0;y<8; y++){
            if(test2[x][y].getTypeOccupation() == 0)
                damier += " ";
            if(test2[x][y].getTypeOccupation() == 1)
                damier += "R";
            if(test2[x][y].getTypeOccupation() == 2)
                damier += "*";
            damier += " ";
            }
        damier += "\n";
        }
        System.out.println(damier);*/
        //test.PremierPlacement();
        while(test.caseVide()){
        test.meilleurPlacement();
        }
        test.afficherEchiquier();
        
    }
    
}
