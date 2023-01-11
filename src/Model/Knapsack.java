package Model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Knapsack {
    private final int maxWeight;
    private int value;
    private int weight;
    private HashSet<Integer> elements;
    private HashSet<Integer> elementsOutside;

    private int[] weights;
    private int[] values;
    private boolean shakeModeReduction = false;

    Random r = new Random(1);


    public Knapsack(int maxWeight, int[] weights, int[] values) {
        this.maxWeight = maxWeight;
        this.weights = weights;
        this.values = values;
        value = 0;
        weight = 0;
        elements = new HashSet<>();
        elementsOutside = new HashSet<>();
        for (int i = 0; i < weights.length; i++) {
            elementsOutside.add(i);
        }
    }

    public void addElem(int elem) {
        this.elements.add(elem);
        this.weight += weights[elem];
        this.value += values[elem];
        this.elementsOutside.remove(elem);
    }

    public void removeElem(int elem) {
        this.elements.remove(elem);
        this.weight -= weights[elem];
        this.value -= values[elem];
        this.elementsOutside.add(elem);
    }

    public boolean contains(int elem) {
        return elements.contains(elem);
    }

    public boolean isFeasible() {
        return weight <= maxWeight;
    }

    public boolean isFeasibleWithElem(int elem) {
        return weight + weights[elem] <= maxWeight;
    }

    public int valueWithElem(int elem) {
        return value + values[elem];
    }

    public int valueWithoutElem(int elem) {
        return value - values[elem];
    }

    public int weightWithElem(int elem) {
        return weight + weights[elem];
    }

    public int weightWithoutElem(int elem) {
        return weight - weights[elem];
    }

    public boolean swapIsFeasible(int elemComingIn, int elemGoingOut) {
        return weight + weights[elemComingIn] - weights[elemGoingOut] <= maxWeight;
    }

    public boolean swapIsFeasibleMultipleElements(List<Integer> elemsComingIn, List<Integer> elemsGoingOut) {
        return weight + elemsComingIn.stream().map(el -> weights[el]).mapToInt(Integer::intValue).sum()
                - elemsGoingOut.stream().map(el -> weights[el]).mapToInt(Integer::intValue).sum() <= maxWeight;
    }

    public HashSet<Integer> getElements() {
        return elements;
    }

    public int deltaValueSwapElements(int elemComingIn, int elemGoingOut) {
        return values[elemComingIn] - values[elemGoingOut];
    }

    public int deltaValueSwapMultipleElements(List<Integer> elemsComingIn, List<Integer> elemsGoingOut) {
        return elemsComingIn.stream().map(el -> values[el]).mapToInt(Integer::intValue).sum()
                - elemsGoingOut.stream().map(el -> values[el]).mapToInt(Integer::intValue).sum();
    }

    public void swapElements(int elemComingIn, int elemGoingOut) {
        elements.remove(elemGoingOut);
        elements.add(elemComingIn);

        elementsOutside.add(elemGoingOut);
        elementsOutside.remove(elemComingIn);

        weight = weight - weights[elemGoingOut] + weights[elemComingIn];
        value = value - values[elemGoingOut] + values[elemComingIn];
    }

    public void swapMultipleElements(List<Integer> elemsComingIn, List<Integer> elemsGoingOut) {

        elemsComingIn.forEach(el -> {
            elements.add(el);
            elementsOutside.remove(el);
            weight += weights[el];
            value += values[el];
        });

        elemsGoingOut.forEach(el -> {
            elements.remove(el);
            elementsOutside.add(el);
            weight -= weights[el];
            value -= values[el];
        });
    }

    /*
    Randomly remove or add a number of elements from the set
     */
    public void shake(int range) {
        if (shakeModeReduction) {
            try {
                for (int i = 0; i < range; i++) {
                    int posToRemove = r.nextInt(elements.size());
                    Iterator<Integer> iter = elements.iterator();
                    iter.next();
                    for (int j = 0; j < posToRemove; j++) {
                        iter.next();
                    }
                    int elem = iter.next();
                    removeElem(elem);
                }
            } catch (Exception ignored) {
            }
            shakeModeReduction = false;
        } else {
            try {
                //Select a random element from a set
                for (int i = 0; i < range; i++) {
                    int posToAdd = r.nextInt(elementsOutside.size());
                    Iterator<Integer> iter = elementsOutside.iterator();
                    iter.next();
                    for (int j = 0; j < posToAdd; j++) {
                        iter.next();
                    }
                    int elem = iter.next();
                    if (isFeasibleWithElem(elem)) {
                        addElem(elem);
                    }
                }
            } catch (Exception ignored) {
            }
            shakeModeReduction = true;
        }
    }

    public Knapsack clone() {
        Knapsack cloned = new Knapsack(maxWeight, weights, values);
        elements.forEach(cloned::addElem);
        return cloned;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    public int[] getWeights() {
        return weights;
    }

    public int[] getValues() {
        return values;
    }

    public HashSet<Integer> getElementsOutside() {
        return elementsOutside;
    }

    public void setSolution(Knapsack solution) {
        for (int i = 0; i < weights.length; i++) {
            if (solution.contains(i)) {
                if (!this.contains(i)) {
                    this.addElem(i);
                }
            } else {
                if (contains(i)) {
                    removeElem(i);
                }
            }
        }
    }


}
