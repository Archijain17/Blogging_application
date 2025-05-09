package com.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	
	private int categoryId;
	
	@NotBlank
	@Size(min=4,message="min size of category title is 4")
	private String categoryTitle;
	
	@NotBlank
	@Size(min=8,message="min size of category description is 8")
	private String categoryDescription;
}
