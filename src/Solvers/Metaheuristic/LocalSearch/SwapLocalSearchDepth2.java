package Solvers.Metaheuristic.LocalSearch;

import Model.Knapsack;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class SwapLocalSearchDepth2 {

    public static Knapsack localSearchBestImprovement(Knapsack knapsackCase) {
        Set<Integer> elementsOutsideSolution = knapsackCase.getElementsOutside();
        boolean improvement = true;

        Set<Integer> elementsInsideSolution = knapsackCase.getElements();


        while (improvement) {
            improvement = false;

            List<Integer> bestElementToAdd = null;
            List<Integer> bestElementToRemove = null;

            int bestValue = 0;
            //Get all posible combinations of 2 elements without order (2 elements from outside and 2 elements from inside)
            for (int elementOutside = 0; elementOutside < knapsackCase.getWeights().length; elementOutside++) {
                for (Integer elementOutside2 = elementOutside; elementOutside2 < knapsackCase.getWeights().length; elementOutside2++) {
                    if (!Objects.equals(elementOutside, elementOutside2) && !knapsackCase.contains(elementOutside) && !knapsackCase.contains(elementOutside2)) {
                        List<Integer> elementsComingIn = List.of(new Integer[]{elementOutside, elementOutside2});
                        for (int elementInside = 0; elementInside < knapsackCase.getWeights().length; elementInside++) {
                            for (Integer elementInside2 = elementInside; elementInside2 < knapsackCase.getWeights().length; elementInside2++) {
                                if (!Objects.equals(elementInside, elementInside2) && knapsackCase.contains(elementInside) && knapsackCase.contains(elementInside2)) {
                                    List<Integer> elementsGoingOut = List.of(new Integer[]{elementInside, elementInside2});
                                    if (knapsackCase.swapIsFeasibleMultipleElements(elementsComingIn, elementsGoingOut)) {
                                        int deltaValue = knapsackCase.deltaValueSwapMultipleElements(elementsComingIn, elementsGoingOut);
                                        if (deltaValue > bestValue) { //There is an improvement
                                            bestElementToAdd = List.of(elementOutside, elementOutside2);
                                            bestElementToRemove = List.of(elementInside, elementInside2);
                                            improvement = true;
                                            bestValue = deltaValue;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (improvement) {
                knapsackCase.swapMultipleElements(bestElementToAdd, bestElementToRemove);
            }
        }
        return knapsackCase;
    }

    public static Knapsack localSearchFirstImprovement(Knapsack knapsackCase) {
        Set<Integer> elementsOutsideSolution = knapsackCase.getElementsOutside();
        boolean improvement = true;

        Set<Integer> elementsInsideSolution = knapsackCase.getElements();
        while (improvement) {
            improvement = false;


            //Get all posible combinations of 2 elements without order (2 elements from outside and 2 elements from inside)
            for (int elementOutside = 0; elementOutside < knapsackCase.getWeights().length; elementOutside++) {
                for (Integer elementOutside2 = elementOutside; elementOutside2 < knapsackCase.getWeights().length; elementOutside2++) {
                    if (!Objects.equals(elementOutside, elementOutside2) && !knapsackCase.contains(elementOutside) && !knapsackCase.contains(elementOutside2)) {
                        List<Integer> elementsComingIn = List.of(new Integer[]{elementOutside, elementOutside2});
                        for (int elementInside = 0; elementInside < knapsackCase.getWeights().length; elementInside++) {
                            for (Integer elementInside2 = elementInside; elementInside2 < knapsackCase.getWeights().length; elementInside2++) {
                                if (!Objects.equals(elementInside, elementInside2) && knapsackCase.contains(elementInside) && knapsackCase.contains(elementInside2)) {
                                    List<Integer> elementsGoingOut = List.of(new Integer[]{elementInside, elementInside2});
                                    if (knapsackCase.swapIsFeasibleMultipleElements(elementsComingIn, elementsGoingOut)) {
                                        int deltaValue = knapsackCase.deltaValueSwapMultipleElements(elementsComingIn, elementsGoingOut);
                                        if (deltaValue > 0) { //There is an improvement
                                            knapsackCase.swapMultipleElements(elementsComingIn, elementsGoingOut);
                                            improvement = true;
                                            break;

                                        }
                                    }
                                }
                            }
                            if (improvement) {
                                break;
                            }
                        }
                        if (improvement) {
                            break;
                        }
                    }
                }
                if (improvement) {
                    break;
                }
            }
        }
        return knapsackCase;
    }
}
