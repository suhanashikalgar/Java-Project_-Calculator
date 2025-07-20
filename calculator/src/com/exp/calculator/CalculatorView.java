package com.exp.calculator;

import javax.swing.*;
import java.awt.*;

public class CalculatorView extends JFrame {
    private final JTextField display = new JTextField("0");
    private final JButton[] buttons;
    private final String[] labels = {
        "C", "←", "%", "/",
        "7", "8", "9", "*",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "^", "="
    };

    public CalculatorView() {
        super("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        display.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 28));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(5, 4, 5, 5));
        buttons = new JButton[labels.length];
        for (int i = 0; i < labels.length; i++) {
            buttons[i] = new JButton(labels[i]);
            buttons[i].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
            panel.add(buttons[i]);
        }
        add(panel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addButtonListener(java.awt.event.ActionListener l) {
        for (JButton btn : buttons) {
            btn.addActionListener(l);
        }
    }

    public String getDisplay() {
        return display.getText();
    }

    public void setDisplay(String text) {
        display.setText(text);
    }
}