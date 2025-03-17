package com.example.esercizio1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoreDTO {

    private Long id;
    private String nome;
    private String cognome;
    private String nazionalita;
}