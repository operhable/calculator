package proyecto;

		import javax.swing.*;
		import java.awt.*;
		import java.awt.event.ActionEvent;
		import java.awt.event.ActionListener;

		public class CalculatorGIU extends JFrame implements ActionListener {
		    private JTextField textField;
		    private double num1, num2, result;
		    private char operator;

		    public CalculatorGIU() {
		        setTitle("Calculadora");
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        setLayout(new BorderLayout());

		        textField = new JTextField();
		        textField.setEditable(false);
		        add(textField, BorderLayout.NORTH);

		        JPanel panel = new JPanel();
		        panel.setLayout(new GridLayout(4, 4));

		        String[] buttonLabels = {
		            "7", "8", "9", "/",
		            "4", "5", "6", "*",
		            "1", "2", "3", "-",
		            "0", ".", "=", "+"
		        };

		        for (String label : buttonLabels) {
		            JButton button = new JButton(label);
		            button.addActionListener(this);
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
		            textField.setText(textField.getText() + command);
		        } else if (command.matches("[+\\-*/=]")) {
		            if (textField.getText().isEmpty()) {
		                return;
		            }
		            if (command.charAt(0) != '=') {
		                num1 = Double.parseDouble(textField.getText());
		                operator = command.charAt(0);
		                textField.setText("");
		            } else {
		                if (operator == '/' && Double.parseDouble(textField.getText()) == 0) {
		                    textField.setText("Error: División por cero");
		                    return;
		                }
		                num2 = Double.parseDouble(textField.getText());
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
		                    case '/':
		                        result = num1 / num2;
		                        break;
		                }
		                textField.setText(String.valueOf(result));
		            }
		        }
		    }

		    public static void main(String[] args) {
		        SwingUtilities.invokeLater(CalculatorGIU::new);
		    }
		}

