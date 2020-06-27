package xyz.zsuatem.appstore.people.employee;

import xyz.zsuatem.appstore.Interface;
import xyz.zsuatem.appstore.Project;
import xyz.zsuatem.appstore.people.Technology;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Programmer extends Employee {

    private final ArrayList<Technology> technologyList = new ArrayList<>();
    private final Integer maxNumberOfSkills = 3;
    private final Integer errors;
    private final Integer punctuality;
    private Project project = null;
    public Programmer() {
        super(ThreadLocalRandom.current().nextDouble(5000.0, 6000.0));
        errors = ThreadLocalRandom.current().nextInt(0, 10);
        punctuality = ThreadLocalRandom.current().nextInt(4, 8);

        technologyList.addAll(Technology.getRandomTechnologyList(maxNumberOfSkills));
    }

    public ArrayList<Technology> getTechnologyList() {
        return technologyList;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String getBasicEmployeeInfo() {
        if (isEmployed().booleanValue() != false) {
            return "Programista: Imię i nazwisko: " + getFullName() + "\tPensja: " + Interface.decimalFormat.format(getSalary().doubleValue()) + "zł\n" +
                    "\tPopełnianie błędów (im mniejsza wartość tym lepiej): " + errors + "/10.\tPracowitość/punktualność (im większa wartość tym lepiej): " + punctuality + "/8.\n";

        } else {
            return "Programista: Imię i nazwisko: " + getFullName() + "\tPensja: " + Interface.decimalFormat.format(getSalary().doubleValue()) + "zł";
        }
    }

    @Override
    public String getEmployeeInfo() {
        if (isEmployed().booleanValue() != false) {
            String returnValue;

            returnValue = "Programista: Imię i nazwisko: " + getFullName() + "\tPensja: " + Interface.decimalFormat.format(getSalary().doubleValue()) + "zł\n" +
                    "\tPopełnianie błędów (im mniejsza wartość tym lepiej): " + errors + "/10.\tPracowitość/punktualność (im większa wartość tym lepiej): " + punctuality + "/8.";

            returnValue = returnValue.concat("Znane technologie: \n");
            for (Technology technology : technologyList) {
                returnValue = returnValue.concat("\t" + technology.getName() + "\n");
            }

            return returnValue;
        } else {
            String returnValue;

            returnValue = "Programista: Imię i nazwisko: " + getFullName() + "\tPensja: " + Interface.decimalFormat.format(getSalary().doubleValue()) + "zł\n";

            returnValue = returnValue.concat("Znane technologie: \n");
            for (Technology technology : technologyList) {
                returnValue = returnValue.concat("\t" + technology.getName() + "\n");
            }

            return returnValue;
        }
    }
}
