package xyz.zsuatem.appstore;

public enum GameState {
    //Menu
    mainMenu,
    moreInfoAboutGame,
    exitGame,

    //Create
    createNewGame,
    createNewPlayer,

    //Players
    showPlayersOrder,

    //Player
    playerMenu,

    //Player - project
    myProjectsMenu,
    myProjectInfo,
    newProjectsMenu,
    newProjectInfo,

    //Player - employee
    employeesMenu,
    employeeInfo,
    newEmployeesMenu,
    newEmployeeInfo,

    //Player - actions
    writeSomeCodeMenu,
    writeSomeCodeInfo,
    testCodeMenu,
    testCodeInfo,
    searchForNewClients,
    keepAccounts,
    finishTurn
}
