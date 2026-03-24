package com.bibliotheque.entrainement.model;

/**
 * Couleurs de mana dans Magic: The Gathering
 */
public enum ManaColor {
    WHITE("Blanc", "W", "#F0F2C0"),
    BLUE("Bleu", "U", "#0E68AB"),
    BLACK("Noir", "B", "#150B00"),
    RED("Rouge", "R", "#D3202A"),
    GREEN("Vert", "G", "#00733E"),
    COLORLESS("Incolore", "C", "#BFB5AF");

    private final String displayName;
    private final String symbol;
    private final String hexColor;

    ManaColor(String displayName, String symbol, String hexColor) {
        this.displayName = displayName;
        this.symbol = symbol;
        this.hexColor = hexColor;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getHexColor() {
        return hexColor;
    }
}
