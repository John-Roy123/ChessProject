package com.john.chess;

import com.john.chess.Engine.Board.Board;


public class BuildStandardBoardTest {

    public static void main(String[] args) {
        Board board = Board.createStartBoard();

        System.out.println(board);
    }
      
    
}
