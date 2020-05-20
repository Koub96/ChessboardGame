package com.example.chessboardgame.presenter;

import android.graphics.Color;
import android.widget.Button;

import com.example.chessboardgame.R;
import com.example.chessboardgame.model.Model;
import com.example.chessboardgame.model.ModelContract;
import com.example.chessboardgame.views.ViewContract;

import java.util.ArrayList;
import java.util.List;

public class Presenter implements PresenterContract {
    private static int clicks = 0;

    ViewContract v;
    ModelContract m = new Model();
    public Presenter(ViewContract v){
        this.v = v;
    }

    public Button drawKnight(Button v){
        int [][] id = getIds();
        Button[][] positions = m.getTable();
        if(this.v.CheckResource(v)){ //if there is a knight on that button
            DetermineButtonColor(v,id); //reset it back to its original color.
            if(clicks == 1){  //if you had only one click more
                clicks = 0; //reset it to two clicks
            }else{ //else if this was the last click you had, reset it to one click
                clicks = 1;
            }
        }else{ //if you clicked on an empty button
            if(clicks == 2){ //and you have no more clicks,then do nothing and return
                return v;
            }

            //else if you have at least one click draw the knight and update the clicks.
            v.setBackgroundResource(R.drawable.knight);
            clicks++;
        }

        //update the chessboard array on the model.
        positions = UpdateTable(positions,v,id);
        m.UpdateTable(positions);
        //we are returning the button for the purpose of testing.
        return v;
    }

    public void ResetGame(){
        int color;
        //getting the chessboard from the model.
        Button[][] positions = m.getTable();
        for(int i = 0; i < positions.length; i++){
            if(i%2 == 1){
                color = Color.BLACK;
            }else{
                color = Color.WHITE;
            }
            for (int j = 0; j < positions.length; j++){
                positions[i][j].setBackgroundColor(color);
                if(color == Color.BLACK){
                    color = Color.WHITE;
                }else if(color == Color.WHITE){
                    color = Color.BLACK;
                }
                clicks = 0;
            }
        }
        //updating the model with the new chessboard.
        m.UpdateTable(positions);
    }

    public List<Boolean> FindValidPaths(){
        //Getting the latest state of the chessboard.
        Button[][] positions = m.getTable();

        //initializing the cases and data structures we need to find valid paths.
        int pos_found = 0;
        int [] first_pos = new int[2];
        int [] second_pos = new int[2];

        List<Boolean> cases = new ArrayList<Boolean>();
        boolean case1 = false;
        boolean case2 = false;
        boolean case3 = false;
        boolean case4 = false;
        boolean case5 = false;

        cases.add(case1);
        cases.add(case2);
        cases.add(case3);
        cases.add(case4);
        cases.add(case5);



        //Search the chessboard for the start and end position of the knight.
        for(int i = 0; i < positions.length; i++){
            for(int j = 0; j < positions.length; j++){
                if(v.CheckResource(positions[i][j]) && pos_found == 0){
                    first_pos[0] = i;
                    first_pos[1] = j;
                    pos_found = 1;
                }else if(v.CheckResource(positions[i][j]) && pos_found == 1){
                    second_pos[0] = i;
                    second_pos[1] = j;
                    pos_found = 2;
                }
            }
        }

        //If none or one knight is drawn on the chessboard update the model and call
        //DrawValidPaths so that the error message can be printed.
        if (pos_found == 0 || pos_found == 1){
            cases.set(4,true);
            m.setCases(cases);
            m.setStartPos(first_pos);
            v.DrawValidPaths(positions,first_pos,cases);
            return cases;
        }

        //Log.d("path","founding paths has started...");
        if(first_pos[0] <= second_pos[0]){
            if((first_pos[0] + 2 == second_pos[0]) && (first_pos[1] + 1 == second_pos[1])){
                //Log.d("path","path was found with case 1");
                cases.set(0,true);
            }
            if((first_pos[0] + 2 == second_pos[0]) && (first_pos[1] - 1 == second_pos[1])){
                //Log.d("path","path was found with case 2");
                cases.set(1,true);
            }
            if((first_pos[0] + 1 == second_pos[0]) && (first_pos[1] - 2 == second_pos[1])){
                //Log.d("path","path was found with case 3");
                cases.set(2,true);
            }
            if((first_pos[0] + 1 == second_pos[0]) && (first_pos[1] + 2 == second_pos[1])){
                //Log.d("path","path was found with case 4");
                cases.set(3,true);

            }
            if((cases.get(0) == false) && (cases.get(1) == false) && (cases.get(2) == false) && (cases.get(3) == false)){
                //Log.d("path","no path was found");
                cases.set(4,true);
            }

        }
        m.setCases(cases);
        m.setStartPos(first_pos);
        v.DrawValidPaths(positions,first_pos,cases);
        //we are returning the cases for the purpose of testing.
        return cases;
    }


    public Button[][] getChessTable(){
        return m.getTable();
    }

    //The cases represent all the possibilities when playing with a knight given a start and end position.
    public List<Boolean> getCases(){
        return m.getCases();
    }

    public String[] getStartPos(){
        //We convert the int array to string array so it would be easier to save it with sharedPreferences.
        String [] startPos = new String[2];
        int [] temp = m.getStartPos();
        for(int i = 0; i < startPos.length; i++){
            startPos[i] = String.valueOf(temp[i]);
        }
        return startPos;
    }

    @Override
    public void InitializeIds(int n) {
        int counter = 0;
        int [][]id = new int[n][n];
        for(int i =0; i < n; i++){
            for(int j = 0; j < n; j++){
                id[i][j] = counter;
                counter++;
            }
        }
        m.setIds(id);
    }

    @Override
    public int[][] getIds() {
        return m.getIds();
    }

    @Override
    public void ClearClicks() {
        clicks = 0;
    }

    @Override
    public void setChessboard(Button[][] positions) {
        m.UpdateTable(positions);
    }


    //helping function that checks if the given button exists by finding its id in the id array and reseting its color.
    private void DetermineButtonColor(Button v,int [][]id){
        int color;
        for (int i =0; i < id.length; i++){
            if(i%2 == 1){
                color = Color.BLACK;
            }else{
                color = Color.WHITE;
            }
            for (int j=0; j <id.length; j++){
                if (v.getId() == id[i][j]){
                    v.setBackgroundColor(color);
                }
                if(color == Color.BLACK){
                    color = Color.WHITE;
                }else if(color == Color.WHITE){
                    color = Color.BLACK;
                }
            }
        }
    }

    //Every time we change a property of a button (color,background) we need to update its reference to the positions array
    //before setting that array as the new positions array in the model.
    private Button[][] UpdateTable(Button [][] positions,Button v,int [][] id){
        for (int i =0; i < id.length; i++){
            for (int j=0; j <id.length; j++){
                if (v.getId() == id[i][j]){
                    positions[i][j] = v;
                }

            }
        }
        return positions;
    }

}
