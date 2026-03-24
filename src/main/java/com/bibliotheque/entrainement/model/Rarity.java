package com.bibliotheque.entrainement.model;

/**
 * Rareté des cartes
 */
public enum Rarity {
    COMMON("Commune"),
    RARE("Rare"),
    EPIC("Épique"),
    LEGENDARY("Légendaire");

    private final String displayName;

    Rarity(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
