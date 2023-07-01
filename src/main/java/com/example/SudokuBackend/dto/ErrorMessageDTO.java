package com.example.SudokuBackend.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder
public class ErrorMessageDTO {

	private LocalDate time;
	private String errorMessage;
	private int status;
	private String suggestion;
	
}
