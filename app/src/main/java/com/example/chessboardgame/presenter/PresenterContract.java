package com.example.chessboardgame.presenter;

import android.widget.Button;

import java.util.List;

public interface PresenterContract {
    /**
     *This function is responsible for drawing the knight on the button that it was clicked, or clear the button and
     * return it to its original color if it already had a knight on it.
     * @param v the Button which we clicked on it and have to determine whether we will draw a knight on it or not.
     * @return The button for which we determined if we should draw a knight or the original color.We return it for test purposes.
     */
    public Button drawKnight(Button v);

    /**
     * This function is responsible for reseting the game to its initial state.This means that the positions table
     * should return back to its original form.
     */
    public void ResetGame();

    /**
     * This function is responsible for finding if there are any valid paths on the chess board.
     * @return the list of all the cases that might happen when you place none,one or two knights on the chess board.It returns the list for test purposes.
     */
    public List<Boolean> FindValidPaths();

    /**
     * This funtion gets the positions table back from the model.
     * @return The positions table,in the state we last updated the model with.
     */
    public Button[][] getChessTable();

    /**
     * This function gets the cases back from the model.The cases correspond with the state of the positions table that model has.
     * @return The cases list,in the state we last updated the model with.
     */
    public List<Boolean> getCases();

    /**
     * This function gets the starting position of the knight on the chess board back from the model.
     * @return The starting position in the form of a String array.
     */
    public String [] getStartPos();

    /**
     * This function initializes the 2d id array for the buttons on the chess board.
     * @param n the dimension of the NxN table.
     */
    public void InitializeIds(int n);

    /**
     * This function gets the id array back from model.
     * @return the 2d NxN id array
     */
    public int[][] getIds();

    /**
     * This function is a helping function that sets the variable 'clicks' to zero(0).
     */
    public void ClearClicks();

    /**
     * This function is responsible for setting the positions array of the model.
     * It is used mainly for the first initialization of the array of the model during the onCreate of the view.
     * @param positions
     */
    public void setChessboard(Button [][] positions);
}
