
public class TestSecretPass {

    public static void main(String[] args) {

        SecretPass sp = new SecretPass();

        String input = "DONT CHEAT";

        if (sp.checkStringValidity(input)) {

            int length = sp.getStringLen(input);

            String result = sp.shiftChar(input, length);

            System.out.println("Original Input: " + input);
            System.out.println("String Length: " + length);
            System.out.println("SecretPass: " + result);

        } else {

            System.out.println("Invalid Input!");
        }
    }
}
