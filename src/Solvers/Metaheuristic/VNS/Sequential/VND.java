package Solvers.Metaheuristic.VNS.Sequential;

import Model.Knapsack;
import Solvers.Metaheuristic.LocalSearch.AdditionLocalSearch;
import Solvers.Metaheuristic.LocalSearch.SwapLocalSearch;
import Solvers.Metaheuristic.LocalSearch.SwapLocalSearchAddition;
import Solvers.Metaheuristic.LocalSearch.SwapLocalSearchDepth2;

public class VND extends SequentialVNS {

    public Knapsack execute(Knapsack knapsack) {
        int k = 1;


        Knapsack explorationSolution = knapsack.clone();
        while (k < kMax) {
            switch (k) {
                case 1 -> AdditionLocalSearch.localSearchBestImprovement(explorationSolution);
                case 2 -> SwapLocalSearch.localSearchBestImprovement(explorationSolution);
            }
            k=neighbourhoodChange(knapsack,explorationSolution,k);
        }
        return null;
    }
}
