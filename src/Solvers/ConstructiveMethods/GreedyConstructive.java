package Solvers.ConstructiveMethods;

import Model.Knapsack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GreedyConstructive {

    public static Knapsack createNewSolution(Knapsack knapsack) {
        int[] values = knapsack.getValues();
        int[] weights = knapsack.getWeights();

        List<Element> elements = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            elements.add(new Element(i, values[i], weights[i]));
        }
        elements = elements.stream().sorted(new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                return Float.compare(o2.getRatio(), o1.getRatio());
            }
        }).collect(Collectors.toList());

        for (Element element : elements) {
            if (knapsack.isFeasibleWithElem(element.getElem())) {
                knapsack.addElem(element.getElem());
            }
        }
        return knapsack;
    }

}