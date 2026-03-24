package com.bibliotheque.entrainement.controller;

import com.bibliotheque.entrainement.model.Card;
import com.bibliotheque.entrainement.service.CardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class CardController {

    CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/cards")
    public String cards(Model model) {
        List<Card> cards = cardService.getAllCards();
        model.addAttribute("cards", cards);
        return "cards/list";
    }

    @GetMapping("/cards/{id}")
    public String card(@PathVariable Long id, Model model){
        Optional<Card> card = cardService.getCardById(id);
        if(card.isPresent()){
            model.addAttribute("card", card.get());
            return "cards/detail";
        } else {
            return "redirect:/cards";
        }
    }

}
