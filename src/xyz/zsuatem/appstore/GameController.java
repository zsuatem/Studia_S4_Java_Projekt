package xyz.zsuatem.appstore;

import xyz.zsuatem.appstore.people.Employee;
import xyz.zsuatem.appstore.people.Player;

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

    public String getDate() {
        return calendar.getFormattedDate();
    }

    public void play() {

        while (gameState != GameState.exitGame) {
            switch (gameState) {
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
                    }
                    break;
                //Players
                case playerMenu:
                    anInterface.playerMenu(this, players.get(0));
                    break;
            }
        }

        anInterface.exitGame();
    }

    private void randomizePlayersOrder() {
        Collections.shuffle(players);
    }
}
