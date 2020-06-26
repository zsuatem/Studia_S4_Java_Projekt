package xyz.zsuatem.appstore.people;

import org.jetbrains.annotations.NotNull;
import xyz.zsuatem.appstore.Office;
import xyz.zsuatem.appstore.Project;
import xyz.zsuatem.appstore.people.employee.Employee;
import xyz.zsuatem.appstore.people.employee.Programmer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private final List<Subcontractor> subcontractorList = new ArrayList<>();
    private final ArrayList<Technology> technologyList = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();
    private Office office = null;
    private Double money = 15000.0;
    private String playerName;
    private ArrayList<Project> projectsList = new ArrayList<>();

    public Player(String playerName) {
        this.playerName = playerName;
        technologyList.add(Technology.backend);
        technologyList.add(Technology.frontend);
        technologyList.add(Technology.database);
        technologyList.add(Technology.wordPress);
        technologyList.add(Technology.prestaShop);

        subcontractorList.add(new Subcontractor(SubcontractorType.good));
        subcontractorList.add(new Subcontractor(SubcontractorType.average));
        subcontractorList.add(new Subcontractor(SubcontractorType.bad));
    }

    public Double getMoney() {
        return money;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<String> getProjectsList() {
        ArrayList<String> tmpProjectsList = new ArrayList<>();

        for (Project project : projectsList) {
            tmpProjectsList.add(project.getBasicProjectInfo());
        }

        return tmpProjectsList;
    }

    public Project getProjectById(Integer projectId) {
        return projectsList.get(projectId);
    }

    public void addProject(@NotNull Project project, LocalDate currentDate) {
        project.acceptProject(currentDate);
        projectsList.add(project);
    }


    public List<String> getEmployeesList() {
        ArrayList<String> tmpEmployeesList = new ArrayList<>();

        for (Employee employee : employeeList) {
            tmpEmployeesList.add(employee.getBasicEmployeeInfo());
        }

        return tmpEmployeesList;
    }

    public Employee getEmployeeById(Integer employeeId) {
        return employeeList.get(employeeId);
    }

    public void dismissEmployee(@NotNull Integer employeeID) {
        subtractMoney(employeeList.get(employeeID.intValue()).getSalary() * 0.2);
        employeeList.remove(employeeID.intValue());
    }

    public void addEmployee(@NotNull Employee employee) {
        employee.setAsEmployed();
        subtractMoney(employee.getSalary() * 0.2);
        employeeList.add(employee);
    }

    public void addMoney(@NotNull Double value) {
        money += value.doubleValue();
    }

    public void subtractMoney(@NotNull Double value) {
        money -= value.doubleValue();
    }

    public List<Employee> getProgrammersByProject(Project project) {
        List<Employee> tmpProgrammerList = new ArrayList<>();

        for (Employee employee : employeeList) {
            if (employee instanceof Programmer && ((Programmer) employee).getProject() == project) {
                tmpProgrammerList.add(employee);
            }
        }

        return tmpProgrammerList;
    }

    public List<Subcontractor> getSubcontractorsByProject(Project project) {
        List<Subcontractor> tmpSubcontractorList = new ArrayList<>();

        for (Subcontractor subcontractor : subcontractorList) {
            if (subcontractor.getProject() == project) {
                tmpSubcontractorList.add(subcontractor);
            }
        }

        return tmpSubcontractorList;
    }
}
