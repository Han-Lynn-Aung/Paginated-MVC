package com.example.booktest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;


@Entity
@Table (name = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required!")
    private String name;

    @NotNull(message = "Date of Birth is required!")
    @Past(message = "Date of Birth must be in the past")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateOfBirth;

    @NotBlank(message = "Address is required!")
    private String Address;
}
