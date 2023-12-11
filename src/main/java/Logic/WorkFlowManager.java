package Logic;

import View.Main;


import java.awt.*;

import java.util.Random;

public class WorkFlowManager {
    // usare up down ecc ecc fa click per clik , usere up 10  o down 20 fa click per un toto di tempo e usare solo 10 o 29 o 39 fa una pausa.sleep tutto in millisecondi!
    // e usare solo un numero fa una pausa di tot millisec
    Random random = new Random();

    public static boolean isRunning = false;
    private boolean isFirstHunt = true;

    private int numberOfSweetScent = 0;

    private int numberOFPokemonPerEnc = -1;
    bot botInstance;
    boolean isAntiCaptchaEnabled = false;
    boolean isBicileEnable = false;

    boolean isTeleportEnable = false;


    Waiter waiter;


    private final String[] SWEET_CATHCING_MEMORIDE =  {"sweet","13000","scan","escape","2800"};

    private final String[] SWEET_CATHCING_FLOAROMATOWN =  {"sweet","13000","scan","escapeWithControl","2800"};
    private final String[] MEMORIDE_PATH_NORMAL = {"z 10500", "x","down 1700","1440","left",
            "left 1780","z 4000","sweetcatch","right 2000","up","up","1750","up 1160"};
    private final String[] MEMORIDE_PATH_BICICLE = {"z 10500","x","down 2000","1440","2","left 925",
          "z 4000","sweetcatch","right 980","up","up","2150","up 1160"};
    private final String[] MEMORIDE_PATH_TELEPORT = {"z 9700","x","down 1500","1400","left",
            "left 1758","z 4060","sweetcatch","3","3755"};

    private final String[] MEMORIDE_TELEPORT_BICICLE_PATH = {"z 10500","down 2000","1440","2","left 925",
            "z 4000","sweetcatch","3","3700"};
    private final String[] MEMORIDE_ATICHEATPATH = {"z 10500","down 2000","1520","right 900", "up 1425", "right 3050", "left 3170","down 1425",
            "left 2700","z 4000","sweetcatch","right 2000","up","up","up","1500","up 1500"};

    private final String[] NEVEPOLI_PATH_TELEPORT = {"z 10500","x", "down 1520","1440","left 1040"
            ,"up 1550","left 1350", "down 2720","left 2600", "up 585", "left 3410","sweetcatch","3","4000"};
    private final String[] NEVEPOLI_PATH_NORMAL = {"z 10500","x", "down 1520","1440","left 1040"
         ,"up 1550","left 1350", "down 2720","left 2600", "up 585", "left 3410","sweetcatch","x","right 3550","down 550","right 2600","up 2720", "right 1500",
        "down 1550","right 700","up 500", "1400","up 1350"};
    private final String[] NEVEPOLI_PATH_AntiCheat = {"z 10500","x", "down 1520","1440","left 1040"
            ,"up 1550","left 150", "up 2000","down 2200", "right 350",
            "down 1550","right 690","up 500", "1400","up 1350"};

    private final String[] SWEET_CATHCING_NEVEPOLI =  {"sweet","15000","scan","escape","2800"};
    private final String[] ETERNACITY_TELEPORT_PATH = {"z 10500","x", "down 1520","1450", "down","left 4200", "up", "up", "sweetcatch" ,"3","4000"};
    private final String[] ETERNACITY_NORMAL_PATH = {"z 10500","x", "down 1520","1450", "down","left 4200", "up", "up", "sweetcatch" ,"down","down", "right 3400", "up","right 910","up","1400","up 1350"};

    private final String[] ETERNACITY_BICICLE_PATH = {"z 10500","x", "down 1520","1450","down","2","left 2000","2", "up", "up", "sweetcatch" ,"down","down","2", "right 1700","2", "up","right 615","up","1400","up 1350"};
    private final String[] ETERNACITY_ANTICHEAT_PATH = {"x", "down 1520","1450", "down 5000", "up 5550", "1400","up 1350"};

