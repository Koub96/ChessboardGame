package com.example.chessboardgame.model;

import android.widget.Button;

import java.util.List;

public class Model implements ModelContract {
    private Button[][] positions;
    private int [] startPos;
    private List<Boolean> cases;
    private int [][]id;
    public void UpdateTable(Button[][] positions){
        this.positions = positions;
    }

    public Button [][] getTable(){
        return positions;
    }

    public void setStartPos(int [] startPos){
        this.startPos = startPos;
    }

    public int [] getStartPos(){
        return startPos;
    }

    public void setCases(List<Boolean> cases){
        this.cases = cases;
    }

    public List<Boolean> getCases(){
        return cases;
    }

    public void setIds(int [][]id){
        this.id = id;
    }

    @Override
    public int[][] getIds() {
        return id;
    }
}
