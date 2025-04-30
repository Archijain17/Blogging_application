package com.blog.payloads;





import java.util.HashSet;
import java.util.Set;

import com.blog.entity.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min =4,message="username must be min of characters !!")
   private String name;
	
	@Email(message="Email address is not valid !!")
	private String email;
	
	@NotEmpty
	@Size(min=3,max=10 ,message="Password must be min of 3 characters and max of 10 characters !!")
	private String password;
	
	
	@NotNull
	private String about;
	
	private Set<RoleDto> roles=new HashSet<>();
	
	
}

	
