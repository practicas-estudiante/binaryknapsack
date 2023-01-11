package Solvers.Metaheuristic.LocalSearch;

import Model.Knapsack;

import java.util.Set;

public class AdditionLocalSearch {

    public static Knapsack localSearchBestImprovement(Knapsack knapsackCase) {
        Set<Integer> elementsOutsideSolution= knapsackCase.getElementsOutside();
        boolean improvement = true;

        while (improvement) {
            improvement = false;

            int bestElementToAdd = -1;
            int bestDeltaValue = 0;

            for (Integer elementOutside : elementsOutsideSolution) {
                if (knapsackCase.isFeasibleWithElem(elementOutside)) {
                    int deltaValue = knapsackCase.valueWithElem(elementOutside);
                    if (deltaValue > bestDeltaValue) { //There is an improvement
                        bestElementToAdd = elementOutside;
                        improvement = true;
                        bestDeltaValue = deltaValue;
                    }
                }
            }
            if (improvement) {
                knapsackCase.addElem(bestElementToAdd);
            }
        }
        return knapsackCase;
    }

    public static Knapsack localSearchFirstImprovement(Knapsack knapsackCase) {
        Set<Integer> elementsOutsideSolution= knapsackCase.getElementsOutside();
        boolean improvement = true;

        while (improvement) {
            improvement = false;


            int bestElementToAdd = -1;

            for (Integer elementOutside : elementsOutsideSolution) {
                if (knapsackCase.isFeasibleWithElem(elementOutside)) {
                    int deltaValue = knapsackCase.valueWithElem(elementOutside);
                    if (deltaValue > 0) { //There is an improvement
                        knapsackCase.addElem(bestElementToAdd);
                        elementsOutsideSolution.remove(bestElementToAdd);
                        improvement = true;
                        break;
                    }
                }
            }
            if (improvement) {
                break;
            }
        }
        return knapsackCase;
    }
}
