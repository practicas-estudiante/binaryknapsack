package Solvers.Metaheuristic.VNS.Sequential;

import Model.Knapsack;

public class SequentialVNS {
    static int kMax = 30;

    public int neighbourhoodChange(Knapsack actualBest,Knapsack newKnapsac,Integer k){
        if (newKnapsac.getValue() > actualBest.getValue()){
            actualBest.setSolution(newKnapsac);
            k = 1;
        }else{
            k++;
            newKnapsac.setSolution(actualBest);
        }
        return k;
    }
}
