package xyz.zsuatem.appstore;

import org.jetbrains.annotations.NotNull;
import xyz.zsuatem.appstore.people.Human;
import xyz.zsuatem.appstore.people.Player;
import xyz.zsuatem.appstore.people.Subcontractor;
import xyz.zsuatem.appstore.people.employee.Employee;
import xyz.zsuatem.appstore.people.employee.Programmer;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Interface {
    public static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private static String interactionInfo = "Aby wybrać danę akcję wpisz odpowiadający jej numer i naciśnij Enter.";

    public Integer readUserInputIntValue(Integer maxValue) {
        Integer userInputValue = null;
        Scanner scanner = new Scanner(System.in);
        Boolean exit = false;

        do {
            if (scanner.hasNextInt()) {
                userInputValue = scanner.nextInt();

                if (userInputValue < 0 || userInputValue > maxValue) {
                    System.out.print("Podana wartość nie ma użycia w tym kontekście\nPodaj inną wartość: ");
                } else {
                    exit = true;
                }
                scanner.nextLine();
            } else {
                System.out.print("Podana wartość nie jest liczbą całkowitą.\nPodaj inną wartość: ");
                scanner.next();
            }
        } while (!exit);

        return userInputValue;
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void exitGame() {
        System.out.println("\nMam nadzieję, że jeszcze tutaj wrócisz ;)\nDo zobaczenia!");
        System.exit(0);
    }

    public void mainMenu(GameController gameController) {
        Integer inputValue;

        clearScreen();
        System.out.println("App Store");
        System.out.println("Gra stworzona na przedmiot programowanie w Javie.\n" +
                "Zaczynasz jako freelancer a Twoim celem jest rozwinięcie firmy tak aby \"nie zaglądać do kodu\" podczas realizacji projektów.\n" + interactionInfo);
        System.out.println("1. Nowa Gra");
        System.out.println("2. Szczegółowe informacje o grze");
        System.out.println("0. Wyjdź z gry");
        System.out.print("Podaj numer: ");

        inputValue = readUserInputIntValue(2);
        switch (inputValue) {
            case 1:
                gameController.gameState = GameState.createNewGame;
                break;
            case 2:
                gameController.gameState = GameState.moreInfoAboutGame;
                break;
            default:
                gameController.gameState = GameState.exitGame;
                break;
        }
    }

    public void moreInfoAboutGame(@NotNull GameController gameController) {
        Integer inputValue;
        clearScreen();
        System.out.println("Tytuł gry: App Store");
        System.out.println("Twórca: Mateusz Pawłowski");
        System.out.println("Gra wykonana na przedmiot programowanie w Javie zgodnie z postawionymi wymaganiami.\n" +
                "Dalszy jej rozwój na ten moment nie jest planowany jednak znalezione błędy można zgłaszać a postaram się je naprawić.");
        System.out.println("\n\n" + interactionInfo);
        System.out.println("0. Powrót do menu głównego.");
        System.out.print("Podaj numer: ");

        inputValue = readUserInputIntValue(0);
        gameController.gameState = GameState.mainMenu;
    }

    public Integer createNewGame(GameController gameController) {
        Integer inputValue;

        clearScreen();
        System.out.println("Tworzenie nowej gry");
        System.out.println("Tutaj możesz stworzyć nową grę. Wystarczy, że teraz podaj liczbę graczy którzy mają brać udział w rozgrywce (max 10).\n" +
                "Jeśli wpiszesz 0 wrócisz do głównego menu.\n\n" +
                "Aby dać wszystkim równe szanse na początku każdej tury kolejność rozgrywania będzie losowana.");
        System.out.print("Liczba graczy: ");

        inputValue = readUserInputIntValue(10);
        if (inputValue != 0) {
            gameController.gameState = GameState.createNewPlayer;
        } else {
            gameController.gameState = GameState.mainMenu;
        }

        return inputValue;
    }

    public Player createNewPlayer(GameController gameController, Integer playerNumber, Integer numberOfPlayers) {
        Player newPlayer;
        Scanner scanner;

        clearScreen();
        System.out.println(String.format("Tworzenie nowego gracza %s z %s.", playerNumber, numberOfPlayers));
        System.out.println("Wpisz nazwę gracza i nasiśnij Enter.");
        System.out.print("Nazwa gracza: ");

        if (playerNumber == numberOfPlayers) {
            gameController.gameState = GameState.playerMenu;
        }
        scanner = new Scanner(System.in);
        newPlayer = new Player(scanner.nextLine());
        return newPlayer;
    }

    public void playerMenu(@NotNull GameController gameController, @NotNull Player player) {
        Integer inputValue;

        clearScreen();
        System.out.println(String.format("Dzisiaj jest:\t\t\t%s", gameController.getFormattedDate()));
        System.out.println(String.format("Gracz: %s\t\t\tPosiada na koncie: %s zł", player.getPlayerName(), decimalFormat.format(player.getMoney())));
        System.out.println(interactionInfo);
        System.out.println("1. Przeglądaj realizowane projekty");
        System.out.println("2. Przeglądaj nowe dostepne projekty");
        System.out.println("3. Zarządzaj pracownikami");
        System.out.println("4. Zatrudnij nowych pracowników");
        System.out.println("5. Poprogramuj");
        System.out.println("6. Potestuj");
        System.out.println("7. Szukaj nowych klientów");
        System.out.println("8. Zajmij się księgowością");
        System.out.println("\t9. Zakończ turę");
        System.out.println("0. Zamknij grę. (wszystkie postępy zostaną utracone gra na razie nie wspiera zapisów)");

        inputValue = readUserInputIntValue(9);
        switch (inputValue) {
            case 1:
                gameController.gameState = GameState.myProjectsMenu;
                break;
            case 2:
                gameController.gameState = GameState.newProjectsMenu;
                break;
            case 3:
                gameController.gameState = GameState.employeesMenu;
                break;
            case 4:
                gameController.gameState = GameState.newEmployeesMenu;
                break;
            case 5:
                gameController.gameState = GameState.writeSomeCodeMenu;
                break;
            case 6:
                gameController.gameState = GameState.testCodeMenu;
                break;
            case 7:
                gameController.gameState = GameState.searchForNewClients;
                break;
            case 8:
                gameController.gameState = GameState.keepAccounts;
                break;
            case 9:
                gameController.gameState = GameState.finishTurn;
                break;
            case 0:
                gameController.gameState = GameState.exitGame;
                break;
        }
    }

    public Integer myProjectsMenu(@NotNull GameController gameController, @NotNull Player player) {
        Integer inputValue;

        clearScreen();
        System.out.println(String.format("Dzisiaj jest:\t\t\t%s", gameController.getFormattedDate()));
        System.out.println(String.format("Gracz: %s\t\t\tPosiada na koncie: %s zł", player.getPlayerName(), decimalFormat.format(player.getMoney())));
        System.out.println("Wybierz jeden z projektów, aby dowiedzieć się o nim więcej. Wpisz 0, aby powrócić do poprzedniego menu.");
        System.out.println(interactionInfo);
        System.out.println("Realizowane projekty:");

        Integer projectNumber = 1;
        for (String basicProjectInfo : player.getProjectsList()) {
            System.out.println(projectNumber + ". " + basicProjectInfo);
            projectNumber++;
        }

        inputValue = readUserInputIntValue(projectNumber);
        if (inputValue == 0) {
            gameController.gameState = GameState.playerMenu;
        } else {
            gameController.gameState = GameState.myProjectInfo;
        }

        return inputValue - 1;
    }

    public void myProjectInfo(@NotNull GameController gameController, @NotNull Player player, Integer projectId) {
        Integer inputValue;

        clearScreen();
        System.out.println(String.format("Dzisiaj jest:\t\t\t%s", gameController.getFormattedDate()));
        System.out.println(String.format("Gracz: %s\t\t\tPosiada na koncie: %s zł", player.getPlayerName(), decimalFormat.format(player.getMoney())));
        System.out.println("Informacje o projekcie. Aby wrócić do porzedniego menu wpisz 0 i naciśnij Enter.");
        System.out.println(player.getProjectById(projectId).getProjectInfo());

        System.out.println();
        System.out.println("Pracownicy przypisani do projektu (dotyczy tylko programistów i podwykonawców): ");

        Integer employeeNumber = 1;
        for (Human human : player.getProjectById(projectId.intValue()).getEmployeeHumanList()) {
            if (human instanceof Subcontractor) {
                System.out.println(employeeNumber + ". " + ((Subcontractor) human).getBasicSubcontractorInfo());

            } else if (human instanceof Employee) {
                System.out.println(employeeNumber + ". " + ((Employee) human).getBasicEmployeeInfo());
            }

            employeeNumber++;
        }

        System.out.println();
        System.out.println("Chcesz dadać lub usunać pracownika z projektu? Wpisz: " + employeeNumber);


        System.out.println();
        if (player.getProjectById(projectId).isReady()) {
            System.out.println("Ten projekt jest gotowy do oddania!\nCzy chcesz go oddać?");
            System.out.println((employeeNumber + 1) + ". Tak");
            System.out.println((employeeNumber + 2) + ". Nie");
        } else {
            System.out.println("Ten projekt nie jest jeszcze gotowy do oddania. Popracuj jeszcze nad nim.");
        }

        inputValue = readUserInputIntValue((employeeNumber + 2));
        if (inputValue == (employeeNumber + 2)) {
            System.out.println("\n\nOddano projekt.");

            for (int i = 3; i >= 1; i--) {
                try {
                    System.out.println(i + "...");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            gameController.gameState = GameState.myProjectsMenu;
        } else if (inputValue == employeeNumber) {
            gameController.gameState = GameState.myProjectInfoEmployees;
        } else {
            gameController.gameState = GameState.myProjectsMenu;
        }
    }

    public void myProjectInfoEmployees(@NotNull GameController gameController, @NotNull Player player, Integer projectId) {
        Integer inputValue;
        Map<Integer, Human> humanMap = new HashMap<>();

        clearScreen();
        System.out.println(String.format("Dzisiaj jest:\t\t\t%s", gameController.getFormattedDate()));
        System.out.println(String.format("Gracz: %s\t\t\tPosiada na koncie: %s zł", player.getPlayerName(), decimalFormat.format(player.getMoney())));
        System.out.println("Informacje o projekcie i pracownikach do niego przypisanych. Aby wrócić do porzedniego menu wpisz 0 i naciśnij Enter.");
        System.out.println(player.getProjectById(projectId).getProjectInfo());

        System.out.println("\n\nInformacje o pracownikach przypisanych do projektu (dotyczy tylko programistów i podwykonawców)");
        System.out.println("Jeśli pracownik jest już dodany do projektu to podanie jego numeru spowoduje usunięcie go z projektu.\n" +
                "Jeśli pracownik nie jest przypisany do projektu podanie jego numeru spowoduje przypisanie go do projektu.");
        System.out.println("Przypisani pracownicy (dotyczy tylko programistów i podwykonawców): ");

        Integer employeeNumber = 1;
        for (Subcontractor subcontractor : player.getSubcontractorsByProject(player.getProjectById(projectId.intValue()))) {
            humanMap.put(employeeNumber, subcontractor);
            System.out.println(employeeNumber + ". " + subcontractor.getBasicSubcontractorInfo());
            employeeNumber++;
        }

        for (Employee employee : player.getProgrammersByProject(player.getProjectById(projectId.intValue()))) {
            humanMap.put(employeeNumber, employee);
            System.out.println(employeeNumber + ". " + employee.getBasicEmployeeInfo());
            employeeNumber++;
        }

        System.out.println("\n\n");
        System.out.println("Pozostali wolni pracownicy (dotyczy tylko programistów i podwykonawców): ");
        for (Subcontractor subcontractor : player.getSubcontractorsByProject(null)) {
            humanMap.put(employeeNumber, subcontractor);
            System.out.println(employeeNumber + ". " + subcontractor.getBasicSubcontractorInfo());
            employeeNumber++;
        }

        for (Employee employee : player.getProgrammersByProject(null)) {
            humanMap.put(employeeNumber, employee);
            System.out.println(employeeNumber + ". " + employee.getBasicEmployeeInfo());
            employeeNumber++;
        }

        inputValue = readUserInputIntValue(employeeNumber - 1);
        System.out.println("\n\nZmienianie przypisania pracownika.");

        if (humanMap.get(inputValue.intValue()) instanceof Subcontractor) {
            if (((Subcontractor) humanMap.get(inputValue.intValue())).getProject() != null) {
                ((Subcontractor) humanMap.get(inputValue.intValue())).setProject(null);
                player.getProjectById(projectId.intValue()).removeEmployeeHumanFromProject(((Subcontractor) humanMap.get(employeeNumber.intValue())));
            } else {
                ((Subcontractor) humanMap.get(inputValue.intValue())).setProject(player.getProjectById(projectId.intValue()));
                player.getProjectById(projectId.intValue()).addEmployHumanToProject(((Subcontractor) humanMap.get(employeeNumber.intValue())));
            }

        } else if (humanMap.get(inputValue.intValue()) instanceof Programmer) {
            if (((Programmer) humanMap.get(inputValue.intValue())).getProject() != null) {
                ((Programmer) humanMap.get(inputValue.intValue())).setProject(null);
                player.getProjectById(projectId.intValue()).removeEmployeeHumanFromProject(((Programmer) humanMap.get(employeeNumber.intValue())));
            } else {
                ((Programmer) humanMap.get(inputValue.intValue())).setProject(player.getProjectById(projectId.intValue()));
                player.getProjectById(projectId.intValue()).addEmployHumanToProject(((Programmer) humanMap.get(employeeNumber.intValue())));
            }
        }

        for (int i = 3; i >= 1; i--) {
            try {
                System.out.println(i + "...");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public Integer newProjectsMenu(@NotNull GameController gameController, @NotNull Player player) {
        Integer inputValue;

        clearScreen();
        System.out.println(String.format("Dzisiaj jest:\t\t\t%s", gameController.getFormattedDate()));
        System.out.println(String.format("Gracz: %s\t\t\tPosiada na koncie: %s zł", player.getPlayerName(), decimalFormat.format(player.getMoney())));
        System.out.println("Wybierz jeden z projektów, aby dowiedzieć się o nim więcej. Wpisz 0, aby powrócić do poprzedniego menu.");
        System.out.println(interactionInfo);
        System.out.println("Lista nowych projektów:");

        Integer projectNumber = 1;
        for (String basicProjectInfo : gameController.getProjectsList()) {
            System.out.println(projectNumber + ". " + basicProjectInfo);
            projectNumber++;
        }

        inputValue = readUserInputIntValue(projectNumber);
        if (inputValue == 0) {
            gameController.gameState = GameState.playerMenu;
        } else {
            gameController.gameState = GameState.newProjectInfo;
        }

        return inputValue - 1;
    }

    public Integer newProjectInfo(@NotNull GameController gameController, @NotNull Player player, Integer projectId) {
        Integer inputValue;

        clearScreen();
        System.out.println(String.format("Dzisiaj jest:\t\t\t%s", gameController.getFormattedDate()));
        System.out.println(String.format("Gracz: %s\t\t\tPosiada na koncie: %s zł", player.getPlayerName(), player.getMoney()));
        System.out.println("Informacje o nowym projekcie. Aby wrócić do porzedniego menu wpisz 0 i naciśnij Enter.");
        System.out.println(gameController.getProjectById(projectId).getProjectInfo());

        System.out.println();
        System.out.println("Możesz się podjąć realizacji tego projektu!\nCzy chcesz to zrobić?");
        System.out.println("1. Tak");
        System.out.println("2. Nie");

        inputValue = readUserInputIntValue(2);
        if (inputValue == 1) {
            System.out.println("\n\nPodjąłeś się realizacji nowego projektu! Zostanie on dodany do listy twoich projektów.");

            for (int i = 3; i >= 1; i--) {
                try {
                    System.out.println(i + "...");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        gameController.gameState = GameState.newProjectsMenu;
        return inputValue;
    }

    public Integer employeeMenu(@NotNull GameController gameController, @NotNull Player player) {
        Integer inputValue;

        clearScreen();
        System.out.println(String.format("Dzisiaj jest:\t\t\t%s", gameController.getFormattedDate()));
        System.out.println(String.format("Gracz: %s\t\t\tPosiada na koncie: %s zł", player.getPlayerName(), decimalFormat.format(player.getMoney())));
        System.out.println("Wybierz jednego z pracowników, aby dowiedzieć się o nim więcej. Wpisz 0, aby powrócić do poprzedniego menu.");
        System.out.println(interactionInfo);
        System.out.println("Lista pracowników:");

        Integer employeeNumber = 1;
        for (String basicEmployeeInfo : player.getEmployeesList()) {
            System.out.println(employeeNumber + ". " + basicEmployeeInfo);
            employeeNumber++;
        }

        inputValue = readUserInputIntValue(employeeNumber);
        if (inputValue == 0) {
            gameController.gameState = GameState.playerMenu;
        } else {
            gameController.gameState = GameState.employeeInfo;
        }

        return inputValue - 1;
    }

    public void employeeInfo(@NotNull GameController gameController, @NotNull Player player, Integer employeeId) {
        Integer inputValue;

        clearScreen();
        System.out.println(String.format("Dzisiaj jest:\t\t\t%s", gameController.getFormattedDate()));
        System.out.println(String.format("Gracz: %s\t\t\tPosiada na koncie: %s zł", player.getPlayerName(), decimalFormat.format(player.getMoney())));
        System.out.println("Informacje o pracowniku. Aby wrócić do porzedniego menu wpisz 0 i naciśnij Enter.");
        System.out.println(player.getEmployeeById(employeeId).getEmployeeInfo());

        System.out.println();
        System.out.println("Możesz zwolnić tego pracownika!\nPamiętaj, że zwolnienie pracownika kosztuje i wynosi 20% pensji.\nCzy chcesz zwolnić ta osobę?");
        System.out.println("1. Tak");
        System.out.println("2. Nie");


        inputValue = readUserInputIntValue(2);
        if (inputValue == 1) {
            System.out.println("\n\nZwalniam pracownika...");
            player.dismissEmployee(employeeId);

            for (int i = 3; i >= 1; i--) {
                try {
                    System.out.println(i + "...");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        gameController.gameState = GameState.employeesMenu;
    }

    public Integer newEmployeeMenu(@NotNull GameController gameController, @NotNull Player player) {
        Integer inputValue;

        clearScreen();
        System.out.println(String.format("Dzisiaj jest:\t\t\t%s", gameController.getFormattedDate()));
        System.out.println(String.format("Gracz: %s\t\t\tPosiada na koncie: %s zł", player.getPlayerName(), decimalFormat.format(player.getMoney())));
        System.out.println("Wybierz jednego z pracowników, aby dowiedzieć się o nim więcej. Wpisz 0, aby powrócić do poprzedniego menu.");
        System.out.println(interactionInfo);
        System.out.println("Lista pracowników do zatrudnienia:");

        Integer employeeNumber = 1;
        for (String basicEmployeeInfo : gameController.getEmployeesList()) {
            System.out.println(employeeNumber + ". " + basicEmployeeInfo);
            employeeNumber++;
        }

        inputValue = readUserInputIntValue(employeeNumber);
        if (inputValue == 0) {
            gameController.gameState = GameState.playerMenu;
        } else {
            gameController.gameState = GameState.newEmployeeInfo;
        }

        return inputValue - 1;
    }

    public Integer newEmployeeInfo(@NotNull GameController gameController, @NotNull Player player, Integer employeeId) {
        Integer inputValue;

        clearScreen();
        System.out.println(String.format("Dzisiaj jest:\t\t\t%s", gameController.getFormattedDate()));
        System.out.println(String.format("Gracz: %s\t\t\tPosiada na koncie: %s zł", player.getPlayerName(), decimalFormat.format(player.getMoney())));
        System.out.println("Informacje o pracowniku możliwym do zatrudnienia. Aby wrócić do porzedniego menu wpisz 0 i naciśnij Enter.");
        System.out.println(gameController.getEmployeeById(employeeId).getEmployeeInfo());

        System.out.println();
        System.out.println("Możesz zatrudnić tego pracownika!\nPamiętaj, że zatrudnienie pracownika kosztuje i wynosi 20% pensji.\nCzy chcesz zatrudnić tą osobę?");
        System.out.println("1. Tak");
        System.out.println("2. Nie");

        inputValue = readUserInputIntValue(2);
        if (inputValue == 1) {
            System.out.println("\n\nZatrudniam pracownika...");

            for (int i = 3; i >= 1; i--) {
                try {
                    System.out.println(i + "...");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        gameController.gameState = GameState.newEmployeesMenu;
        return inputValue;
    }
}
