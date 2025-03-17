package com.example.esercizio1.controllers;

import com.example.esercizio1.DTO.AutoreDTO;
import com.example.esercizio1.mappers.AutoreMapper;
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
    public ResponseEntity<Page<AutoreDTO>> getAllAutori(Pageable pageable) {
        Page<Autore> autori = autoreService.getAllAutori(pageable);
        Page<AutoreDTO> autoreDTOs = autori.map(AutoreMapper::toDTO);
        return ResponseEntity.ok(autoreDTOs);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")  // Solo gli utenti autenticati con ruolo 'USER' o superiore possono accedere
    @Operation(summary = "Ottieni un autore per ID", description = "Ritorna un autore specifico in base all'ID")
    @ApiResponse(responseCode = "200", description = "Autore trovato")
    @ApiResponse(responseCode = "404", description = "Autore non trovato")
    public ResponseEntity<AutoreDTO> getAutoreById(@PathVariable Long id) {
        Autore autore = autoreService.getAutoreById(id);
        return ResponseEntity.ok(AutoreMapper.toDTO(autore));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")  // Solo gli utenti con il ruolo 'ADMIN' possono aggiungere autori
    @Operation(summary = "Aggiungi un nuovo autore", description = "Salva un nuovo autore nel database")
    @ApiResponse(responseCode = "201", description = "Autore creato con successo")
    public ResponseEntity<AutoreDTO> addAutore(@RequestBody AutoreDTO autoreDTO) {
        Autore autore = AutoreMapper.toEntity(autoreDTO);
        autore = autoreService.addAutore(autore);
        return ResponseEntity.ok(AutoreMapper.toDTO(autore));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")  // Solo gli utenti con il ruolo 'ADMIN' possono aggiungere autori
    @Operation(summary = "Modifica un autore", description = "Aggiorna i dettagli di un autore esistente")
    @ApiResponse(responseCode = "200", description = "Autore aggiornato con successo")
    @ApiResponse(responseCode = "404", description = "Autore non trovato")
    public ResponseEntity<AutoreDTO> updateAutore(@PathVariable Long id, @RequestBody AutoreDTO autoreDTO) {
        Autore autoreDetails = AutoreMapper.toEntity(autoreDTO);
        autoreDetails.setId(id);
        Autore updatedAutore = autoreService.updateAutore(id, autoreDetails);
        return ResponseEntity.ok(AutoreMapper.toDTO(updatedAutore));
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
