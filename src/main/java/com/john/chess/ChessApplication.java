package com.john.chess;

import com.john.chess.Engine.Board.Board;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ChessApplication {


	private static Board chessboard;

	public static void main(String[] args) {
		SpringApplication.run(ChessApplication.class, args);
		createBoard();
		System.out.println(chessboard);
    }

	public static void createBoard(){
		chessboard = Board.createStartBoard();
	}

	public static Board getChessboard(){
		return chessboard;
	}



}


