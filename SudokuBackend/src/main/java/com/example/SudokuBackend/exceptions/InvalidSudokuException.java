package com.example.SudokuBackend.exceptions;

public class InvalidSudokuException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidSudokuException(String message) {
		super(message);
	}
}
