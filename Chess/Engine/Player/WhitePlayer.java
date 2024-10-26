/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Chess.Engine.Player;

import Chess.Engine.Team;
import Chess.Engine.Board.Board;
import Chess.Engine.Board.Move;
import Chess.Engine.Pieces.Piece;
import java.util.Collection;


public class WhitePlayer extends Player {

    public WhitePlayer(final Board board, final Collection<Move> whiteLegalMoves, final Collection<Move> blackLegalMoves) {
        super(board, whiteLegalMoves, blackLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }
    @Override
    public Team getTeam(){
        return Team.WHITE;
    }
    @Override
    public Player getOpponent(){
        return this.board.blackPlayer();
    }

}
