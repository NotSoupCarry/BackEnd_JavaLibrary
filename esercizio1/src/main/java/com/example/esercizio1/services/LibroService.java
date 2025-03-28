package com.example.esercizio1.services;

import org.springframework.stereotype.Service;

import com.example.esercizio1.models.Libro;
import com.example.esercizio1.repositories.LibroRepository;
import com.example.esercizio1.websocket.LibroWebSocketHandler;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibroService {
    private final LibroRepository libroRepository;

    // Metodo per ottenere i libri con paginazione e ordinamento
    @Cacheable(value = "libri", key = "#pageable.pageNumber + '_' + #pageable.pageSize")
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
    @CacheEvict(value = "libri", key = "#id")
    public void deleteLibro(Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro non trovato con id: " + id));

        libroRepository.delete(libro);
    }

    private final LibroWebSocketHandler libroWebSocketHandler;

    @Async
    public void notifyOnNewLibro(Libro libro) {
        String message = "Nuovo libro aggiunto: " + libro.getTitolo();
        libroWebSocketHandler.sendNotification(message);
    }
}
