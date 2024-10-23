package Chess.Engine.Pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Chess.Engine.Team;
import Chess.Engine.Board.Board;
import Chess.Engine.Board.BoardUtils;
import Chess.Engine.Board.Move;
import Chess.Engine.Board.Move.MajorMove;
import Chess.Engine.Board.Move.MajorMove.AttackMove;
import Chess.Engine.Board.tile;

public class King extends Piece{
    final static int[] CANDIDATE_MOVE_COORD = {-9,-8,-7,-1,1,7,8,9};

    King(int piecePosition, Team pieceTeam) {
        super(piecePosition, pieceTeam);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        
        final List<Move> legalMoves = new ArrayList<>();
        
        for(final int offSet : CANDIDATE_MOVE_COORD){
            final int destinationCoord;
            destinationCoord = this.piecePosition + offSet;

            if(isFirstColumnExclusion(this.piecePosition, offSet) || isEighthColumnExclusion(this.piecePosition, offSet)){
                continue;
            }

            if(BoardUtils.isValidCoordinate(destinationCoord)){
                 final tile destinationTile = board.getTile(destinationCoord);
                if(!destinationTile.isFull()){
                    legalMoves.add(new MajorMove(board, this, destinationCoord));
                    //checks if destination already has a piece, and it not then moves piece to tile
                }else{
                    final Piece pieceOnTile = destinationTile.getPiece();
                    final Team pieceTeam = pieceOnTile.getPieceTeam();
                    if(this.pieceTeam != pieceTeam){
                        legalMoves.add(new AttackMove(board, this, destinationCoord, pieceOnTile));
                    }
                }

            }
        }
        
        return legalMoves;
    }
    
    private static boolean isEighthColumnExclusion(final int currentPosition, final int offSet){
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && ((offSet == -7) || (offSet == 1) || (offSet == 9));
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int offSet){
        return BoardUtils.FIRST_COLUMN[currentPosition] && ((offSet == -9) || (offSet == -1) || (offSet == 7));
    }
}