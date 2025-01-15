package Chess;

import Chess.Engine.Board.Board;
import Chess.GUI.Table;

public class ChessDriver {
    public static void main(String[] args) {
        Board board = Board.createStartBoard();
        System.out.println(board);
        Table table = new Table();
    }
}
