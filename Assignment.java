import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * TMF2954 Group Assignment - Member 3: Java Swing GUI and main program.
 *
 * This file depends on the team's SecretPass.java (Members 1 and 2).
 * Keep both source files in the same folder with no package declarations.
 */
public class Assignment extends JFrame {
    private static final Color BACKGROUND = new Color(245, 247, 251);
    private static final Color NAVY = new Color(34, 48, 76);
    private static final Color BLUE = new Color(38, 96, 181);
    private static final Color MUTED = new Color(90, 104, 125);
    private static final Color SUCCESS = new Color(30, 120, 72);
    private static final Color ERROR = new Color(186, 53, 53);

    private final JTextField inputField = new JTextField(28);
    private final JLabel lengthValue = new JLabel("—");
    private final JTextField shiftedField = new JTextField();
    private final JTextField secretPassField = new JTextField();
    private final JLabel statusLabel = new JLabel("Enter text and click Encode.");

    public Assignment() {
        super("SecretPass Encoder | TMF2954");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(640, 480));
        setSize(740, 530);
        setLocationRelativeTo(null);
        buildInterface();
    }

    private void buildInterface() {
        JPanel root = new JPanel(new BorderLayout(0, 18));
        root.setBackground(BACKGROUND);
        root.setBorder(BorderFactory.createEmptyBorder(24, 30, 24, 30));
        setContentPane(root);

        JPanel heading = new JPanel(new BorderLayout(0, 6));
        heading.setOpaque(false);
        JLabel titleLabel = new JLabel("SecretPass Encoder");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 27));
        titleLabel.setForeground(NAVY);
        JLabel subtitleLabel = new JLabel("Java Swing application  |  TMF2954 Group Assignment");
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitleLabel.setForeground(MUTED);
        heading.add(titleLabel, BorderLayout.NORTH);
        heading.add(subtitleLabel, BorderLayout.SOUTH);
        root.add(heading, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(223, 230, 240)),
                BorderFactory.createEmptyBorder(18, 22, 18, 22)));
        root.add(form, BorderLayout.CENTER);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(0, 0, 7, 0);
        form.add(makeLabel("Input string"), c);

        c.gridy++;
        c.insets = new Insets(0, 0, 5, 0);
        inputField.setFont(new Font("Monospaced", Font.PLAIN, 17));
        inputField.setPreferredSize(new Dimension(200, 40));
        inputField.setToolTipText("Example: zoo kch (use lowercase letters only)");
        form.add(inputField, c);

        c.gridy++;
        c.insets = new Insets(0, 0, 13, 0);
        JLabel hint = new JLabel("Allowed: lowercase a–z, digits 0–9 and whitespace. Spaces matter.");
        hint.setFont(new Font("SansSerif", Font.PLAIN, 12));
        hint.setForeground(MUTED);
        form.add(hint, c);

        c.gridy++;
        c.insets = new Insets(0, 0, 16, 0);
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        controls.setOpaque(false);
        JButton encodeButton = new JButton("Encode");
        encodeButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        encodeButton.setForeground(Color.WHITE);
        encodeButton.setBackground(BLUE);
        encodeButton.setOpaque(true);
        encodeButton.setFocusPainted(false);
        encodeButton.setPreferredSize(new Dimension(110, 36));
        encodeButton.addActionListener(this::encodeInput);

        JButton clearButton = new JButton("Clear");
        clearButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        clearButton.setFocusPainted(false);
        clearButton.setPreferredSize(new Dimension(90, 36));
        clearButton.addActionListener(e -> clearForm());
        controls.add(encodeButton);
        controls.add(clearButton);
        form.add(controls, c);
        getRootPane().setDefaultButton(encodeButton);

        c.gridy++;
        c.insets = new Insets(0, 0, 13, 0);
        JPanel countPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        countPanel.setOpaque(false);
        countPanel.add(makeLabel("String length (excluding whitespace):"));
        lengthValue.setFont(new Font("SansSerif", Font.BOLD, 17));
        lengthValue.setForeground(BLUE);
        countPanel.add(lengthValue);
        form.add(countPanel, c);

        c.gridy++;
        c.insets = new Insets(0, 0, 6, 0);
        form.add(makeLabel("Shifted characters (before reversing)"), c);
        c.gridy++;
        c.insets = new Insets(0, 0, 14, 0);
        prepareOutputField(shiftedField);
        form.add(shiftedField, c);

        c.gridy++;
        c.insets = new Insets(0, 0, 6, 0);
        form.add(makeLabel("Final SecretPass (reversed string)"), c);
        c.gridy++;
        c.insets = new Insets(0, 0, 10, 0);
        prepareOutputField(secretPassField);
        form.add(secretPassField, c);

        c.gridy++;
        c.insets = new Insets(0, 0, 0, 0);
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        statusLabel.setForeground(MUTED);
        form.add(statusLabel, c);

        JLabel footer = new JLabel("Tip: Press Enter to encode. Copy results directly from the output fields.");
        footer.setFont(new Font("SansSerif", Font.PLAIN, 12));
        footer.setForeground(MUTED);
        root.add(footer, BorderLayout.SOUTH);
        inputField.requestFocusInWindow();
    }

    private static JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setForeground(NAVY);
        return label;
    }

    private static void prepareOutputField(JTextField field) {
        field.setEditable(false);
        field.setFont(new Font("Monospaced", Font.PLAIN, 16));
        field.setBackground(new Color(248, 250, 254));
        field.setPreferredSize(new Dimension(200, 37));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(219, 226, 237)),
                BorderFactory.createEmptyBorder(6, 9, 6, 9)));
    }

    private void encodeInput(ActionEvent event) {
        // Never trim: whitespace is included in the encoding table, so it matters.
        String input = inputField.getText();
        shiftedField.setText("");
        secretPassField.setText("");

        if (input.isEmpty()) {
            lengthValue.setText("—");
            showError("Please enter a string before encoding.");
            inputField.requestFocusInWindow();
            return;
        }

        try {
            // Calls the methods implemented by Members 1 and 2.
            SecretPass secretPass = new SecretPass(input);
            // Step 2 of the assignment: validate BEFORE counting or encoding.
            // If invalid, prompt the user to enter a new input.
            if (!secretPass.checkStringValidity(input)) {
                lengthValue.setText("—");
                shiftedField.setText("Invalid");
                secretPassField.setText("Invalid");
                String message = "Invalid input. Please enter a NEW string using only "
                        + "lowercase letters (a-z), digits (0-9), or whitespace.";
                showError("Invalid input. Please correct the string and try again.");
                JOptionPane.showMessageDialog(this, message,
                        "Invalid SecretPass Input", JOptionPane.ERROR_MESSAGE);
                inputField.requestFocusInWindow();
                inputField.selectAll(); // Replace invalid text by typing a new string.
                return;
            }

            // Step 3: only compute length once the input passes validation.
            int length = secretPass.getStringLen(input);
            lengthValue.setText(String.valueOf(length));

            // Member 2 should ALSO call checkStringValidity() inside shiftChar().
            // Per the assignment, shiftChar() already returns the REVERSED SecretPass.
            String finalResult = secretPass.shiftChar(input, length);
            // Reverse again only for showing the intermediate shifted text in the GUI.
            String shifted = secretPass.reversedString(finalResult);
            shiftedField.setText(shifted);
            secretPassField.setText(finalResult);
            shiftedField.setCaretPosition(0);
            secretPassField.setCaretPosition(0);
            statusLabel.setForeground(SUCCESS);
            statusLabel.setText("Encoding completed successfully.");
        } catch (RuntimeException ex) {
            lengthValue.setText("—");
            shiftedField.setText("");
            secretPassField.setText("");
            showError("Encoding failed. Check the SecretPass methods and try again.");
            ex.printStackTrace(); // Useful when integrating teammates' code.
        }
    }

    private void showError(String message) {
        statusLabel.setForeground(ERROR);
        statusLabel.setText(message);
    }

    private void clearForm() {
        inputField.setText("");
        lengthValue.setText("—");
        shiftedField.setText("");
        secretPassField.setText("");
        statusLabel.setForeground(MUTED);
        statusLabel.setText("Enter text and click Encode.");
        inputField.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
                // The normal Swing appearance will still work.
            }
            new Assignment().setVisible(true);
        });
    }
}
