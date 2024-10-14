package Chess.Engine.Pieces;

import java.util.List;

import Chess.Engine.Team;
import Chess.Engine.Board.Board;
import Chess.Engine.Board.Move;

public abstract class Piece{
    protected final int piecePosition;
    protected final Team pieceTeam;

    Piece(final int piecePosition, final Team pieceTeam){
        this.piecePosition = piecePosition;
        this.pieceTeam = pieceTeam;
    }

    public abstract List<Move> calculateLegalMoves(final Board board);

    public Team getPieceTeam(){
        return this.pieceTeam;
    }

    

}