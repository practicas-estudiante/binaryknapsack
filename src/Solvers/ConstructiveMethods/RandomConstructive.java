package Solvers.ConstructiveMethods;

import Model.Knapsack;

import java.util.Random;

public class RandomConstructive {
    public static Knapsack createNewBadSolution(Knapsack knapsack) {
        Random r = new Random(14);
        int maxWeight = knapsack.getMaxWeight();
        int[] values = knapsack.getValues();
        //The max weight is divided by 2 to avoid the solution to be too good
        while (knapsack.getWeight() < maxWeight / 2) {
            int randomElem = (int) (r.nextInt(values.length));
            if (!knapsack.contains(randomElem)) {
                knapsack.addElem(randomElem);
            }
        }
        return knapsack;
    }
}
