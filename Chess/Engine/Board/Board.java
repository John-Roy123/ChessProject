package Chess.Engine.Board;

import Chess.Engine.Pieces.*;
import Chess.Engine.Team;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Board {
    private final List<tile> gameBoard;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;

    private Board(Builder builder){ 
        this.gameBoard = createBoard(builder);
        this.whitePieces = determineActivePieces(this.gameBoard, Team.WHITE);
        this.blackPieces = determineActivePieces(this.gameBoard, Team.BLACK);

        final Collection<Move> whiteLegalMoves = calculateLegalMoves(this.whitePieces);
        final Collection<Move> blackLegalMoves = calculateLegalMoves(this.blackPieces);
    }

    @Override
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < BoardUtils.NUM_TILES; i++){
            final String text = this.gameBoard.get(i).toString();
            sb.append(String.format("%3s", text));
            if((i+1) % BoardUtils.NUM_TILES_PER_ROW == 0){
                sb.append("\n");
            }
        }
        return sb.toString();
    }


    //Method to determine what moves are legal for a given team
    private Collection<Move> calculateLegalMoves(Collection<Piece> pieces){
        final List<Move> legalMoves = new ArrayList<>();
        for(final Piece piece : pieces){
            legalMoves.addAll(piece.calculateLegalMoves(this));
        }
        return legalMoves;
    }

    //Method to determine what pieces of given team are still on the board
    private Collection<Piece> determineActivePieces(final List<tile> gameBoard, final Team team){
        final List<Piece> activePieces = new ArrayList<>();
        for(final tile t : gameBoard){
            if(t.isFull()){
                final Piece piece = t.getPiece();
                if(piece.getPieceTeam() == team){
                    activePieces.add(piece);
                }
            }
        }

        return activePieces;
    }

    //Method that builds the board as 64 tiles
    private static List<tile> createBoard(final Builder builder){
        final tile[] tiles = new tile[BoardUtils.NUM_TILES];
        for(int i = 0; i < BoardUtils.NUM_TILES; i++){
            tiles[i] = tile.createTile(i, builder.boardState.get(i));
        }
        return ImmutableList.copyOf(tiles);
    }

    public tile getTile(final int destinationCoord) {
        return gameBoard.get(destinationCoord);
    }

    //Creates the standard starting board for a chess game using the buidler class
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
        builder.setPiece(new Knight(60, Team.WHITE));

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
        builder.setPiece(new Knight(4, Team.BLACK));

        builder.setMover(Team.WHITE);
        return builder.build();

        
    }


//Builder pattern class used to build the board
    public static class Builder{

        Map<Integer, Piece> boardState;
        Team currentTurn;

        public Builder(){
            this.boardState = new HashMap<>();
        }

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
