package com.john.chess.Engine.Player;

import com.john.chess.Engine.Board.*;
import com.john.chess.Engine.Pieces.*;
import com.john.chess.Engine.Team;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import java.util.ArrayList;

public abstract class Player {
    
    protected final Board board;

    protected final King playerKing;

    protected final Collection<Move> legalMoves;

    private final boolean isInCheck;

    Player(final Board board, final Collection<Move> legalMoves, final Collection<Move> oppMoves){
        this.board = board;
        this.playerKing = confirmKing();
        this.legalMoves = ImmutableList.copyOf(Iterables.concat(legalMoves, calculateKingCastles(legalMoves, oppMoves)));
        this.isInCheck = !Player.calculateAttacksOnTile(this.playerKing.getPiecePosition(), oppMoves).isEmpty();
    }

    public Collection<Move> getLegalMoves(){
        return this.legalMoves;
    }

    public King getPlayerKing(){
        return this.playerKing;
    }

    protected static Collection<Move> calculateAttacksOnTile(int piecePosition, Collection<Move> oppMoves){
        final List<Move> attackMoves = new ArrayList<>();
        for(final Move move : oppMoves){
            if(piecePosition == move.getDestination()){
                attackMoves.add(move);
            }
        }
        return attackMoves;
    }
    
    protected boolean canEscape(){
        for(final Move move : this.legalMoves){
            final MoveTransition transition = makeMove(move);
            if(transition.getMoveStatus().isDone()){
                return true;
            }
        }
        return false;
    }

    public boolean isChecked(){
        return this.isInCheck;
    }
    public boolean isCheckmated(){
        return this.isInCheck && !canEscape();
    }

    
    public boolean isStalemate(){
        return !this.isInCheck && !canEscape();
    }
    public boolean hasCastled(){
        return false;
    }

    //Checks if attempted move is illegal and cancels it, then checks if the move is a check, and if not makes the move as a normal move
    public MoveTransition makeMove(final Move move){

        if(!isMoveLegal(move)){
            return new MoveTransition(this.board, move, MoveStatus.ILLEGAL_MOVE);
        }
        final Board transitionBoard = move.execute();

        final Collection<Move> kingAttacks = Player.calculateAttacksOnTile(transitionBoard.currentPlayer().getOpponent().getPlayerKing().getPiecePosition(),
         transitionBoard.currentPlayer().getLegalMoves());

         if(!kingAttacks.isEmpty()){
            return new MoveTransition(this.board, move, MoveStatus.CHECKING_MOVE);
         }

        return new MoveTransition(transitionBoard, move, MoveStatus.DONE);
    }

    private King confirmKing(){
        for (final Piece piece : getActivePieces()){
            System.out.println("Checking " + piece.getPieceType());
            if(piece.getPieceType().isKing()){
                return (King) piece;
            }
        }
        String boardState = board.toString();
        throw new RuntimeException("King is missing - Should not happen, board state: \n" + boardState);
    }

    public boolean isMoveLegal(final Move move){
       return this.legalMoves.contains(move);
    }
    
    public abstract Collection<Piece> getActivePieces();
    public abstract Team getTeam();
    public abstract Player getOpponent();
    protected abstract Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentLegals);

}
