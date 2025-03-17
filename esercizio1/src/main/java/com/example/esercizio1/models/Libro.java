package com.example.esercizio1.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "libri")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titolo;

    private String genere;

    private int annoPubblicazione;

    @ManyToOne
    @JoinColumn(name = "autore_id", nullable = false)
    private Autore autore;  
}