    private final String[]  ETERNACITY_TELEPORT_BICICLE_PATH = {"z 10500","x", "down 1600","1450","down","2","left 2000","2", "up", "up","sweetcatch" ,"3","4000"};

    private final String[]  FLOAROMATOWN_NORMAL_PATH = {"z 10500","x","down 1520","1450","right 930","up 1040","right 7810","down","right 1070","up","up","sweetcatch","down","down","left 7435","down","down","down","left 400","down","down","down","left 1980","up 340","1400","up 1350"};
    private final String[]   FLOAROMATOWN_BICICLE_PATH ={"z 10500","x","down 1520","1450", "right 930","up 1040","2","right 3600","2","down","right 1070","up","up","sweetcatch","down","down","2", "left 3450", "2","down","down","down","left 400","down","down","down","left 1980","up 340","1400","up 1350" };
    private final String[]  FLOAROMATOWN_ATICHEATPATH = {"x","down 1520","1450","right 1445","up","up","4000","down","down","1800","left 1445","up","1400","up 1350"};
    private final String[]  FLOAROMATOWN_TELEPORT_PATH = {"z 10500","x","down 1520","1450","right 930","up 1040","right 7780","down","right 1070","up","up","sweetcatch","3","4000"};
    private final String[]  FLOAROMATOWN_TELEPORTBICICLE_PATH ={"z 10500","x","down 1520","1450", "right 930","up 1040","2","right 3600","2","down","right 1070","up","up","sweetcatch","3","4000"};


    private final String[]   SANDGEM_NORMAL_PATH = {"z 10500","x","down 1520","1450","right 500","down 5675","left","z 4000","sweetcatch","up","right","up 1155","right","up 4425","left 500","up","1400","up 1350" };
    private final String[]  SANDGEM_BICICLE_PATH = {"z 10500","x","down 1520","1450","right 500","2","down 2770","left","2","z 4000","sweetcatch","up","right","up 1155","right","2","up 2070","2","left 500","up","1400","up 1350" };
    private final String[]  SANDGEM_TELEPORTBICICLE_PATH = {"z 10500","x","down 1520","1450","right 500","2","down 2770","left","2","z 4000","sweetcatch","3","4000" };
    private final String[]  SANDGEM_TELEPORT_PATH = {"z 10500","x","down 1520","1450","right 500","down 5675","left","z 4000","sweetcatch","3","4000" };
    private final String[]  SANDGEM_ANTICHEAT_PATH = {"x","down 1520","1450","left 2120","down 1200","right 1500","up 1450","down","right","right","right","up","1400","up 1350"};


    private final String[]  VERMILION_NORMAL_PATH  = {"z 6200","x","down 1400","1275","down 710","z 4000","sweetcatch","up 1450","1275","up 860"};

    private final String[]  VERMILION_TELEPORT_PATH  = {"z 6200","x","down 1400","1275","down 710","z 4000","sweetcatch","3","4000"};

    private final String[]  VERMILION_ANTICHEAT_PATH  = {"z 6200","x","down 1400","1275","down 710","left","z 4000","sweetcatch","right","right","up 1450","1275","up 860"};

    private final String[] ROUTE4_NORMAL_PATH = {"z 6200","x","down 1400","1275","right 1280","up","1450","sweetcatch","down","1450","left 1280","up","1275","up 860"};
    private final String[] ROUTE4_TELEPORT_PATH = {"z 6200","x","down 1400","1275","right 1280","up","1450","sweetcatch","down","1450","3","4000"};

    private final String[] ROUTE4_ANTICHEAT_PATH = {"x","down 1400","1275","down","right 1000","up","right 300","up","1450","sweetcatch","down","1450","left 1280","up","1275","up 860"};

