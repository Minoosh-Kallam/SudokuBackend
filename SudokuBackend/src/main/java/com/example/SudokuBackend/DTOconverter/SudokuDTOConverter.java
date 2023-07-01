package com.example.SudokuBackend.DTOconverter;

import java.util.ArrayList;
import java.util.List;

import com.example.SudokuBackend.dto.SudokuDTO;
import com.example.SudokuBackend.model.Sudoku;

public class SudokuDTOConverter {

	public static Sudoku convertFromDTO(SudokuDTO sudokuDTO) {
		
		List<List<Character>> board = new ArrayList<>();
		int id = sudokuDTO.getId();
		int invalidAttempts = sudokuDTO.getInvalidAttempts();
		
		for(List<String> row : sudokuDTO.getBoard()) {
			List<Character> charRow = new ArrayList<>();
			for(String ele : row) {
				charRow.add(ele.charAt(0));
			}
			board.add(charRow);
		}
		
		return Sudoku.builder().id(id).invalidAttempts(invalidAttempts).board(board).build();
		
	}
	
	
	public static SudokuDTO convertToDTO(Sudoku sudoku) {
		
		List<List<String>> board = new ArrayList<>();
		int invalidAttempts = sudoku.getInvalidAttempts(), id = sudoku.getId();
		
		for(List<Character> row : sudoku.getBoard()) {
			List<String> charRow = new ArrayList<>();
			for(Character ele : row) {
				charRow.add(""+ele);
			}
			board.add(charRow);
		}
		
		return SudokuDTO.builder().invalidAttempts(invalidAttempts).id(id).board(board).build() ;
	}
}
