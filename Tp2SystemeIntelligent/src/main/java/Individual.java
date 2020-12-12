/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import jdk.jshell.spi.ExecutionControl;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
/**
 *
 * @author north
 */
public class Individual {
    private int[] genes;
    private int fitnessScore;

    /**
     * Representation of an individual with pseudo-randomly generated genes
     * @param numberOfGenes sets the number of genes of this individual
     */
    public Individual(int numberOfGenes)
    {
        this.genes = new int[numberOfGenes];
        Random rand = new Random();
        for(int i=0; i<numberOfGenes; i++){
            this.genes[i] = rand.nextInt(2);
        this.computeFitnessScore();}
    }

    /**
     * Representation of an individual with pre-computed genes
     * @param genes an array of genes
     */
    public Individual(int[] genes)
    {
        this.genes = genes;
        this.computeFitnessScore();
    }

    /**
     * Self compute the fitness score
     */
    public void computeFitnessScore()// A faire
    {
       int taille = genes.length-1;
       int totalfitness = 0;
       for (int i=taille;i>=0;i--)
       {
           int v = genes[i];
           totalfitness += v;
       }
       fitnessScore = totalfitness;
    }

    /**
     * Selects a bit in the genes array and flips it to either 1 or 0
     * @param geneIndex index of the gene that needs to be flipped
     */
    public void geneFlip(int geneIndex) // A faire
    {
        genes[geneIndex] ^= 1;
        this.computeFitnessScore();
    }

    public int[] getGenes()
    {
        return genes;
    }
    
    public int getFitenesse()
    {
        return fitnessScore;
    }

    @Override
    public String toString()
    {
        String res ="";
        res = "Individual{" +"genes=" + Arrays.toString(genes)+" "+"fitenesse :"+ fitnessScore +'}'+"\n";
        return res;
    } 
}
