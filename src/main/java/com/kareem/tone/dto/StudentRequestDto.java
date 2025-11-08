package com.kareem.tone.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentRequestDto {
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @Email(message = "Email is not valid")
    @NotBlank(message = "Email cannot be empty")
    private String email;
    //@NotBlank(message = "Age cannot be empty")
    @Min(value = 18, message = "Age must be greater than 18")
    private int age;
}
