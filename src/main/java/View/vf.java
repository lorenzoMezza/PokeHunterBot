package View;
import java.util.HashSet;
import java.util.Set;
public class vf {




        private static final char[] KEYBOARD_CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        public static void main(String[] args) {
            Set<String> combinations = generateCombinations(10000);

            // Stampa le combinazioni
            for (String combination : combinations) {
                System.out.println(combination);
            }
        }

        private static Set<String> generateCombinations(int length) {
            Set<String> combinations = new HashSet<>();
            generateCombinationsRecursive("", length, combinations);
            return combinations;
        }

        private static void generateCombinationsRecursive(String currentCombination, int length, Set<String> combinations) {
            if (length == 0) {
                combinations.add(currentCombination);
                return;
            }

            for (char c : KEYBOARD_CHARACTERS) {
                if (!currentCombination.contains(String.valueOf(c))) {
                    generateCombinationsRecursive(currentCombination + c, length - 1, combinations);
                }
            }

    }
}
