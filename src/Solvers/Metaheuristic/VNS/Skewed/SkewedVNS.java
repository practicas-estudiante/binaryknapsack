package Solvers.Metaheuristic.VNS.Skewed;

import Model.Knapsack;

public class SkewedVNS {
    static int kMax = 30;
    static int maxNumberOfSkewedIterations = 10;
    static float alpha = 0.95f;
    private int numberOfSkewedIterations = 0;




    public int neighbourhoodChange(Knapsack actualBest, Knapsack newKnapsack, Integer k, int bestValue) {
        //Alpha of 0.9 means that the new solution can be 10% worse than the best solution and still be accepted
        if (newKnapsack.getValue() > bestValue) { //If the new solution is better we just accept it
            actualBest.setSolution(newKnapsack);
            k = 1;
            numberOfSkewedIterations = 0;
        } else if (newKnapsack.getValue() > bestValue * alpha && numberOfSkewedIterations<maxNumberOfSkewedIterations && actualBest.getValue()!=newKnapsack.getValue()) { //If the solution is skewed we continue exploring with it, but we don't save it
           numberOfSkewedIterations++;
        } else {
            k++;
            newKnapsack.setSolution(actualBest);
            numberOfSkewedIterations = 0;
        }
        return k;
    }

}
