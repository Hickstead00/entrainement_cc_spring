package com.bibliotheque.entrainement.controller;

import com.bibliotheque.entrainement.model.Deck;
import com.bibliotheque.entrainement.service.DeckService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/decks")
public class DeckController {

    DeckService deckService;

    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @GetMapping
    public String decks(Model model) {
        List<Deck> decks = deckService.getAllDecks();
        model.addAttribute("decks", decks);
        return "decks/list";
    }

    @GetMapping("/new")
    public String newDeck(Model model) {
        Deck deck = new Deck();
        model.addAttribute("deck", deck);
        return "decks/form";
    }

    @PostMapping("/new")
    public String newDeck(@Valid @ModelAttribute Deck deck, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "decks/form";
        }
        deckService.saveDeck(deck);
        return "redirect:/decks";
    }

}
