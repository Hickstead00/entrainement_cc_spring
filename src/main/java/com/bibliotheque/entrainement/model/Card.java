package com.bibliotheque.entrainement.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente une carte Magic: The Gathering
 */
public class Card {
    private Long id;
    private String name;
    private String manaCost; // Format: "{3}{R}{G}" pour 3 incolore + 1 rouge + 1 vert
    private int convertedManaCost; // Coût total converti
    private CardType type;
    private String subtype; // Ex: "Humain Soldat", "Dragon", etc.
    private List<ManaColor> colors; // Couleurs de la carte
    private Rarity rarity;
    private String rulesText; // Texte de règles de la carte
    private String flavorText; // Texte d'ambiance

    // Pour les créatures uniquement
    private Integer power; // Force (null si ce n'est pas une créature)
    private Integer toughness; // Endurance (null si ce n'est pas une créature)

    private String expansion; // Extension (ex: "Innistrad", "Modern Horizons 2")
    private String artist; // Artiste

    // Constructeur par défaut
    public Card() {
        this.colors = new ArrayList<>();
    }

    // Constructeur simplifié
    public Card(Long id, String name, String manaCost, int convertedManaCost,
                CardType type, String subtype, Rarity rarity, String rulesText) {
        this.id = id;
        this.name = name;
        this.manaCost = manaCost;
        this.convertedManaCost = convertedManaCost;
        this.type = type;
        this.subtype = subtype;
        this.rarity = rarity;
        this.rulesText = rulesText;
        this.colors = new ArrayList<>();
    }

    // Constructeur pour créatures
    public Card(Long id, String name, String manaCost, int convertedManaCost,
                CardType type, String subtype, Rarity rarity, String rulesText,
                Integer power, Integer toughness) {
        this(id, name, manaCost, convertedManaCost, type, subtype, rarity, rulesText);
        this.power = power;
        this.toughness = toughness;
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

    public String getManaCost() {
        return manaCost;
    }

    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    public int getConvertedManaCost() {
        return convertedManaCost;
    }

    public void setConvertedManaCost(int convertedManaCost) {
        this.convertedManaCost = convertedManaCost;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public List<ManaColor> getColors() {
        return colors;
    }

    public void setColors(List<ManaColor> colors) {
        this.colors = colors;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public String getRulesText() {
        return rulesText;
    }

    public void setRulesText(String rulesText) {
        this.rulesText = rulesText;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getToughness() {
        return toughness;
    }

    public void setToughness(Integer toughness) {
        this.toughness = toughness;
    }

    public String getExpansion() {
        return expansion;
    }

    public void setExpansion(String expansion) {
        this.expansion = expansion;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    // Méthodes utilitaires

    /**
     * Vérifie si la carte est une créature
     */
    public boolean isCreature() {
        return type == CardType.CREATURE;
    }

    /**
     * Vérifie si la carte est multicolore
     */
    public boolean isMulticolor() {
        return colors.size() > 1;
    }

    /**
     * Retourne les stats de la créature au format "P/T"
     */
    public String getStats() {
        if (isCreature() && power != null && toughness != null) {
            return power + "/" + toughness;
        }
        return "";
    }

    /**
     * Retourne le type complet (type + sous-type)
     */
    public String getFullType() {
        if (subtype != null && !subtype.isEmpty()) {
            return type.getDisplayName() + " - " + subtype;
        }
        return type.getDisplayName();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" ").append(manaCost);
        if (isCreature()) {
            sb.append(" [").append(getStats()).append("]");
        }
        return sb.toString();
    }
}
