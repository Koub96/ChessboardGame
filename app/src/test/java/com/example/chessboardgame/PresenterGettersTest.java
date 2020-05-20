package com.example.chessboardgame;


import android.widget.Button;

import com.example.chessboardgame.presenter.Presenter;
import com.example.chessboardgame.presenter.PresenterContract;
import com.example.chessboardgame.views.MainActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class PresenterGettersTest {
    PresenterContract p;
    MainActivity activity;
    Button[][] positions;
    int[][] id;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(MainActivity.class);

        p = new Presenter(activity);
        positions = new Button[10][10];
        id = new int[10][10];

        int counter = 0;
        for(int i =0; i < id.length; i++){
            for(int j = 0; j < id.length; j++){
                id[i][j] = counter;
                counter++;
            }
        }

        for (int i = 0; i < positions.length; i++){
            for (int j = 0; j < positions.length; j++){
                Button btn = new Button(activity.getApplicationContext());
                positions[i][j] = btn;

            }
        }

        p.setChessboard(positions);
    }


    @Test
    public void ConfirmThatGetTableReturnsArrayAfterResetGameFunction(){
        p.ResetGame();

        Assert.assertNotNull(p.getChessTable());
    }

    @Test
    public void ConfirmThatGetCasesReturnsAListAfterFindPathsFunction(){
        p.InitializeIds(10);

        p.drawKnight(positions[2][2]);
        p.drawKnight(positions[3][5]);
        p.FindValidPaths();

        Assert.assertNotNull(p.getCases());
    }

    @Test
    public void ConfirmThatGetStartPosReturnsArrayAfterFindPathsFunction(){
        p.InitializeIds(10);

        p.drawKnight(positions[2][2]);
        p.drawKnight(positions[3][5]);
        p.FindValidPaths();

        Assert.assertNotNull(p.getStartPos());
    }


    @Test
    public void ConfirmThatGetIdsReturnsArrayAfterInitializeIdsFunction(){
        p.InitializeIds(10);

        Assert.assertNotNull(p.getIds());
    }

}
