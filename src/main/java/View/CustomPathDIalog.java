package View;

import Helper.InstructionParser;
import Helper.InstructionSaver;
import Helper.OpenGuide;
import WriteYourOwnPath.PathRunningManager;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class CustomPathDIalog extends JFrame {

    public static CustomPathDIalog instance;
    private JButton stopButton;

    private static Thread  botThread;

    public static  Thread CorecctorThread;
    public static boolean IsCorrectorRunning = false;
    private JPanel panel1;
    private JTextField textField2;

    private boolean IsAlreadyExistent = false;
    private JButton saveButton;
    private JButton startButton;
    private JTextArea textArea1;
    private JLabel status;
    private JSlider slider1;
    private JButton deleteButton;
    private JLabel needHelp;

    private PathRunningManager pathRunningManager;

    public static boolean IsBotRunning = false;

    public String SelectID;

    private Boolean isSaved = false;

    public  CustomPathDIalog(String PATHID){
        SelectID = PATHID;
        main();

    }

    public void main(){
        instance = this;
        if(!SelectID.equals("CREATE CUSTOM PATH...") ){
            textField2.setEditable(false);
            IsAlreadyExistent = true;
        }

        if( SelectID != null && !SelectID.equals("CREATE CUSTOM PATH...")  ){

            setTitle("Custom Path Creator "+ "-" + SelectID );
            loadWinosWithInfo(SelectID);

        }else {
            setTitle("Custom Path Creator ");
        }


        InputStream iconStream = getClass().getClassLoader().getResourceAsStream("Logo/logo.png");
        if (iconStream != null) {
            try {
                BufferedImage iconImage = ImageIO.read(iconStream);
                if (iconImage != null) {
                    this.setIconImage(iconImage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);;
        setSize(550,450);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        add(panel1);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(!isSaved){
                    Toolkit.getDefaultToolkit().beep();
                    int choice = JOptionPane.showConfirmDialog(SwingUtilities.getRoot(e.getComponent()), "      Are you sure to quit without savaing?", "Close Path Editor",  JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (choice == JOptionPane.YES_OPTION) {
                        Main.isPathCostumDialOpen = false;
                        Main.instance.setEnabled(true);
                        stop();
                        dispose(); // Chiudi l'applicazione

                    }

                }
                else{
                    Main.instance.setEnabled(true);
                    Main.isPathCostumDialOpen = false;
                    stop();
                    dispose(); // Chiudi l'applicazione
                }

            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField2.getText().length() == 0 || textField2.getText().trim().isEmpty()){
                    status.setForeground(Color.red);
                    status.setText("|Status| Error path not saved: unique Path Title must be set" );
                    return;
                }
                isSaved = true;
                String isValidParsing  = InstructionParser.parseInstructions(textArea1.getText());

                if(isValidParsing == null){



                    Map previousInstruction =  InstructionSaver.loadInstructions();
                    String finaltitle = "";
                    String title = textField2.getText();
                    if(title.length() > 60){
                        String shrinkrtTigle = title.substring(0, 60);
                        String instruction = textArea1.getText() + "$" + slider1.getValue();
                        finaltitle = shrinkrtTigle;
                        previousInstruction.put(shrinkrtTigle,instruction);
                    }
                    else{
                        String instruction = textArea1.getText() + "$" + slider1.getValue();
                        previousInstruction.put(textField2.getText(), instruction);
                        finaltitle = textField2.getText();
                    }


                    InstructionSaver.saveInstructions(previousInstruction);
                    status.setForeground(Color.green);
                    Main.instance.reloadComboboxArray();
                    status.setText("|Status| Saved successfully" );
                    SelectID  = finaltitle;

                }
                else{
                    if (isValidParsing.length() > 58) {
                        String ERRORLOG = isValidParsing.substring(0, 58) + "...";
                        status.setForeground(Color.red);
                        status.setText("|Status| Not saved because " + ERRORLOG );
                    } else {
                        status.setForeground(Color.red);
                        status.setText("|Status| Not saved because " + isValidParsing );
                    }
                    if(isValidParsing.equals("Warning: Unsaved beacuse Path is not defined")){
                        status.setForeground(Color.orange);
                    }

                }



            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!IsBotRunning) {
                    if(textField2.getText().length() == 0 || textField2.getText().trim().isEmpty()){
                        status.setForeground(Color.red);
                        status.setText("|Status| Can't start because: unique Path Title must be set" );
                        return;
                    }
                    String isValidParsing  = InstructionParser.parseInstructions(textArea1.getText());
                    if(isValidParsing == null){

                        String instructions = textArea1.getText();
                        System.out.println(instructions);
                        String timerValue = getSubstringAfterSymbol(instructions,"$");
                        System.out.println(timerValue);
                        int timerValueint = slider1.getValue();


                        instructions =  getSubstringBeforeSymbol(instructions,"$");
                        System.out.println( instructions  );

                        String[] instructionArray = InstructionParser.transformString(instructions);
                        IsBotRunning = true;
                        botThread = new Thread(() -> {
                            while(IsBotRunning){
                                try {
                                    pathRunningManager = new PathRunningManager(instructionArray,timerValueint);
                                } catch (AWTException ex) {
                                    throw new RuntimeException(ex);
                                }
                                try {
                                    pathRunningManager.runbot();
                                } catch (TesseractException ex) {
                                    throw new RuntimeException(ex);
                                } catch (InterruptedException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        });
                        botThread.start();

                        status.setForeground(Color.green);
                        status.setText("|Status| Started successfully" );
                    }
                    else{
                        if (isValidParsing.length() > 58) {
                            String ERRORLOG = isValidParsing.substring(0, 58) + "...";
                            status.setForeground(Color.red);
                            status.setText("|Status| Can't start because " + ERRORLOG );
                        } else {
                            status.setForeground(Color.red);
                            status.setText("|Status| Can't start because " + isValidParsing );
                        }
                        if(isValidParsing.equals("Warning: Can't start beacuse Path is not defined")){
                            status.setForeground(Color.orange);
                        }

                    }





                }
                else{
                    status.setForeground(Color.orange);
                    status.setText("|Status| Bot is already running");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Toolkit.getDefaultToolkit().beep();
                System.out.println(IsAlreadyExistent);
                if(!isSaved &&  !IsAlreadyExistent){
                    JOptionPane.showOptionDialog(
                            SwingUtilities.getRoot(CustomPathDIalog.instance),
                            "     Path isn't saved, so it can't be deleted",
                            "Message",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            new Object[]{"OK"},
                            "OK"
                    );
                }
                else{
                    int choice = JOptionPane.showConfirmDialog(SwingUtilities.getRoot(CustomPathDIalog.instance), "   Are you sure to Delete forever this path?", "Delete Path",  JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (choice == JOptionPane.YES_OPTION) {
                        if( SelectID != null && !SelectID.equals("CREATE CUSTOM PATH...") ){
                            InstructionSaver.deleteInstruction(SelectID);
                            quitPage();
                        }
                        quitPage();

                    }

                }

        }
    });
        Map<TextAttribute, Object> HelpfontAttributes = new HashMap<>();
        HelpfontAttributes.put(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);
        HelpfontAttributes.put(TextAttribute.FAMILY, "Consolas");
        HelpfontAttributes.put(TextAttribute.SIZE, 14);

        Color colorHelp = new Color(120, 120, 125);
        HelpfontAttributes.put(TextAttribute.FOREGROUND, colorHelp);

        Font fontHelp = new Font(HelpfontAttributes);
        needHelp.setFont(fontHelp);


        needHelp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Color hoverColor = new Color(80, 80, 82);
                HelpfontAttributes.put(TextAttribute.FOREGROUND, hoverColor);
                Font hoverFont = new Font(HelpfontAttributes);
                needHelp.setFont(hoverFont);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                Color defaultColor = new Color(120, 120, 125);
                HelpfontAttributes.put(TextAttribute.FOREGROUND, defaultColor);
                Font defaultFont = new Font(HelpfontAttributes);
                needHelp.setFont(defaultFont);
            }
        });

        needHelp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                OpenGuide.openCustomLocationGuide();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IsBotRunning = false;
                if(botThread != null && botThread.isAlive()){
                    botThread.interrupt();
                    status.setForeground(Color.orange);
                    status.setText("|Status| Stopped");
                }
                else{
                    status.setForeground(Color.orange);
                    status.setText("|Status| Already Stopped");
                }




            }
        });




        IsCorrectorRunning = true;
        CorecctorThread = new Thread(() -> {
            while(IsCorrectorRunning){
                CorettorLoop();
            }
        });
        CorecctorThread.start();







    }

    private void stop(){
        IsBotRunning = false;
        if( botThread != null && botThread.isAlive()){
            botThread.interrupt();
            status.setForeground(Color.orange);
            status.setText("|Status| Stopped");
        }
        else{
            status.setForeground(Color.orange);
            status.setText("|Status| Already Stopped");
        }




    }

    private void CorettorLoop(){
        String isValidParsing2  = InstructionParser.parseInstructions(textArea1.getText());

        if(isValidParsing2 == null){
            textArea1.setForeground(Color.black);
        }
        else{
            textArea1.setForeground(Color.red);
        }

    }
    public static String getSubstringAfterSymbol(String input, String symbol) {
        int index = input.indexOf(symbol);

        // Verifica se il simbolo è presente nella stringa
        if (index != -1) {
            // Estrai la parte della stringa che segue il simbolo
            System.out.println(input.substring(index + 1));
            return input.substring(index + 1);
        } else {
            // Se il simbolo non è presente, restituisci la stringa originale
            System.out.println(input.substring(index + 1));
            return input;

        }
    }


    private void quitPage(){

                Main.isPathCostumDialOpen = false;
                Main.instance.setEnabled(true);
                stop();
                System.out.println("wuittews");
        Main.instance.reloadComboboxArray();
        CustomPathDIalog.instance.dispose(); // Chiudi l'applicazione


    }

    public void TimeOut(){
        IsBotRunning = false;
        botThread.interrupt();

        status.setForeground(Color.orange);
        status.setText("|Status| Bot TimedOut" );
    }
    public static String getSubstringBeforeSymbol(String input, String symbol) {
        int index = input.indexOf(symbol);

        // Verifica se il simbolo è presente nella stringa
        if (index != -1) {
            // Estrai la parte della stringa che precede il simbolo
            System.out.println(input.substring(0, index));
            return input.substring(0, index);
        } else {
            // Se il simbolo non è presente, restituisci la stringa originale
            System.out.println(input + "d");
            return input;
        }
    }

    private void loadWinosWithInfo(String id){
         Map Instrucitons =   InstructionSaver.loadInstructions();
         String instructionTitle = id;

        textField2.setText(id);

        String instructionDefinedPath = (String)Instrucitons.get(id);

        textArea1.setText( getSubstringBeforeSymbol(instructionDefinedPath,"$") );

        slider1.setValue(Integer.parseInt(getSubstringAfterSymbol(instructionDefinedPath,"$")));


    }
}
