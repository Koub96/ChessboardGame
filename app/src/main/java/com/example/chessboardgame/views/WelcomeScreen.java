package com.example.chessboardgame.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chessboardgame.R;

public class WelcomeScreen extends AppCompatActivity {
    public static final String MESSAGE = "Dimension";
    private long backPressedTime;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        final EditText input = findViewById(R.id.input);
        Button beginBtn = findViewById(R.id.beginBtn);
        final Intent intent = new Intent(this, MainActivity.class);


        beginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = input.getText().toString();
                userInput = userInput.trim();

                try {
                    int dimension = Integer.parseInt(userInput);
                    if(dimension >= 6 && dimension <= 16){
                        intent.putExtra(MESSAGE,dimension);
                        startActivity(intent);
                    }else{
                        errorDimensionMsg();
                    }
                }catch(NumberFormatException e){
                    errorNotANumberMsg();
                }
            }
        });


    }

    private void errorDimensionMsg(){
        Toast.makeText(getApplicationContext(),"Please enter a value from 6 to 16!",Toast.LENGTH_LONG).show();
    }

    private void errorNotANumberMsg(){
        Toast.makeText(getApplicationContext(),"Please enter a number from 6 to 16!",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed(){
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else{
            Toast.makeText(getBaseContext(),"Press Back again to exit.",Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}
