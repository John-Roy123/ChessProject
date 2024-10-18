package Chess.Engine.Pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import Chess.Engine.Team;
import Chess.Engine.Board.Board;
import Chess.Engine.Board.BoardUtils;
import Chess.Engine.Board.Move;
import Chess.Engine.Board.tile;
import Chess.Engine.Board.Move.MajorMove;
import Chess.Engine.Board.Move.MajorMove.AttackMove;

public class Knight extends Piece{

    private final static int[] LEGAL_MOVES = {-17, -15, -10, -6, 6, 10, 15, 17};

    Knight(int piecePosition, Team pieceTeam) {
        super(piecePosition, pieceTeam);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        int destinationCoord;
        List<Move> legalMoves = new ArrayList<>();


        for(final int currentMove : LEGAL_MOVES){

            destinationCoord = this.piecePosition + currentMove;
            //sets destinationCoord to where piece is + where it wants to go

            if(BoardUtils.isValidCoordinate(destinationCoord)){

            if(isFirstColumnExclusion(this.piecePosition, currentMove) || 
            isSecondColumnExclusion(this.piecePosition, currentMove) || 
            isSeventhColumnExclusion(this.piecePosition, currentMove) || 
            isEighthColumnExclusion(this.piecePosition, destinationCoord)) continue;

            //checks if the destination is within bounds
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
    
    private static boolean isSecondColumnExclusion(final int currentPosition, final int offSet){
        return BoardUtils.SECOND_COLUMN[currentPosition] && ((offSet == -10) || (offSet == 6));
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int offSet){
        return BoardUtils.FIRST_COLUMN[currentPosition] && ((offSet == -17) || (offSet == -10) || (offSet == 6) || offSet == 15);
    }

    private static boolean isSeventhColumnExclusion(final int currentPosition, final int offSet){
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && ((offSet == -6) || (offSet == 10));
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int offSet){
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && ((offSet == -15) || (offSet == -6) || (offSet == 10) || (offSet == 17));
    }

}
