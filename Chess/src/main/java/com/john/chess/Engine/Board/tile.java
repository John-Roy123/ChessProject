package com.john.chess.Engine.Board;

import com.john.chess.Engine.Pieces.Piece;
import java.util.HashMap;
import java.util.Map;

public abstract class tile {

    protected final int coord;
    //declared final so that the tile is immutable

    private static final Map<Integer, emptyTile> EMPTY_TILES = createAllPossibleEmptyTiles();

    private static Map<Integer, emptyTile> createAllPossibleEmptyTiles() {
            
            final Map<Integer, emptyTile> emptyTileMap = new HashMap<>();
            //final because there is only 1 Map for board

            for(int i = 0; i<BoardUtils.NUM_TILES; i++){
                emptyTileMap.put(i, new emptyTile(i));
                //creates all 64 tiles on a chess board with an assigned integer for each tile
            }

            //create an immutablemap of the emptyTileMap using the guava library to stick to the theme of immutability
            return emptyTileMap;
        }

    public static tile createTile(final int coord, final Piece piece){
        return piece != null ? new fullTile(coord, piece) : EMPTY_TILES.get(coord);
        //Only outside accessible class that returns whether a tile is empty or not and if not, what piece is on it
    }

    private tile(int coord){
        this.coord = coord;
    }

    public int getTileCoordinate(){
        return this.coord;
    }

    

    

    public abstract boolean isFull();

    public abstract Piece getPiece();

    public static final class emptyTile extends tile{
        private emptyTile(final int coord){
            super(coord);
            
        }
        @Override 
    public String toString(){
        return "-";
    }
        @Override
        public boolean isFull(){
            return false;
            //no piece on tile
        }

        @Override
        public Piece getPiece(){
            return null;
            //no piece on tile so piece is null
        }
    }

    public static final class fullTile extends tile{

        private final Piece piece;

        private fullTile(final int coord, Piece piece){
            super(coord);
            this.piece = piece;
            //sets this coordinate to have the correct piece
        }
        @Override 
        public String toString(){
            return getPiece().getPieceTeam().isBlack() ? getPiece().toString().toLowerCase() : getPiece().toString();
        }
        @Override
        public boolean isFull(){
            return true;
            //yes piece on tile
        }

        @Override 
        public Piece getPiece(){
            return piece;
            //return what piece is on the tile
        }
    }
}
