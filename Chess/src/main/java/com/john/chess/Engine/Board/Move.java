package com.john.chess.Engine.Board;

import com.john.chess.Engine.Pieces.Pawn;
import com.john.chess.Engine.Pieces.Piece;
import com.john.chess.Engine.Pieces.Rook;
import com.john.chess.Engine.Board.Board.Builder;

import java.util.Collection;

public abstract class Move {

        final Board board;
        final Piece movedPiece;
        final int destination;

        public static final Move INVALID_MOVE = new InvalidMove();

        private Move(final Board board, final Piece movedPiece, final int destination){
            this.board = board;
            this.movedPiece = movedPiece;
            this.destination = destination;
        }

        @Override
        public int hashCode(){
            final int prime = 31;
            int result = 1;
            result = prime * result + this.destination;
            result = prime * result + this.movedPiece.hashCode();

            return result;
        }

        @Override
        public boolean equals(final Object other){
            if(this == other) return true;
            if(!(other instanceof Move)) return false;
            final Move otherMove = (Move) other;
            return getDestination() == otherMove.getDestination() &&
                   getMovedPiece().equals(otherMove.getMovedPiece());
        }

        public int getDestination(){
            return this.destination;
        }

        public int getCurrentCoord(){
            return this.movedPiece.getPiecePosition();
        }

        public Piece getMovedPiece(){
            return this.movedPiece;
        }

        public boolean isAttack(){
            return false;
        }

        public boolean isCastlingMove(){
            return false;
        }

        public Piece getAttackedPiece(){
            return null;
        }

        public Board execute(){
            final Builder builder = new Builder();
            for(final Piece piece : this.board.currentPlayer().getActivePieces()){
                if(!this.movedPiece.equals(piece)){
                    builder.setPiece(piece);
                }

            }
            for(final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()){
                builder.setPiece(piece);
            }
            builder.setPiece(this.movedPiece.movePiece(this));
            builder.setMover(this.board.currentPlayer().getOpponent().getTeam());
            return builder.build();

        }
    
    public static final class MajorMove extends Move{
        public MajorMove(Board board, Piece movedPiece, int destination) {
            super(board, movedPiece, destination);
        }
    }
            
    public static class AttackMove extends Move{
                final Piece attackedPiece;
                public AttackMove(Board board, Piece movedPiece, int destination, Piece attackedPiece) {
                    super(board, movedPiece, destination);
                    this.attackedPiece = attackedPiece;
                }
                @Override
                public Board execute(){

                    final Builder builder = new Builder();
                    for(final Piece piece : this.board.currentPlayer().getActivePieces()){
                        //TODO make an equals method in Piece
                        if(!this.movedPiece.equals(piece)){
                            builder.setPiece(piece);
                        }
                    }

                    for(final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()){
                        builder.setPiece(piece);
                    }
                    builder.setPiece(null); //This will move the moved piece
                    builder.setMover(this.board.currentPlayer().getOpponent().getTeam()); //Changes the mover to the other team (opponent of current)
                    return builder.build();
                }

                @Override
                public int hashCode(){
                    return this.attackedPiece.hashCode() + super.hashCode();
                }
                @Override
                public boolean equals(final Object other){
                    if(this == other){
                        return true;
                    }
                    if(!(other instanceof AttackMove)){
                        return false;
                    }
                    final AttackMove otherAttackMove = (AttackMove) other;
                    return super.equals(otherAttackMove) && getAttackedPiece().equals(otherAttackMove.getAttackedPiece());
                }
                @Override
                public boolean isAttack(){
                    return true;
                }
                @Override
                public Piece getAttackedPiece(){
                    return this.attackedPiece;
                }

        }
    
        //Pawn-related moves
        public static final class PawnMove extends Move{
            public PawnMove(Board board, Piece movedPiece, int destination) {
                super(board, movedPiece, destination);
            }
        }
        public static final class PawnJump extends Move{
            public PawnJump(Board board, Piece movedPiece, int destination) {
                super(board, movedPiece, destination);
            }

