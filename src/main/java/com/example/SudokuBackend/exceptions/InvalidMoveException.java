package com.example.SudokuBackend.exceptions;

public class InvalidMoveException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidMoveException(String message) {
		super(message);
	}
	
}
