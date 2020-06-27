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
    myProjectInfoEmployees,
    newProjectsMenu,
    newProjectInfo,

    //Player - employee
    employeesMenu,
    employeeInfo,
    newEmployeesMenu,
    newEmployeeInfo,

    //Player - office
    officeMenu,

    //Player - actions
    writeSomeCodeMenu,
    testCodeMenu,
    searchForNewClients,
    keepAccounts,
    finishTurn,

    //Other
    win,
    loss
}
