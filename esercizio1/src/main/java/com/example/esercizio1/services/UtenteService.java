package com.example.esercizio1.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.esercizio1.enums.Ruolo;
import com.example.esercizio1.models.Utente;
import com.example.esercizio1.repositories.UtenteRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UtenteService {
     private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Utente register(Utente utente) {
        if (utente.getRuolo() == null || utente.getRuolo().name() == null) {
            utente.setRuolo(Ruolo.ROLE_USER);
        }
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        return utenteRepository.save(utente);
    }

    public Utente findByEmail(String userame) {
        return utenteRepository.findByUsername(userame);
    }
}
