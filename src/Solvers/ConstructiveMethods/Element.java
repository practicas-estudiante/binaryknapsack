package Solvers.ConstructiveMethods;

public class Element {
    private int elem;
    private int value;
    private int weight;
    private float ratio;

    public Element(int elem,int value, int weight) {
        this.elem=elem;
        this.value = value;
        this.weight = weight;
        this.ratio= (float) value/weight;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    public float getRatio() {
        return ratio;
    }

    public int getElem() {
        return elem;
    }
}
