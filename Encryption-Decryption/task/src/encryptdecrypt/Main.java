package encryptdecrypt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        String mode = "enc";
        int key = 0;
        String data = "";
        String fileToRead = "";
        String fileToWrite = "";

        boolean isData = false;
        boolean isFileToRead = false;

        String algorithm = "shift";
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode" -> mode = args[i + 1];
                case "-key" -> key = Integer.parseInt(args[i + 1]);
                case "-data" -> {
                    data = args[i + 1];
                    isData = true;
                }
                case "-in" -> {
                    fileToRead = args[i + 1];
                    isFileToRead = true;
                }
                case "-out" -> fileToWrite = args[i + 1];
                case "-alg" -> algorithm = args[i + 1];
            }
        }

        validateParams(mode, key, data, fileToRead, fileToWrite, isData, isFileToRead);

        data = isData ? data : Files.readString(Paths.get(fileToRead));

        StringBuilder result = new StringBuilder();
        if (mode.equals("enc")) {
            encrypt(data, key, algorithm, result);
        } else if (mode.equals("dec")) {
            decrypt(data, key, algorithm, result);
        }

        System.out.println(result);
        Files.writeString(Paths.get(fileToWrite), result);
    }

    private static void validateParams(String mode, int key, String data,
                                       String fileToRead, String fileToWrite, boolean isData, boolean isFileToRead) {
        if (isData && isFileToRead) {
            System.out.println("Error: -data and -in are mutually exclusive");
            return;
        }
        if (!isData && !isFileToRead) {
            System.out.println("Error: -data or -in is required");
            return;
        }
        if (!mode.equals("enc") && !mode.equals("dec")) {
            System.out.println("Error: -mode must be enc or dec");
            return;
        }
        if (key < 0) {
            System.out.println("Error: -key must be positive");
            return;
        }
        if (Files.notExists(Paths.get(data)) && Files.notExists(Paths.get(fileToRead))) {
            System.out.println("Error: input file not found");
            return;
        }
        if (Files.exists(Paths.get(fileToWrite)) && !Files.isWritable(Paths.get(fileToWrite))) {
            System.out.println("Error: output file not found");
        }

    }

    private static void decrypt(String msg, int shift, String algorithm, StringBuilder result) {
        for (char ch : msg.toCharArray()) {
            if (algorithm.equals("unicode")) {
                result.append((char) (ch - shift % 65536));
            } else if (algorithm.equals("shift")) {
                if (ch != ' ' && Character.isUpperCase(ch)) {
                    int originalAlphabetPosition = ch - 'A';
                    int newAlphabetPosition = (originalAlphabetPosition - shift) % 26;
                    if (newAlphabetPosition < 0) {
                        newAlphabetPosition += 26;
                    }
                    char newCharacter = (char) ('A' + newAlphabetPosition);
                    result.append(newCharacter);
                }
                else if (ch != ' ' && Character.isLowerCase(ch)) {
                    int originalAlphabetPosition = ch - 'a';
                    int newAlphabetPosition = (originalAlphabetPosition - shift) % 26;
                    if (newAlphabetPosition < 0) {
                        newAlphabetPosition += 26;
                    }
                    char newCharacter = (char) ('a' + newAlphabetPosition);
                    result.append(newCharacter);
                }
                else
                    result.append(ch);
            }
        }
    }

    private static void encrypt(String msg, int shift, String algorithm, StringBuilder result) {
        for (char ch : msg.toCharArray()) {
            if (algorithm.equals("unicode")) {
                //Get the unicode value of a character
                result.append((char) (ch + shift % 65536));
            } else if (algorithm.equals("shift")) {
                if (ch != ' ' && Character.isUpperCase(ch)) {
                    int originalAlphabetPosition = ch - 'A';
                    int newAlphabetPosition = (originalAlphabetPosition + shift) % 26;
                    char newCharacter = (char) ('A' + newAlphabetPosition);
                    result.append(newCharacter);
                }
                else if (ch != ' ' && Character.isLowerCase(ch)) {
                    int originalAlphabetPosition = ch - 'a';
                    int newAlphabetPosition = (originalAlphabetPosition + shift) % 26;
                    char newCharacter = (char) ('a' + newAlphabetPosition);
                    result.append(newCharacter);
                }
                else
                    result.append(ch);
            }
        }
    }
}
