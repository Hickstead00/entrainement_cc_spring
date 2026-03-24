package com.bibliotheque.entrainement.controller;

import com.bibliotheque.entrainement.service.CardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    CardService cardService;

    public HomeController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Bienvenue dans le gestionnaire de deck Magic");
        int totalCards = cardService.getTotalCards();
        model.addAttribute("totalCards", totalCards);
        return "home";
    }

}
