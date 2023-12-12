package Helper;


import java.util.prefs.Preferences;

public class FirstLunchVeriyer {


        private static final String PREF_KEY_FIRST_LAUNCH = "firstEverOpening";

        public static boolean isItTheFirstTime() {

            Preferences prefs = Preferences.userRoot().node("src");

            if (!prefs.getBoolean(PREF_KEY_FIRST_LAUNCH, false)) {
                prefs.putBoolean(PREF_KEY_FIRST_LAUNCH, true);
        
                return true;
            } else {
                return false;
            }
        }

        public static void resetToFirstLaunchState() {
            Preferences prefs = Preferences.userRoot().node("src");
            prefs.putBoolean(PREF_KEY_FIRST_LAUNCH, false);
        }
    }
