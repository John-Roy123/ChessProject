/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Chess.Engine.Player;

import Chess.Engine.Team;
import Chess.Engine.Board.Board;
import Chess.Engine.Board.Move;
import Chess.Engine.Board.tile;
import Chess.Engine.Pieces.Piece;
import Chess.Engine.Pieces.Rook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class WhitePlayer extends Player {

    public WhitePlayer(final Board board, final Collection<Move> whiteLegalMoves, final Collection<Move> blackLegalMoves) {
        super(board, whiteLegalMoves, blackLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }
    @Override
    public Team getTeam(){
        return Team.WHITE;
    }
    @Override
    public Player getOpponent(){
        return this.board.blackPlayer();
    }

    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals, final Collection<Move> opponentLegals) {
        final List<Move> kingCastles = new ArrayList<>();
        if(this.playerKing.isFirstMove() && !this.isChecked()){
            //kingside castle
            if(!this.board.getTile(61).isFull() && !this.board.getTile(62).isFull()){
                final tile rookTile = this.board.getTile(63);

                if(rookTile.isFull() && rookTile.getPiece().isFirstMove()){
                    if(Player.calculateAttacksOnTile(61, opponentLegals).isEmpty() && Player.calculateAttacksOnTile(62, opponentLegals).isEmpty() 
                    && rookTile.getPiece() instanceof Rook) //instanceof Rook may be error
                        kingCastles.add(new Move.KingSideCastle(
                            this.board, this.playerKing, 
                            62, (Rook)rookTile.getPiece(), 
                            rookTile.getTileCoordinate(), 61));
                }
            }
            //queenside castle
            if(!this.board.getTile(59).isFull() && !this.board.getTile(58).isFull() && !this.board.getTile(57).isFull()){
                final tile rookTile = this.board.getTile(56);
                if(rookTile.isFull() && rookTile.getPiece().isFirstMove()){
                    kingCastles.add(new Move.QueenSideCastle(
                        this.board, this.playerKing, 
                        58, (Rook)rookTile.getPiece(), 
                        rookTile.getTileCoordinate(), 59));
                }
            }
        }
        return kingCastles;
    }

}
