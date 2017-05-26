package com.github.angelikaowczarek;

import java.util.ArrayList;
import java.util.List;

public class CutsCalculator {

    private Float[] elementsLengths;
    private int[] quantities;
    private float bar;
    private List<Cut> cuts = new ArrayList<>();

    public CutsCalculator(Float[] elementsLengths, float bar) {
        this.elementsLengths = elementsLengths;
        this.bar = bar;
        quantities = new int[elementsLengths.length];
    }

    public void calculateCutMethods() {
        int startIndex = 0;
        recursionCut(startIndex);
    }

    private void recursionCut(int currIndex) {
        for (int i = 0; i <= bar / elementsLengths[currIndex]; i++) {

            if (currIndex == elementsLengths.length - 1) {

                float lengthsSum = 0;
                for (int j = 0; j < quantities.length - 1; j++) {
                    lengthsSum += quantities[j] * elementsLengths[j];
                }
                quantities[currIndex] = (int) ((bar - lengthsSum) / elementsLengths[currIndex]);

                float cull = (bar - (lengthsSum + quantities[currIndex] * elementsLengths[currIndex]));
                cull = (float) Math.round(cull * 100) / 100;

                cuts.add(new Cut(quantities.clone(), cull));

                return;

            } else {
                quantities[currIndex] = i;
                recursionCut(currIndex + 1);
            }
        }
    }

    public List<Cut> getCuts() {
        return cuts;
    }

}
