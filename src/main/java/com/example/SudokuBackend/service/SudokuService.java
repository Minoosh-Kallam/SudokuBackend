package com.example.SudokuBackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.SudokuBackend.dto.SudokuDTO;
import com.example.SudokuBackend.model.Move;
import com.example.SudokuBackend.model.Sudoku;
import com.example.SudokuBackend.utilities.SudokuUtilities;

@Service
public class SudokuService {

	public Sudoku getNewSudoku() {
		List<List<Character>> board = new ArrayList<>();
		return new Sudoku(board);
	}
	
	public Sudoku createNewSudoku(Sudoku sudoku) {
		if(!SudokuUtilities.isValidSudoku(sudoku.getBoard())) {
			throw new RuntimeException("Given board is not valid");
		}
		sudoku.setAnswer(SudokuUtilities.solveSudoku(sudoku.getBoard()));
		return sudoku; 
	}
	
	public Move move(int row, int column) {
		
		if(row < 0 || column >= 9)
			return null;
		Move move = Move.builder().row(row).column(column).build();
		return move;
	}

	public String insert(Sudoku sudoku, Move move, int number) {
		
		if(number < 1 || number > 9)
			return "Invalid";
		if(sudoku == null || move == null)
			return "Invalid! Set move first"+sudoku+" "+move;
		
		List<List<Character>> board = sudoku.getBoard();
		if(sudoku.getAnswer().get(move.getRow()).get(move.getColumn()).equals((char)(number+'0'))) {
			board.get(move.getRow()).set(move.getColumn(), (char)(number+'0'));
			
			if(board.toString().equals(sudoku.getAnswer().toString())) {
				return "Congratulations!! Game Over";
			}
			sudoku.setInvalidAttempts(0);
			return "Correct";
		}
		else {
			sudoku.setInvalidAttempts(sudoku.getInvalidAttempts()+1);
			if(sudoku.getInvalidAttempts() == 3)
				return "Incorrect, suggestion: "+sudoku.getAnswer().get(move.getRow()).get(move.getColumn());
			return "Incorrect";
		}
		
	}
	
	

}
