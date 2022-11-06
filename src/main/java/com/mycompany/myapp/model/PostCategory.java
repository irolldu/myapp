package com.mycompany.myapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCategory {

	private Integer id;
	private Post post;
	private Integer categoryId;
	
}
