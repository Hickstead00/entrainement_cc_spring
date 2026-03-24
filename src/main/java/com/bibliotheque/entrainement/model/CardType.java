package com.bibliotheque.entrainement.model;

/**
 * Types de cartes Magic: The Gathering
 */
public enum CardType {
    CREATURE("Créature"),
    INSTANT("Éphémère"),
    SORCERY("Rituel"),
    ENCHANTMENT("Enchantement"),
    ARTIFACT("Artefact"),
    PLANESWALKER("Planeswalker"),
    LAND("Terrain");

    private final String displayName;

    CardType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
