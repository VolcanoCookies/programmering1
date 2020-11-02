package eu3;

import java.util.Random;

public class ChessTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		Chessboard chessBoard = new Chessboard();
		System.out.println(chessBoard);
		Chessboard.Chesspiece[] pieces = new Chessboard.Chesspiece[6];
		pieces[0] = chessBoard.new Pawn('w', 'P');
		pieces[1] = chessBoard.new Rook('b', 'R');
		pieces[2] = chessBoard.new Queen('w', 'Q');
		pieces[3] = chessBoard.new Bishop('w', 'B');
		pieces[4] = chessBoard.new King('b', 'K');
		pieces[5] = chessBoard.new Knight('w', 'N');
		
		Random random = new Random();
		for (Chessboard.Chesspiece piece : pieces) {
			try {
				piece.moveTo((char) ('a' + random.nextInt(8)), (byte) (1 + random.nextInt(8)));
			} catch (Chessboard.NotValidFieldException e) {
				e.printStackTrace();
			}
			System.out.println(chessBoard);
			Thread.sleep(1000);
			piece.markReachableFields();
			System.out.println(chessBoard);
			Thread.sleep(2000);
			piece.unmarkReachableFields();
			piece.moveOut();
			System.out.println(chessBoard);
			Thread.sleep(1000);
		}
		
	}
	
}
