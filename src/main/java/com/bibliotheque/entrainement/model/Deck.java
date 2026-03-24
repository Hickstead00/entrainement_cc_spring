package com.bibliotheque.entrainement.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Représente un deck de cartes
 */
public class Deck {
    private Long id;

    @NotBlank(message = "Le nom du deck est obligatoire")
    @Size(min = 3, max = 50, message = "Le nom doit contenir entre 3 et 50 caractères")
    private String name;

    private String description;

    // Map: cardId -> quantité (max 2 par carte)
    private Map<Long, Integer> cards;

    // Constantes de validation
    public static final int MIN_DECK_SIZE = 30;
    public static final int MAX_DECK_SIZE = 30;
    public static final int MAX_CARD_COPIES = 2;

    public Deck() {
        this.cards = new HashMap<>();
    }

    public Deck(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cards = new HashMap<>();
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<Long, Integer> getCards() {
        return cards;
    }

    public void setCards(Map<Long, Integer> cards) {
        this.cards = cards;
    }

    // Méthodes utilitaires

    /**
     * Retourne le nombre total de cartes dans le deck
     */
    public int getTotalCards() {
        return cards.values().stream().mapToInt(Integer::intValue).sum();
    }

    /**
     * Ajoute une carte au deck (max 2 copies)
     */
    public boolean addCard(Long cardId) {
        if (getTotalCards() >= MAX_DECK_SIZE) {
            return false; // Deck plein
        }

        int currentCount = cards.getOrDefault(cardId, 0);
        if (currentCount >= MAX_CARD_COPIES) {
            return false; // Déjà 2 copies
        }

        cards.put(cardId, currentCount + 1);
        return true;
    }

    /**
     * Retire une carte du deck
     */
    public boolean removeCard(Long cardId) {
        if (!cards.containsKey(cardId)) {
            return false;
        }

        int currentCount = cards.get(cardId);
        if (currentCount <= 1) {
            cards.remove(cardId);
        } else {
            cards.put(cardId, currentCount - 1);
        }
        return true;
    }

    /**
     * Vérifie si le deck est valide (exactement 30 cartes)
     */
    public boolean isValid() {
        return getTotalCards() == MIN_DECK_SIZE;
    }

    /**
     * Retourne la liste des IDs de cartes (avec répétitions)
     */
    public List<Long> getCardIds() {
        List<Long> result = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : cards.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return name + " (" + getTotalCards() + " cartes)";
    }
}
