package xyz.zsuatem.appstore;

import xyz.zsuatem.appstore.people.Human;
import xyz.zsuatem.appstore.people.Player;
import xyz.zsuatem.appstore.people.Technology;
import xyz.zsuatem.appstore.people.employee.Employee;
import xyz.zsuatem.appstore.people.employee.Programmer;
import xyz.zsuatem.appstore.people.employee.Salesman;
import xyz.zsuatem.appstore.people.employee.Tester;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameController {
    public GameState gameState = GameState.mainMenu;
    public List<Project> projects = new ArrayList<>();
    public List<Employee> employees = new ArrayList<>();
    private Interface anInterface = new Interface();
    private Calendar calendar = new Calendar();
    private List<Player> players = new ArrayList<>();
    private Integer numberOfPlayers;

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

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
        GameState finishTurnGameState = null;

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
                            projects.add(new Project(ProjectType.easy, 80, null));
                            projects.add(new Project(ProjectType.medium, 160, null));
                            projects.add(new Project(ProjectType.hard, 320, null));
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
                        case writeSomeCodeMenu:
                            finishTurnGameState = anInterface.writeSomeCodeMenu(this, currentPlayer);
                            break;
                        case testCodeMenu:
                            finishTurnGameState = anInterface.testCodeMenu(this, currentPlayer);
                            break;
                        case searchForNewClients:
                            finishTurnGameState = GameState.searchForNewClients;
                            break;
                        case keepAccounts:
                            finishTurnGameState = GameState.keepAccounts;
                            break;
                    }

                    if (gameState == GameState.finishTurn) {
                        Integer hours;


                        //Player
                        if (finishTurnGameState != null) {
                            switch (finishTurnGameState) {
                                case writeSomeCodeMenu:
                                    hours = 8;

                                    for (Technology technology : currentPlayer.getProject().getTechnologies()) {
                                        if (currentPlayer.getProject().getCurrentWorkHours().get(technology).intValue() < currentPlayer.getProject().getWorkHours().get(technology).intValue()) {
                                            if (currentPlayer.getProject().getCurrentWorkHours().get(technology).intValue() + hours > currentPlayer.getProject().getWorkHours().get(technology).intValue()) {
                                                currentPlayer.getProject().getCurrentWorkHours().replace(technology, currentPlayer.getProject().getWorkHours().get(technology));
                                            } else {
                                                currentPlayer.getProject().getCurrentWorkHours().replace(technology, currentPlayer.getProject().getCurrentWorkHours().get(technology).intValue() + hours);
                                            }
                                            hours = 0;
                                            currentPlayer.getProject().increaseCurrentTotalWorkHours(hours);
                                        }
                                    }

                                    currentPlayer.getProject().setPlayerActivityToTrue();
                                    break;
                                case testCodeMenu:
                                    hours = 8;

                                    currentPlayer.getProject().increaseQuality(hours);
                                    currentPlayer.getProject().setPlayerActivityToTrue();
                                    break;
                                case searchForNewClients:

                                    currentPlayer.addNextDayToNewClient();
                                    if (currentPlayer.isFiveDaysToNewClient()) {
                                        projects.add(new Project(null));
                                    }
                                    break;
                                case keepAccounts:

                                    currentPlayer.addNextDayToAccounting();
                                    break;
                            }
                        }

                        if (currentPlayer.hasEmployees()) {
                            for (Project project : currentPlayer.getProjectsList()) {
                                if (project.hasEmployees()) {
                                    for (Human human : project.getEmployeeHumanList()) {

                                        Integer sick = ThreadLocalRandom.current().nextInt(1, 100);
                                        if (sick < 95) {

                                            hours = 8;
                                            for (Technology technology : project.getTechnologies()) {
                                                if (project.getCurrentWorkHours().get(technology).intValue() < project.getWorkHours().get(technology).intValue()) {
                                                    if (project.getCurrentWorkHours().get(technology).intValue() + hours > project.getWorkHours().get(technology).intValue()) {
                                                        project.getCurrentWorkHours().replace(technology, project.getWorkHours().get(technology).intValue());
                                                    } else {
                                                        project.getCurrentWorkHours().replace(technology, project.getCurrentWorkHours().get(technology).intValue() + hours);
                                                    }
                                                    hours = 0;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }


                        //Project quality
                        Integer programmers = 0;
                        Integer testers = 0;

                        for (Employee employee : currentPlayer.getEmployeeList()) {
                            if (employee instanceof Programmer) {
                                programmers++;
                            } else if (employee instanceof Tester) {
                                testers++;
                            }
                        }

                        if (programmers != 0 && programmers / 3 >= testers) {
                            currentPlayer.getProject().increaseQuality(100);
                        } else if (testers != 0) {
                            currentPlayer.getProject().increaseQuality(testers * 8);
                        }


                        //New projects
                        for (Employee employee : currentPlayer.getEmployeeList()) {
                            if (employee instanceof Salesman) {

                                Integer sick = ThreadLocalRandom.current().nextInt(1, 100);
                                if (sick < 95) {
                                    ((Salesman) employee).addNextDay();

                                    if (((Salesman) employee).isFiveDays()) {
                                        projects.add(new Project(employee));
                                    }
                                }
                            }
                        }


                        //Completed projects
                        for (Project project : currentPlayer.getProjectsList()) {
                            if (project.getHandedIn() == true) {
                                currentPlayer.moveProjectToOldProjects(project);

                                Integer avoidPunishment;
                                Integer paymentDelay;
                                Integer breachContract;

                                Double paymentAmount;
                                LocalDate paymentDate;
                                switch (project.getClient().getClientType()) {
                                    case easy:
                                        avoidPunishment = ThreadLocalRandom.current().nextInt(1, 100);
                                        paymentDelay = ThreadLocalRandom.current().nextInt(1, 100);

                                        if (avoidPunishment <= 20 && paymentDelay <= 30) {
                                            currentPlayer.setPendingPayments(project.getDateOfPayment().plusWeeks(1), project.getTotalPrice().doubleValue());
                                        } else if (avoidPunishment > 20 && paymentDelay <= 30) {
                                            if (getDate().isAfter(project.getEndDate())) {
                                                currentPlayer.setPendingPayments(project.getDateOfPayment().plusWeeks(1), (project.getTotalPrice().doubleValue() - project.getContractualPenalty().doubleValue()));
                                            } else {
                                                currentPlayer.setPendingPayments(project.getDateOfPayment().plusWeeks(1), (project.getTotalPrice().doubleValue()));
                                            }
                                        } else if (avoidPunishment <= 20 && paymentDelay > 30) {
                                            currentPlayer.setPendingPayments(project.getDateOfPayment(), project.getTotalPrice().doubleValue());
                                        } else if (avoidPunishment > 20 && paymentDelay > 30) {
                                            if (getDate().isAfter(project.getEndDate())) {
                                                currentPlayer.setPendingPayments(project.getDateOfPayment(), (project.getTotalPrice().doubleValue() - project.getContractualPenalty().doubleValue()));
                                            } else {
                                                currentPlayer.setPendingPayments(project.getDateOfPayment(), (project.getTotalPrice().doubleValue()));
                                            }
                                        }
                                        break;
                                    case medium:
                                        breachContract = ThreadLocalRandom.current().nextInt(1, 100);

                                        if (breachContract > 50) {
                                            currentPlayer.setPendingPayments(project.getDateOfPayment(), (project.getTotalPrice().doubleValue()));
                                        } else {
                                            if (currentPlayer.getProject().getQuality() == 100) {
                                                currentPlayer.setPendingPayments(project.getDateOfPayment(), (project.getTotalPrice().doubleValue()));
                                            }
                                        }
                                        break;
                                    case hard:
                                        paymentDelay = ThreadLocalRandom.current().nextInt(1, 100);

                                        paymentAmount = 0.0;
                                        paymentDate = null;

                                        if (currentPlayer.getProject().getQuality() == 100) {
                                            if (getDate().isAfter(project.getEndDate())) {
                                                paymentAmount = project.getTotalPrice().doubleValue();
                                            } else {
                                                paymentAmount = project.getTotalPrice().doubleValue() - project.getContractualPenalty().doubleValue();
                                            }

                                            if (paymentDelay >= 100) {
                                                paymentDate = null;
                                            } else if (paymentDelay <= 5) {
                                                paymentDate = project.getDateOfPayment().plusMonths(1);
                                            } else if (paymentDelay >= 30 && paymentDelay <= 60) {
                                                paymentDate = project.getDateOfPayment().plusWeeks(1);
                                            }
                                        }

                                        if (paymentDate != null) {
                                            currentPlayer.setPendingPayments(paymentDate, paymentAmount);
                                        }
                                        break;
                                }
                            }
                        }

                        //Payment
                        if (currentPlayer.getPendingPayments().containsKey(getDate())) {
                            currentPlayer.addMoney(currentPlayer.getPendingPayments().get(getDate()));
                            currentPlayer.setMoneyEarnedInMonth(currentPlayer.getMoneyEarnedInMonth() + currentPlayer.getPendingPayments().get(getDate()));
                            currentPlayer.getPendingPayments().remove(getDate());
                        }

                        //Every month
                        if (getDate().getMonth() != getDate().plusDays(1).getMonth()) {
                            Double tax = currentPlayer.getMoneyEarnedInMonth() * 0.1;
                            currentPlayer.setMoneyEarnedInMonth(0.0);

                            currentPlayer.subtractMoney(tax);

                            for (Employee employee : currentPlayer.getEmployeeList()) {
                                currentPlayer.subtractMoney(employee.getSalary());
                            }

                            Integer numberOfEmployee = currentPlayer.getEmployeeList().size();

                            currentPlayer.subtractMoney(numberOfEmployee * 200.0);

                            if (!currentPlayer.inTwoDaysToAccounting()) {
                                players.removeIf(player -> currentPlayer == player);
                                anInterface.lose(this, currentPlayer);
                                gameState = GameState.loss;
                            }
                        }

                        //Every day
                        if (currentPlayer.getMoney() < 0) {
                            players.removeIf(player -> currentPlayer == player);
                            anInterface.lose(this, currentPlayer);
                            gameState = GameState.loss;
                        }

                        //Win conditions
                        Integer meetsConditions = 0;
                        Integer capturedByEmployee = 0;

                        if (currentPlayer.getMoney() > 15000.0) {

                            for (Project project : currentPlayer.getOldProjectList()) {
                                if (project.getProjectType() == ProjectType.hard) {
                                    if (!project.getPlayerActivity() && project.getCapturedBy() != null) {
                                        capturedByEmployee++;
                                    }

                                    if (!project.getPlayerActivity()) {
                                        meetsConditions++;
                                    }
                                }
                            }

                            if (meetsConditions >= 3 && capturedByEmployee >= 1) {
                                players.removeIf(player -> currentPlayer == player);
                                anInterface.win(this, currentPlayer);
                                gameState = GameState.win;
                            }
                        }


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
