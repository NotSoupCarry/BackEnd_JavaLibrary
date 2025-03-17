package com.example.esercizio1.services;

import com.example.esercizio1.models.Autore;
import com.example.esercizio1.repositories.AutoreRepository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class AutoreService {

    private final AutoreRepository autoreRepository;

    // Metodo per ottenere gli autori con paginazione e ordinamento
    @Cacheable(value = "autori", key = "#pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<Autore> getAllAutori(Pageable pageable) {
        return autoreRepository.findAll(pageable);
    }

    // Ottieni un autore per ID
    public Autore getAutoreById(Long id) {
        return autoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autore non trovato con id: " + id));
    }

    // Aggiungi un nuovo autore
    @Transactional
    public Autore addAutore(Autore autore) {
        return autoreRepository.save(autore);
    }

    // Modifica un autore esistente
    @Transactional
    public Autore updateAutore(Long id, @Valid Autore autoreDetails) {
        Autore autore = getAutoreById(id);
        autore.setNome(autoreDetails.getNome());
        autore.setCognome(autoreDetails.getCognome());
        return autoreRepository.save(autore);
    }

    // Elimina un autore per ID
    @Transactional
    @CacheEvict(value = "autori", key = "#id")
    public void deleteAutore(Long id) {
        autoreRepository.deleteById(id);
    }
}
