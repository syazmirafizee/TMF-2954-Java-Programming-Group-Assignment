public class SecretPass {
    // Required UML private fields
    private String inString;
    private int stringLen;
    private String outString;

    // Required UML constructors
    public SecretPass() {
        this.inString = "";
        this.stringLen = 0;
        this.outString = "";
    }

    public SecretPass(String inString) {
        this.inString = inString;
        this.stringLen = getStringLen(inString);
        this.outString = "";
    }

    // --- MEMBER 1 METHODS ---
    public int getStringLen(String inString) {
        // TODO (Member 1): Count characters excluding whitespace (' ')
        int count = 0;
        for (int i = 0; i < inString.length(); i++) {
            if (inString.charAt(i) != ' ') {
                count++;
            }
        }
        return count;
    }

    public Boolean checkStringValidity(String inString) {
        // TODO (Member 1): Return true ONLY if all chars are 'a'-'z', '0'-'9', or ' '
        if (inString == null || inString.isEmpty()) {
            return false;
        }
        for (int i = 0; i < inString.length(); i++) {
            char ch = inString.charAt(i);
            boolean isLower = (ch >= 'a' && ch <= 'z');
            boolean isDigit = (ch >= '0' && ch <= '9');
            boolean isSpace = (ch == ' ');

            if (!isLower && !isDigit && !isSpace) {
                return false;
            }
        }
        return true;
    }

    // --- MEMBER 2 STUBS (Leave empty for Member 2 to implement) ---
    public String reversedString(String inString) {
        return "";
    }

    public String shiftChar(String inString, int stringLen) {
        return "";
    }

    // Optional getters/setters for Member 3's GUI integration
    public String getInString() { return inString; }
    public void setInString(String inString) { 
        this.inString = inString; 
        this.stringLen = getStringLen(inString);
    }
    public int getStringLen() { return stringLen; }
    public String getOutString() { return outString; }
    public void setOutString(String outString) { this.outString = outString; }
}
