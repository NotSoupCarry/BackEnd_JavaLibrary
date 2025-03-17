package com.example.esercizio1.controllers;

import com.example.esercizio1.models.Autore;
import com.example.esercizio1.services.AutoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/autori")
@RequiredArgsConstructor
@Tag(name = "Autori", description = "Gestione degli autori") // Nome per Swagger
public class AutoreController {

    private final AutoreService autoreService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Ottieni tutti gli autori", description = "Ritorna una lista di autori con paginazione e ordinamento")
    @ApiResponse(responseCode = "200", description = "Lista di autori recuperata con successo")
    public ResponseEntity<Page<Autore>> getAllAutori(Pageable pageable) {
        Page<Autore> autori = autoreService.getAllAutori(pageable);
        return ResponseEntity.ok(autori);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")  // Solo gli utenti autenticati con ruolo 'USER' o superiore possono accedere
    @Operation(summary = "Ottieni un autore per ID", description = "Ritorna un autore specifico in base all'ID")
    @ApiResponse(responseCode = "200", description = "Autore trovato")
    @ApiResponse(responseCode = "404", description = "Autore non trovato")
    public ResponseEntity<Autore> getAutoreById(@PathVariable Long id) {
        return ResponseEntity.ok(autoreService.getAutoreById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")  // Solo gli utenti con il ruolo 'ADMIN' possono aggiungere autori
    @Operation(summary = "Aggiungi un nuovo autore", description = "Salva un nuovo autore nel database")
    @ApiResponse(responseCode = "201", description = "Autore creato con successo")
    public ResponseEntity<Autore> addAutore(@RequestBody Autore autore) {
        return ResponseEntity.ok(autoreService.addAutore(autore));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")  // Solo gli utenti con il ruolo 'ADMIN' possono aggiungere autori
    @Operation(summary = "Modifica un autore", description = "Aggiorna i dettagli di un autore esistente")
    @ApiResponse(responseCode = "200", description = "Autore aggiornato con successo")
    @ApiResponse(responseCode = "404", description = "Autore non trovato")
    public ResponseEntity<Autore> updateAutore(@PathVariable Long id, @RequestBody Autore autoreDetails) {
        return ResponseEntity.ok(autoreService.updateAutore(id, autoreDetails));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")  // Solo gli utenti con il ruolo 'ADMIN' possono aggiungere autori
    @Operation(summary = "Elimina un autore", description = "Elimina un autore per ID")
    @ApiResponse(responseCode = "204", description = "Autore eliminato con successo")
    @ApiResponse(responseCode = "404", description = "Autore non trovato")
    public ResponseEntity<Void> deleteAutore(@PathVariable Long id) {
        autoreService.deleteAutore(id);
        return ResponseEntity.noContent().build();
    }
}
