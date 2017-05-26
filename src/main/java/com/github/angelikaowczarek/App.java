package com.github.angelikaowczarek;

import javax.swing.*;
import java.util.*;

public class App {
    private JTextArea dataTextField;
    private JTextArea barLengthField;
    private JPanel panel;
    private JButton calculateBtn;
    private JLabel resultsLabel;
    private JTextArea resultsTextArea;
    private JLabel crippleLabel;


    public App() {

        calculateBtn.addActionListener(e -> {
            DataParser dataParser = new DataParser();

            Map<Float, Integer> elements = dataParser.extractElements(dataTextField.getText());
            float bar = dataParser.extractBar(barLengthField.getText());

            CutMethod cutMethod = new CutMethod(elements, bar);
            cutMethod.compute();

            resultsTextArea.setText("Ilość pociętych sztang: " + cutMethod.getTotalBars() +
                    "\nOdpad całkowity: " + cutMethod.getTotalCull());
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Problem rozkroju");
        frame.setContentPane(new App().panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}