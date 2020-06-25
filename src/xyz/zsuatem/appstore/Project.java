package xyz.zsuatem.appstore;

import org.jetbrains.annotations.NotNull;
import xyz.zsuatem.appstore.people.Client;
import xyz.zsuatem.appstore.people.Technology;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Project {
    private final String projectName;
    private final Client client;
    private final List<Technology> technologies;
    private final Map<Technology, Integer> workHours;
    private final Integer totalWorkHours;
    private final Double contractualPenalty;
    private final Double totalPrice;
    private final ProjectType projectType;
    private Integer currentTotalWorkHours = 0;
    private Map<Technology, Integer> currentWorkHours;
    private LocalDate dateOfPayment;
    private LocalDate endDate;
    private Boolean handedIn = false;

    public Project(@NotNull ProjectType projectType, Integer totalWorkHours) {
        this.projectType = projectType;
        this.totalWorkHours = totalWorkHours;

        ProjectNameGenerator projectNameGenerator = new ProjectNameGeneratorFromTxtFile();
        projectName = projectNameGenerator.getRandomProjectName();

        FullNameGenerator fullNameGenerator = new FullFullNameGeneratorFromTxtFile();
        String fullName = fullNameGenerator.getRandomFullName();

        client = new Client(fullName);

        switch (projectType) {
            case easy:
                technologies = Technology.getRandomTechnologyList(generateRandomInt(1, 3));
                totalWorkHours = generateRandomInt(80, 160);
                break;
            case medium:
                technologies = Technology.getRandomTechnologyList(generateRandomInt(2, 4));
                totalWorkHours = generateRandomInt(160, 320);
                break;
            default:
                technologies = Technology.getRandomTechnologyList(generateRandomInt(3, 5));
                totalWorkHours = generateRandomInt(320, 640);
                break;
        }

        workHours = new HashMap<>();
        currentWorkHours = new HashMap<>();
        for (Technology technology : technologies) {
            if (technology == technologies.get(technologies.size() - 1)) {
                workHours.put(technology, (totalWorkHours / technologies.size()) + (totalWorkHours % technologies.size()));
            } else {
                workHours.put(technology, totalWorkHours / technologies.size());
            }
            currentWorkHours.put(technology, 0);
        }

        totalPrice = totalWorkHours * 200.0;
        contractualPenalty = totalPrice * 0.15;
    }


    public Project() {
        FullNameGenerator fullNameGenerator = new FullFullNameGeneratorFromTxtFile();
        String fullName = fullNameGenerator.getRandomFullName();

        ProjectNameGenerator projectNameGenerator = new ProjectNameGeneratorFromTxtFile();
        projectName = projectNameGenerator.getRandomProjectName();

        client = new Client(fullName);
        projectType = ProjectType.getRandomProjectType();

        switch (projectType) {
            case easy:
                technologies = Technology.getRandomTechnologyList(generateRandomInt(1, 3));
                totalWorkHours = generateRandomInt(80, 160);
                break;
            case medium:
                technologies = Technology.getRandomTechnologyList(generateRandomInt(2, 4));
                totalWorkHours = generateRandomInt(160, 320);
                break;
            default:
                technologies = Technology.getRandomTechnologyList(generateRandomInt(3, 5));
                totalWorkHours = generateRandomInt(320, 640);
                break;
        }

        workHours = new HashMap<>();
        currentWorkHours = new HashMap<>();
        for (Technology technology : technologies) {
            if (technology == technologies.get(technologies.size() - 1)) {
                workHours.put(technology, (totalWorkHours / technologies.size()) + (totalWorkHours % technologies.size()));
            } else {
                workHours.put(technology, totalWorkHours / technologies.size());
            }
            currentWorkHours.put(technology, 0);
        }

        totalPrice = totalWorkHours * 200.0;
        contractualPenalty = totalPrice * 0.15;
    }

    private @NotNull Integer generateRandomInt(Integer minIncluded, Integer maxIncluded) {
        return ThreadLocalRandom.current().nextInt(minIncluded, maxIncluded);
    }

    public void acceptProject(@NotNull LocalDate currentDate) {
        endDate = currentDate.plusWeeks((totalWorkHours / 8 / 5) + 1);
        dateOfPayment = endDate.plusWeeks(2);
    }

    public String getBasicProjectInfo() {
        if (endDate != null) {
            return "Nazwa: " + projectName + "\n" +
                    "\tKlient: " + client.getFullName() + "\tPoziom trudności: " + projectType.getName() + "\tWartość: " + totalPrice + "\tWykonano: " + currentTotalWorkHours + "/" + totalWorkHours + "h" + "\tData oddania: " + endDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        } else {
            return "Nazwa: " + projectName + "\n" +
                    "\tKlient: " + client.getFullName() + "\tPoziom trudności: " + projectType.getName() + "\tWartość: " + totalPrice + "\tLiczba godzin: " + totalWorkHours;
        }
    }

    public String getProjectInfo() {
        if (endDate != null) {
            String returnValue;
            returnValue = "Nazwa: " + projectName + "\n" +
                    "\tKlient: " + client.getFullName() + "\tPoziom trudności: " + projectType.getName() + "\tWartość: " + totalPrice + "\n" +
                    "\tWykonano: " + currentTotalWorkHours + "/" + totalWorkHours + "h" + "\tData oddania: " + endDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + "\n";

            returnValue = returnValue.concat("Technologie: \n");
            for (Technology technology : technologies) {
                returnValue = returnValue.concat("\t" + technology.getName() + " Wykonano: " + currentWorkHours.get(technology) + "/" + workHours.get(technology) + "h\n");
            }

            returnValue = returnValue.concat("Inne informacje:\n\t" +
                    "Data płatnosci: " + dateOfPayment.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + "\n\t" +
                    "Kara umowna za oddanie po terminie: " + contractualPenalty);

            return returnValue;

        } else {
            String returnValue;
            returnValue = "Nazwa: " + projectName + "\n" +
                    "\tKlient: " + client.getFullName() + "\tPoziom trudności: " + projectType.getName() + "\tWartość: " + totalPrice + "\tLiczba godzin: " + totalWorkHours + "\n";

            returnValue = returnValue.concat("Technologie: \n");
            for (Technology technology : technologies) {
                returnValue = returnValue.concat("\t" + technology.getName() + " Potrzebny czas: " + workHours.get(technology) + "h\n");
            }

            returnValue = returnValue.concat("Inne informacje:\n\t" +
                    "Kara umowna za oddanie po terminie: " + contractualPenalty);

            return returnValue;
        }
    }

    public Boolean isReady() {
        if (currentTotalWorkHours >= totalWorkHours) {
            return true;
        } else {
            return false;
        }
    }

    public void markAsHandedIn() {
        handedIn = true;
    }

}
