package Solvers.Metaheuristic.LocalSearch;

import Model.Knapsack;

import java.util.Set;

public class SwapLocalSearch {

    public static Knapsack localSearchBestImprovement(Knapsack knapsackCase) {
        Set<Integer> elementsOutsideSolution=knapsackCase.getElementsOutside();
        boolean improvement = true;

        Set<Integer> elementsInsideSolution = knapsackCase.getElements();
        while (improvement) {
            improvement = false;


            int bestElementToAdd = -1;
            int bestElementToRemove = -1;

            int bestValue = 0;
            for (Integer elementOutside : elementsOutsideSolution) {
                for (Integer elementInside : elementsInsideSolution) {
                    if (knapsackCase.swapIsFeasible(elementOutside, elementInside)) {
                        int deltaValue = knapsackCase.deltaValueSwapElements(elementOutside, elementInside);
                        if (deltaValue > bestValue) { //There is an improvement
                            bestElementToAdd = elementOutside;
                            bestElementToRemove = elementInside;
                            improvement = true;
                            bestValue = deltaValue;
                        }
                    }
                }
            }
            if (improvement) {
                knapsackCase.swapElements(bestElementToAdd, bestElementToRemove);
            }
        }
        return knapsackCase;
    }
    public static Knapsack localSearchFirstImprovement(Knapsack knapsackCase) {
        Set<Integer> elementsOutsideSolution=knapsackCase.getElementsOutside();
        boolean improvement = true;

        Set<Integer> elementsInsideSolution = knapsackCase.getElements();
        while (improvement) {
            improvement = false;


            for (Integer elementOutside : elementsOutsideSolution) {
                for (Integer elementInside : elementsInsideSolution) {
                    if (knapsackCase.swapIsFeasible(elementOutside, elementInside)) {
                        int deltaValue = knapsackCase.deltaValueSwapElements(elementOutside, elementInside);
                        if (deltaValue > 0) { //There is an improvement
                            knapsackCase.swapElements(elementOutside,elementInside );
                            improvement = true;
                            break;
                        }
                    }
                }
                if (improvement){
                    break;
                }
            }
        }
        return knapsackCase;
    }
}
