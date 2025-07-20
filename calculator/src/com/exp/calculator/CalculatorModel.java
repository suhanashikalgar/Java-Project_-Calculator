package com.exp.calculator;



public class CalculatorModel {
    private double current = 0;
    private char operator = ' ';
    private boolean start = true;

    public void reset() {
        current = 0;
        operator = ' ';
        start = true;
    }

    public double getCurrent() {
        return current;
    }

    public void calculate(double input) {
        if (start) {
            current = input;
            start = false;
        } else {
            switch (operator) {
                case '+': current += input; break;
                case '-': current -= input; break;
                case '*': current *= input; break;
                case '/': current /= input; break;
                case '%': current %= input; break;
                case '^': current = Math.pow(current, input); break;
            }
        }
    }

    public void setOperator(char op) {
        operator = op;
    }
}
