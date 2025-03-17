package com.example.esercizio1.controllers.thymeleaf;

import com.example.esercizio1.models.Autore;
import com.example.esercizio1.models.Libro;
import com.example.esercizio1.services.AutoreService;
import com.example.esercizio1.services.LibroService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final LibroService libroService;
    private final AutoreService autoreService;

    // Pagina principale per l'amministratore (elenco di autori e libri)
    @GetMapping("/admin/home")
    public String adminHome(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Libro> libriPage = libroService.getAllLibri(pageable);
        Page<Autore> autoriPage = autoreService.getAllAutori(pageable);

        model.addAttribute("libri", libriPage);
        model.addAttribute("autori", autoriPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPagesLibri", libriPage.getTotalPages());
        model.addAttribute("totalPagesAutori", autoriPage.getTotalPages());
        model.addAttribute("size", size);

        return "admin/home"; // Restituisce la pagina admin/home.html
    }

    // Aggiungi Libro (GET)
    @GetMapping("/admin/libri/add")
    public String addLibroForm(Model model) {
        List<Autore> autori = autoreService.getListAllAutori(); // Recupera tutti gli autori dal DB
        model.addAttribute("libro", new Libro());
        model.addAttribute("autori", autori); // Aggiungi la lista degli autori al modello
        return "admin/addLibro"; // Vista per l'aggiunta del libro
    }

    // Aggiungi Libro (POST)
    @PostMapping("/admin/libri/add")
    public String addLibro(@ModelAttribute Libro libro, Model model) {
        libroService.addLibro(libro);
        return "redirect:/admin/home"; // Redirect alla lista dei libri dopo l'aggiunta
    }

    // Aggiungi Autore (GET)
    @GetMapping("/admin/autori/add")
    public String addAutoreForm(Model model) {
        model.addAttribute("autore", new Autore());
        return "admin/addAutore"; // Vista per l'aggiunta dell'autore
    }

    // Aggiungi Autore (POST)
    @PostMapping("/admin/autori/add")
    public String addAutore(@ModelAttribute Autore autore, Model model) {
        autoreService.addAutore(autore);
        return "redirect:/admin/home"; // Redirect alla lista degli autori dopo l'aggiunta
    }

    // Gestione Libri
    @GetMapping("/admin/libri")
    public String gestioneLibri(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Libro> libriPage = libroService.getAllLibri(pageable);
        model.addAttribute("libri", libriPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", libriPage.getTotalPages());
        model.addAttribute("size", size);
        return "admin/libri"; // Restituisce la pagina di gestione dei libri
    }

    // Gestione Autori
    @GetMapping("/admin/autori")
    public String gestioneAutori(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Autore> autoriPage = autoreService.getAllAutori(pageable);
        model.addAttribute("autori", autoriPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", autoriPage.getTotalPages());
        model.addAttribute("size", size);
        return "admin/autori"; // Restituisce la pagina di gestione degli autori
    }

    // Modifica Libro (GET)
    @GetMapping("/admin/libri/edit/{id}")
    public String editLibro(@PathVariable Long id, Model model) {
        // Recupera il libro con il suo id
        Libro libro = libroService.getLibroById(id);

        // Recupera la lista di autori dal database
        List<Autore> autori = autoreService.getListAllAutori();

        // Aggiungi l'autore e il libro al modello
        model.addAttribute("libro", libro);
        model.addAttribute("autori", autori);

        // Restituisci la vista di modifica libro
        return "admin/editLibro";
    }

    // Modifica Libro (POST)
    @PostMapping("/admin/libri/edit/{id}")
    public String updateLibro(@PathVariable Long id, @ModelAttribute Libro libroDetails, Model model) {
        // Ottieni il libro esistente dal database
        Libro libro = libroService.getLibroById(id);

        // Aggiorna le informazioni del libro
        libro.setTitolo(libroDetails.getTitolo());
        libro.setGenere(libroDetails.getGenere());
        libro.setAnnoPubblicazione(libroDetails.getAnnoPubblicazione());

        // Imposta l'autore selezionato
        Autore autoreSelezionato = autoreService.getAutoreById(libroDetails.getAutore().getId());
        libro.setAutore(autoreSelezionato);

        // Salva il libro aggiornato nel database
        libroService.addLibro(libro);

        return "redirect:/admin/home"; // Reindirizza alla lista dei libri dopo l'aggiornamento
    }

    // Elimina Libro
    @GetMapping("/admin/libri/delete/{id}")
    public String deleteLibro(@PathVariable Long id) {
        libroService.deleteLibro(id);
        return "redirect:/admin/home"; // Redirect alla lista dei libri dopo la cancellazione
    }

    // Modifica Autore (GET)
    @GetMapping("/admin/autori/edit/{id}")
    public String editAutore(@PathVariable Long id, Model model) {
        Autore autore = autoreService.getAutoreById(id);
        model.addAttribute("autore", autore);
        return "admin/editAutore"; // Vista per la modifica dell'autore
    }

    // Modifica Autore (POST)
    @PostMapping("/admin/autori/edit/{id}")
    public String updateAutore(@PathVariable Long id, @ModelAttribute Autore autoreDetails, Model model) {
        autoreService.updateAutore(id, autoreDetails);
        return "redirect:/admin/home"; // Redirect alla lista degli autori dopo la modifica
    }

    // Elimina Autore
    @GetMapping("/admin/autori/delete/{id}")
    public String deleteAutore(@PathVariable Long id) {
        autoreService.deleteAutore(id);
        return "redirect:/admin/home"; // Redirect alla lista degli autori dopo la cancellazione
    }
}
