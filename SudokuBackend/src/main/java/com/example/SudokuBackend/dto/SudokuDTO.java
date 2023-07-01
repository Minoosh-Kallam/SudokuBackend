package com.example.SudokuBackend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class SudokuDTO {

	private List<List<String>> board;
	private int invalidAttempts;
	private int id;
	
}
