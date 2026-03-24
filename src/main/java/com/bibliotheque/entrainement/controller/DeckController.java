package com.bibliotheque.entrainement.controller;

import com.bibliotheque.entrainement.model.Deck;
import com.bibliotheque.entrainement.service.CardService;
import com.bibliotheque.entrainement.service.DeckService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/decks")
public class DeckController {

    private final CardService cardService;
    private final DeckService deckService;

    public DeckController(DeckService deckService, CardService cardService) {
        this.deckService = deckService;
        this.cardService = cardService;
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

    @GetMapping("/{id}")
    public String showDeck(@PathVariable Long id, Model model) {
        Optional<Deck> deck = deckService.getDeckById(id);
        if (deck.isPresent()) {
            model.addAttribute("deck", deck.get());
            model.addAttribute("stats", deckService.getDeckStats(id, cardService));
            return "decks/detail";
        } else {
            return "redirect:/decks";
        }
    }

}