    private final String[] LAVANDERTOWN_NORMAL_PATH = {"z 6200","x","down 1400","1275","right 1150","down","right 1015","up","1400","up 1500","right 1700","1400","up 680","left 2180","down","down","left 410","down","down","left","1400","right","sweetcatch","left","left","1400","up 750","right 2200","down","down","right","right","down","down","right","1400","left 1325","down 1815","1275","left 1445","up","left 860","up","1275","up 860"};
    private final String[] LAVANDERTOWN_TELEPORT_PATH = {"z 6200","x","down 1400","1275","right 1150","down","right 1015","up","1400","up 1500","right 1700","1400","up 680","left 2180","down","down","left 410","down","down","left","1400","right","sweetcatch","left","left","1400","up 750","right 2200","down","down","right","right","down","down","right","1400","left 1325","down 1815","1275","3","4000"};
    private final String[] LAVANDERTOWN_ANTICHEAT_PATH = {"z 6200","x","down 1400","1275","right 1150","up","left","up 1050","down 1750","left 880","up","up","up","1275","up 860"};


    private final String[][] LAVANDERTOWN_POSSIBILES_Path = {SWEET_CATHCING_NEVEPOLI, LAVANDERTOWN_NORMAL_PATH, null, LAVANDERTOWN_TELEPORT_PATH,LAVANDERTOWN_ANTICHEAT_PATH};
    private final String[][] ROUTE4_POSSIBILES_Path = {SWEET_CATHCING_NEVEPOLI,ROUTE4_NORMAL_PATH,null,ROUTE4_TELEPORT_PATH, ROUTE4_ANTICHEAT_PATH };
    private final String[][] VERMINLION_POSSIBILES_Path = {SWEET_CATHCING_MEMORIDE,VERMILION_NORMAL_PATH,null,VERMILION_TELEPORT_PATH , VERMILION_ANTICHEAT_PATH};
    private final String[][] SANDGEM_POSSIBILES_Path = {SWEET_CATHCING_MEMORIDE, SANDGEM_NORMAL_PATH, SANDGEM_BICICLE_PATH,SANDGEM_TELEPORT_PATH,SANDGEM_ANTICHEAT_PATH, SANDGEM_TELEPORTBICICLE_PATH };
    private final String[][] FLOAROMATOWN_POSSIBILES_Path = {SWEET_CATHCING_FLOAROMATOWN, FLOAROMATOWN_NORMAL_PATH,FLOAROMATOWN_BICICLE_PATH,FLOAROMATOWN_TELEPORT_PATH ,FLOAROMATOWN_ATICHEATPATH, FLOAROMATOWN_TELEPORTBICICLE_PATH};
    private final String[][] ETERNACITY_POSSIBILES_Path = {SWEET_CATHCING_NEVEPOLI,  ETERNACITY_NORMAL_PATH , ETERNACITY_BICICLE_PATH, ETERNACITY_TELEPORT_PATH ,ETERNACITY_ANTICHEAT_PATH,ETERNACITY_TELEPORT_BICICLE_PATH};
    private final String[][] PossibileMomoridePath = {SWEET_CATHCING_MEMORIDE, MEMORIDE_PATH_NORMAL,MEMORIDE_PATH_BICICLE,MEMORIDE_PATH_TELEPORT, MEMORIDE_ATICHEATPATH,MEMORIDE_TELEPORT_BICICLE_PATH };
    private final String[][] NEVEPOLI_POSSIBILES_Path = { SWEET_CATHCING_NEVEPOLI,NEVEPOLI_PATH_NORMAL,null,NEVEPOLI_PATH_TELEPORT,NEVEPOLI_PATH_AntiCheat };
    Main mainView = Main.GetInstance();
    public static int SelectedTimer;
    public WorkFlowManager() throws AWTException {
        botInstance = new bot();
        waiter = Waiter.instance;
        isAntiCaptchaEnabled = mainView.isAnticapthaEnable();
        isBicileEnable = mainView.isBicicleEnable();
        isTeleportEnable = mainView.isTeleportaEnable();
        SelectedTimer = mainView.getSliderValue();


    }

