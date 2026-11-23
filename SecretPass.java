
public class SecretPass {

    // Required UML private fields
    private String inString;
    private int stringLen;
    private String outString;

    // Character table from the assignment
    private static final String CHAR_TABLE =
            "abcdefghij0123456789klmnopqrst !@#$%uvwxyz<>?^&*;:";

    // Default constructor
    public SecretPass() {
        this.inString = "";
        this.stringLen = 0;
        this.outString = "";
    }

    // Parameterized constructor
    public SecretPass(String inString) {
        this.inString = inString;
        this.stringLen = getStringLen(inString);
        this.outString = "";
    }

    // Count characters excluding whitespace
    public int getStringLen(String inString) {

        int count = 0;

        for (int i = 0; i < inString.length(); i++) {

            if (inString.charAt(i) != ' ') {
                count++;
            }
        }

        return count;
    }

    // Check input validity
    // Only uppercase letters, numbers and spaces are allowed
    public Boolean checkStringValidity(String inString) {

        if (inString == null || inString.isEmpty()) {
            return false;
        }

        for (int i = 0; i < inString.length(); i++) {

            char ch = inString.charAt(i);

            boolean isUppercase = (ch >= 'A' && ch <= 'Z');
            boolean isDigit = (ch >= '0' && ch <= '9');
            boolean isSpace = (ch == ' ');

            if (!isUppercase && !isDigit && !isSpace) {
                return false;
            }
        }

        return true;
    }

    // Shift each character forward according to stringLen
    public String shiftChar(String inString, int stringLen) {

        StringBuilder encoded = new StringBuilder();

        for (int i = 0; i < inString.length(); i++) {

            char ch = Character.toLowerCase(inString.charAt(i));

            int index = CHAR_TABLE.indexOf(ch);

            if (index == -1) {
                return "";
            }

            int newIndex = (index + stringLen) % CHAR_TABLE.length();

            char shiftedChar = CHAR_TABLE.charAt(newIndex);

            encoded.append(shiftedChar);
        }

        // Reverse the encoded String
        return reversedString(encoded.toString());
    }

    // Reverse the String
    public String reversedString(String inString) {

        return new StringBuilder(inString).reverse().toString();
    }

    // Getters and setters for GUI integration
    public String getInString() {
        return inString;
    }

    public void setInString(String inString) {
        this.inString = inString;
        this.stringLen = getStringLen(inString);
    }

    public int getStringLen() {
        return stringLen;
    }

    public String getOutString() {
        return outString;
    }

    public void setOutString(String outString) {
        this.outString = outString;
    }
}
