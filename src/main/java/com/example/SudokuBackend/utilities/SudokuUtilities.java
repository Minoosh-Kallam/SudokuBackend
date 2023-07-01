package com.example.SudokuBackend.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuUtilities {

	private static boolean isValidChunk(List<Character> nums) {
		Set<Character> set = new HashSet<>();
		for (Character ch : nums) {
			if (!ch.equals('.') && set.contains(ch))
				return false;
			set.add(ch);
		}
		return true;
	}

	private static boolean isValidChunk(List<List<Character>> board, int index) {
		Set<Character> set = new HashSet<>();
		for (int i = 0; i < 9; i++) {
			if (!board.get(i).get(index).equals('.') && set.contains(board.get(i).get(index)))
				return false;
			set.add(board.get(i).get(index));
		}
		return true;
	}

	private static boolean isValidChunk(List<List<Character>> board, int x1, int y1, int x2, int y2) {
		Set<Character> set = new HashSet<>();

		for (int i = x1; i <= x2; i++) {
			for (int j = y1; j <= y2; j++) {
				if (!board.get(i).get(j).equals('.') && set.contains(board.get(i).get(j)))
					return false;
				set.add(board.get(i).get(j));
			}
		}
		return true;
	}

	public static boolean isValidSudoku(List<List<Character>> board) {
		int n = 9;
		for (int i = 0; i < n; i++) {
			if (!isValidChunk(board.get(i)))
				return false;
		}
		for (int i = 0; i < n; i++) {
			if (!isValidChunk(board, i))
				return false;
		}
		int period = 3;
		for (int i = 0; i < period; i++) {
			for (int j = 0; j < period; j++) {
				if (!isValidChunk(board, i * 3, j * 3, (i + 1) * 3 - 1, (j + 1) * 3 - 1))
					return false;
			}
		}
		return true;
	}

	
	
	
	public static boolean isValidPosition(List<List<Character>> board, int row, int column, int number) {
		char ch = (char) (number + '0');

		for (int i = 0; i < 9; i++) {
			if (board.get(i).get(column).equals(ch) || board.get(row).get(i).equals(ch))
				return false;
		}

		int left = 3 * (column / 3), top = 3 * (row / 3);

		for (int i = top; i < top + 3; i++) {
			for (int j = left; j < left + 3; j++) {
				if (board.get(i).get(j).equals(ch) && i != row && j != column)
					return false;
			}
		}
		// System.out.println("["+row+","+column+"]="+ch);
		return true;

	}

	public static boolean fillSudoku(List<List<Boolean>> originalBoard, List<List<Character>> board,int row, int column) {
		if (row == 9)
			return true;
		if (column == 9) {
			// System.out.println("next row");
			return fillSudoku(originalBoard, board, row + 1, 0);
		}
		if (originalBoard.get(row).get(column))
			return fillSudoku(originalBoard, board, row, column + 1);

		for (int i = 1; i <= 9; i++) {
			if (isValidPosition(board, row, column, i)) {
				board.get(row).set(column, (char) (i + '0'));
				if (fillSudoku(originalBoard, board, row, column + 1)) {
					// System.out.println("Found");
					return true;
				}
				board.get(row).set(column,'.');
			}
		}

		return false;
	}

	public static List<List<Character>>  solveSudoku(List<List<Character>> board) {
		List<List<Boolean>> originalBoard = new ArrayList<>();
		List<List<Character>> answer = new ArrayList<>();
		
		for(int i=0;i<9;i++) {
			List<Boolean> temp1 = new ArrayList<>();
			List<Character> temp2 = new ArrayList<>();
			for(int j=0;j<9;j++) {
				temp1.add(false);
				temp2.add(board.get(i).get(j));
			}
			originalBoard.add(temp1);
			answer.add(temp2);
		}
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!board.get(i).get(j).equals('.') )
					originalBoard.get(i).set(j, true);
			}
		}
		fillSudoku(originalBoard, answer, 0, 0);
		return answer;
		
	}
}
