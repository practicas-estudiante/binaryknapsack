package Solvers.Exact;

import Model.Knapsack;

public class DynamicProgramming {

    public static void execute(Knapsack knapsack) {
        int[] values = knapsack.getValues();
        int[] weights = knapsack.getWeights();
        int maxWeight = knapsack.getMaxWeight();

        int[][] matrix = new int[values.length + 1][maxWeight + 1];

        for (int i = 1; i <= values.length; i++) {
            for (int j = 1; j <= maxWeight; j++) {
                if (weights[i - 1] <= j) {
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i - 1][j - weights[i - 1]] + values[i - 1]);
                } else {
                    matrix[i][j] = matrix[i - 1][j];
                }
            }
        }

        int[] solution = new int[values.length];
        int j = maxWeight;
        for (int i = values.length; i > 0; i--) {
            if (matrix[i][j] != matrix[i - 1][j]) {
                solution[i - 1] = 1;
                j = j - weights[i - 1];
            }
        }
        for (int i = 0; i <solution.length; i++) {
            if (solution[i] == 1) {
                knapsack.addElem(i);
            }
        }
    }
}
