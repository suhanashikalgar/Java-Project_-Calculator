package com.exp.calculator;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController implements ActionListener {
    private final CalculatorView view;
    private double firstOperand = 0;
    private char operator = ' ';
    private boolean startNewInput = true;

    public CalculatorController(CalculatorView v) {
        view = v;
        view.addButtonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        try {
            switch (cmd) {
                case "C":
                    clear();
                    break;
                case "←":
                    backspace();
                    break;
                case "+": case "-": case "*": case "/": case "%": case "^":
                    selectOperator(cmd.charAt(0));
                    break;
                case "=":
                    evaluate();
                    break;
                default:
                    appendNumber(cmd);
            }
        } catch (Exception ex) {
            view.setDisplay("Error");
            clear();
        }
    }

    private void appendNumber(String digit) {
        if (startNewInput) {
            view.setDisplay(digit.equals(".") ? "0." : digit);
            startNewInput = false;
        } else {
            String txt = view.getDisplay();
            if (digit.equals(".") && txt.contains(".")) return;
            view.setDisplay(txt.equals("0") && !digit.equals(".") ? digit : txt + digit);
        }
    }

    private void selectOperator(char op) {
        if (!startNewInput) {
            firstOperand = Double.parseDouble(view.getDisplay());
        }
        operator = op;
        view.setDisplay(format(firstOperand) + " " + op);
        startNewInput = true;
    }

    private void evaluate() {
        if (operator == ' ') return;

        double secondOperand;
        String disp = view.getDisplay().trim();

        if (startNewInput) {
            secondOperand = firstOperand;
        } else {
            int idx = disp.lastIndexOf(' ');
            secondOperand = Double.parseDouble(disp.substring(idx + 1));
        }

        double result = calculate(firstOperand, secondOperand, operator);
        view.setDisplay(format(result));
        firstOperand = result;
        operator = ' ';
        startNewInput = true;
    }

    private double calculate(double a, double b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return b == 0 ? 0 : a / b;
            case '%': return a % b;
            case '^': return Math.pow(a, b);
            default: return b;
        }
    }

    private void backspace() {
        String txt = view.getDisplay();
        if (txt.length() <= 1) view.setDisplay("0");
        else view.setDisplay(txt.substring(0, txt.length() - 1));
    }

    private void clear() {
        firstOperand = 0;
        operator = ' ';
        startNewInput = true;
        view.setDisplay("0");
    }

    private String format(double v) {
        return (v == (long) v) ? String.format("%d", (long) v) : String.valueOf(v);
    }
}