import java.util.ArrayList;
import jdk.jshell.spi.ExecutionControl;
import java.util.Arrays;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author north
 */
public class Population {
    private Individual[] individuals;
    private int genesPerPop;
    private Crosstype crosstype;
    private float mutationChance;

    /**
     * Representation of a population of pseudo-randomly generated individuals
     * @param popSize set the size of this population
     * @param genesPerPop sets the gene size of each individual in the pool
     * @param crosstype the crosstype to be used by this population
     * @param mutationChance chance for an individual to mutate at birth
     */
    public Population(int popSize, int genesPerPop, Crosstype crosstype, float mutationChance)
    {
        this.individuals = new Individual[popSize];
        this.genesPerPop = genesPerPop;
        this.crosstype = crosstype;
        this.mutationChance = mutationChance;
        for(int i=0; i<popSize; i++)
            this.individuals[i] = new Individual(genesPerPop);
    }

    /**
     * Representation of a population of pre-computed individuals
     * @param individuals an array of individuals
     * @param crosstype the crosstype to be used by this population
     * @param mutationChance chance for an individual to mutate at birth
     */
    public Population(Individual[] individuals, Crosstype crosstype, float mutationChance)
    {
        assert individuals.length > 0;
        this.individuals = individuals;
        this.genesPerPop = individuals[0].getGenes().length;
        this.crosstype = crosstype;
        this.mutationChance = mutationChance;
    }

    /**
     * Creates a new population using this generation's individuals
     * @return the newly generated population
     */
    public Population generateNewPopulation() // A faire
    {       
        //Utilisez les CROSSTYPE ici pour différencier le type de sélection
        
        if(crosstype == Crosstype.ROULETTE)
        {
            //ToDo generate using a ROULETTE crosstype
            int sommeF = 0;
            for (int i =0;i<individuals.length;i++)
            {
                sommeF += individuals[i].getFitenesse();
            }
            Random rand = new Random();
            int alea = rand.nextInt(sommeF);
            int cumul =0;
            int index = 0;
            ArrayList<Individual> individu = new ArrayList<Individual>();
            
            //choix des individus
            for (int i = 0;i<individuals.length;i++){
                while(cumul + individuals[index].getFitenesse()< alea)
                {
                    cumul += individuals[index].getFitenesse();
                    index ++;
                }
                individu.add(individuals[index]);
                cumul = 0;
                index = 0;
                alea = rand.nextInt(sommeF);
            }
            //remplissage tableau
            Individual[] Pop = new Individual[individu.size()];
            for (int i = 0; i < individu.size();i++)
            {
                Pop[i] = individu.get(i);
            }
            ArrayList<Individual> enfants = new ArrayList<Individual>();
            for (int i = 0;i<Pop.length-1;i++ ){
                Individual firstParent =Pop[i];              
                i++;
                Individual secondParent =Pop[i];
                int crosspoint = genesPerPop/2;
                Individual [] recup = reproduceIndividuals(firstParent,secondParent,crosspoint); 
                for (int b = 0; b < 2;b++)
                {
                    enfants.add(recup[b]);
                }
            }            
            //transformation list to tableau
            Individual[] newGen = new Individual[enfants.size()];
            for (int i = 0; i < individu.size();i++)
            {
                newGen[i] = enfants.get(i);
            }
            Population P = new Population(newGen,crosstype,mutationChance);
            return P;
        }
        else{
            //ToDo generate using a TOURNOI crosstype
            ArrayList<Individual> Pop = new ArrayList<Individual>();
            ArrayList<Individual> Selection = new ArrayList<Individual>();
            for (int i = 0;i<individuals.length;i++){
                Pop.add(individuals[i]);
            }
            //seletion des individus
            while (!Pop.isEmpty()){
                Random rand = new Random();
                int alea1 = 0;
                int alea2 = 0;
                while (alea1 == alea2){
                    alea1 = rand.nextInt(Pop.size());
                    alea2 = rand.nextInt(Pop.size());
                }
                Individual P1 = Pop.get(alea1);
                Individual P2 = Pop.get(alea2);
                if (P1.getFitenesse()>= P2.getFitenesse())
                {
                    Selection.add(P1);
                }else{Selection.add(P2);}
                if (alea1 > alea2){
                    Pop.remove(alea1);
                    Pop.remove(alea2);}
                else{
                    Pop.remove(alea2);
                    Pop.remove(alea1);
                }
            }
            
            //génération des enfants
            ArrayList<Individual> newGen = new ArrayList<Individual>();
            for (int i = 0;i<Selection.size()-1;i++){
                Individual[] enfants = reproduceIndividuals(Selection.get(i),Selection.get(i+1),genesPerPop/2);
                newGen.add(enfants[0]);
                newGen.add(enfants[1]);              
            }
            Individual[] enfants = reproduceIndividuals(Selection.get(0),Selection.get(Selection.size()-1),genesPerPop/2);
            newGen.add(enfants[0]);
            newGen.add(enfants[1]);
            //remplissage tableau
            Individual[] PopFinal = new Individual[newGen.size()];
            for (int i = 0; i < newGen.size();i++)
            {
                PopFinal[i] = newGen.get(i);
            }
            Population P = new Population(PopFinal,crosstype,mutationChance);
            return P;

        }
        
    }

    /**
     * Takes 2 individuals and create 2 children using their genes
     * @param firstParent the first selected individual
     * @param secondParent the second selected individual
     * @param crosspoint index of the crosspoint
     * @return an array of 2 individuals
     */
    public Individual[] reproduceIndividuals(Individual firstParent, Individual secondParent, int crosspoint) // A faire
    {
        Individual[] offsprings = new Individual[2];

        int[] firstChildGenes = new int[genesPerPop];
        int[] secondChildGenes = new int[genesPerPop];
//      ToDo compute the genes
        int [] geneP1 = firstParent.getGenes();
        int [] geneP2 = secondParent.getGenes();
        for (int x = 0;x < genesPerPop ;x ++){
            if (x < crosspoint){
                firstChildGenes[x] = geneP1[x];
                secondChildGenes[x] = geneP2[x];
            }
            else{
                firstChildGenes[x] = geneP2[x];
                secondChildGenes[x] = geneP1[x];
            }             
        }
        offsprings[0] = new Individual(firstChildGenes);
        offsprings[1] = new Individual(secondChildGenes);  
//      ToDo compute a possible mutation of a gene
        for (int i =0;i<2;i++){
            double nb = Math.random();
            if (nb <= mutationChance)
            {
               Random random = new Random();
               int x;
               x = random.nextInt(genesPerPop);
               offsprings[i].geneFlip(x);
            }
        }  
        return offsprings;
    }
    
    public int maxdec()
    { 
        int taille = genesPerPop-1;     
        int Maxdec = 0;
        
        for (Individual x: individuals){
            int T = 0;
            int Vdec = 0;
            int [] gene = x.getGenes();
            for (int i=taille;i>=0;i--)
            {
                double p = Math.pow(2,T);
                int res = (int)p;
                int v = gene[i];
                Vdec += v*res;
                T++;
            }
            if (Vdec > Maxdec){Maxdec = Vdec;}
        }
        return Maxdec;
    }
    @Override
    public String toString()
    {
        String res ="";
        res = "Population{"+"\n"+"individuals="+"\n"+ Arrays.toString(individuals) +", genesPerPop=" + genesPerPop +'}'+"\n";
        return res;
    }
    
}
