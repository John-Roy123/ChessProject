package Chess;

import Chess.Engine.Board.Board;


public class BuildStandardBoardTest {

    public static void main(String[] args) {
        Board board = Board.createStartBoard();

        System.out.println(board);
    }
      
    
}