            @Override
            public Board execute(){
                final Builder builder = new Builder();
                for(final Piece piece : this.board.currentPlayer().getActivePieces()){
                    if(!this.movedPiece.equals(piece)){
                        builder.setPiece(piece);
                    }
                }
                for(final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()){
                    builder.setPiece(piece);
                }   
                final Pawn movedPawn = (Pawn) this.movedPiece.movePiece(this);
                builder.setPiece(movedPawn);
                builder.setEnPassant(movedPawn);
                builder.setMover((this.board.currentPlayer().getOpponent().getTeam()));
                return builder.build();
            }
        }
        public static class PawnAttackMove extends AttackMove{
            public PawnAttackMove(Board board, Piece movedPiece, int destination, final Piece attackedPiece) {
                super(board, movedPiece, destination, attackedPiece);
            }
        }
        public static final class EnPassantAttackMove extends PawnAttackMove{
            public EnPassantAttackMove(Board board, Piece movedPiece, int destination, final Piece attackedPiece) {
                super(board, movedPiece, destination, attackedPiece);
            }
        }


        //Castling moves
        static abstract class CastlingMove extends Move{

            protected final Rook castleRook;
            protected final int castleRookStart, castleRookDestination;

            public CastlingMove(Board board, Piece movedPiece, int destination, final Rook castleRook,
            final int castleRookStart, final int castleRookDestination) {
                super(board, movedPiece, destination);
                this.castleRook = castleRook;
                this.castleRookStart = castleRookStart;
                this.castleRookDestination = castleRookDestination;
            }

            public Rook getCastleRook(){
                return castleRook;
            }
            @Override
            public boolean isCastlingMove() {
                // TODO Auto-generated method stub
                return true;
            }
            @Override
            public Board execute(){
                final Builder builder = new Builder();
                for(final Piece piece : this.board.currentPlayer().getActivePieces()){
                    if(!this.movedPiece.equals(piece) && !this.castleRook.equals(piece)){
                        builder.setPiece(piece);
                    }
                } 
                for(final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()){
                    builder.setPiece(piece);
                }
                builder.setPiece(this.movedPiece.movePiece(this));
                builder.setPiece(new Rook(this.castleRookDestination, this.castleRook.getPieceTeam()));
                builder.setMover(this.board.currentPlayer().getOpponent().getTeam());
                return builder.build();
            }
        }

        public static final class KingSideCastle extends CastlingMove{
            public KingSideCastle(Board board, Piece movedPiece, int destination, final Rook castleRook,
            final int castleRookStart, final int castleRookDestination) {
                super(board, movedPiece, destination, castleRook, castleRookStart, castleRookDestination);
            }
                @Override
                public String toString(){
                    return "O-O";
                }

        }
        public static final class QueenSideCastle extends CastlingMove{
            public QueenSideCastle(Board board, Piece movedPiece, int destination, final Rook castleRook,
            final int castleRookStart, final int castleRookDestination) {
                super(board, movedPiece, destination, castleRook, castleRookStart, castleRookDestination);
            }
            @Override
                public String toString(){
                    return "O-O-O";
                }
        }

        public static final class InvalidMove extends Move{
            public InvalidMove() {
                super(null, null, -1);
            }
            @Override
            public Board execute(){
                throw new RuntimeException("cannot execute the null move!");
            }
        }
        
        public static class MoveFactory{

            private MoveFactory(){
                throw new RuntimeException("Not instantiable");
            }

            public static Move createMove(final Board board, final int currentCoordinate, final int destinationCoordinate){
                for(Move move : board.getAllLegalMoves()){
                    if(move.getCurrentCoord() == currentCoordinate && move.getDestination() == destinationCoordinate){
                        return move;
                    }
                }
                return INVALID_MOVE;
            }

        }
}
