package com.blm.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    private String publication;
    private Integer publicationYear;
    private Integer availableCopies;
    private String genre;

}
