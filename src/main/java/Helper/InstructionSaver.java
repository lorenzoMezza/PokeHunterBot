package Helper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;  // Add this import
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;


public class InstructionSaver {
    static String tempFolderPath = System.getProperty("java.io.tmpdir");


    private static final String FILE_NAME =  tempFolderPath  +"PokemmoHunterBotCustomPaths.json";
    private static final Gson gson = new Gson();
    private static void deleteFile() {
        System.out.println(tempFolderPath);

        File fileDaEliminare = new File(FILE_NAME);

        if (fileDaEliminare.exists()) {
            if (fileDaEliminare.delete()) {

            } else {

            }
        } else {

        }
    }

    public static void saveInstructions(Map<String, String> instructionsMap) {
        deleteFile();

        try {
            Path filePath = Paths.get(FILE_NAME);  // Change this line

            // Check if the file exists
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }

            try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE)) {
                String json = gson.toJson(instructionsMap);
                writer.write(json);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> loadInstructions() {
        try {
            Path filePath = Paths.get(FILE_NAME);

            // Check if the file exists
            if (Files.exists(filePath)) {
                StringBuilder content = new StringBuilder();
                try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line);
                    }
                }

                String json = content.toString();
                Type type = new TypeToken<Map<String, String>>() {}.getType();
                return gson.fromJson(json, type);
            } else {
                return new HashMap<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
    public static void deleteInstruction(String instructionId) {
        Map<String, String> instructionsMap = loadInstructions();






            instructionsMap.remove(instructionId);
            saveInstructions(instructionsMap);

    }



    public static void main(String[] args) {
        System.out.println( FILE_NAME);
        // Esempio di istruzioni





    }
}