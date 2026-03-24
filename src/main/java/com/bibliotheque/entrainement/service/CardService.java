package com.bibliotheque.entrainement.service;

import com.bibliotheque.entrainement.model.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service de gestion des cartes Magic: The Gathering
 * Contient la collection complète de cartes disponibles
 */
@Service
public class CardService {

    private final Map<Long, Card> cards = new HashMap<>();
    private Long nextId = 1L;

    public CardService() {
        initializeCards();
    }

    /**
     * Initialise une collection de cartes pour l'entraînement
     */
    private void initializeCards() {
        // Créatures
        addCard("Grizzlis", "{1}{G}", 2, CardType.CREATURE, "Ours",
                List.of(ManaColor.GREEN), Rarity.COMMON,
                "Un ours imposant de la forêt.", 2, 2);

        addCard("Chevalier Blanc", "{W}{W}", 2, CardType.CREATURE, "Humain Chevalier",
                List.of(ManaColor.WHITE), Rarity.RARE,
                "Protection contre le noir\nInitiative", 2, 2);

        addCard("Mage Prodige", "{2}{U}", 3, CardType.CREATURE, "Humain Sorcier",
                List.of(ManaColor.BLUE), Rarity.COMMON,
                "Quand le Mage Prodige arrive sur le champ de bataille, piochez une carte.", 2, 1);

        addCard("Dragon Shivan", "{4}{R}{R}", 6, CardType.CREATURE, "Dragon",
                List.of(ManaColor.RED), Rarity.RARE,
                "Vol\n{R}: Le Dragon Shivan gagne +1/+0 jusqu'à la fin du tour.", 5, 5);

        addCard("Seigneur Vampire", "{3}{B}", 4, CardType.CREATURE, "Vampire",
                List.of(ManaColor.BLACK), Rarity.EPIC,
                "Les autres vampires que vous contrôlez gagnent +1/+1.\nLien de vie", 2, 2);

        addCard("Elfe Mystique", "{G}", 1, CardType.CREATURE, "Elfe Druide",
                List.of(ManaColor.GREEN), Rarity.COMMON,
                "{T}: Ajoutez {G}.", 1, 1);

        addCard("Ange de la Miséricorde", "{4}{W}", 5, CardType.CREATURE, "Ange",
                List.of(ManaColor.WHITE), Rarity.EPIC,
                "Vol\nQuand l'Ange de la Miséricorde arrive, vous gagnez 3 points de vie.", 3, 3);

        addCard("Sphinx de Jwar", "{4}{U}{U}", 6, CardType.CREATURE, "Sphinx",
                List.of(ManaColor.BLUE), Rarity.RARE,
                "Vol, linceul", 5, 5);

        // Sorts
        addCard("Éclair", "{R}", 1, CardType.INSTANT, null,
                List.of(ManaColor.RED), Rarity.COMMON,
                "L'Éclair inflige 3 blessures à n'importe quelle cible.", null, null);

        addCard("Contresort", "{U}{U}", 2, CardType.INSTANT, null,
                List.of(ManaColor.BLUE), Rarity.COMMON,
                "Contrecarrez un sort ciblé.", null, null);

        addCard("Courroux Divin", "{3}{W}{W}", 5, CardType.SORCERY, null,
                List.of(ManaColor.WHITE), Rarity.RARE,
                "Détruisez toutes les créatures. Elles ne peuvent pas être régénérées.", null, null);

        addCard("Démembrement", "{B}", 1, CardType.SORCERY, null,
                List.of(ManaColor.BLACK), Rarity.COMMON,
                "Détruisez une créature ciblée. Elle ne peut pas être régénérée.", null, null);

        addCard("Croissance Gigantesque", "{G}", 1, CardType.INSTANT, null,
                List.of(ManaColor.GREEN), Rarity.COMMON,
                "Une créature ciblée gagne +3/+3 jusqu'à la fin du tour.", null, null);

        addCard("Divination", "{2}{U}", 3, CardType.SORCERY, null,
                List.of(ManaColor.BLUE), Rarity.COMMON,
                "Piochez deux cartes.", null, null);

        // Enchantements
        addCard("Pacifisme", "{1}{W}", 2, CardType.ENCHANTMENT, "Aura",
                List.of(ManaColor.WHITE), Rarity.COMMON,
                "Enchanter : créature\nLa créature enchantée ne peut ni attaquer ni bloquer.", null, null);

        addCard("Folie Sanguinaire", "{1}{R}", 2, CardType.ENCHANTMENT, "Aura",
                List.of(ManaColor.RED), Rarity.COMMON,
                "Enchanter : créature\nLa créature enchantée attaque à chaque tour si possible.", null, null);

        // Artefacts
        addCard("Épée de Feu et de Glace", "{3}", 3, CardType.ARTIFACT, "Équipement",
                List.of(ManaColor.COLORLESS), Rarity.LEGENDARY,
                "La créature équipée gagne +2/+2 et a protection contre le rouge et le bleu.\nÉquiper {2}", null, null);

        addCard("Sol's Ring", "{1}", 1, CardType.ARTIFACT, null,
                List.of(ManaColor.COLORLESS), Rarity.EPIC,
                "{T}: Ajoutez {C}{C}.", null, null);

        // Terrains
        addCard("Forêt", "", 0, CardType.LAND, "Terrain de base - Forêt",
                List.of(ManaColor.GREEN), Rarity.COMMON,
                "{T}: Ajoutez {G}.", null, null);

        addCard("Montagne", "", 0, CardType.LAND, "Terrain de base - Montagne",
                List.of(ManaColor.RED), Rarity.COMMON,
                "{T}: Ajoutez {R}.", null, null);

        addCard("Plaine", "", 0, CardType.LAND, "Terrain de base - Plaine",
                List.of(ManaColor.WHITE), Rarity.COMMON,
                "{T}: Ajoutez {W}.", null, null);

        addCard("Île", "", 0, CardType.LAND, "Terrain de base - Île",
                List.of(ManaColor.BLUE), Rarity.COMMON,
                "{T}: Ajoutez {U}.", null, null);

        addCard("Marais", "", 0, CardType.LAND, "Terrain de base - Marais",
                List.of(ManaColor.BLACK), Rarity.COMMON,
                "{T}: Ajoutez {B}.", null, null);
    }

