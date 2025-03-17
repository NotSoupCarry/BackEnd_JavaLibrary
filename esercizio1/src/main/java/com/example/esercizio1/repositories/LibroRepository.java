package com.example.esercizio1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.esercizio1.models.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{
    
}
