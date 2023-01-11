package Solvers.Metaheuristic.VNS.Sequential;

import Model.Knapsack;

public class RVNS extends SequentialVNS {

    public Knapsack execute(Knapsack knapsack) {
        int k = 0; //Starts in 0 because we want the first iteration done over the original greedy solution

        Knapsack explorationSolution = knapsack.clone();

        while (k < kMax) {
            explorationSolution.shake(k);
            k=neighbourhoodChange(knapsack,explorationSolution,k);
        }
        return null;
    }
}
