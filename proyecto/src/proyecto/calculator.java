package proyecto;

	import java.util.Scanner;

	public class calculator {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        double num1, num2, result;
	        char operator;

	        System.out.println("Ingrese el primer número: ");
	        num1 = scanner.nextDouble();

	        System.out.println("Ingrese el operador (+, -, *, /): ");
	        operator = scanner.next().charAt(0);

	        System.out.println("Ingrese el segundo número: ");
	        num2 = scanner.nextDouble();

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
	                if (num2 != 0) {
	                    result = num1 / num2;
	                } else {
	                    System.out.println("No se puede dividir por cero.");
	                    return;
	                }
	                break;
	            default:
	                System.out.println("Operador inválido.");
	                return;
	        }

	        System.out.println("El resultado es: " + result);
	    }
	}
