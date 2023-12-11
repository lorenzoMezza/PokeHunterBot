package Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EULA {
    private static String EULA ="By clicking on 'Accept,' you agree with the following End User License Agreement (EULA):\n\n"
            + "Please read this End User License Agreement carefully before using the software.\n\n"
            + "1. Grant of License:\n"
            + "This End User License Agreement (\"EULA\") grants you a limited, non-exclusive, non-transferable license to use the software for personal and non-commercial purposes only. You may not sublicense, rent, lease, or otherwise transfer the software to any third party.\n\n"
            + "2. Restrictions:\n"
            + "You may not use the software to create, develop, or distribute any software or tools intended for cheating, automation, or unauthorized gameplay in online or offline video games that prohibit such activities.\n\n"
            + "3. You may not use the software to violate the terms of service or policies of some other software. The software is not intended to be used for cheating or any actions that may result in legal consequences or breaches of the terms set by other software.\n\n"
            + "4. You may not resell or use the software for commercial purposes without express written permission from the software owner.\n\n"
            + "5. Termination:\n"
            + "This EULA is effective until terminated. You may terminate it at any time by destroying all copies of the software. This EULA will terminate immediately without notice from the software owner if you fail to comply with any provision of this EULA. Upon termination, you must destroy all copies of the software.\n\n"
            + "6. Disclaimer of Warranty:\n"
            + "THE SOFTWARE IS PROVIDED 'AS IS,' WITHOUT ANY WARRANTY. TO THE MAXIMUM EXTENT PERMITTED BY APPLICABLE LAW, THE SOFTWARE OWNER DISCLAIMS ALL WARRANTIES, EITHER EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, AND NONINFRINGEMENT.\n\n"
            + "7. Limitation of Liability:\n"
            + "TO THE MAXIMUM EXTENT PERMITTED BY APPLICABLE LAW, IN NO EVENT SHALL THE SOFTWARE OWNER BE LIABLE FOR ANY SPECIAL, INCIDENTAL, INDIRECT, OR CONSEQUENTIAL DAMAGES WHATSOEVER (INCLUDING, WITHOUT LIMITATION, DAMAGES FOR LOSS OF BUSINESS PROFITS, BUSINESS INTERRUPTION, LOSS OF BUSINESS INFORMATION, OR ANY OTHER PECUNIARY LOSS) ARISING OUT OF THE USE OF OR INABILITY TO USE THE SOFTWARE.\n\n"
            + "8. Disclaimer of Developer's Liability:\n"
            + "The software is provided 'as is' and without any warranty. The developer of the software is not responsible for any misuse, improper use, or any actions taken by users that may result in legal consequences, including but not limited to violations of the terms of service of online video games. The user acknowledges that they are solely responsible for their use of the software, and the developer shall not be held liable for any consequences arising from such use.\n\n"
            + "By installing or using the software, you acknowledge that you have read and understood this EULA, including the disclaimer of the developer's liability, and agree to be bound by its terms.\n\n";

    public static void showEULA(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, "User License Agreement", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(400, 300);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(parentFrame);

        JTextArea termsText = new JTextArea();
        termsText.setLineWrap(true);
        termsText.setWrapStyleWord(true);
        termsText.setEditable(false); // Imposta il JTextArea come non editabile
        JScrollPane scrollPane = new JScrollPane(termsText);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


        termsText.setText(EULA);

        JButton acceptButton = new JButton("Accept");
        JButton rejectButton = new JButton("Reject");

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        rejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(acceptButton);
        buttonPanel.add(rejectButton);

        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
        dialog.setFocusable(false);


    }}

