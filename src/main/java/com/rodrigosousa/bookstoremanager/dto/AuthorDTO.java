package com.rodrigosousa.bookstoremanager.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {

	private Long id;
	
	@NotBlank
	@Size(max = 200)
	private String name;
	
	@NotNull
	@Size(max = 100)
	private Integer age;
}
