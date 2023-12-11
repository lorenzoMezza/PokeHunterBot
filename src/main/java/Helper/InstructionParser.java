package Helper;

import java.util.ArrayList;
import java.util.List;

public class InstructionParser {




    public static String parseInstructions(String input) {
        if(input == null){
            return "Unknown Error";
        }
        String[] instructions = input.split(",");

        if(input.length() == 0){
            return "Warning: Path is not defined";
        }

        for (String instruction : instructions) {
            String trimmedInstruction = instruction.trim();

            if (trimmedInstruction.equals("escape")) {
                // Istruzione di attesa senza tempo specificato
                continue;
            }

            String[] parts = trimmedInstruction.split("\\s+");

            if (parts.length > 2) {
                return "Error: To many token in one instruction -> "+parts[0] + " " + parts[1] ;
            }

            String key = parts[0];
            if (!isValidKey(key)) {
                if(key.equals("escape")){
                    return "Error: this instruction doesn't need time -> " + key;

                }
                if(key.trim().isEmpty()){
                    return "Error: Empty space is not an instruction";
                }
                return "Error: invalid Key instruction -> " + key;
            }

            if (parts.length == 2) {
                // L'istruzione ha un tempo specificato
                String time = parts[1];
                if (!isValidTime(time)) {
                    return "Error: Invalid delay time ->  " + time ;
                }
            }
        }

        return null;
    }

    private static boolean isValidKey(String key) {
        // Verifica se il tasto è valido
        return key.matches("(up|down|left|wait|right|1|2|3|z|x)");
    }

    private static boolean isValidTime(String time) {
        // Verifica se il tempo è un numero intero positivo
        try {
            int milliseconds = Integer.parseInt(time);
            return milliseconds >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }




    public static String[] transformString(String input) {
        // Rimuovi gli spazi superflui e dividi la stringa in base alle virgole
        String[] tokens = input.split(",");

        // Lista per memorizzare i risultati
        List<String> result = new ArrayList<>();

        // Itera attraverso i token e aggiungi alla lista
        for (String token : tokens) {
            // Rimuovi gli spazi superflui e aggiungi alla lista
            result.add(token.trim());
        }

        // Converti la lista in un array di stringhe
        return result.toArray(new String[0]);
    }



}