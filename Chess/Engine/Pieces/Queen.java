package Chess.Engine.Pieces;

import Chess.Engine.Board.Board;
import Chess.Engine.Board.BoardUtils;
import Chess.Engine.Board.Move;
import Chess.Engine.Board.Move.MajorMove;
import Chess.Engine.Board.Move.MajorMove.AttackMove;
import Chess.Engine.Board.tile;
import Chess.Engine.Team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

public class Queen extends Piece{

    private final static int[] MOVE_VECTOR_COORDINATES = {-9, -8, -7, -1, 1, 7, 8, 9};

    Queen(int piecePosition, Team pieceTeam) {
        super(piecePosition, pieceTeam);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        int destinationCoord;

        final List<Move> legalMoves = new ArrayList<>();

        for(final int coordinateOffset: MOVE_VECTOR_COORDINATES){
            destinationCoord = this.piecePosition;

            while(BoardUtils.isValidCoordinate(destinationCoord)){

                if(isFirstColumnExclusion(destinationCoord, coordinateOffset) || isEighthColumnExclusion(destinationCoord, coordinateOffset)){
                    break;
                }

                destinationCoord += coordinateOffset;

                if(BoardUtils.isValidCoordinate(destinationCoord)){
                     final tile destinationTile = board.getTile(destinationCoord);
                
                if(!destinationTile.isFull()){
                    legalMoves.add(new MajorMove(board, this, destinationCoord));
                    //checks if destination already has a piece, and it not then moves piece to tile

                }else{
                    final Piece pieceOnTile = destinationTile.getPiece();
                    final Team pieceTeam = pieceOnTile.getPieceTeam();

                    if(this.pieceTeam != pieceTeam) legalMoves.add(new AttackMove(board, this, destinationCoord, pieceOnTile));
                    
                    break;
                }
                
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }
    
    private static boolean isFirstColumnExclusion(final int currentPostition, final int offSet){
        return BoardUtils.FIRST_COLUMN[currentPostition] && (offSet == -9 || offSet == 7 || offSet == -1);
    }
    private static boolean isEighthColumnExclusion(final int currentPostition, final int offSet){
        return BoardUtils.EIGHTH_COLUMN[currentPostition] && (offSet == -7 || offSet == 9 || offSet == 1);
    }
    
}