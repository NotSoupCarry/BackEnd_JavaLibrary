package com.example.esercizio1.services;

import org.springframework.stereotype.Service;

import com.example.esercizio1.models.Libro;
import com.example.esercizio1.repositories.LibroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibroService {
    private final LibroRepository libroRepository;

    // Metodo per ottenere i libri con paginazione e ordinamento
    public Page<Libro> getAllLibri(Pageable pageable) {
        return libroRepository.findAll(pageable);
    }

    // GET - Ottieni un libro per ID
    public Libro getLibroById(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro non trovato con id: " + id));
    }

    // POST - Aggiungi un nuovo libro
    @Transactional
    public Libro addLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    // PUT - Modifica un libro esistente
    @Transactional
    public Libro updateLibro(Long id, @Valid Libro libroDetails) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro non trovato con id: " + id));

        libro.setTitolo(libroDetails.getTitolo());
        libro.setAutore(libroDetails.getAutore());
        libro.setAnnoPubblicazione(libroDetails.getAnnoPubblicazione());

        return libroRepository.save(libro);
    }

    // DELETE - Elimina un libro per ID
    @Transactional
    public void deleteLibro(Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro non trovato con id: " + id));

        libroRepository.delete(libro);
    }
}
