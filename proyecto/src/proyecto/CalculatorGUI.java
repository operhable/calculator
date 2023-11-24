package proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField textField;
    private double num1, num2, result;
    private char operator;
    private boolean resultDisplayed;

    public CalculatorGUI() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK); // Black background

        textField = new JTextField();
        textField.setEditable(false);
        textField.setBackground(Color.RED); // Red background
        textField.setForeground(Color.YELLOW); // Yellow text
        Font font = new Font("Arial", Font.BOLD, 20);
        textField.setFont(font);
        add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));
        panel.setBackground(Color.BLACK); // Black background

        String[] buttonLabels = {
                "1", "2", "3", "+",
                "4", "5", "6", "-",
                "7", "8", "9", "/",
                "0", ".", "%", "*",
                "C", "="
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            button.setBackground(Color.BLACK); // Black background
            button.setForeground(Color.YELLOW); // Yellow text
            button.setFont(font);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9.]")) {
            if (resultDisplayed) {
                textField.setText("");
                resultDisplayed = false;
            }
            textField.setText(textField.getText() + command);
        } else if (command.matches("[+\\-*%/=]")) {
            if (textField.getText().isEmpty()) {
                return;
            }
            if (command.charAt(0) != '=') {
                if (operator != 0) {
                    num2 = Double.parseDouble(textField.getText());
                    calculateResult();
                    operator = command.charAt(0);
                    textField.setText(result + String.valueOf(operator));
                } else {
                    num1 = Double.parseDouble(textField.getText());
                    operator = command.charAt(0);
                    textField.setText(textField.getText() + String.valueOf(operator));
                }
            } else {
                calculateResult();
                textField.setText(String.valueOf(result));
                resultDisplayed = true;
            }
        } else if (command.equals("C")) {
            textField.setText("");
            operator = 0;
            resultDisplayed = false;
        }
    }

    private void calculateResult() {
        if (operator != 0) {
            if (!textField.getText().endsWith(String.valueOf(operator))) {
                num2 = Double.parseDouble(textField.getText().substring(textField.getText().lastIndexOf(operator) + 1));
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '%':
                        result = num1 * (num2 / 100);
                        break;
                    case '/':
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            textField.setText("Error: División por cero");
                            resultDisplayed = true;
                        }
                        break;
                }
                num1 = result;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorGUI::new);
    }
}
