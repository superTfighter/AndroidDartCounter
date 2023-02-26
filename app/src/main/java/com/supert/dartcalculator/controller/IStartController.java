package com.supert.dartcalculator.controller;

import android.view.View;

import androidx.annotation.NonNull;

import com.supert.dartcalculator.model.IGame;
import com.supert.dartcalculator.model.IPlayer;
import com.supert.dartcalculator.model.Player;
import com.supert.dartcalculator.view.IStartView;

public interface IStartController {

    void bind(IStartView view);
    void onAddPlayerClicked(@NonNull Player player);

    void onSetMaxScore(@NonNull int score);

    Boolean checkStartGameConditions();

    IGame getGameParameters();
}
