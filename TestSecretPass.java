
import java.util.Scanner;

public class TestSecretPass {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        SecretPass sp = new SecretPass();

        String input;

        System.out.println("================================");
        System.out.println("       SECRETPASS PROGRAM");
        System.out.println("================================");

        // Ask user to enter input
        while (true) {

            System.out.print("\nEnter your text: ");
            input = scanner.nextLine();

            // Check input validity
            if (sp.checkStringValidity(input)) {
                break;
            } else {
                System.out.println("Invalid input!");
                System.out.println("Please enter lowercase letters (a-z),");
                System.out.println("numbers (0-9), or spaces only.");
            }
        }

        // Set input into SecretPass object
        sp.setInString(input);

        // Calculate String length excluding spaces
        int length = sp.getStringLen(input);

        // Perform character shifting and reversing
        String result = sp.shiftChar(input, length);

        // Store output
        sp.setOutString(result);

        // Display results
        System.out.println("\n================================");
        System.out.println("          RESULTS");
        System.out.println("================================");

        System.out.println("Original Input : " + sp.getInString());
        System.out.println("String Length  : " + length);
        System.out.println("SecretPass     : " + sp.getOutString());

        System.out.println("================================");

        scanner.close();
    }
}
