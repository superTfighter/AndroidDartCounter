package com.supert.dartcalculator.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.supert.dartcalculator.R;
import com.supert.dartcalculator.controller.GameController;
import com.supert.dartcalculator.controller.IGameController;
import com.supert.dartcalculator.model.GameModel;
import com.supert.dartcalculator.model.IGame;

public class GameActivity extends AppCompatActivity implements IGameView {

    private IGameController gamePresenter;
    private TextView playerName;
    private EditText pointsText;
    private Button nextPlayerButton , addScoreButton;

    private IGame gameModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Get init game data
        gameModel = (GameModel) getIntent().getSerializableExtra("Game");
        gamePresenter = new GameController();
        gamePresenter.bind(this,gameModel);

        this.gameModel.beginGame();

        // View resources
        playerName = findViewById(R.id.current_player);
        pointsText = findViewById(R.id.points_text);

        nextPlayerButton = findViewById(R.id.next_player);
        addScoreButton = findViewById(R.id.enter_point);

        // Bind buttons
        nextPlayerButton.setOnClickListener(view ->{
            this.gameModel.nextTurn();
            updatePlayerName();
        });

        addScoreButton.setOnClickListener(view ->{

            int newPoint = this.gameModel.getCurrentPlayer().getScore() - Integer.parseInt(pointsText.getText().toString());

            if(newPoint <= 0)
            {
                Toast.makeText(getApplicationContext(), "Congrats: " + this.gameModel.getCurrentPlayer().getName() + " won the game!", Toast.LENGTH_LONG).show();
            }

            this.gameModel.getCurrentPlayer().setScore(newPoint);

            updatePlayerName();
        });

        updatePlayerName();
    }

    @Override
    public void updatePlayerName() {
        playerName.setText(gameModel.getCurrentPlayer().getName() + " points: " + gameModel.getCurrentPlayer().getScore());
    }
}
