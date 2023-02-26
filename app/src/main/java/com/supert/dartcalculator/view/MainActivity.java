package com.supert.dartcalculator.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.supert.dartcalculator.R;
import com.supert.dartcalculator.controller.IStartController;
import com.supert.dartcalculator.controller.StartController;
import com.supert.dartcalculator.model.GameModel;
import com.supert.dartcalculator.model.Player;
import com.supert.dartcalculator.model.PlayerListAdapter;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IStartView {

    private Button addPlayerButton, startGameButton;
    private EditText playerNameText;
    private NumberPicker maxPointText;
    private ListView playersList;

    private IStartController startPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init controller and bind it
        startPresenter = new StartController();
        startPresenter.bind(this);

        //List init
        playersList = findViewById(R.id.players_list);

        PlayerListAdapter adapter = new PlayerListAdapter(getApplicationContext(), startPresenter.getGameParameters().getPlayers());
        playersList.setAdapter(adapter);

        // Find activity elements
        addPlayerButton = findViewById(R.id.add_player_button);
        startGameButton = findViewById(R.id.start_game);

        playerNameText = findViewById(R.id.player_name_text);
        maxPointText = findViewById(R.id.max_point);

        if (maxPointText != null) {
            maxPointText.setMinValue(101);
            maxPointText.setMaxValue(501);
            maxPointText.setWrapSelectorWheel(true);
            maxPointText.setOnValueChangedListener((picker, oldVal, newVal) -> {
                startPresenter.onSetMaxScore(newVal);
            });
        }
        
        playersList = findViewById(R.id.players_list);

        // Override onClick button function to controller functions
        addPlayerButton.setOnClickListener(view -> {

            int currentMaxScore = startPresenter.getGameParameters().getMaxScore();

            String playerNameString = playerNameText.getText().toString();

            if(!TextUtils.isEmpty(playerNameString) && !playerNameString.equals("Enter player name"))
            {
                startPresenter.onAddPlayerClicked(new Player(playerNameString));
                Toast.makeText(getApplicationContext(), "Player added: " + playerNameString, Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Please enter a name!", Toast.LENGTH_SHORT).show();
            }

            adapter.notifyDataSetChanged();
            startPresenter.onSetMaxScore(currentMaxScore);
        });

        startGameButton.setOnClickListener(view -> {

            if(startPresenter.checkStartGameConditions()) {

                //Start new intent
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra("Game", (Serializable) startPresenter.getGameParameters());

                startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Check if everything is OK!", Toast.LENGTH_SHORT).show();

            }
        });
    }



}
