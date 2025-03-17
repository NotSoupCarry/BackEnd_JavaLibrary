package com.example.esercizio1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroDTO {

    private Long id;
    private String titolo;
    private String genere;
    private int annoPubblicazione;
    private Long autoreId; // Solo l'ID dell'autore, non l'intero oggetto Autore
}
