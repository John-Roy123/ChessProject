package Chess.Engine.Board;

import Chess.Engine.Pieces.*;
import Chess.Engine.Team;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;

public class Board {
    private final List<tile> gameBoard;

    private Board(Builder builder){
        this.gameBoard = createGame(builder);
    }

    private static List<tile> createGame(final Builder builder){
        final tile[] tiles = new tile[BoardUtils.NUM_TILES];
        for(int i = 0; i < BoardUtils.NUM_TILES; i++){
            tiles[i] = tile.createTile(i, builder.boardState.get(i));
        }
        return ImmutableList.copyOf(tiles);
    }

    public tile getTile(final int destinationCoord) {
        return null;
    }

    public static Board createStartBoard(){
        final Builder builder = new Builder();
        //white layout
        builder.setPiece(new Pawn(48, Team.WHITE));
        builder.setPiece(new Pawn(49, Team.WHITE));
        builder.setPiece(new Pawn(50, Team.WHITE));
        builder.setPiece(new Pawn(51, Team.WHITE));
        builder.setPiece(new Pawn(52, Team.WHITE));
        builder.setPiece(new Pawn(53, Team.WHITE));
        builder.setPiece(new Pawn(54, Team.WHITE));
        builder.setPiece(new Pawn(55, Team.WHITE));
        builder.setPiece(new Rook(56, Team.WHITE));
        builder.setPiece(new Rook(63, Team.WHITE));
        builder.setPiece(new Knight(57, Team.WHITE));
        builder.setPiece(new Knight(62, Team.WHITE));
        builder.setPiece(new Bishop(58, Team.WHITE));
        builder.setPiece(new Bishop(61, Team.WHITE));
        builder.setPiece(new Queen(59, Team.WHITE));
        //builder.setPiece(new Knight(60, Team.WHITE));

        //black layout
        builder.setPiece(new Pawn(8, Team.BLACK));
        builder.setPiece(new Pawn(9, Team.BLACK));
        builder.setPiece(new Pawn(10, Team.BLACK));
        builder.setPiece(new Pawn(11, Team.BLACK));
        builder.setPiece(new Pawn(12, Team.BLACK));
        builder.setPiece(new Pawn(13, Team.BLACK));
        builder.setPiece(new Pawn(14, Team.BLACK));
        builder.setPiece(new Pawn(15, Team.BLACK));
        builder.setPiece(new Rook(0, Team.BLACK));
        builder.setPiece(new Rook(7, Team.BLACK));
        builder.setPiece(new Knight(1, Team.BLACK));
        builder.setPiece(new Knight(6, Team.BLACK));
        builder.setPiece(new Bishop(2, Team.BLACK));
        builder.setPiece(new Bishop(5, Team.BLACK));
        builder.setPiece(new Queen(3, Team.BLACK));
        //builder.setPiece(new Knight(4, Team.BLACK));

        builder.setMover(Team.WHITE);
        return builder.build();

        
    }



    public static class Builder{

        Map<Integer, Piece> boardState;
        Team currentTurn;

        public Builder setPiece(final Piece piece){
            this.boardState.put(piece.getPiecePosition(), piece);
            return this;
        }

        public Builder setMover(final Team currentTurn){
            this.currentTurn = currentTurn;
            return this;
        }

        public Board build(){
            return new Board(this);
        }
    } 
}
