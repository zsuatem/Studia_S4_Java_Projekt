package xyz.zsuatem.appstore.people;

import xyz.zsuatem.appstore.FullFullNameGeneratorFromTxtFile;
import xyz.zsuatem.appstore.Office;
import xyz.zsuatem.appstore.Project;

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

        subcontractorList.add(new Subcontractor(new FullFullNameGeneratorFromTxtFile().getRandomFullName(), SubcontractorType.good));
        subcontractorList.add(new Subcontractor(new FullFullNameGeneratorFromTxtFile().getRandomFullName(), SubcontractorType.average));
        subcontractorList.add(new Subcontractor(new FullFullNameGeneratorFromTxtFile().getRandomFullName(), SubcontractorType.bad));
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

    public void addProject(Project project, LocalDate currentDate) {
        project.acceptProject(currentDate);
        projectsList.add(project);
    }
}
