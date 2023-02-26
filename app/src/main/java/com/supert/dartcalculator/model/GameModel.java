package com.supert.dartcalculator.model;

import android.util.Log;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class GameModel implements IGame , Serializable {

    private List<IPlayer> players;
    private int maxScore;

    private int playerTurnIndex;


    public GameModel()
    {
        this.players = new LinkedList<IPlayer>();
        this.playerTurnIndex = 0;
    }


    @Override
    public void addPlayer(IPlayer player) {

        players.add(player);
        maxScore = 0;
    }

    @Override
    public IPlayer getPlayer(String name) {
        return null;
    }

    @Override
    public IPlayer getCurrentPlayer() {
        return this.getPlayers().get(this.playerTurnIndex);
    }

    @Override
    public List<IPlayer> getPlayers() {
        return players;
    }

    @Override
    public int getMaxScore() {
        return maxScore;
    }

    @Override
    public void setMaxScore(int score) {
        this.maxScore = score;
    }

    @Override
    public void beginGame() {

        Log.d("GameModel", "Init base scores: " + this.maxScore);

        for (IPlayer player: players
             ) {
            player.setScore(this.maxScore);
        }
    }

    @Override
    public void nextTurn()
    {
        int nextTurn = this.playerTurnIndex + 1;

        if(nextTurn >= this.players.size())
            nextTurn = 0;

        this.playerTurnIndex = nextTurn;

    }
}
