package com.bibliotheque.entrainement.service;

import com.bibliotheque.entrainement.model.Card;
import com.bibliotheque.entrainement.model.Deck;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service de gestion des decks
 * Permet de créer, modifier, supprimer des decks
 */
@Service
public class DeckService {

    private final Map<Long, Deck> decks = new HashMap<>();
    private Long nextId = 1L;

    /**
     * Crée un nouveau deck
     */
    public Deck createDeck(String name, String description) {
        Deck deck = new Deck(nextId++, name, description);
        decks.put(deck.getId(), deck);
        return deck;
    }

    /**
     * Retourne tous les decks
     */
    public List<Deck> getAllDecks() {
        return new ArrayList<>(decks.values());
    }

    /**
     * Retourne un deck par son ID
     */
    public Optional<Deck> getDeckById(Long id) {
        return Optional.ofNullable(decks.get(id));
    }

    /**
     * Sauvegarde un deck (mise à jour)
     */
    public void saveDeck(Deck deck) {
        if (deck.getId() == null) {
            deck.setId(nextId++);
        }
        decks.put(deck.getId(), deck);
    }

    /**
     * Supprime un deck
     */
    public boolean deleteDeck(Long id) {
        return decks.remove(id) != null;
    }

    /**
     * Ajoute une carte à un deck
     */
    public boolean addCardToDeck(Long deckId, Long cardId) {
        Optional<Deck> deckOpt = getDeckById(deckId);
        if (deckOpt.isEmpty()) {
            return false;
        }

        Deck deck = deckOpt.get();
        return deck.addCard(cardId);
    }

    /**
     * Retire une carte d'un deck
     */
    public boolean removeCardFromDeck(Long deckId, Long cardId) {
        Optional<Deck> deckOpt = getDeckById(deckId);
        if (deckOpt.isEmpty()) {
            return false;
        }

        Deck deck = deckOpt.get();
        return deck.removeCard(cardId);
    }

    /**
     * Vide complètement un deck
     */
    public void clearDeck(Long deckId) {
        Optional<Deck> deckOpt = getDeckById(deckId);
        if (deckOpt.isPresent()) {
            Deck deck = deckOpt.get();
            deck.getCards().clear();
        }
    }

    /**
     * Vérifie si un deck est valide (30 cartes exactement)
     */
    public boolean isDeckValid(Long deckId) {
        Optional<Deck> deckOpt = getDeckById(deckId);
        return deckOpt.map(Deck::isValid).orElse(false);
    }

    /**
     * Retourne les statistiques d'un deck
     */
    public Map<String, Object> getDeckStats(Long deckId, CardService cardService) {
        Map<String, Object> stats = new HashMap<>();

        Optional<Deck> deckOpt = getDeckById(deckId);
        if (deckOpt.isEmpty()) {
            return stats;
        }

        Deck deck = deckOpt.get();
        List<Long> cardIds = deck.getCardIds();

        stats.put("totalCards", deck.getTotalCards());
        stats.put("uniqueCards", deck.getCards().size());
        stats.put("isValid", deck.isValid());

        // Calcul du CMC moyen
        double avgCmc = cardIds.stream()
                .map(cardService::getCardById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .mapToInt(Card::getConvertedManaCost)
                .average()
                .orElse(0.0);
        stats.put("averageCmc", String.format("%.2f", avgCmc));

        return stats;
    }

    /**
     * Retourne le nombre total de decks
     */
    public int getTotalDecks() {
        return decks.size();
    }
}
