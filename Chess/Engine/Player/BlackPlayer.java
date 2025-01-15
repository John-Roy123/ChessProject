package Chess.Engine.Player;

import Chess.Engine.Board.Board;
import Chess.Engine.Board.Move;
import Chess.Engine.Board.tile;
import Chess.Engine.Pieces.Piece;
import Chess.Engine.Pieces.Rook;
import Chess.Engine.Team;
import java.io.File;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


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
                if(rookTile.isFull() && rookTile.getPiece().isFirstMove()){
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
