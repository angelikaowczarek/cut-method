package com.github.angelikaowczarek;

import java.util.HashMap;
import java.util.Map;

public class DataParser {

    public Float extractBar(String data) {
        return Float.parseFloat(data);
    }

    public Map<Float, Integer> extractElements(String data) {
        Map<Float, Integer> elements = new HashMap<>();

        String[] lines = data.split("\\r?\\n");

        for (String line : lines) {
            String[] attr = line.split(" ");

            elements.put(
                    Float.parseFloat(attr[0]),
                    Integer.parseInt(attr[1]));
        }

        return elements;
    }
}
