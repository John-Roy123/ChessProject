package com.john.chess;


import com.john.chess.Engine.Board.Board;
import com.john.chess.Engine.Board.Move;
import com.john.chess.Engine.Player.MoveTransition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChessRestController {

    Board board = ChessApplication.getChessboard();

    @PostMapping("/message")
    public ResponseEntity<String> receiveMessage(@RequestBody MessageRequest msgReq){
    String message = MessageRequest.getMessage();
    String pieceName = MessageRequest.getDraggedElementName();
    int startTile = MessageRequest.getStartPositionId();
    int endTile = MessageRequest.getTargetTile();
    String pieceOnTile = MessageRequest.getPieceOnTile();

    //System.out.println(message);
    System.out.println(pieceName + " from tile: " + startTile + " moved to tile: " + endTile + " tile is currently occupied by: " + pieceOnTile);

    System.out.println(board);

    final Move move = Move.MoveFactory.createMove(board, startTile, endTile);
    final MoveTransition transition = board.currentPlayer().makeMove(move);
        if(transition.getMoveStatus().isDone()){
            board = transition.getTransitionBoard();
        }


    return ResponseEntity.ok("Message Received Successfully");
    }



}





class MessageRequest{
    private static String pieceOnTile;
    private static int targetTile;
    private static String message;
    private static int startPositionId;
    private static String draggedElementName;

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
