package Solvers.Metaheuristic.VNS.Sequential;

import Model.Knapsack;
import Solvers.Metaheuristic.LocalSearch.SwapLocalSearch;

public class BVNS extends SequentialVNS {

    public Knapsack execute(Knapsack knapsack) {
        int k = 0; //Starts in 0 because we want the first iteration done over the original greedy solution

        Knapsack explorationSolution = knapsack.clone();

        while (k < kMax) {
            explorationSolution.shake(k);
            SwapLocalSearch.localSearchBestImprovement(explorationSolution);
            k=neighbourhoodChange(knapsack,explorationSolution,k);
        }
        return null;
    }
}
