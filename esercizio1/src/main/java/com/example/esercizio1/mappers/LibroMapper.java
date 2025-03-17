package com.example.esercizio1.mappers;

import com.example.esercizio1.DTO.LibroDTO;
import com.example.esercizio1.models.Libro;

public class LibroMapper {

    // Converti da Libro a LibroDTO
    public static LibroDTO toDTO(Libro libro) {
        return new LibroDTO(
                libro.getId(),
                libro.getTitolo(),
                libro.getGenere(),
                libro.getAnnoPubblicazione(),
                libro.getAutore() != null ? libro.getAutore().getId() : null  // Aggiungi solo l'ID dell'autore
        );
    }

    // Converti da LibroDTO a Libro (quando riceviamo dati da un client)
    public static Libro toEntity(LibroDTO libroDTO) {
        Libro libro = new Libro();
        libro.setId(libroDTO.getId());
        libro.setTitolo(libroDTO.getTitolo());
        libro.setGenere(libroDTO.getGenere());
        libro.setAnnoPubblicazione(libroDTO.getAnnoPubblicazione());
        return libro;
    }
}
