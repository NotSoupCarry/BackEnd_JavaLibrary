package com.example.esercizio1.controllers.thymeleaf;

import com.example.esercizio1.models.Autore;
import com.example.esercizio1.models.Libro;
import com.example.esercizio1.services.AutoreService;
import com.example.esercizio1.services.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class UserPagesController {

    private final AutoreService autoreService;
    private final LibroService libroService;

    @GetMapping
    public String home(Pageable pageable, Model model) {
        // Otteniamo la lista di autori e libri con paginazione
        Page<Autore> autori = autoreService.getAllAutori(pageable);
        Page<Libro> libri = libroService.getAllLibri(pageable);

        // Aggiungiamo i dati al model
        model.addAttribute("autori", autori);
        model.addAttribute("libri", libri);

        // Restituiamo il nome della pagina thymeleaf da visualizzare
        return "home"; 
    }
}
