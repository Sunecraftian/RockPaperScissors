package com.example.rockpaperscissors;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.example.rockpaperscissors.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private Random rand;
    private int playerScore;
    private int computerScore;
    private Weapon playerWeapon;
    private Weapon computerWeapon;
    private Weapon[] weapons;

    private String winner;

    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
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



//        binding.clickMe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                TextView t = binding.plotTextbox;
//                int randomPlot = random.nextInt(plot_fragments.length);
//                String plot = plot_fragments[randomPlot];
//                t.setText(plot);
//            }
//        });

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

        if (condition == 0) { // TIE
            textView6.setText(R.string.tie);
        } else if (condition % 2 == 0)
            if (condition < 0) {
                textView6.setText(R.string.rock_win);
                playerScore++;
            } else {
                textView6.setText(R.string.scissor_win);
                computerScore++;

        // TODO fix PAPER win conditions
        } else {
                if (condition < 0) {
                    textView6.setText(R.string.scissor_win);
                    computerScore++;
                } else {
                    textView6.setText(R.string.paper_win);
                    playerScore++;
                }
        }

        textView3.setText(getString(R.string.score,playerScore,computerScore));
    }
}