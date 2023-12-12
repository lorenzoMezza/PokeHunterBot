package View;
import Helper.*;
import Logic.BackgroundPanel;
import Logic.WorkFlowManager;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class Main extends JFrame   {
    private boolean isTeleportaEnable = false;
    private boolean isAnticaptchaEnable= true;;
    private boolean isBicicleEnable= false;
    private boolean canIIncreaseEncounter = true;
    private int encounter;
    private JLabel StartButton;
    private JPanel mainPanel;
    private boolean dontMOve = false;
    private JPanel rightpanel;
    private JPanel leftpanel;
    private JPanel bottompannel;
    private JPanel upperpannel;
    private JLabel SelectLocationLabel;
    private JLabel huntingTimeLabel;
    private JComboBox comboBox1;
    private JSlider timeSlider;
    private JButton STARTHUNTINGButton;
    private JButton STOPHUNTINGButton;
    private JCheckBox useAntiCaptchaCheckBox;
    private JCheckBox useTeleportCheckBox;
    private JCheckBox useBicicle;
    private JLabel donationLabel;
    public static boolean freezeMainWindow = false;
    private JLabel NeedHelp;
    private JLabel startingLabel;
    private JLabel stioppinglabel;
    private JLabel PokemonEncouterLabel;
    private static int sleectIndexCombobox1 = -1;
    private String currentSelectLocation= "MEMORIDE (Sinnoh), x5 Psyduck, Sweet Scent hunting";
    public static Main instance;
    private Thread botThread = null;
    private  WorkFlowManager workFlowManager;
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private  UIManager.LookAndFeelInfo[] lookAndFeels = UIManager.getInstalledLookAndFeels();
    public static JFrame mainFrame;
    private String[] locationsOption = {"CELESTIC TOWN (Sinnoh), x5 Psyduck, Sweet Scent hunting","SNOWPOINT CITY (Sinnoh), x5 Snover/Noctowl, Sweet Scent hunting","ETERNA CITY (Sinnoh), x3 Bidoof/Buizel, Sweet Scent hunting","FLOAROMA TOWN (Sinnoh), x3 Shinx/Pachirisu, Sweet Scent hunting","SANDGEM TOWN (Sinnoh), x5 Wingull, Sweet Scent hunting",
            "VERMILION CITY (Kanto), x5 Tentacool, Sweet Scent hunting", "ROUTE 4 (Kanto), x3 Zubat, Sweet Scent hunting", "LAVENDER TOWN 1 (Kanto), x3 Gastly, Sweet Scent hunting"};
    private static int numberOfSeetScentAvaible = 0;
    public static boolean isPathCostumDialOpen = false;
    private String[] harcodedPAth;

    public Main() throws AWTException {
        mainFrame = this;

        harcodedPAth = locationsOption.clone();

        String[] CostumLocation = InstructionSaver.loadInstructions().keySet().toArray(new String[0]);
        String[] arrayMem = {"CREATE CUSTOM PATH..."};
        locationsOption = mergeArrays( locationsOption, CostumLocation );
        locationsOption = mergeArrays( locationsOption, arrayMem );


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
         try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("Logo/BG.png")));

            setLayout(new BorderLayout());

            BackgroundPanel backgroundPanel = new BackgroundPanel(img,mainPanel);
            backgroundPanel.setLayout(new FlowLayout());

            setContentPane(backgroundPanel);

            mainPanel.setOpaque(false);

            backgroundPanel.setLayout(new GridLayout());

            backgroundPanel.add(mainPanel);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        setTitle("PokéHunterBot (BETA VERSION)");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);;
        setSize(820,420);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Toolkit.getDefaultToolkit().beep();
                int choice = JOptionPane.showConfirmDialog(SwingUtilities.getRoot(e.getComponent()), "      Are you sure to quit the application?", "Close Application",  JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (choice == JOptionPane.YES_OPTION) {

                    dispose(); // Chiudi l'applicazione
                    System.exit(0);
                }
            }
        });
        Map<TextAttribute, Object> DonationfontAttributes = new HashMap<>();
        DonationfontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        DonationfontAttributes.put(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);
        DonationfontAttributes.put(TextAttribute.FAMILY, "Consolas");
        DonationfontAttributes.put(TextAttribute.SIZE, 14);
        Color color = new Color(0, 102, 255);
        DonationfontAttributes.put(TextAttribute.FOREGROUND, color   );
        Font font = new Font(DonationfontAttributes);
    donationLabel.setFont(font);

        instance = this;
        for (String location: locationsOption
             ) {
            comboBox1.addItem(location);
        }
        
        comboBox1.setSelectedIndex(-1);
        sleectIndexCombobox1 = -1;
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!dontMOve){
                    System.out.println("action perfomed");
                    currentSelectLocation = (String)comboBox1.getSelectedItem();
                    if(currentSelectLocation == null){
                        return;

                    }

                    if (!currentSelectLocation.equals("CELESTIC TOWN (Sinnoh), x5 Psyduck, Sweet Scent hunting") &&
                            !currentSelectLocation.equals("SNOWPOINT CITY (Sinnoh), x5 Snover/Noctowl, Sweet Scent hunting") &&
                            !currentSelectLocation.equals("ETERNA CITY (Sinnoh), x3 Bidoof/Buizel, Sweet Scent hunting") &&
                            !currentSelectLocation.equals("FLOAROMA TOWN (Sinnoh), x3 Shinx/Pachirisu, Sweet Scent hunting") &&
                            !currentSelectLocation.equals("SANDGEM TOWN (Sinnoh), x5 Wingull, Sweet Scent hunting") &&
                            !currentSelectLocation.equals("VERMILION CITY (Kanto), x5 Tentacool, Sweet Scent hunting") &&
                            !currentSelectLocation.equals("ROUTE 4 (Kanto), x3 Zubat, Sweet Scent hunting") &&
                            !currentSelectLocation.equals("LAVENDER TOWN 1 (Kanto), x3 Gastly, Sweet Scent hunting"))
                    {
                        ManageCheckBoxVisibility();
                        stopbot();
                        CustomPathDIalog customPathDIalog = new CustomPathDIalog(currentSelectLocation);
                        isPathCostumDialOpen = true;
                        setEnabled(false);
                    }
                    else{
                        ManageCheckBoxVisibility();
                        int number = SceentScentEnter.showNumberInputDialog(Main.mainFrame);
                        if(number == -1){// uno costa 5 con 32 n fai 6
                            sleectIndexCombobox1 = -1;
                            comboBox1.setSelectedIndex(-1);
                            ManageCheckBoxVisibility();
                        }
                        else{
                            Main.numberOfSeetScentAvaible = (int)number/5;
                            sleectIndexCombobox1 = comboBox1.getSelectedIndex();


                        }
                    }
                }
            }
        });

        ManageCheckBoxVisibility();

        donationLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Desktop desktop = Desktop.getDesktop();
                StringBuilder builder = new StringBuilder();
                URI uri = null;
                try {
                    desktop.browse(uri);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                builder = null;
                System.gc();
            }
        });
        donationLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Color color = new Color(0, 0, 255);
                DonationfontAttributes.put(TextAttribute.FOREGROUND, color   );
                Font font = new Font(DonationfontAttributes);
                donationLabel.setFont(font);
            }
        });
        donationLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                Color color = new Color(0, 102, 255);
                DonationfontAttributes.put(TextAttribute.FOREGROUND, color   );
                Font font = new Font(DonationfontAttributes);
                donationLabel.setFont(font);
            }
        });

        Map<TextAttribute, Object> HelpfontAttributes = new HashMap<>();
        HelpfontAttributes.put(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);
        HelpfontAttributes.put(TextAttribute.FAMILY, "Consolas");
        HelpfontAttributes.put(TextAttribute.SIZE, 14);

        Color colorHelp = new Color(125, 125, 128);
        HelpfontAttributes.put(TextAttribute.FOREGROUND, colorHelp);

        Font fontHelp = new Font(HelpfontAttributes);
        NeedHelp.setFont(fontHelp);

        NeedHelp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Color hoverColor = new Color(80, 80, 82);
                HelpfontAttributes.put(TextAttribute.FOREGROUND, hoverColor);
                Font hoverFont = new Font(HelpfontAttributes);
                NeedHelp.setFont(hoverFont);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                Color defaultColor = new Color(128, 128, 132);
                HelpfontAttributes.put(TextAttribute.FOREGROUND, defaultColor);
                Font defaultFont = new Font(HelpfontAttributes);
                NeedHelp.setFont(defaultFont);
            }
        });
        NeedHelp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                OpenGuide.open();
            }
        });
        useBicicle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                isBicicleEnable = !isBicicleEnable;
            }
        });
        useAntiCaptchaCheckBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                isAnticaptchaEnable = !isAnticaptchaEnable;
            }
        });
        useTeleportCheckBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                isTeleportaEnable = !isTeleportaEnable;
            }
        });



        useBicicle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                useBicicle.setToolTipText("<html>If you want the bot to use bicycle,<br>set bicycle item as hotkey number 2.</html>");
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                useBicicle.setToolTipText(null); // Rimuovi il tooltip quando il mouse esce
            }
        });

        useAntiCaptchaCheckBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                useAntiCaptchaCheckBox.setToolTipText("<html>Enable to avoid the bot getting <br>caught by the anti-bot system</html>");
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                useAntiCaptchaCheckBox.setToolTipText(null); // Rimuovi il tooltip quando il mouse esce
            }
        });
        
        useTeleportCheckBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                useTeleportCheckBox.setToolTipText("<html>If you want the bot to use teleport,<br>set teleport move as hotkey number 3.</html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                useTeleportCheckBox.setToolTipText(null); // Rimuovi il tooltip quando il mouse esce
            }
        });

        if(FirstLunchVeriyer.isItTheFirstTime()){
            EULA.showEULA(this);
        }
        STOPHUNTINGButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (WorkFlowManager.isRunning) {
                    canIIncreaseEncounter = false;
                    stioppinglabel.setText("Status: Stopped");
                    startingLabel.setText("     ");
                    WorkFlowManager.isRunning = false;
                    workFlowManager = null;
                    botThread.interrupt();
                    botThread = null;

                }
            }
        });

        
        STARTHUNTINGButton.addActionListener(new ActionListener() {
            int selectedIndex = comboBox1.getSelectedIndex();
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Main.sleectIndexCombobox1 != -1){
                    if (!WorkFlowManager.isRunning) {


                        if(botThread != null){
                            botThread.interrupt();
                            botThread = null;
                        }
                        startingLabel.setText("Status: Started");
                        stioppinglabel.setText("     ");
                        if(botThread == null) {
                            try {
                                workFlowManager = new WorkFlowManager();
                                WorkFlowManager.isRunning = true;
                            } catch (AWTException ex) {
                                throw new RuntimeException(ex);
                            }
                            botThread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    workFlowManager.Start(numberOfSeetScentAvaible);

                                }
                            });
                            canIIncreaseEncounter = true;
                            botThread.start();
                        }}
                }
                else{
                    JOptionPane.showMessageDialog(Main.mainFrame, "You must Select a Location first!", "Alert", JOptionPane.WARNING_MESSAGE);

                }

            }
        });
        
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        if(screenRect.width != 1920 ||  screenRect.height != 1080  ){
            JOptionPane.showMessageDialog(Main.mainFrame, "Your screen resolution must be set to 1920*1080 and scale to 100% \n\n--You need to change your operative system resolution parameter--", "Alert", JOptionPane.WARNING_MESSAGE);
            System.exit(0);

        }
    }


   public void ManageCheckBoxVisibility(){
        useAntiCaptchaCheckBox.setVisible(true);
        useTeleportCheckBox.setVisible(true);
        useBicicle.setVisible(true);
       
        if(comboBox1.getSelectedItem() == null){
            useBicicle.setEnabled(false);
            useTeleportCheckBox.setEnabled(false);
            useAntiCaptchaCheckBox.setEnabled(false);
            useAntiCaptchaCheckBox.setEnabled(false);
        }
        else{
            if(currentSelectLocation.equals("CELESTIC TOWN (Sinnoh), x5 Psyduck, Sweet Scent hunting" )  || currentSelectLocation.equals("ETERNA CITY (Sinnoh), x3 Bidoof/Buizel, Sweet Scent hunting")  || currentSelectLocation.equals("FLOAROMA TOWN (Sinnoh), x3 Shinx/Pachirisu, Sweet Scent hunting") || currentSelectLocation.equals( "SANDGEM TOWN (Sinnoh), x5 Wingull, Sweet Scent hunting" )){
                useAntiCaptchaCheckBox.setEnabled(true);
                useAntiCaptchaCheckBox.setVisible(true);
                useAntiCaptchaCheckBox.setSelected(true);
                useTeleportCheckBox.setEnabled(true);
                useTeleportCheckBox.setVisible(true);
                useBicicle.setEnabled(true);
                useBicicle.setVisible(true);
            }
       if(currentSelectLocation.equals("SNOWPOINT CITY (Sinnoh), x5 Snover/Noctowl, Sweet Scent hunting") || currentSelectLocation.equals( "VERMILION CITY (Kanto), x5 Tentacool, Sweet Scent hunting") || currentSelectLocation.equals("ROUTE 4 (Kanto), x3 Zubat, Sweet Scent hunting")   || currentSelectLocation.equals( "LAVENDER TOWN 1 (Kanto), x3 Gastly, Sweet Scent hunting")  ){
                isBicicleEnable = false;
                useAntiCaptchaCheckBox.setEnabled(true);
                useAntiCaptchaCheckBox.setVisible(true);
                useAntiCaptchaCheckBox.setSelected(true);
                useTeleportCheckBox.setEnabled(true);
                useTeleportCheckBox.setVisible(true);

                useBicicle.setEnabled(false);
                useBicicle.setSelected(false);
            }

            if(currentSelectLocation.equals("CREATE CUSTOM PATH..." )){
                useBicicle.setEnabled(false);
                useTeleportCheckBox.setEnabled(false);
                useAntiCaptchaCheckBox.setEnabled(false);
                useAntiCaptchaCheckBox.setEnabled(false);
            }
            else{
                if (!currentSelectLocation.equals("CELESTIC TOWN (Sinnoh), x5 Psyduck, Sweet Scent hunting") &&
                        !currentSelectLocation.equals("SNOWPOINT CITY (Sinnoh), x5 Snover/Noctowl, Sweet Scent hunting") &&
                        !currentSelectLocation.equals("ETERNA CITY (Sinnoh), x3 Bidoof/Buizel, Sweet Scent hunting") &&
                        !currentSelectLocation.equals("FLOAROMA TOWN (Sinnoh), x3 Shinx/Pachirisu, Sweet Scent hunting") &&
                        !currentSelectLocation.equals("SANDGEM TOWN (Sinnoh), x5 Wingull, Sweet Scent hunting") &&
                        !currentSelectLocation.equals("VERMILION CITY (Kanto), x5 Tentacool, Sweet Scent hunting") &&
                        !currentSelectLocation.equals("ROUTE 4 (Kanto), x3 Zubat, Sweet Scent hunting") &&
                        !currentSelectLocation.equals("LAVENDER TOWN 1 (Kanto), x3 Gastly, Sweet Scent hunting"))
                {

                    useBicicle.setEnabled(false);
                    useTeleportCheckBox.setEnabled(false);
                    useAntiCaptchaCheckBox.setEnabled(false);
                    useAntiCaptchaCheckBox.setEnabled(false);
                }
            }
        }
    }
    public static Main GetInstance(){
        return instance;
    }

    public String getSelectedLocation(){
        return (String)comboBox1.getSelectedItem();
    }

    public int getSliderValue(){
        return timeSlider.getValue();
    }

    public boolean isAnticapthaEnable(){
        return useAntiCaptchaCheckBox.isSelected();
    }
    
    public boolean isTeleportaEnable(){
        return useTeleportCheckBox.isSelected();
    }
    
    public boolean isBicicleEnable(){
        return useBicicle.isSelected();
    }
    
    public static void main(String[] args) throws AWTException {
        new Main();
    }

    public  void SetTimedOut(){
        startingLabel.setText("Status: TimedOut");
        stioppinglabel.setText("     ");
    }
    
    public void changeLabelsAlertAntiTrade() {
        Toolkit.getDefaultToolkit().beep();
        startingLabel.setText("     ");
        stioppinglabel.setText("Status: Forced Stopped");
        JOptionPane.showMessageDialog(this, "                    The game has been closed due to a trade request received.\nThis action was taken to prevent bot from getting stuck and suspected by other player.", "Alert", JOptionPane.WARNING_MESSAGE);
        WorkFlowManager.isRunning = false;
        workFlowManager = null;
        botThread.interrupt();
        botThread = null;
    }
    
    public void increaseEncounterLabel(int numberToSum){
        if(canIIncreaseEncounter){
            encounter += numberToSum;
            PokemonEncouterLabel.setText("Total Pokemons Encoutered: "+ encounter);
        }
    }

    private  String[] mergeArrays(String[] array1, String[] array2) {
        // Calcola la lunghezza del nuovo array
        int length = array1.length + array2.length;

        // Crea un nuovo array con la lunghezza combinata
        String[] result = new String[length];

        // Copia gli elementi di array1 in result
        System.arraycopy(array1, 0, result, 0, array1.length);

        // Copia gli elementi di array2 in result, iniziando dalla fine di array1
        System.arraycopy(array2, 0, result, array1.length, array2.length);

        return result;
    }
    
    private static String[] reduceArrayLength(String[] array, int newLength) {
        // Verifica se la nuova lunghezza è valida
        if (newLength < 0 || newLength > array.length) {
            throw new IllegalArgumentException("Nuova lunghezza non valida");
        }

        // Crea un nuovo array con la lunghezza desiderata
        String[] newArray = new String[newLength];

        // Copia gli elementi rilevanti nell'array risultante
        System.arraycopy(array, 0, newArray, 0, newLength);

        return newArray;
    }

    public void reloadComboboxArray(){
        dontMOve = true;

        String[] CostumLocation = InstructionSaver.loadInstructions().keySet().toArray(new String[0]);
        String[] arrayMem = {"CREATE CUSTOM PATH..."};
        locationsOption = mergeArrays( harcodedPAth, CostumLocation );
        locationsOption = mergeArrays( locationsOption, arrayMem );
        comboBox1.removeAllItems();
        for (String location: locationsOption
        ) {
            comboBox1.addItem(location);
        }
        comboBox1.setSelectedIndex(-1);
        dontMOve = false;
    }
    
    public static void setEnabledRecursively(Container container, boolean enabled) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof Container) {
                setEnabledRecursively((Container) component, enabled);
            }
            component.setEnabled(enabled);
        }
    }

    private void setDisabledRecursively(Container container) {
        setEnabledRecursively(container, false);
    }

    public void stopbot(){

        if (WorkFlowManager.isRunning) {
            canIIncreaseEncounter = false;
            stioppinglabel.setText("Status: Forced Stop");
            startingLabel.setText("     ");
            WorkFlowManager.isRunning = false;
            workFlowManager = null;
            botThread.interrupt();
            botThread = null;

        }
    }
}
