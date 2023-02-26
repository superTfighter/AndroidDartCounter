package com.supert.dartcalculator.controller;

import com.supert.dartcalculator.model.IGame;
import com.supert.dartcalculator.view.IGameView;

public interface IGameController {

    void bind(IGameView view, IGame game);

}
