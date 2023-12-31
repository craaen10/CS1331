import java.util.Scanner;
import java.util.InputMismatchException;

public class FahrenheitToCelsiusExceptions {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);  // Define scanner variable

        boolean success = false; // Exception variable
        int fahrenheit = 0; // store int input from user in variable
        while (!success){
            try {
                System.out.print("Enter a Fahrenheit value: "); // Prompt user for Fahrenheit value
                fahrenheit = input.nextInt(); // Cleans up new line at end of user input nothing to read so it waits for data
                success = true;
            }
            catch (InputMismatchException | ArithmeticException e){
                System.out.println("Sorry that wasn't an valid value."); // Print error message
                System.out.println("Please run the program again."); // Print error message
                System.out.println("However, enter a non-zero integer."); // Print error message
            }
            catch (Exception e){
                System.out.println(e.getMessage()); // Print error message
            }
            finally {
                input.nextLine(); // Cleans up new line at end of user input nothing to read so it waits for data
            }
        }

        System.out.print("Enter day of the week, month and year: "); // Prompt user for day of the week
        String day = input.nextLine();
        double celsius = (5.0/9) * (fahrenheit - 32); // Convert to Celsius
        System.out.println(day + " Fahrenheit: " + fahrenheit); // Print values
        System.out.println(day + " Celsius: " + celsius);
        input.close();
    }
}