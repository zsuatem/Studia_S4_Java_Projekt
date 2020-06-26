package xyz.zsuatem.appstore;

import xyz.zsuatem.appstore.people.Player;
import xyz.zsuatem.appstore.people.employee.Employee;
import xyz.zsuatem.appstore.people.employee.Programmer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameController {
    public GameState gameState = GameState.mainMenu;
    public List<Project> projects = new ArrayList<>();
    public List<Employee> employees = new ArrayList<>();
    private Interface anInterface = new Interface();
    private Calendar calendar = new Calendar();
    private List<Player> players = new ArrayList<>();
    private Integer numberOfPlayers;


    public String getFormattedDate() {
        return calendar.getFormattedDate();
    }

    public LocalDate getDate() {
        return calendar.getDate();
    }

    public void play() {
        Integer tmpLastIntValue = null;
        Integer openedProjectId;
        Integer openedEmployeeInfoId;

        employees.add(new Programmer());


        while (gameState != GameState.exitGame) {

            while (players.size() == 0) {
                switch (gameState) {
                    case exitGame:
                        anInterface.exitGame();
                        break;
                    //Menu
                    case mainMenu:
                        anInterface.mainMenu(this);
                        break;
                    case moreInfoAboutGame:
                        anInterface.moreInfoAboutGame(this);
                        break;
                    //Create
                    case createNewGame:
                        numberOfPlayers = anInterface.createNewGame(this);
                        break;
                    case createNewPlayer:
                        for (int i = 0; i < numberOfPlayers; i++) {
                            players.add(anInterface.createNewPlayer(this, i + 1, numberOfPlayers));
                            projects.add(new Project(ProjectType.easy, 80));
                            projects.add(new Project(ProjectType.medium, 160));
                            projects.add(new Project(ProjectType.hard, 320));
                        }
                        break;
                }
            }

            for (Player currentPlayer : players) {
                while (gameState != GameState.finishTurn) {
                    switch (gameState) {
                        case exitGame:
                            anInterface.exitGame();
                            break;
                        //Player
                        case playerMenu:
                            anInterface.playerMenu(this, currentPlayer);
                            break;
                        case myProjectsMenu:
                            tmpLastIntValue = anInterface.myProjectsMenu(this, currentPlayer);
                            break;
                        case myProjectInfo:
                            openedProjectId = tmpLastIntValue;

                            anInterface.myProjectInfo(this, currentPlayer, openedProjectId);
                            break;
                        case newProjectsMenu:
                            tmpLastIntValue = anInterface.newProjectsMenu(this, currentPlayer);
                            break;
                        case newProjectInfo:
                            openedProjectId = tmpLastIntValue;

                            tmpLastIntValue = anInterface.newProjectInfo(this, currentPlayer, openedProjectId);

                            if (tmpLastIntValue == 1) {
                                currentPlayer.addProject(projects.get(openedProjectId.intValue()), getDate());

                                projects.remove(openedProjectId.intValue());
                            }
                            break;
                        case employeesMenu:
                            tmpLastIntValue = anInterface.employeeMenu(this, currentPlayer);
                            break;
                        case employeeInfo:
                            openedEmployeeInfoId = tmpLastIntValue;

                            anInterface.employeeInfo(this, currentPlayer, openedEmployeeInfoId);
                            break;
                        case newEmployeesMenu:
                            tmpLastIntValue = anInterface.newEmployeeMenu(this, currentPlayer);
                            break;
                        case newEmployeeInfo:
                            openedEmployeeInfoId = tmpLastIntValue;

                            tmpLastIntValue = anInterface.newEmployeeInfo(this, currentPlayer, openedEmployeeInfoId);

                            if (tmpLastIntValue == 1) {
                                currentPlayer.addEmployee(employees.get(openedEmployeeInfoId.intValue()));

                                employees.remove(openedEmployeeInfoId.intValue());
                            }
                            break;
                        case myProjectInfoEmployees:
                            openedProjectId = tmpLastIntValue;

                            anInterface.myProjectInfoEmployees(this, currentPlayer, openedProjectId);
                            break;

                    }

                    if (gameState == GameState.finishTurn) {
                        gameState = GameState.playerMenu;
                        break;
                    }
                }
            }

            randomizePlayersOrder();
            calendar.nextDay();
        }
        anInterface.exitGame();
    }

    private void randomizePlayersOrder() {
        Collections.shuffle(players);
    }

    public List<String> getProjectsList() {
        ArrayList<String> tmpProjectsList = new ArrayList<>();

        for (Project project : projects) {
            tmpProjectsList.add(project.getBasicProjectInfo());
        }

        return tmpProjectsList;
    }

    public Project getProjectById(Integer projectId) {
        return projects.get(projectId);
    }

    public List<String> getEmployeesList() {
        ArrayList<String> tmpEmployeeList = new ArrayList<>();

        for (Employee employee : employees) {
            tmpEmployeeList.add(employee.getBasicEmployeeInfo());
        }

        return tmpEmployeeList;
    }

    public Employee getEmployeeById(Integer employeeId) {
        return employees.get(employeeId);
    }
}
