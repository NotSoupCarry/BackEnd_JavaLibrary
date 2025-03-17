package com.example.esercizio1.controllers;

import com.example.esercizio1.DTO.LibroDTO;
import com.example.esercizio1.mappers.LibroMapper;
import com.example.esercizio1.models.Libro;
import com.example.esercizio1.services.LibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/libri")
@RequiredArgsConstructor
@Tag(name = "Libri", description = "API per la gestione dei libri")
public class LibroController {

    private final LibroService libroService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Recupera tutti i libri", description = "Restituisce l'elenco completo dei libri con paginazione e ordinamento")
    public ResponseEntity<Page<LibroDTO>> getAllLibri(Pageable pageable) {
        Page<Libro> libri = libroService.getAllLibri(pageable);
        Page<LibroDTO> libroDTOs = libri.map(LibroMapper::toDTO);
        return ResponseEntity.ok(libroDTOs);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')") // Solo gli utenti autenticati con ruolo 'USER' o superiore possono accedere
    @Operation(summary = "Recupera un libro per ID", description = "Restituisce un libro dato il suo ID")
    public ResponseEntity<LibroDTO> getLibroById(@PathVariable Long id) {
        Libro libro = libroService.getLibroById(id);
        return ResponseEntity.ok(LibroMapper.toDTO(libro));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')") // Solo gli utenti con il ruolo 'ADMIN' possono aggiungere libri
    @Operation(summary = "Aggiungi un nuovo libro", description = "Permette di aggiungere un nuovo libro al database")
    public ResponseEntity<LibroDTO> addLibro(@RequestBody LibroDTO libroDTO) {
        Libro libro = LibroMapper.toEntity(libroDTO);
        libro = libroService.addLibro(libro);
        return ResponseEntity.ok(LibroMapper.toDTO(libro));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')") // Solo gli utenti con il ruolo 'ADMIN' possono aggiornare i libri
    @Operation(summary = "Aggiorna un libro", description = "Modifica i dettagli di un libro esistente")
    public ResponseEntity<LibroDTO> updateLibro(@PathVariable Long id, @RequestBody LibroDTO libroDTO) {
        Libro libroDetails = LibroMapper.toEntity(libroDTO);
        libroDetails.setId(id);
        Libro updatedLibro = libroService.updateLibro(id, libroDetails);
        return ResponseEntity.ok(LibroMapper.toDTO(updatedLibro));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')") // Solo gli utenti con il ruolo 'ADMIN' possono eliminare i libri
    @Operation(summary = "Elimina un libro", description = "Cancella un libro dal database dato il suo ID")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
        libroService.deleteLibro(id);
        return ResponseEntity.noContent().build();
    }
}
