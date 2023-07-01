package com.example.SudokuBackend.controller;

import java.time.LocalDate;

import javax.naming.InvalidNameException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.SudokuBackend.dto.ErrorMessageDTO;
import com.example.SudokuBackend.exceptions.InvalidMoveException;
import com.example.SudokuBackend.exceptions.InvalidNumberException;
import com.example.SudokuBackend.exceptions.InvalidSudokuException;

@RestControllerAdvice
public class ErrorController {
	
	@ExceptionHandler(value = InvalidMoveException.class)
	public ResponseEntity<ErrorMessageDTO> handleInvalidMove(InvalidMoveException exception) {
		ErrorMessageDTO error = ErrorMessageDTO.builder()
				.errorMessage(exception.getMessage())
				.status(400)
				.time(LocalDate.now())
				.suggestion("Give the co-ordinates from 0-8 (inclusive) and the co-ordinates should point to empty block")
				.build();
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = InvalidSudokuException.class)
	public ResponseEntity<ErrorMessageDTO> handleInvalidSudoku(InvalidSudokuException exception) {
		ErrorMessageDTO error = ErrorMessageDTO.builder()
				.errorMessage(exception.getMessage())
				.status(400)
				.time(LocalDate.now())
				.suggestion("Please follow the rules to create valid sudoku")
				.build();
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = InvalidNumberException.class)
	public ResponseEntity<ErrorMessageDTO> handleInvalidNumber(InvalidNumberException exception) {
		ErrorMessageDTO error = ErrorMessageDTO.builder()
				.errorMessage(exception.getMessage())
				.status(400)
				.time(LocalDate.now())
				.suggestion("make sure the value is in 1-9 (inclusive), also make sure that the board and move are set")
				.build();
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<ErrorMessageDTO> handleRuntime(RuntimeException exception) {
		ErrorMessageDTO error = ErrorMessageDTO.builder()
				.errorMessage(exception.getMessage())
				.status(500)
				.time(LocalDate.now())
				.build();
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}

