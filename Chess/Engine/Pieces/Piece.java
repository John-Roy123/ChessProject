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

    public int getPiecePosition(){
        return this.piecePosition;
    }

    public boolean isFirstMove(){
        return this.isFirstMove;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);

    public Team getPieceTeam(){
        return this.pieceTeam;
    }

    public enum PieceType{

        PAWN("P"),
        ROOK("R"),
        KNIGHT("H"),
        BISHOP("B"),
        QUEEN("Q"),
        KING("K");


        private String pieceName;

        PieceType(final String pieceName){
            this.pieceName = pieceName;
        }

        @Override
        public String toString(){
            return this.pieceName;
        }
    }

    

}