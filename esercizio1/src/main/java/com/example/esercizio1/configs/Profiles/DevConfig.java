package com.example.esercizio1.configs.Profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevConfig {
    // Configurazione specifica per lo sviluppo
}

