package com.example.chessboardgame.model;

import android.widget.Button;

import java.util.List;

public interface ModelContract {
    /**
     * This function updates the state of the positions table.
     * @param positions 2d table representing the chess board as given by the Presenter.
     */
    public void UpdateTable(Button[][] positions);

    /**
     * This function returns back the positions table in the state it was lastly updated with.
     * @return the 2d Button array positions representing the chess board.
     */
    public Button [][] getTable();

    /**
     * This function sets the starting position of the knight on the chess board.
     * @param startPos the starting position of the knight to be saved.Its a array with length equals to two(2).
     *                 startPos[0] is the x coordinate of the starting position
     *                 startPos[1] is the y coordinate of the starting position
     */
    public void setStartPos(int [] startPos);

    /**
     * This fucntion returns the starting position array as lastly updated by the Presenter.
     * @return the startingPos array holding the x and y coordinate of the starting position of the knight on the chess board.
     */
    public int [] getStartPos();

    /**
     * This function sets the list of possible cases on the chess board as given by the Presenter.
     * @param cases the list of boolean cases
     */
    public void setCases(List<Boolean> cases);

    /**
     * This function returns the boolean list of cases on the chess board as lastly update by the Presenter.
     * @return the boolean list of cases.
     */
    public List<Boolean> getCases();

    /**
     * This function sets the id 2d array as given by the Presenter.
     * @param id the 2d array consisting of the ids of each button on the chess board.
     */
    public void setIds(int[][]id);

    /**
     * This function returns the id 2d array as lastly updated by the Presenter.
     * @return the 2d id array.
     */
    public int[][] getIds();

}
