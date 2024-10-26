package Chess.Engine.Board;

import Chess.Engine.Pieces.Piece;
import Chess.Engine.Board.Board.Builder;

public abstract class Move {

        final Board board;
        final Piece movedPiece;
        final int destination;

        private Move(final Board board, final Piece movedPiece, final int destination){
            this.board = board;
            this.movedPiece = movedPiece;
            this.destination = destination;
        }

        public int getDestination(){
            return this.destination;
        }

        public abstract Board execute();
    
    public static final class MajorMove extends Move{
        public MajorMove(Board board, Piece movedPiece, int destination) {
            super(board, movedPiece, destination);
        }
        @Override
        public Board execute(){
            return null;
        }
    }
            
    public static final class AttackMove extends Move{
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

        }
    
        
}