    private void addCard(String name, String manaCost, int cmc, CardType type, String subtype,
                         List<ManaColor> colors, Rarity rarity, String rulesText,
                         Integer power, Integer toughness) {
        Card card = new Card(nextId++, name, manaCost, cmc, type, subtype, rarity, rulesText, power, toughness);
        card.setColors(colors);
        cards.put(card.getId(), card);
    }

    // === Méthodes de récupération ===

    /**
     * Retourne toutes les cartes
     */
    public List<Card> getAllCards() {
        return new ArrayList<>(cards.values());
    }

    /**
     * Retourne une carte par son ID
     */
    public Optional<Card> getCardById(Long id) {
        return Optional.ofNullable(cards.get(id));
    }

    /**
     * Recherche des cartes par nom (contient, insensible à la casse)
     */
    public List<Card> searchByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return getAllCards();
        }
        String searchTerm = name.toLowerCase();
        return cards.values().stream()
                .filter(card -> card.getName().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());
    }

    /**
     * Filtre les cartes par type
     */
    public List<Card> filterByType(CardType type) {
        if (type == null) {
            return getAllCards();
        }
        return cards.values().stream()
                .filter(card -> card.getType() == type)
                .collect(Collectors.toList());
    }

    /**
     * Filtre les cartes par couleur
     */
    public List<Card> filterByColor(ManaColor color) {
        if (color == null) {
            return getAllCards();
        }
        return cards.values().stream()
                .filter(card -> card.getColors().contains(color))
                .collect(Collectors.toList());
    }

    /**
     * Filtre les cartes par rareté
     */
    public List<Card> filterByRarity(Rarity rarity) {
        if (rarity == null) {
            return getAllCards();
        }
        return cards.values().stream()
                .filter(card -> card.getRarity() == rarity)
                .collect(Collectors.toList());
    }

    /**
     * Filtre les cartes par coût de mana converti
     */
    public List<Card> filterByManaCost(Integer minCost, Integer maxCost) {
        return cards.values().stream()
                .filter(card -> {
                    int cmc = card.getConvertedManaCost();
                    boolean matchMin = (minCost == null || cmc >= minCost);
                    boolean matchMax = (maxCost == null || cmc <= maxCost);
                    return matchMin && matchMax;
                })
                .collect(Collectors.toList());
    }

    /**
     * Filtre combiné (type + couleur + rareté + cmc)
     */
    public List<Card> filterCards(CardType type, ManaColor color, Rarity rarity,
                                   Integer minCost, Integer maxCost) {
        return cards.values().stream()
                .filter(card -> type == null || card.getType() == type)
                .filter(card -> color == null || card.getColors().contains(color))
                .filter(card -> rarity == null || card.getRarity() == rarity)
                .filter(card -> {
                    int cmc = card.getConvertedManaCost();
                    boolean matchMin = (minCost == null || cmc >= minCost);
                    boolean matchMax = (maxCost == null || cmc <= maxCost);
                    return matchMin && matchMax;
                })
                .collect(Collectors.toList());
    }

    /**
     * Retourne le nombre total de cartes
     */
    public int getTotalCards() {
        return cards.size();
    }
}
