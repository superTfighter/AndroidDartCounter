package com.supert.dartcalculator.model;

import java.util.List;

public interface IGame {

    void addPlayer(IPlayer player);
    IPlayer getPlayer(String name);

    IPlayer getCurrentPlayer();

    List<IPlayer> getPlayers();

    int getMaxScore();
    void setMaxScore(int score);

    void beginGame();

    void nextTurn();


}
