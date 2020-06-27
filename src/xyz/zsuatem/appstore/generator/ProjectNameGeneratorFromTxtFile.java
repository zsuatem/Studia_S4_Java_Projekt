package xyz.zsuatem.appstore.generator;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ProjectNameGeneratorFromTxtFile implements ProjectNameGenerator {

    @Override
    public String getRandomProjectName() {
        List<String> adjectivesList = readAllLinesFromTxtFile("projectName-adjectives.txt");
        List<String> nounsList = readAllLinesFromTxtFile("projectName-nouns.txt");

        String adjective = adjectivesList.get(generateRandomInt(adjectivesList.size()));
        String noun = nounsList.get(generateRandomInt(nounsList.size()));

        return (adjective.substring(0, 1).toUpperCase() + adjective.substring(1)) + " " + noun;
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
