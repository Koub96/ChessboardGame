package com.example.chessboardgame.views;

import android.widget.Button;

import java.util.List;

public interface ViewContract {
    /**
     * This function prints a toast with an error message about not finding valid paths
     * on the chess board.
     */
    public void ErrorMsg();

    /**
     * This function is responsible to draw the valid paths,if any,as given by the Presenter
     * from the boolean list of cases.
     * @param positions the positions 2d array representing the chess board.
     * @param start_pos the starting position of the knight.
     *                  startPos[0] is the x coordinate,startPos[1] is the y coordinate.
     * @param cases The boolean list of cases on the chess board.
     */
    public void DrawValidPaths(Button[][] positions,int [] start_pos, List<Boolean> cases);

    /**
     * This function is responsible for checking if the given Button has the image of the knight.
     * @param v The button to check.
     * @return True or False depending on if the button has a knight image set.
     */
    public boolean CheckResource(Button v);
}
