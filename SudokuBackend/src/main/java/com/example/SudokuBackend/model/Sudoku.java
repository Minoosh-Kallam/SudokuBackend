package com.example.SudokuBackend.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder
public class Sudoku {

	private int id ;
	private List<List<Character>> board;
	private List<List<Character>> answer;
	private int invalidAttempts;

	public Sudoku(List<List<Character>> board) {
		super();
		this.board = board;
	}
	
	
	
	
}
