package com.example.chessboardgame.views;
import com.example.chessboardgame.presenter.Presenter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chessboardgame.presenter.PresenterContract;
import com.example.chessboardgame.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewContract{
    private int n = 6; //default value
    private Button[][] positions;
    final private String TAG = "orientation";
    private Button findPaths;
    private Button reset;
    private PresenterContract p = new Presenter(this);

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // load the layout
        setContentView(R.layout.activity_main);

        //Getting the user input n dimension and setting the arrays.
        Intent intent = getIntent();
        n = intent.getIntExtra(WelcomeScreen.MESSAGE,6);
        positions = new Button[n][n];



        //We initiliaze the ids for the buttons on the chessboard
        p.InitializeIds(n);

        //We create the dynamic layout bases on the dimension given by the user input.
        CreateDynamicLayout();

        //For each tile of the chessboard we calculate the tile color.
        CalculateTileColor();


        //The listener for the reset Button.It calls the corresponding Presenter method to reset the board to its initial state.
        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(findPaths.getVisibility() == View.GONE){
                    findPaths.setVisibility(View.VISIBLE);
                }
                p.ResetGame();
            }
        });


        //The listener for the find path Button.It calls the corresponding Presenter method to start calculating possible paths.
        findPaths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.FindValidPaths();
            }
        });

        //The listener for the back button.It starts the WelcomeScreen Activity,thus taking us back to the home page.
        Button back = findViewById(R.id.backButton);
        final Intent backIntent = new Intent(this, WelcomeScreen.class);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.ClearClicks();
                startActivity(backIntent);
            }
        });

    }

    private void CalculateTileColor() {
        //This calculation for the colors doesnt go to the presenter because we need the context.
        int color;
        for(int i = 0; i < positions.length; i++){
            //We decide the color of each button based on its position so it resembles a chessboard.
            if(i%2 == 1){
                color = Color.BLACK;
            }else{
                color = Color.WHITE;
            }
            for (int j = 0; j < positions.length; j++){
                positions[i][j] = findViewById(p.getIds()[i][j]);
                positions[i][j].setBackgroundColor(color);
                //For each button,we initialize an onClickListener provided by the class
                positions[i][j].setOnClickListener(this);

                if(color == Color.BLACK){
                    color = Color.WHITE;
                }else if(color == Color.WHITE){
                    color = Color.BLACK;
                }

            }
        }

        p.setChessboard(positions);
    }

    private void CreateDynamicLayout() {
        LinearLayout root = findViewById(R.id.root);


        //We start building the dynamic layout based on the n given by the user.
        RelativeLayout rl1 = new RelativeLayout(this);
        rl1.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT));

        reset = new Button(this); //The reset button
        reset.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT));
        reset.setId(R.id.reset);
        reset.setText("RESET");

        findPaths = new Button(this); //The find path button.
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params.addRule(RelativeLayout.ALIGN_PARENT_END);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.topMargin = 1;
        params.rightMargin = 11;
        findPaths.setLayoutParams(params);
        findPaths.setId(R.id.findpaths);
        findPaths.setText("FIND PATHS");

        //We add the two buttons inside the relative layout and the relative layout inside our root linear layout.
        rl1.addView(reset);
        rl1.addView(findPaths);
        root.addView(rl1);

        //Each linear layout with n button is like a 1xn array.So we construct n linear Layouts
        //So that we get the nxn chessboard.
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1);
        for (int i = 0; i < n; i++){
            LinearLayout l = new LinearLayout(this);
            l.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1));

            for (int j = 0; j < n; j++){
                Button btn = new Button(this);
                btn.setLayoutParams(params2);
                btn.setTextSize(35);
                btn.setId(p.getIds()[i][j]);
                l.addView(btn);
            }
            root.addView(l);
        }
    }

    @Override
    //The on click  for each Button of the board.It calls the corresponding Presenter method to mark with X the button you pressed.
    public void onClick(View v) {
        //we invoke a presenter method
        p.drawKnight((Button)v);

    }

    @Override
    public void ErrorMsg() {
        Toast.makeText(getApplicationContext(),"No valid paths were found!",Toast.LENGTH_LONG).show();
    }

    @Override
    //This method gets called by the Presenter after the calculation of valid or not valid paths.
    //This method is responsible to draw the valid paths based on the data produced by the Presenter.
    public void DrawValidPaths(Button [][] positions,int [] start_pos,List<Boolean> cases) {
        for (int i = 0; i < cases.size(); i++){
            if(cases.get(i) == true){
                if(i == 0){
                    positions[start_pos[0] + 1][start_pos[1]].setBackgroundColor(Color.GREEN);
                    positions[start_pos[0] + 2][start_pos[1]].setBackgroundColor(Color.GREEN);

                    //alternative path
                    positions[start_pos[0]][start_pos[1]+1].setBackgroundColor(Color.BLUE);
                    positions[start_pos[0] + 1][start_pos[1]+1].setBackgroundColor(Color.BLUE);
                }else if( i == 1){
                    positions[start_pos[0] + 1][start_pos[1]].setBackgroundColor(Color.GREEN);
                    positions[start_pos[0] + 2][start_pos[1]].setBackgroundColor(Color.GREEN);

                    //alternative path
                    positions[start_pos[0]][start_pos[1]-1].setBackgroundColor(Color.BLUE);
                    positions[start_pos[0]+1][start_pos[1]-1].setBackgroundColor(Color.BLUE);

                }else if(i==2){
                    positions[start_pos[0] + 1][start_pos[1]].setBackgroundColor(Color.GREEN);
                    positions[start_pos[0] + 1][start_pos[1] - 1].setBackgroundColor(Color.GREEN);

                    //alternative path
                    positions[start_pos[0]][start_pos[1]-1].setBackgroundColor(Color.BLUE);
                    positions[start_pos[0]][start_pos[1]-2].setBackgroundColor(Color.BLUE);
                }else if(i == 3){
                    positions[start_pos[0] + 1][start_pos[1]].setBackgroundColor(Color.GREEN);
                    positions[start_pos[0] + 1][start_pos[1] + 1].setBackgroundColor(Color.GREEN);

                    //alternative path
                    positions[start_pos[0]][start_pos[1]+1].setBackgroundColor(Color.BLUE);
                    positions[start_pos[0]][start_pos[1]+2].setBackgroundColor(Color.BLUE);
                }else if(i == 4){
                    ErrorMsg();
                }
            }
        }
        //the only way to find another path is to reset the game first.
        findPaths.setVisibility(View.GONE);

    }

    @Override
    //This method checks if the button has the knight image resource set.
    //This method doesnt go to the presenter because we need the context.
    public boolean CheckResource(Button v) {
        if(v.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.knight).getConstantState())){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    //If the app goes to the background due system calls,we should save all the data representing the current state of the app.
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");

        SharedPreferences sharedPreferences = this.getSharedPreferences("ChessboardPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(p.getCases() != null) {
            editor.putBoolean("case1", p.getCases().get(0));
            editor.putBoolean("case2", p.getCases().get(1));
            editor.putBoolean("case3", p.getCases().get(2));
            editor.putBoolean("case4", p.getCases().get(3));
            editor.putBoolean("case5", p.getCases().get(4));
            editor.putString("pos1", p.getStartPos()[0]);
            editor.putString("pos2", p.getStartPos()[1]);
            editor.apply();
        }

    }

    @Override
    //When the app gets resumed and comes to the foreground,we should reset the game.
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
