package Solvers.Metaheuristic.VNS.Skewed;

import Model.Knapsack;
import Solvers.Metaheuristic.LocalSearch.AdditionLocalSearch;
import Solvers.Metaheuristic.LocalSearch.SwapLocalSearch;
import Solvers.Metaheuristic.LocalSearch.SwapLocalSearchAddition;
import Solvers.Metaheuristic.LocalSearch.SwapLocalSearchDepth2;

public class GVNS extends SkewedVNS {

    public Knapsack execute(Knapsack knapsack) {
        int k = 0; //Starts in 0 because we want the first iteration done over the original greedy solution


        Knapsack explorationSolution = knapsack.clone();
        while (k < kMax) {
            explorationSolution.shake(k);
            switch (k) {
                case 0, 1 -> AdditionLocalSearch.localSearchBestImprovement(explorationSolution);
                case 2 -> SwapLocalSearch.localSearchBestImprovement(explorationSolution);
            }
            k=neighbourhoodChange(knapsack,explorationSolution,k, knapsack.getValue());
        }
        return null;
    }
}
