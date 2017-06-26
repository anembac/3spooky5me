package com.tda367.infinityrun.Utils;
/*
* These are all of the screens used in the game, this utility enum has the purpose of allowing screens to notify the
* ScreenManager about which screen should be opened without having to import the actual screen classes, which would
* otherwise be required in order to reference that particular screen.
*/
public enum ScreenStates {
    GameScreen,
    LoadScreen,
    MainMenuScreen,
    PauseMenuScreen,
    ShopScreen
}
