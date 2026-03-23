package ex4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame implements ActionListener {
    private JTextField display;
    private String currentOperator = "";
    private double firstOperand = 0;
    private boolean startNewInput = true;

    public CalculatorApp() {
        setTitle("Calculator");
        setSize(320, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));
        setLocationRelativeTo(null);

        // Display
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 32));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(display, BorderLayout.NORTH);

        // Buttons Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        String[] buttons = {
            "C", "CE", "DEL", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "±", "0", ".", "="
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setFocusPainted(false);
            
            if (text.matches("[0-9]")) {
                button.setBackground(Color.WHITE);
            } else if (text.equals("=") || text.equals("C") || text.equals("CE") || text.equals("DEL")) {
                button.setBackground(new Color(220, 220, 220));
            } else {
                button.setBackground(new Color(240, 240, 240));
            }
            
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]") || command.equals(".")) {
            if (startNewInput) {
                if (command.equals(".")) {
                    display.setText("0.");
                } else {
                    display.setText(command);
                }
                startNewInput = false;
            } else {
                if (command.equals(".") && display.getText().contains(".")) {
                    return; // Prevent multiple dots
                }
                display.setText(display.getText() + command);
            }
        } else if (command.equals("C") || command.equals("CE")) {
            display.setText("0");
            currentOperator = "";
            firstOperand = 0;
            startNewInput = true;
        } else if (command.equals("DEL")) {
            String text = display.getText();
            if (text.length() > 0 && !startNewInput) {
                text = text.substring(0, text.length() - 1);
                if (text.isEmpty() || text.equals("-")) {
                    text = "0";
                    startNewInput = true;
                }
                display.setText(text);
            }
        } else if (command.equals("±")) {
            if (!display.getText().equals("0")) {
                if (display.getText().startsWith("-")) {
                    display.setText(display.getText().substring(1));
                } else {
                    display.setText("-" + display.getText());
                }
            }
        } else if (command.equals("=")) {
            if (!currentOperator.isEmpty()) {
                calculateResult();
                currentOperator = "";
            }
        } else {
            // Operators: +, -, *, /
            if (!currentOperator.isEmpty() && !startNewInput) {
                calculateResult();
            }
            try {
                firstOperand = Double.parseDouble(display.getText());
            } catch (NumberFormatException ex) {
                firstOperand = 0;
            }
            currentOperator = command;
            startNewInput = true;
        }
    }

    private void calculateResult() {
        if (currentOperator.isEmpty()) return;
        
        double secondOperand;
        try {
            secondOperand = Double.parseDouble(display.getText());
        } catch (NumberFormatException ex) {
            return;
        }
        
        double result = 0;

        switch (currentOperator) {
            case "+": result = firstOperand + secondOperand; break;
            case "-": result = firstOperand - secondOperand; break;
            case "*": result = firstOperand * secondOperand; break;
            case "/": 
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand; 
                } else {
                    display.setText("Error");
                    startNewInput = true;
                    return;
                }
                break;
        }
        
        // Remove trailing zero if integer
        if (result == (long) result) {
            display.setText(String.format("%d", (long) result));
        } else {
            display.setText(String.format("%s", result));
        }
        startNewInput = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorApp calc = new CalculatorApp();
            calc.setVisible(true);
        });
    }
}
