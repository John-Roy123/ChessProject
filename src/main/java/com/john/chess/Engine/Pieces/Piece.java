package com.john.chess.Engine.Pieces;

import com.john.chess.Engine.Board.Board;
import com.john.chess.Engine.Board.Move;
import com.john.chess.Engine.Team;
import java.util.Collection;

public abstract class Piece{

    protected final PieceType pieceType;
    protected int piecePosition;
    protected final Team pieceTeam;
    protected final boolean isFirstMove;
    private final int cachedHashCode;

    Piece(final PieceType pieceType,
        final int piecePosition, final Team pieceTeam){
        this.pieceType = pieceType;
        this.piecePosition = piecePosition;
        this.pieceTeam = pieceTeam;
        this.isFirstMove = true;
        this.cachedHashCode = computeHashCode();
    }

    private int computeHashCode(){
        int result = pieceType.hashCode();
        result = 31 * result + pieceTeam.hashCode();
        result = 31 * result + piecePosition;
        result = 31 * result + (isFirstMove ? 1 : 0);
        return result;
    }

    @Override
    public boolean equals(final Object other){
        if(this == other){
            return true;
        }
        if(!(other instanceof Piece)){
            return false;
        }
        final Piece otherPiece = (Piece) other;
        return  this.piecePosition == otherPiece.getPiecePosition() && this.pieceType == otherPiece.getPieceType() &&
                this.pieceTeam == otherPiece.getPieceTeam() && this.isFirstMove == otherPiece.isFirstMove();
    }
    @Override
    public int hashCode(){
        return this.cachedHashCode;
    }

    public int getPiecePosition(){
        return this.piecePosition;
    }

    public void setPiecePosition(int piecePosition){ this.piecePosition = piecePosition;}

    public boolean isFirstMove(){
        return this.isFirstMove;
    }

    public Piece movePiece(final Move move){
        return null;
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