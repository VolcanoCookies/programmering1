package eu3;

import java.util.function.Consumer;

public class Chessboard {
	
	public static class Field {
		
		private char row;
		private byte column;
		private Chesspiece piece = null;
		private boolean marked = false;
		
		public Field(char row, byte column) {
			this.row = row;
			this.column = column;
		}
		
		public void put(Chesspiece piece) {
			this.piece = piece;
		}
		
		public Chesspiece take() {
			Chesspiece p = piece;
			piece = null;
			return p;
		}
		
		public void mark() {
			marked = true;
		}
		
		public void unmark() {
			marked = false;
		}
		
		@Override
		public String toString() {
			String s = (marked) ? "xx" : "++";
			return (piece == null) ? s : piece.toString();
		}
	}
	
	public static final int NUMBER_OF_ROWS = 8;
	public static final int NUMBER_OF_COLUMNS = 8;
	public static final int FIRST_ROW = 'a';
	public static final int FIRST_COLUMN = 1;
	private Field[][] fields;
	
	public Chessboard() {
		fields = new Field[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
		char row = 0;
		byte column = 0;
		for (int r = 0; r < NUMBER_OF_ROWS; r++) {
			row = (char) (FIRST_ROW + r);
			column = FIRST_COLUMN;
			for (int c = 0; c < NUMBER_OF_COLUMNS; c++) {
				fields[r][c] = new Field(row, column);
				column++;
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("   ");
		for (int i = 1; i < 9; i++) {
			stringBuilder.append(i);
			stringBuilder.append("  ");
		}
		stringBuilder.append("\n--+");
		stringBuilder.append("-".repeat(24));
		for (int i = 0; i < fields.length; i++) {
			Field[] column = fields[i];
			stringBuilder.append("\n");
			stringBuilder.append((char) ('a' + i));
			stringBuilder.append(" |");
			for (Field cell : column) {
				stringBuilder.append(cell.toString());
				stringBuilder.append(" ");
			}
		}
		return stringBuilder.toString();
	}
	
	public boolean isValidField(char row, byte column) {
		if (column < 1) {
			return false;
		} else if (column > 8) {
			return false;
		} else if (row < 'a') {
			return false;
		} else if (row > 'h') {
			return false;
		}
		return true;
		//return fields[row - FIRST_ROW][column - FIRST_COLUMN].piece == null;
	}
	
	public abstract class Chesspiece {
		
		private char color;
		// w - white, b - black
		private char name;
		// K - King, Q - Queen, R - Rook, B - Bishop, N - Knight,
		// P – Pawn
		protected char row = 0;
		protected byte column = -1;
		
		protected Chesspiece(char color, char name) {
			this.color = color;
			this.name = name;
		}
		
		@Override
		public String toString() {
			return "" + color + name;
		}
		
		public boolean isOnBoard() {
			return isValidField(row, column);
		}
		
		public void moveTo(char row, byte column) throws NotValidFieldException {
			if (!isValidField(row, column)) {
				throw new NotValidFieldException("bad field: ", row, column);
			}
			this.row = row;
			this.column = column;
			int r = row - FIRST_ROW;
			int c = column - FIRST_COLUMN;
			fields[r][c].put(this);
		}
		
		public void moveOut() {
			fields[row - FIRST_ROW][column - FIRST_COLUMN].take();
		}
		
		public abstract void consumeReachableFields(Consumer<Field> consumer);
		
		public void markReachableFields() {
			consumeReachableFields(Field::mark);
		}
		
		public void unmarkReachableFields() {
			consumeReachableFields(Field::unmark);
		}
		
	}
	
	public class Pawn extends Chesspiece {
		
		public Pawn(char color, char name) {
			super(color, name);
		}
		
		@Override
		public void consumeReachableFields(Consumer<Field> consumer) {
			byte col = (byte) (column + 1);
			if (isValidField(row, col)) {
				int r = row - FIRST_ROW;
				int c = col - FIRST_COLUMN;
				consumer.accept(fields[r][c]);
			}
		}
		
	}
	
	public class Rook extends Chesspiece {
		
		protected Rook(char color, char name) {
			super(color, name);
		}
		
		@Override
		public void consumeReachableFields(Consumer<Field> consumer) {
			char r = row;
			byte c = column;
			while (isValidField(r, ++c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			c = column;
			while (isValidField(r, --c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			c = column;
			while (isValidField(++r, c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			r = row;
			while (isValidField(--r, c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
		}
		
	}
	
	public class Knight extends Chesspiece {
		
		protected Knight(char color, char name) {
			super(color, name);
		}
		
		@Override
		public void consumeReachableFields(Consumer<Field> consumer) {
			char r = (char) (row + 2);
			byte c = (byte) (column + 1);
			if (isValidField(r, c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			w
			c -= 2;
			if (isValidField(r, c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			r -= 1;
			c -= 1;
			if (isValidField(r, c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			r -= 2;
			if (isValidField(r, c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			r -= 1;
			c += 1;
			if (isValidField(r, c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			c += 2;
			if (isValidField(r, c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			r += 1;
			c += 1;
			if (isValidField(r, c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			r += 2;
			if (isValidField(r, c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
		}
	}
	
	public class Bishop extends Chesspiece {
		
		protected Bishop(char color, char name) {
			super(color, name);
		}
		
		@Override
		public void consumeReachableFields(Consumer<Field> consumer) {
			char r = row;
			byte c = column;
			while (isValidField(++r, ++c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			r = row;
			c = column;
			while (isValidField(++r, --c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			r = row;
			c = column;
			while (isValidField(--r, ++c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			r = row;
			c = column;
			while (isValidField(--r, --c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
		}
	}
	
	public class Queen extends Chesspiece {
		
		protected Queen(char color, char name) {
			super(color, name);
		}
		
		@Override
		public void consumeReachableFields(Consumer<Field> consumer) {
			char r = row;
			byte c = column;
			while (isValidField(r, ++c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			c = column;
			while (isValidField(++r, ++c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			r = row;
			c = column;
			while (isValidField(++r, c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			r = row;
			while (isValidField(++r, --c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			r = row;
			c = column;
			while (isValidField(r, --c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			c = column;
			while (isValidField(--r, --c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			r = row;
			c = column;
			while (isValidField(--r, c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			r = row;
			while (isValidField(--r, ++c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
		}
	}
	
	public class King extends Chesspiece {
		
		protected King(char color, char name) {
			super(color, name);
		}
		
		@Override
		public void consumeReachableFields(Consumer<Field> consumer) {
			char r = row;
			byte c = column;
			if (isValidField(r, ++c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			c = column;
			if (isValidField(++r, ++c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			r = row;
			c = column;
			if (isValidField(++r, c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			r = row;
			if (isValidField(++r, --c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			r = row;
			c = column;
			if (isValidField(r, --c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			c = column;
			if (isValidField(--r, --c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			r = row;
			c = column;
			if (isValidField(--r, c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
			r = row;
			if (isValidField(--r, ++c)) {
				consumer.accept(fields[r - FIRST_ROW][c - FIRST_COLUMN]);
			}
		}
	}
	
	public class NotValidFieldException extends Exception {
		
		private char row;
		private byte column;
		
		public NotValidFieldException(String message, char row, byte column) {
			super(message + row + column);
			this.row = row;
			this.column = column;
		}
		
		public char getRow() {
			return row;
		}
		
		public byte getColumn() {
			return column;
		}
	}
	
}

