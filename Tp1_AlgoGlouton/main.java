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
        //test.placerReine(0, 0);
        while(test.caseVide()){
        test.meilleurPlacement();
        }
        test.afficherEchiquier();
        
    }
    
}
