package com.example.rockpaperscissors;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rockpaperscissors.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random rand;
    private int playerScore;
    private int computerScore;
    private Weapon playerWeapon;
    private Weapon computerWeapon;
    private Weapon[] weapons;

    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.rockpaperscissors.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        rand = new Random();

        weapons = Weapon.values();

        playerScore = 0;
        computerScore = 0;

        textView3 = binding.textView3;
        textView4 = binding.textView4;
        textView5 = binding.textView5;
        textView6 = binding.textView6;

        binding.rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(v);
                computerTurn();
                scoring();
            }
        });

        binding.paperButton.setOnClickListener(v -> {
            playerTurn(v);
            computerTurn();
            scoring();
        });

        binding.scissorsButton.setOnClickListener(v -> {
            playerTurn(v);
            computerTurn();
            scoring();
        });


    }
    private void playerTurn(View v) {
        int id = v.getId();

        if (id == R.id.rock_button) {
            playerWeapon = Weapon.ROCK;
        } else if (id == R.id.paper_button) {
            playerWeapon = Weapon.PAPER;
        } else if (id == R.id.scissors_button) {
            playerWeapon = Weapon.SCISSORS;
        }
        String playerSelection = getString(R.string.player_selection, playerWeapon.toString());
        textView4.setText(playerSelection);
    }

    private void computerTurn() {
        computerWeapon = weapons[rand.nextInt(weapons.length)];
        String computerSelection = getString(R.string.computer_selection, computerWeapon.toString());
        textView5.setText(computerSelection);
    }

    private void scoring() {
        // EVEN distance -> Lower value wins
        // ODD distance -> Higher value wins

        int condition = playerWeapon.compareTo(computerWeapon);

        switch (condition) {
            case -2:
                textView6.setText(R.string.rock_win);
                playerScore++;
                break;
            case -1:
                if (playerWeapon == Weapon.ROCK) textView6.setText(R.string.paper_win);
                if (playerWeapon == Weapon.PAPER) textView6.setText(R.string.scissor_win);
                computerScore++;
                break;
            case 0:
                textView6.setText(R.string.tie);
                break;
            case 1:
                if (playerWeapon == Weapon.PAPER) textView6.setText(R.string.paper_win);
                if (playerWeapon == Weapon.SCISSORS) textView6.setText(R.string.scissor_win);
                playerScore++;
                break;
            case 2:
                textView6.setText(R.string.rock_win);
                computerScore++;
                break;

        }
        String winner = getString(R.string.score, playerScore, computerScore);
        textView3.setText(winner);
    }
}