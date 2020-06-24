package xyz.zsuatem.appstore;

import org.jetbrains.annotations.NotNull;
import xyz.zsuatem.appstore.people.Player;

import java.util.Scanner;

public class Interface {
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
        System.out.println(String.format("Dzisiaj jest:\t\t\t%s", gameController.getDate()));
        System.out.println(String.format("Gracz: %s\t\t\tPosiada na koncie: %s zł", player.getPlayerName(), player.getMoney()));
        System.out.println(interactionInfo);
        System.out.println("1. Przeglądaj realizowane projekty");
        System.out.println("2. Przeglądaj nowe dostepne projekty");
        System.out.println("3. Zarządzaj pracownikami");
        System.out.println("4. Zatrudnij nowych pracowników");
        System.out.println("5. Poprogramuj");
        System.out.println("6. Potestuj");
        System.out.println("7. Szukaj nowych klientów");
        System.out.println("8. Zajmij się księgowością");
        System.out.println("\t0. Zakończ turę");

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
            case 0:
                gameController.gameState = GameState.finishTurn;
                break;
        }
    }
}