    public void Start(int numberOfSweetScent){
        this.numberOfSweetScent = numberOfSweetScent;
        waiter.wait(5050);



            while(isRunning){

                if(mainView.getSelectedLocation().equals("CELESTIC TOWN (Sinnoh), x5 Psyduck, Sweet Scent hunting")){
                    numberOFPokemonPerEnc = 5;

                    Parkour(PossibileMomoridePath);
                } else if (mainView.getSelectedLocation().equals("SNOWPOINT CITY (Sinnoh), x5 Snover/Noctowl, Sweet Scent hunting")) {
                    numberOFPokemonPerEnc = 5;

                    Parkour(NEVEPOLI_POSSIBILES_Path);



                }

                else if (mainView.getSelectedLocation().equals("ETERNA CITY (Sinnoh), x3 Bidoof/Buizel, Sweet Scent hunting")) {
                    numberOFPokemonPerEnc = 3;

                    Parkour(ETERNACITY_POSSIBILES_Path);



                }

                else if (mainView.getSelectedLocation().equals("FLOAROMA TOWN (Sinnoh), x3 Shinx/Pachirisu, Sweet Scent hunting")) {
                    numberOFPokemonPerEnc = 3;

                    Parkour(FLOAROMATOWN_POSSIBILES_Path);
                }
                else if (mainView.getSelectedLocation().equals("SANDGEM TOWN (Sinnoh), x5 Wingull, Sweet Scent hunting")) {
                    numberOFPokemonPerEnc = 5;

                    Parkour(SANDGEM_POSSIBILES_Path);
                }
                else if (mainView.getSelectedLocation().equals("VERMILION CITY (Kanto), x5 Tentacool, Sweet Scent hunting")) {
                    numberOFPokemonPerEnc = 5;

                    Parkour(VERMINLION_POSSIBILES_Path);
                }
                else if (mainView.getSelectedLocation().equals("ROUTE 4 (Kanto), x3 Zubat, Sweet Scent hunting")) {
                    numberOFPokemonPerEnc = 3;

                    Parkour(ROUTE4_POSSIBILES_Path);
                }
                else if (mainView.getSelectedLocation().equals("LAVENDER TOWN 1 (Kanto), x3 Gastly, Sweet Scent hunting")) {
                    numberOFPokemonPerEnc = 3;

                    Parkour(LAVANDERTOWN_POSSIBILES_Path);
                }



                }


    }


    private void Parkour(String[][] paths ){
        int casualNumber = random.nextInt(10) + 1;
        if(!isAntiCaptchaEnabled){
            casualNumber = 2;
        }
        int indexPathToUse = 0;

        if(casualNumber != 1){


            if(isBicileEnable && !isTeleportEnable ){

                indexPathToUse = 2;

            } else if (!isBicileEnable && isTeleportEnable ) {
                indexPathToUse = 3;


            } else if (isBicileEnable && isTeleportEnable) {
                indexPathToUse = 5;

            }
            else if (!isBicileEnable && !isTeleportEnable) {
                indexPathToUse = 1;

            }



            for (String key : paths[indexPathToUse]
            ) {


                if("sweetcatch".equals(key)){
                    for(int x = 0 ; x < numberOfSweetScent ; x++){
                        mainView.increaseEncounterLabel(numberOFPokemonPerEnc);
                        if(!WorkFlowManager.isRunning){
                            break;
                        }

                        for (String keyc : paths[0]){
                            if(isFirstHunt){
                                waiter.wait(650);
                                isFirstHunt = !isFirstHunt;
                            }

                            botInstance.Press(keyc);

                        }

                    }

                }
                else{
                    botInstance.Press(key);

                }


            }

        }
        else{
            for (String key : paths[4]
            ) {
                if(!WorkFlowManager.isRunning){
                    break;
                }



                if("sweetcatch".equals(key)){
                    for (String keyc :paths[0]){
                        if(!WorkFlowManager.isRunning){
                            break;
                        }
                        botInstance.Press(keyc);

                    }
                }
                else{
                    botInstance.Press(key);

                }


            }


        }

    }








}
