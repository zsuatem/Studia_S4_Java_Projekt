package xyz.zsuatem.appstore.people;

import xyz.zsuatem.appstore.Interface;
import xyz.zsuatem.appstore.Project;
import xyz.zsuatem.appstore.generator.FullFullNameGeneratorFromTxtFile;

import java.util.ArrayList;

public class Subcontractor extends Human {
    private final ArrayList<Technology> technologyList = new ArrayList<>();
    private final Integer maxNumberOfSkills = 3;
    private final SubcontractorType type;
    private Project project = null;

    public Subcontractor(SubcontractorType type) {
        super(new FullFullNameGeneratorFromTxtFile().getRandomFullName());
        this.type = type;

        technologyList.addAll(Technology.getRandomTechnologyList(maxNumberOfSkills));
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getBasicSubcontractorInfo() {
        return "Podwykonawca: Imię i nazwisko: " + getFullName() + "\tPensja: " + Interface.decimalFormat.format(type.getSalary().doubleValue()) + "zł\n" +
                "\tPopełnianie błędów (im mniejsza wartość tym lepiej): " + type.getErrors() + "/10.\tPracowitość/punktualność (im większa wartość tym lepiej): " + type.getPunctuality() + "/8.\n";

    }

    public String getSubcontractorInfo() {
        String returnValue;

        returnValue = "Programista: Imię i nazwisko: " + getFullName() + "\tPensja: " + Interface.decimalFormat.format(type.getSalary().doubleValue()) + "zł\n" +
                "\tPopełnianie błędów (im mniejsza wartość tym lepiej): " + type.getErrors() + "/10.\tPracowitość/punktualność (im większa wartość tym lepiej): " + type.getPunctuality() + "/8.";

        returnValue = returnValue.concat("Znane technologie: \n");
        for (Technology technology : technologyList) {
            returnValue = returnValue.concat("\t" + technology.getName() + "\n");
        }

        return returnValue;
    }

    public ArrayList<Technology> getTechnologyList() {
        return technologyList;
    }
}