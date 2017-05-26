package com.github.angelikaowczarek;


public class Cut {
    private int[] elementsQuantity;
    private float cull;

    public Cut(int[] elementsQuantity, float cull) {
        this.elementsQuantity = elementsQuantity;
        this.cull = cull;
    }

    public float getCull() {
        return cull;
    }

    public int[] getElementsQuantity() {
        return elementsQuantity;
    }

    public String toString() {
        String string = "";
        for (int quantity : elementsQuantity) {
            string += quantity + ", ";
        }
        string += "odpad: " + cull;
        return string;
    }
}
