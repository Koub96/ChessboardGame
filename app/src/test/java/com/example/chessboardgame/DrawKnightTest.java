package com.example.chessboardgame;

import android.widget.Button;

import com.example.chessboardgame.presenter.Presenter;
import com.example.chessboardgame.presenter.PresenterContract;
import com.example.chessboardgame.views.MainActivity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class DrawKnightTest {
    @Test
    public void ConfirmThatDrawMarksFunctionDrawsTheKnightOnAClearButton(){
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        PresenterContract p = new Presenter(activity);
        Button [][] positions = new Button[10][10];

        for (int i = 0; i < positions.length; i++){
            for (int j = 0; j < positions.length; j++){
                Button btn = new Button(activity.getApplicationContext());
                positions[i][j] = btn;

            }
        }

        //Creating the expected button that should be returned with a background knight image because it is empty and it was clicked.
        Button v = new Button(activity.getApplicationContext());
        v.setBackground(activity.getApplicationContext().getResources().getDrawable(R.drawable.knight));
        p.InitializeIds(10);
        p.setChessboard(positions);
        Assert.assertEquals(v.getBackground().getConstantState(),p.drawKnight(positions[2][2]).getBackground().getConstantState());
    }


}
