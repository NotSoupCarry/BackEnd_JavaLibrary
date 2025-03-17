package com.example.esercizio1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.esercizio1.models.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Long>{
    Utente findByUsername(String username);

}
