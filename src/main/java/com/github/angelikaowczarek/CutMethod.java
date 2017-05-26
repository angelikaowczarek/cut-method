package com.github.angelikaowczarek;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CutMethod {

    private Map<Float, Integer> elements;
    private float bar;
    private double[] results;
    private List<Cut> cuts;

    public CutMethod(Map<Float, Integer> elements, float bar) {
        this.elements = elements;
        this.bar = bar;
    }

    public void compute() {
        Float[] elementsLengths = getElementsLengths(elements);
        cuts = calculateCutMethods(elementsLengths, bar);

        Optimizer optimizer = new Optimizer();
        optimizer.findMin(cuts, getDesiredQuantities(elements, elementsLengths));
        results = optimizer.getResults();
    }

    private Float[] getElementsLengths(Map<Float, Integer> elements) {
        Float[] elementsLengths = elements.keySet().toArray(new Float[elements.size()]);
        Arrays.sort(elementsLengths, Collections.reverseOrder());
        return elementsLengths;
    }

    private int[] getDesiredQuantities(Map<Float, Integer> elements, Float[] elementsLengths) {
        int[] desiredQuantites = new int[elements.size()];
        for (int i = 0; i < desiredQuantites.length; i++) {
            desiredQuantites[i] = elements.get(elementsLengths[i]);
        }
        return desiredQuantites;
    }

    private List<Cut> calculateCutMethods(Float[] elementsLengths, float bar) {
        CutsCalculator cutsCalculator = new CutsCalculator(elementsLengths, bar);
        cutsCalculator.calculateCutMethods();
        return cutsCalculator.getCuts();
    }

    public float getTotalCull() {
        float minCull = 0;
        for (int i = 0; i < cuts.size(); i++) {
            minCull += results[i] * cuts.get(i).getCull();
        }
        return minCull;
    }

    public float getTotalBars() {
        float barsQuantity = 0;
        for (int i = 0; i < results.length; i++) {
            barsQuantity += results[i];
        }
        return barsQuantity;
    }
}
