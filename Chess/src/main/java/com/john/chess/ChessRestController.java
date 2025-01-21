package com.john.chess;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.john.chess.Engine.Board.Board;
import com.john.chess.Engine.Board.Move;
import com.john.chess.Engine.Pieces.Piece;
import com.john.chess.Engine.Player.MoveTransition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ChessRestController {

    private Board board;
    private String currBoard;

    @GetMapping("/boardstate")
    public String getBoardState() {
            System.out.println("Current Board to string: " + currBoard);
            return currBoard;
    }

    @PostMapping("/message")
    public ResponseEntity<String> receiveMessage(@RequestBody MessageRequest msgReq){

        if(board == null){
            board = Board.createStartBoard();
        }

    String message = MessageRequest.getMessage();
    String pieceName = MessageRequest.getDraggedElementName();
    int startTile = MessageRequest.getStartPositionId();
    int endTile = MessageRequest.getTargetTile();
    String pieceOnTile = MessageRequest.getPieceOnTile();
    String pieceTeam = MessageRequest.getPieceTeam();

    //System.out.println(message);
    System.out.println(pieceName + " from tile: " + startTile + " moved to tile: " + endTile + " tile is currently occupied by: " + pieceOnTile);
    System.out.println(pieceTeam);



    final Move move = Move.MoveFactory.createMove(board, startTile, endTile);
    final MoveTransition transition = board.currentPlayer().makeMove(move);
        if(transition.getMoveStatus().isDone()){
            board = transition.getTransitionBoard();
        }

        currBoard = board.getBoardState();
        System.out.println(board);

    return ResponseEntity.ok("Message Received Successfully");
    }



}





class MessageRequest{
    private static String pieceOnTile;
    private static int targetTile;
    private static String message;
    private static int startPositionId;
    private static String draggedElementName;
    private static String pieceTeam;



    public static String getPieceTeam() {return pieceTeam; }
    public void setPieceTeam(String pieceTeam) {this.pieceTeam = pieceTeam; }

    public static String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public static String getDraggedElementName() {
        return draggedElementName;
    }
    public void setDraggedElementName(String pieceName) {
        this.draggedElementName = pieceName;
    }

    public static int getStartPositionId() {
        return startPositionId;
    }
    public void setStartPositionId(int startPositionId) {
        this.startPositionId = startPositionId;
    }

    public static int getTargetTile() {
        return targetTile;
    }
    public void setTargetTile(int endTile) {
        this.targetTile = endTile;
    }

    public static String getPieceOnTile() {
        return pieceOnTile;
    }
    public void setPieceOnTile(String pieceOnTile) {
        this.pieceOnTile = pieceOnTile;
    }
}
