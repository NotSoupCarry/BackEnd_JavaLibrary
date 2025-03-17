package com.example.esercizio1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.esercizio1.models.Autore;

public interface AutoreRepository extends JpaRepository<Autore, Long>{
    
}
