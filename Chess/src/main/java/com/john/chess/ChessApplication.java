package com.john.chess;

import com.john.chess.Engine.Board.Board;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ChessApplication {


	public static Board chessboard;

	public static void main(String[] args) {
		SpringApplication.run(ChessApplication.class, args);

    }

	public static Board getChessboard(){
		return chessboard;
	}



}


