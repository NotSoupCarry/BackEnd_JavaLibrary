package com.example.esercizio1.configs.Profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev2")
public class Dev2Config {
    // Configurazione specifica per lo sviluppo
}

