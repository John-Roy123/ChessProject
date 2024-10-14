package Chess.Engine.Board;

import Chess.Engine.Pieces.Piece;

public abstract class Move {

        final Board board;
        final Piece movedPiece;
        final int destination;

        private Move(final Board board, final Piece movedPiece, final int destination){
            this.board = board;
            this.movedPiece = movedPiece;
            this.destination = destination;
        }

    public static final class MajorMove extends Move{
        public MajorMove(Board board, Piece movedPiece, int destination) {
            super(board, movedPiece, destination);
        }
            
    public static final class AttackMove extends Move{
                final Piece attackedPiece;
                public AttackMove(Board board, Piece movedPiece, int destination, Piece attackedPiece) {
                    super(board, movedPiece, destination);
                    this.attackedPiece = attackedPiece;
                }
        }
    }
        }

