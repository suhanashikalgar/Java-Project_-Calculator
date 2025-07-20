package com.exp.calculator;


import javax.swing.SwingUtilities;

public class Program {
    public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> {
            CalculatorView view = new CalculatorView();
            new CalculatorController(view);
        });
    }
}

