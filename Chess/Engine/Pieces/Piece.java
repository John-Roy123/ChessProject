package Chess.Engine.Pieces;

import Chess.Engine.Board.Board;
import Chess.Engine.Board.Move;
import Chess.Engine.Team;
import java.util.Collection;

public abstract class Piece{

    protected final PieceType pieceType;
    protected final int piecePosition;
    protected final Team pieceTeam;
    protected final boolean isFirstMove;

    Piece(final PieceType pieceType,
        final int piecePosition, final Team pieceTeam){
        this.pieceType = pieceType;
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

    public PieceType getPieceType(){
        return this.pieceType;
    }

    public enum PieceType{

        PAWN("P") {
            @Override
            public boolean isKing() {
                // TODO Auto-generated method stub
                return false;
            }
        },
        ROOK("R"){
            @Override
            public boolean isKing() {
                // TODO Auto-generated method stub
                return false;
            }
        },
        KNIGHT("H"){
            @Override
            public boolean isKing() {
                // TODO Auto-generated method stub
                return false;
            }
        },
        BISHOP("B"){
            @Override
            public boolean isKing() {
                // TODO Auto-generated method stub
                return false;
            }
        },
        QUEEN("Q"){
            @Override
            public boolean isKing() {
                // TODO Auto-generated method stub
                return false;
            }
        },
        KING("K"){
            @Override
            public boolean isKing() {
                // TODO Auto-generated method stub
                return true;
            }
        };


        private String pieceName;

        PieceType(final String pieceName){
            this.pieceName = pieceName;
        }

        @Override
        public String toString(){
            return this.pieceName;
        }

        public abstract boolean isKing();
    }

    

}