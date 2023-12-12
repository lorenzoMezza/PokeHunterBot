package Helper;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class SceentScentEnter {
    private static  int returnValue = -1;


    public static int showNumberInputDialog(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, "Sweet Scent move number", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(320, 180);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setResizable(false);


        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("<html>Number of Available Sweet Scent moves<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(PP 0 to 32):</html>");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Imposta l'allineamento orizzontale al centro
        titlePanel.add(titleLabel);




        JTextField numberField = new JTextField(6); // Imposta una larghezza minore
        numberField.setDocument(new NumberDocument());
        numberField.setHorizontalAlignment(JTextField.CENTER); // Centra il testo nel campo di input

  
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = numberField.getText();
                try {
                    int numericValue = Integer.parseInt(inputText);
                    if (numericValue >= 0 && numericValue <= 32) {
                        SceentScentEnter.returnValue = numericValue;

                        dialog.dispose();
                    }
                } catch (NumberFormatException ex) {
                    dialog.dispose();
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

     
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(numberField, constraints);


        dialog.add(titlePanel, BorderLayout.NORTH);
        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
        dialog.setFocusable(false);
        int toRet = returnValue;
        returnValue = -1;
        return toRet;

    }

    private static class NumberDocument extends PlainDocument {
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (str == null) {
                return;
            }
            String newValue = getText(0, getLength()) + str;
            if (newValue.matches("^[0-9]{0,2}$")) {
                if (Integer.parseInt(newValue) >= 0 && Integer.parseInt(newValue) <= 32) {
                    super.insertString(offs, str, a);
                }
            }
        }
    }
}
