package com.supert.dartcalculator.model;

import java.io.Serializable;

public class Player implements  IPlayer, Serializable {

    private String name;
    private int score;

    public Player(String name)
    {
        this.setName(name);
        this.score = 0;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void setScore(int score) {

        this.score = score;

    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
