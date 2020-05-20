package com.example.chessboardgame;

import android.widget.Button;

import com.example.chessboardgame.presenter.Presenter;
import com.example.chessboardgame.presenter.PresenterContract;
import com.example.chessboardgame.views.MainActivity;
import com.example.chessboardgame.R.drawable;

import org.apache.tools.ant.Main;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class AllPossibleCasesTest {
    MainActivity activity;
    PresenterContract p;
    Button[][] positions;

    @Before
    public void setup(){
        activity = Robolectric.setupActivity(MainActivity.class);
        p = new Presenter(activity);
        positions = new Button[10][10];
    }


    @Test
    public void ConfirmThatCheckResourceReturnsValidResult(){
        Button btn = new Button(activity.getApplicationContext());
        btn.setBackground(activity.getApplicationContext().getResources().getDrawable(drawable.knight));

        Assert.assertEquals(true,activity.CheckResource(btn));
    }

    @Test
    public void ConfirmThatCase1IsTrue(){
        for (int i = 0; i < positions.length; i++){
            for (int j = 0; j < positions.length; j++){
                Button btn = new Button(activity.getApplicationContext());
                positions[i][j] = btn;

                if (i == 0 && j == 0){
                    Button _btn = new Button(activity.getApplicationContext());
                    _btn.setBackground(activity.getApplicationContext().getResources().getDrawable(drawable.knight));
                    positions[i][j] = _btn;
                }

                if (i == 2 && j == 1){
                    Button _btn = new Button(activity.getApplicationContext());
                    _btn.setBackground(activity.getApplicationContext().getResources().getDrawable(drawable.knight));
                    positions[i][j] = _btn;
                }
            }
        }

        p.setChessboard(positions);
        Assert.assertEquals(true,p.FindValidPaths().get(0));

    }

    @Test
    public void ConfirmThatCase2IsTrue(){
        for (int i = 0; i < positions.length; i++){
            for (int j = 0; j < positions.length; j++){
                Button btn = new Button(activity.getApplicationContext());
                positions[i][j] = btn;

                if (i == 0 && j == 1){
                    Button _btn = new Button(activity.getApplicationContext());
                    _btn.setBackground(activity.getApplicationContext().getResources().getDrawable(drawable.knight));
                    positions[i][j] = _btn;
                }

                if (i == 2 && j == 0){
                    Button _btn = new Button(activity.getApplicationContext());
                    _btn.setBackground(activity.getApplicationContext().getResources().getDrawable(drawable.knight));
                    positions[i][j] = _btn;
                }
            }
        }

        p.setChessboard(positions);
        Assert.assertEquals(true,p.FindValidPaths().get(1));

    }

    @Test
    public void ConfirmThatCase3IsTrue(){
        for (int i = 0; i < positions.length; i++){
            for (int j = 0; j < positions.length; j++){
                Button btn = new Button(activity.getApplicationContext());
                positions[i][j] = btn;

                if (i == 0 && j == 2){
                    Button _btn = new Button(activity.getApplicationContext());
                    _btn.setBackground(activity.getApplicationContext().getResources().getDrawable(drawable.knight));
                    positions[i][j] = _btn;
                }

                if (i == 1 && j == 0){
                    Button _btn = new Button(activity.getApplicationContext());
                    _btn.setBackground(activity.getApplicationContext().getResources().getDrawable(drawable.knight));
                    positions[i][j] = _btn;
                }
            }
        }

        p.setChessboard(positions);
        Assert.assertEquals(true,p.FindValidPaths().get(2));

    }

    @Test
    public void ConfirmThatCase4IsTrue(){
        for (int i = 0; i < positions.length; i++){
            for (int j = 0; j < positions.length; j++){
                Button btn = new Button(activity.getApplicationContext());
                positions[i][j] = btn;

                if (i == 0 && j == 2){
                    Button _btn = new Button(activity.getApplicationContext());
                    _btn.setBackground(activity.getApplicationContext().getResources().getDrawable(drawable.knight));
                    positions[i][j] = _btn;
                }

                if (i == 1 && j == 4){
                    Button _btn = new Button(activity.getApplicationContext());
                    _btn.setBackground(activity.getApplicationContext().getResources().getDrawable(drawable.knight));
                    positions[i][j] = _btn;
                }
            }
        }

        p.setChessboard(positions);
        Assert.assertEquals(true,p.FindValidPaths().get(3));

    }

    @Test
    public void ConfirmThatCase5IsTrue(){
        for (int i = 0; i < positions.length; i++){
            for (int j = 0; j < positions.length; j++){
                Button btn = new Button(activity.getApplicationContext());
                positions[i][j] = btn;

                if (i == 0 && j == 0){
                    Button _btn = new Button(activity.getApplicationContext());
                    _btn.setBackground(activity.getApplicationContext().getResources().getDrawable(drawable.knight));
                    positions[i][j] = _btn;
                }

                if (i == 6 && j == 2){
                    Button _btn = new Button(activity.getApplicationContext());
                    _btn.setBackground(activity.getApplicationContext().getResources().getDrawable(drawable.knight));
                    positions[i][j] = _btn;
                }
            }
        }

        p.setChessboard(positions);
        Assert.assertEquals(true,p.FindValidPaths().get(4));

    }

    @Test
    public void ConfirmThatCase1HasBeenUpdatedInsideModel(){
        for (int i = 0; i < positions.length; i++){
            for (int j = 0; j < positions.length; j++){
                Button btn = new Button(activity.getApplicationContext());
                positions[i][j] = btn;

                if (i == 0 && j == 0){
                    Button _btn = new Button(activity.getApplicationContext());
                    _btn.setBackground(activity.getApplicationContext().getResources().getDrawable(drawable.knight));
                    positions[i][j] = _btn;
                }

                if (i == 2 && j == 1){
                    Button _btn = new Button(activity.getApplicationContext());
                    _btn.setBackground(activity.getApplicationContext().getResources().getDrawable(drawable.knight));
                    positions[i][j] = _btn;
                }
            }
        }

        p.setChessboard(positions);
        p.FindValidPaths();
        Assert.assertEquals(true,p.getCases().get(0));

    }

    @Test
    public void ConfirmThatCase2HasBeenUpdatedInsideModel(){
        for (int i = 0; i < positions.length; i++){
            for (int j = 0; j < positions.length; j++){
                Button btn = new Button(activity.getApplicationContext());
                positions[i][j] = btn;

                if (i == 0 && j == 1){
                    Button _btn = new Button(activity.getApplicationContext());
                    _btn.setBackground(activity.getApplicationContext().getResources().getDrawable(drawable.knight));
                    positions[i][j] = _btn;
                }

                if (i == 2 && j == 0){
                    Button _btn = new Button(activity.getApplicationContext());
                    _btn.setBackground(activity.getApplicationContext().getResources().getDrawable(drawable.knight));
                    positions[i][j] = _btn;
                }
            }
        }

        p.setChessboard(positions);
        p.FindValidPaths();
        Assert.assertEquals(true,p.getCases().get(1));

    }


    @Test
    public void ConfirmThatCase3HasBeenUpdatedInsideModel(){
        for (int i = 0; i < positions.length; i++){
            for (int j = 0; j < positions.length; j++){
                Button btn = new Button(activity.getApplicationContext());
                positions[i][j] = btn;

                if (i == 0 && j == 2){
                    Button _btn = new Button(activity.getApplicationContext());
                    _btn.setBackground(activity.getApplicationContext().getResources().getDrawable(drawable.knight));
                    positions[i][j] = _btn;
                }

                if (i == 1 && j == 0){
                    Button _btn = new Button(activity.getApplicationContext());
                    _btn.setBackground(activity.getApplicationContext().getResources().getDrawable(drawable.knight));
                    positions[i][j] = _btn;
                }
            }
        }

        p.setChessboard(positions);
        p.FindValidPaths();
        Assert.assertEquals(true,p.getCases().get(2));

    }


    @Test
    public void ConfirmThatCase4HasBeenUpdatedInsideModel(){
        for (int i = 0; i < positions.length; i++){
            for (int j = 0; j < positions.length; j++){
                Button btn = new Button(activity.getApplicationContext());
                positions[i][j] = btn;

                if (i == 0 && j == 2){
                    Button _btn = new Button(activity.getApplicationContext());
                    _btn.setBackground(activity.getApplicationContext().getResources().getDrawable(drawable.knight));
                    positions[i][j] = _btn;
                }

                if (i == 1 && j == 4){
                    Button _btn = new Button(activity.getApplicationContext());
                    _btn.setBackground(activity.getApplicationContext().getResources().getDrawable(drawable.knight));
                    positions[i][j] = _btn;
                }
            }
        }

        p.setChessboard(positions);
        p.FindValidPaths();
        Assert.assertEquals(true,p.getCases().get(3));

    }

    @Test
    public void ConfirmThatCase5HasBeenUpdatedInsideModel(){
        for (int i = 0; i < positions.length; i++){
            for (int j = 0; j < positions.length; j++){
                Button btn = new Button(activity.getApplicationContext());
                positions[i][j] = btn;

                if (i == 0 && j == 0){
                    Button _btn = new Button(activity.getApplicationContext());
                    _btn.setBackground(activity.getApplicationContext().getResources().getDrawable(drawable.knight));
                    positions[i][j] = _btn;
                }

                if (i == 6 && j == 2){
                    Button _btn = new Button(activity.getApplicationContext());
                    _btn.setBackground(activity.getApplicationContext().getResources().getDrawable(drawable.knight));
                    positions[i][j] = _btn;
                }
            }
        }

        p.setChessboard(positions);
        p.FindValidPaths();
        Assert.assertEquals(true,p.getCases().get(4));

    }
}
