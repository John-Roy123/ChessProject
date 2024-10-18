package Chess.Engine.Pieces;

import Chess.Engine.Board.Board;
import Chess.Engine.Board.BoardUtils;
import Chess.Engine.Board.Move;
import Chess.Engine.Board.Move.MajorMove;
import Chess.Engine.Team;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece{

    private final static int[] CANDIDATE_MOVE_COORD = {8, 16};

    Pawn(final int piecePosition, final Team pieceTeam) {
        super(piecePosition, pieceTeam);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for(final int offSet : CANDIDATE_MOVE_COORD){
            int destinationCoord = this.piecePosition + (offSet * this.getPieceTeam().getDirection());

            if(!BoardUtils.isValidCoordinate(destinationCoord)){
                continue;
            }
            if(offSet == 8 && board.getTile(destinationCoord).isFull()){
                legalMoves.add(new MajorMove(board, this, destinationCoord));
                //needs change for promotions
            } else if(offSet == 16 && this.isFirstMove() && (BoardUtils.SECOND_ROW[this.piecePosition] && this.pieceTeam.isBlack()) 
            || (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceTeam().isWhite())){
                final int behindDestinationCoord = this.piecePosition + (this.pieceTeam.getDirection()*8);
                if(!board.getTile(behindDestinationCoord).isFull() && !board.getTile(destinationCoord).isFull()){
                    legalMoves.add(new MajorMove(board, this, destinationCoord));
                }
            } 
            else if(offSet == 7 && !(BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceTeam.isWhite()||
            BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceTeam.isBlack())){

                if(board.getTile(destinationCoord).isFull()){
                    final Piece pieceOnDestination = board.getTile(destinationCoord).getPiece();
                    if(this.pieceTeam != pieceOnDestination.getPieceTeam()){
                        //TODO attack into promotion condition
                        legalMoves.add(new MajorMove(board, this, destinationCoord));
                    }
                }


            } else if(offSet == 9 && !(BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceTeam.isWhite()||
            BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceTeam.isBlack())){
                if(board.getTile(destinationCoord).isFull()){
                    final Piece pieceOnDestination = board.getTile(destinationCoord).getPiece();
                    if(this.pieceTeam != pieceOnDestination.getPieceTeam()){
                        //TODO attack into promotion condition
                        legalMoves.add(new MajorMove(board, this, destinationCoord));
                    }
                }
            }
            
        }
        // TODO Auto-generated method stub
        return legalMoves;
    }
    
}
