package com.supert.dartcalculator.controller;

import android.util.Log;

import androidx.annotation.NonNull;

import com.supert.dartcalculator.model.GameModel;
import com.supert.dartcalculator.model.IGame;
import com.supert.dartcalculator.model.Player;
import com.supert.dartcalculator.view.IStartView;

public class StartController implements IStartController {

    private IStartView view;
    private IGame gameData;

    public StartController()
    {
        gameData = new GameModel();
    }

    @Override
    public void bind(IStartView view) {
        this.view = view;
    }

    @Override
    public void onAddPlayerClicked(@NonNull Player player) {
        gameData.addPlayer(player);
    }

    @Override
    public void onSetMaxScore(@NonNull int score) {
        gameData.setMaxScore(score);
    }

    @Override
    public Boolean checkStartGameConditions() {
        if(gameData.getPlayers().size() > 0 && gameData.getMaxScore() > 0)
            return true;

        return false;
    }

    @Override
    public IGame getGameParameters() {
        return this.gameData;
    }
}
