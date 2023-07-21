package com.example.booktest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Title is required!")
    private String title;
    @NotBlank(message = "Genre is required!")
    private String genre;
    @NotBlank(message = "Published Date is required!")
    private String publishedDate;

    @ManyToOne
    private Author author;
}
