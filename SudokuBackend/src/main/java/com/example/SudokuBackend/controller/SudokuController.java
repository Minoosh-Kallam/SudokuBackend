package com.example.SudokuBackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SudokuBackend.DTOconverter.SudokuDTOConverter;
import com.example.SudokuBackend.dto.SudokuDTO;
import com.example.SudokuBackend.model.Move;
import com.example.SudokuBackend.model.Sudoku;
import com.example.SudokuBackend.service.SudokuService;
import com.example.SudokuBackend.utilities.SudokuUtilities;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("sudoku")
@AllArgsConstructor
public class SudokuController {

	
	private final SudokuService sudokuService;
	
	
	@GetMapping("new")
	public Sudoku getNewSudoku(HttpSession session){
		for(int k=0;k<10;k++){
			System.out.println("Hello from branch2");
		}
		Sudoku sudoku = sudokuService.getNewSudoku();
		session.setAttribute("sudoku", sudoku);
		return sudoku;
	}
	
	@PostMapping("START")
	public String createNewSudoku(HttpSession session, @RequestBody SudokuDTO sudokuDTO){
		Sudoku sudoku = SudokuDTOConverter.convertFromDTO(sudokuDTO);
		sudokuService.createNewSudoku(sudoku);
		session.setAttribute("sudoku", sudoku);
		return "READY";
	}
	
	@GetMapping("")
	public SudokuDTO getCurrentSudoku(HttpSession session) {
		Sudoku sudoku = (Sudoku)session.getAttribute("sudoku");
		return SudokuDTOConverter.convertToDTO(sudoku);
	}
	
	@GetMapping("move/{row}/{column}")
	public String move(@PathVariable int row, @PathVariable int column, HttpSession session) {
		Sudoku sudoku = (Sudoku)session.getAttribute("sudoku");
		Move move = sudokuService.move(sudoku, row, column);
		session.setAttribute("move", move);
		return "Valid";
	}
	
	@PostMapping("move")
	public String move(@RequestBody Move move, HttpSession session) {
		Sudoku sudoku = (Sudoku)session.getAttribute("sudoku");
		move = sudokuService.move(sudoku, move.getRow(), move.getColumn());
		session.setAttribute("move", move);
		return "Valid";
	}
	
	@PostMapping("insert")
	public String insert(@RequestBody int number, HttpSession session) {
			
		Move move = (Move)session.getAttribute("move");
		Sudoku sudoku = (Sudoku)session.getAttribute("sudoku");
		
		return sudokuService.insert(sudoku, move, number);
		
	}
	
	@PostMapping("insert/{number}")
	public String moveAndInsert(@RequestBody Move move, @PathVariable int number, HttpSession session) {
		
		Sudoku sudoku = (Sudoku)session.getAttribute("sudoku");
		move = sudokuService.move(sudoku, move.getRow(), move.getColumn());
		return sudokuService.insert(sudoku, move, number);
	}
}
