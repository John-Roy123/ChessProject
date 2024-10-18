package Chess.Engine.Pieces;

import Chess.Engine.Board.Board;
import Chess.Engine.Board.Move;
import Chess.Engine.Team;
import java.util.Collection;

public abstract class Piece{
    protected final int piecePosition;
    protected final Team pieceTeam;
    protected final boolean isFirstMove;

    Piece(final int piecePosition, final Team pieceTeam){
        this.piecePosition = piecePosition;
        this.pieceTeam = pieceTeam;
        this.isFirstMove = true;
    }

    public boolean isFirstMove(){
        return this.isFirstMove;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);

    public Team getPieceTeam(){
        return this.pieceTeam;
    }

    

}