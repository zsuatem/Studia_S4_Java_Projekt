package xyz.zsuatem.appstore;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class FullFullNameGeneratorFromTxtFile implements FullNameGenerator {

    @Override
    public String getFullNameF() {
        List<String> fNames = readAllLinesFromTxtFile("firstName-f.txt");
        List<String> lNames = readAllLinesFromTxtFile("lastName.txt");

        String fName = fNames.get(generateRandomInt(fNames.size()));
        String lName = lNames.get(generateRandomInt(lNames.size()));

        if (lName.endsWith("ski")) {
            lName = lName.replace("ski", "ska");
        } else if (lName.endsWith("cki")) {
            lName = lName.replace("cki", "cka");
        } else if (lName.endsWith(("dzki"))) {
            lName = lName.replace("dzki", "dzka");
        }

        return fName + " " + lName;
    }

    @Override
    public String getFullNameM() {
        List<String> fNames = readAllLinesFromTxtFile("firstName-m.txt");
        List<String> lNames = readAllLinesFromTxtFile("lastName.txt");

        String fName = fNames.get(generateRandomInt(fNames.size()));
        String lName = lNames.get(generateRandomInt(lNames.size()));

        return fName + " " + lName;
    }

    @Override
    public String getFullName() {
        String fullName;
        if (generateRandomInt(2) == 0) {
            fullName = getFullNameM();
        } else {
            fullName = getFullNameF();
        }

        return fullName;
    }

    private ArrayList<String> readAllLinesFromTxtFile(String fileName) {
        Scanner scanner;
        ArrayList<String> lines = new ArrayList<>();
        String filePath = "";

        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            URI uri = new URI(classloader.getResource(fileName).toString());
            filePath = uri.getPath();
        } catch (URISyntaxException exception) {
            exception.printStackTrace();
            return lines;
        }

        try {
            scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
            return lines;
        }

        String line;
        while (scanner.hasNextLine() == true) {
            line = scanner.nextLine();
            lines.add(line);
        }

        scanner.close();
        return lines;
    }

    private @NotNull Integer generateRandomInt(Integer maxExcluded) {
        return ThreadLocalRandom.current().nextInt(0, maxExcluded);
    }

}
