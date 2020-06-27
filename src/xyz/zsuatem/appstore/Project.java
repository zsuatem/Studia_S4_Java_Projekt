package xyz.zsuatem.appstore;

import org.jetbrains.annotations.NotNull;
import xyz.zsuatem.appstore.generator.FullFullNameGeneratorFromTxtFile;
import xyz.zsuatem.appstore.generator.FullNameGenerator;
import xyz.zsuatem.appstore.generator.ProjectNameGenerator;
import xyz.zsuatem.appstore.generator.ProjectNameGeneratorFromTxtFile;
import xyz.zsuatem.appstore.people.Client;
import xyz.zsuatem.appstore.people.Human;
import xyz.zsuatem.appstore.people.Technology;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
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
    private final Human capturedBy;
    private Integer currentTotalWorkHours = 0;
    private Map<Technology, Integer> currentWorkHours;
    private LocalDate dateOfPayment;
    private LocalDate endDate;
    private Boolean handedIn = false;
    private Integer quality = 100;
    private List<Human> employeesHuman = new ArrayList<>();
    private Boolean playerActivity = false;
    private Boolean penaltyApplied = false;
    public Project(@NotNull ProjectType projectType, Integer totalWorkHours, Human capturedBy) {
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
        this.capturedBy = capturedBy;
    }

    public Project(Human capturedBy) {
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
        this.capturedBy = capturedBy;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public Human getCapturedBy() {
        return capturedBy;
    }

    public void setPenaltyAppliedToTrueValue() {
        this.penaltyApplied = true;
    }

    public Boolean getPenaltyApplied() {
        return penaltyApplied;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Double getContractualPenalty() {
        return contractualPenalty;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public LocalDate getDateOfPayment() {
        return dateOfPayment;
    }

    public Client getClient() {
        return client;
    }

    public Boolean getHandedIn() {
        return handedIn;
    }

    public void setHandedIn(Boolean handedIn) {
        this.handedIn = handedIn;
    }

    public Integer getTotalWorkHours() {
        return totalWorkHours;
    }

    public void increaseCurrentTotalWorkHours(Integer hours) {
        if (this.currentTotalWorkHours + hours > totalWorkHours) {
            this.currentTotalWorkHours = 100;
        } else {
            this.currentTotalWorkHours += hours;
        }
    }

    public Integer getQuality() {
        return quality;
    }

    public void increaseQuality(Integer quality) {
        if (this.quality + quality > 100) {
            this.quality = 100;
        } else {
            this.quality += quality;
        }
    }

    public Map<Technology, Integer> getWorkHours() {
        return workHours;
    }

    public Map<Technology, Integer> getCurrentWorkHours() {
        return currentWorkHours;
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
                    "\tKlient: " + client.getFullName() + "\tPoziom trudności: " + projectType.getName() + "\tWartość: " + Interface.decimalFormat.format(totalPrice) + "zł\tWykonano: " + currentTotalWorkHours + "/" + totalWorkHours + "h" + "\tData oddania: " + endDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        } else {
            return "Nazwa: " + projectName + "\n" +
                    "\tKlient: " + client.getFullName() + "\tPoziom trudności: " + projectType.getName() + "\tWartość: " + Interface.decimalFormat.format(totalPrice) + "zł\tLiczba godzin: " + totalWorkHours;
        }
    }

    public String getProjectInfo() {
        if (endDate != null) {
            String returnValue;
            returnValue = "Nazwa: " + projectName + "\n" +
                    "\tKlient: " + client.getFullName() + "\tPoziom trudności: " + projectType.getName() + "\tWartość: " + Interface.decimalFormat.format(totalPrice) + "zł\n" +
                    "\tWykonano: " + currentTotalWorkHours + "/" + totalWorkHours + "h" + "\tData oddania: " + endDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + "\n" +
                    "\tJakość kodu: " + quality + "/100.";

            returnValue = returnValue.concat("Technologie: \n");
            for (Technology technology : technologies) {
                returnValue = returnValue.concat("\t" + technology.getName() + " Wykonano: " + currentWorkHours.get(technology) + "/" + workHours.get(technology) + "h\n");
            }

            returnValue = returnValue.concat("Inne informacje:\n\t" +
                    "Data płatnosci: " + dateOfPayment.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + "\n\t" +
                    "Kara umowna za oddanie po terminie: " + Interface.decimalFormat.format(contractualPenalty));

            return returnValue;

        } else {
            String returnValue;
            returnValue = "Nazwa: " + projectName + "\n" +
                    "\tKlient: " + client.getFullName() + "\tPoziom trudności: " + projectType.getName() + "\tWartość: " + Interface.decimalFormat.format(totalPrice) + "\tLiczba godzin: " + totalWorkHours + "\n";

            returnValue = returnValue.concat("Technologie: \n");
            for (Technology technology : technologies) {
                returnValue = returnValue.concat("\t" + technology.getName() + " Potrzebny czas: " + workHours.get(technology) + "h\n");
            }

            returnValue = returnValue.concat("Inne informacje:\n\t" +
                    "Kara umowna za oddanie po terminie: " + Interface.decimalFormat.format(contractualPenalty));

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
        employeesHuman = null;
        handedIn = true;
    }

    public void addEmployHumanToProject(Human human) {
        employeesHuman.add(human);
    }

    public void removeEmployeeHumanFromProject(Human human) {

        employeesHuman.removeIf(human1 -> human == human1);
    }

    public List<Human> getEmployeeHumanList() {
        List<Human> tmpEmployeeHumanList = new ArrayList<>();

        if (employeesHuman.size() > 0) {
            for (Human human : employeesHuman) {
                tmpEmployeeHumanList.add(human);
            }
        }

        return tmpEmployeeHumanList;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setPlayerActivityToTrue() {
        this.playerActivity = true;
    }

    public Boolean getPlayerActivity() {
        return playerActivity;
    }

    public Boolean hasEmployees() {
        if (employeesHuman.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
