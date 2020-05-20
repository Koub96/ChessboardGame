package com.example.chessboardgame;

import android.widget.Button;

import com.example.chessboardgame.model.Model;
import com.example.chessboardgame.model.ModelContract;
import com.example.chessboardgame.views.MainActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
public class ModelGettersTest {
    MainActivity activity;
    ModelContract m;

    @Before
    public void setup(){
        activity = Robolectric.setupActivity(MainActivity.class);
        m = new Model();
    }


    @Test
    public void ConfirmThatGetTableReturnsA2DTableAfterUpdate(){

        Button[][] positions = new Button[10][10];

        for (int i = 0; i < positions.length; i++){
            for (int j = 0; j < positions.length; j++){
                Button btn = new Button(activity.getApplicationContext());
                positions[i][j] = btn;

            }
        }

        m.UpdateTable(positions);

        Assert.assertNotNull(m.getTable());
    }

    @Test
    public void ConfirmThatGetStartPosReturnsArrayAfterInitialize(){
        int [] startPos = new int[2];
        startPos[0] = 1;
        startPos[0] = 2;
        m.setStartPos(startPos);

        Assert.assertNotNull(m.getStartPos());
    }

    @Test
    public void ConfirmThatGetCasesReturnsListAfterInitialize(){
        List<Boolean> cases = new ArrayList<Boolean>();

        cases.add(true);
        cases.add(false);
        cases.add(false);
        cases.add(false);
        cases.add(false);


        m.setCases(cases);
        Assert.assertNotNull(m.getCases());
    }

    @Test
    public void ConfirmThatGetIdsReturnsArrayAfterInitialize(){
        int [][] id = new int[10][10];

        int counter = 0;
        for(int i =0; i < id.length; i++){
            for(int j = 0; j < id.length; j++){
                id[i][j] = counter;
                counter++;
            }
        }


        m.setIds(id);
        Assert.assertNotNull(m.getIds());
    }
}
