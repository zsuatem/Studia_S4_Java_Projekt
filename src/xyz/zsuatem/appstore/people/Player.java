package xyz.zsuatem.appstore.people;

import org.jetbrains.annotations.NotNull;
import xyz.zsuatem.appstore.Office;
import xyz.zsuatem.appstore.Project;
import xyz.zsuatem.appstore.people.employee.Employee;
import xyz.zsuatem.appstore.people.employee.Programmer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    private final List<Subcontractor> subcontractorList = new ArrayList<>();
    private final ArrayList<Technology> technologyList = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();
    private Office office = null;
    private Double money = 15000.0;
    private Double moneyEarnedInMonth = 0.0;
    private String playerName;
    private ArrayList<Project> projectsList = new ArrayList<>();
    private Integer daysToNewClient = 0;
    private Integer daysToAccounting = 0;
    private Project project;
    private ArrayList<Project> oldProjectList = new ArrayList<>();
    private Map<LocalDate, Double> pendingPayments = new HashMap<>();

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

    public Double getMoneyEarnedInMonth() {
        return moneyEarnedInMonth;
    }

    public void setMoneyEarnedInMonth(Double moneyEarnedInMonth) {
        this.moneyEarnedInMonth = moneyEarnedInMonth;
    }

    public Map<LocalDate, Double> getPendingPayments() {
        return pendingPayments;
    }

    public void setPendingPayments(LocalDate date, Double pendingPayments) {
        this.pendingPayments.put(date, pendingPayments);
    }

    public ArrayList<Project> getOldProjectList() {
        return oldProjectList;
    }

    public void setOldProjectList(ArrayList<Project> oldProjectList) {
        this.oldProjectList = oldProjectList;
    }

    public ArrayList<Project> getProjectsList() {
        return projectsList;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Double getMoney() {
        return money;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<String> getProjectsListAsString() {
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

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public List<String> getEmployeesListAsString() {
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

    public void addNextDayToNewClient() {
        daysToNewClient++;
    }

    public Boolean isFiveDaysToNewClient() {
        if (daysToNewClient >= 5) {
            daysToNewClient = 0;
            return true;
        } else {
            return false;
        }
    }

    public Integer getDaysToNewClient() {
        return daysToNewClient;
    }

    public void addNextDayToAccounting() {
        daysToAccounting++;
    }

    public Boolean inTwoDaysToAccounting() {
        if (daysToAccounting >= 2) {
            daysToAccounting = 0;
            return true;
        } else {
            return false;
        }
    }

    public Integer getDaysToAccounting() {
        return daysToAccounting;
    }

    public ArrayList<Technology> getTechnologyList() {
        return technologyList;
    }

    public Boolean hasEmployees() {
        if (employeeList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void moveProjectToOldProjects(Project project) {
        oldProjectList.add(project);
        projectsList.removeIf(project1 -> project == project1);
    }
}
