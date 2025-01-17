package com.john.chess.Engine.Player;

import com.john.chess.Engine.Board.Board;
import com.john.chess.Engine.Board.Move;
import com.john.chess.Engine.Board.tile;
import com.john.chess.Engine.Pieces.Piece;
import com.john.chess.Engine.Pieces.Rook;
import com.john.chess.Engine.Team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;




public class BlackPlayer extends Player {

    public BlackPlayer(final Board board, final Collection<Move> blackLegalMoves, final Collection<Move> whiteLegalMoves) {
        super(board, blackLegalMoves, whiteLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }
    @Override
    public Team getTeam(){
        return Team.BLACK;
    }
    @Override
    public Player getOpponent(){
        return this.board.whitePlayer();
    }

    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals, final Collection<Move> opponentLegals) {
        final List<Move> kingCastles = new ArrayList<>();
        if(this.playerKing.isFirstMove() && !this.isChecked()){
            //kingside castle
            if(!this.board.getTile(5).isFull() && !this.board.getTile(6).isFull()){
                final tile rookTile = this.board.getTile(7); 

                if(rookTile.isFull() && rookTile.getPiece().isFirstMove()){
                    if(Player.calculateAttacksOnTile(5, opponentLegals).isEmpty() && Player.calculateAttacksOnTile(6, opponentLegals).isEmpty() 
                    && rookTile.getPiece() instanceof Rook) //instanceof Rook may be error
                    kingCastles.add(new Move.KingSideCastle(
                        this.board, this.playerKing, 
                        6, (Rook)rookTile.getPiece(), 
                        rookTile.getTileCoordinate(), 5));
                }
            }
            //queen side castle
            if(!this.board.getTile(1).isFull() && !this.board.getTile(2).isFull() && !this.board.getTile(3).isFull()){
                final tile rookTile = this.board.getTile(0);
                if(rookTile.isFull() && rookTile.getPiece().isFirstMove() && 
                Player.calculateAttacksOnTile(2, opponentLegals).isEmpty() && Player.calculateAttacksOnTile(3, opponentLegals).isEmpty() &&
                rookTile.getPiece() instanceof Rook){
                    kingCastles.add(new Move.QueenSideCastle(
                        this.board, this.playerKing, 
                        2, (Rook)rookTile.getPiece(), 
                        rookTile.getTileCoordinate(), 3));
                }
            }
        }
        return kingCastles;
    }

}
