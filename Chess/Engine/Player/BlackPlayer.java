package Chess.Engine.Player;

import Chess.Engine.Board.Board;
import Chess.Engine.Board.Move;
import Chess.Engine.Pieces.Piece;
import Chess.Engine.Team;
import java.util.Collection;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class BlackPlayer extends Player {

    public BlackPlayer(final Board board, final Collection<Move> blackLegalMoves, final Collection<Move> whiteLegalMoves) {
        super(board, blackLegalMoves, whiteLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }
    @Override
    public Team getTeam(){
        return Team.BLACK;
    }
    @Override
    public Player getOpponent(){
        return this.board.whitePlayer();
    }

}
