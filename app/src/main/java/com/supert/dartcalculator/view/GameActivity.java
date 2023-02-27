package com.supert.dartcalculator.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.supert.dartcalculator.R;
import com.supert.dartcalculator.controller.GameController;
import com.supert.dartcalculator.controller.IGameController;
import com.supert.dartcalculator.model.GameModel;
import com.supert.dartcalculator.model.IGame;

import java.io.Serializable;

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
                // Show player won
                showWonDialog(this.gameModel.getCurrentPlayer().getName());
            }

            this.gameModel.getCurrentPlayer().setScore(newPoint);

            updatePlayerName();
        });

        updatePlayerName();
    }

    @Override
    public void updatePlayerName() {

        String playerNameS = gameModel.getCurrentPlayer().getName();
        int playerScore = gameModel.getCurrentPlayer().getScore();
        String pointMessage = getString(R.string.player_score);
        pointMessage = String.format(pointMessage,playerNameS,playerScore);

        playerName.setText(pointMessage);
    }

    @Override
    public void showWonDialog(String playerName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        String wonMessage = String.format(getString(R.string.player_won_message),playerName);

        dialogBuilder.setMessage(wonMessage).setPositiveButton(R.string.accept_new_game, (dialogInterface, i) -> {

            Intent intent = new Intent(getBaseContext(), MainActivity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("Game", (Serializable) GameActivity.this.gameModel);

            startActivity(intent);

            GameActivity.this.finish();

        });

        AlertDialog dialog = dialogBuilder.create();

        dialog.show();
    }
}
