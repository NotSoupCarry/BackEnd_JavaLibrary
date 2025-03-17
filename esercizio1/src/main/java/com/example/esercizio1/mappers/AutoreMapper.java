package com.example.esercizio1.mappers;

import com.example.esercizio1.DTO.AutoreDTO;
import com.example.esercizio1.models.Autore;

public class AutoreMapper {

    // Converti da Autore a AutoreDTO
    public static AutoreDTO toDTO(Autore autore) {
        return new AutoreDTO(
                autore.getId(),
                autore.getNome(),
                autore.getCognome(),
                autore.getNazionalita()
        );
    }

    // Converti da AutoreDTO a Autore (per quando si ricevono dati da un client)
    public static Autore toEntity(AutoreDTO autoreDTO) {
        Autore autore = new Autore();
        autore.setId(autoreDTO.getId());
        autore.setNome(autoreDTO.getNome());
        autore.setCognome(autoreDTO.getCognome());
        autore.setNazionalita(autoreDTO.getNazionalita());
        return autore;
    }
}

