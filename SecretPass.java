
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

        if (inString == null) {
            return 0;
        }

        int count = 0;

        for (int i = 0; i < inString.length(); i++) {

            if (inString.charAt(i) != ' ') {
                count++;
            }
        }

        return count;
    }

    // Check input validity
    // Only lowercase letters, numbers and spaces are allowed
    public Boolean checkStringValidity(String inString) {

        if (inString == null || inString.isEmpty()) {
            return false;
        }

        for (int i = 0; i < inString.length(); i++) {

            char ch = inString.charAt(i);

            boolean isLowercase = (ch >= 'a' && ch <= 'z');
            boolean isDigit = (ch >= '0' && ch <= '9');
            boolean isSpace = (ch == ' ');

            if (!isLowercase && !isDigit && !isSpace) {
                return false;
            }
        }

        return true;
    }

    // Shift each character forward according to stringLen
    public String shiftChar(String inString, int stringLen) {

        if (!checkStringValidity(inString)) {
            return "";
        }

        StringBuilder encoded = new StringBuilder();

        for (int i = 0; i < inString.length(); i++) {

            char ch = inString.charAt(i);

            // Find original character position
            int index = CHAR_TABLE.indexOf(ch);

            // Shift character forward
            int newIndex = (index + stringLen) % CHAR_TABLE.length();

            // Get shifted character
            char shiftedChar = CHAR_TABLE.charAt(newIndex);

            encoded.append(shiftedChar);
        }

        // Reverse the encoded String
        outString = reversedString(encoded.toString());

        return outString;
    }

    // Reverse the String
    public String reversedString(String inString) {

        if (inString == null) {
            return "";
        }

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
