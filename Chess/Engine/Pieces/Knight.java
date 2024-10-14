package Chess.Engine.Pieces;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

import Chess.Engine.Team;
import Chess.Engine.Board.Board;
import Chess.Engine.Board.Move;
import Chess.Engine.Board.tile;
import Chess.Engine.Board.BoardUtils;

public class Knight extends Piece{

    private final static int[] LEGAL_MOVES = {-17, -15, -10, -6, 6, 10, 15, 17};

    Knight(int piecePosition, Team pieceTeam) {
        super(piecePosition, pieceTeam);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {

        int destinationCoord;
        List<Move> legalMoves = new ArrayList<>();


        for(final int currentMove : LEGAL_MOVES){

            destinationCoord = this.piecePosition + currentMove;
            //sets destinationCoord to where piece is + where it wants to go

            if(BoardUtils.isValidCoordinate(destinationCoord)){
            //checks if the destination is within bounds
                final tile destinationTile = board.getTile(destinationCoord);
                

                if(!destinationTile.isFull()){
                    legalMoves.add(new Move());
                    //checks if destination already has a piece, and it not then moves piece to tile
                }else{
                    final Piece pieceOnTile = destinationTile.getPiece();
                    final Team pieceTeam = pieceOnTile.getPieceTeam();

                    if(this.pieceTeam != pieceTeam){
                        legalMoves.add(new Move());
                    }
                }
            }

        }

        return ImmutableList.copyOf(legalMoves);
        
    } 
    
    private static boolean isFirstColumnExclusion(final int currentPosition, final int offSet){
        BoardUtils.FIRST_COLUMN[currentPosition] && ((offSet == -17)|| (offSet == -10) || (offSet == 6) || offSet ==15);
    }

}
