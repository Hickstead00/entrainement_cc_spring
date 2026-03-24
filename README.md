# 🎴 Gestionnaire de Deck Magic: The Gathering

Projet d'entraînement Spring MVC + Thymeleaf pour votre CC de programmation.

## 📚 Objectif

Apprendre à créer des contrôleurs Spring MVC en construisant un gestionnaire de decks pour Magic: The Gathering.

## 🗂️ Structure du Projet

```
entrainement/
├── src/main/java/com/bibliotheque/entrainement/
│   ├── model/           ✅ Modèles fournis (Card, Deck, etc.)
│   ├── service/         ✅ Services fournis (CardService, DeckService)
│   └── controller/      🎯 À CRÉER PAR VOUS
│
├── src/main/resources/
│   ├── templates/       ✅ Vues Thymeleaf fournies
│   └── static/css/      ✅ Fichier CSS fourni
│
├── EXERCICES.md         📖 Guide d'exercices progressifs
└── pom.xml              ✅ Configuration Maven
```

## 🚀 Démarrage Rapide

### 1️⃣ Lancer l'application

```bash
mvn spring-boot:run
```

Ou depuis votre IDE : clic droit sur `EntrainementApplication.java` → Run

### 2️⃣ Ouvrir le navigateur

```
http://localhost:8080
```

**Note :** Au départ, vous aurez une erreur car aucun contrôleur n'est encore créé. C'est normal !

### 3️⃣ Suivre les exercices

Ouvrez le fichier **`EXERCICES.md`** et suivez les exercices dans l'ordre.

## 📖 Exercices Disponibles

### Niveau 1 : Affichage de Base
- Exercice 1.1 : Page d'accueil
- Exercice 1.2 : Liste de toutes les cartes
- Exercice 1.3 : Détails d'une carte

### Niveau 2 : Filtres et Recherche
- Exercice 2.1 : Recherche de cartes par nom
- Exercice 2.2 : Filtre par type de carte

### Niveau 3 : Gestion de Decks
- Exercice 3.1 : Liste des decks
- Exercice 3.2 : Formulaire de création de deck
- Exercice 3.3 : Traitement du formulaire
- Exercice 3.4 : Détails d'un deck

### Niveau 4 : Construction de Deck (Sessions)
- Exercice 4.1 : Deck Builder - Affichage
- Exercice 4.2 : Ajouter une carte au deck
- Exercice 4.3 : Retirer une carte du deck
- Exercice 4.4 : Valider le deck

### Niveau 5 : Bonus
- Exercice 5.1 : Supprimer un deck
- Exercice 5.2 : Filtres combinés
- Exercice 5.3 : Dupliquer un deck

## 🎯 Ce que Vous Allez Apprendre

- ✅ Créer des contrôleurs avec `@Controller`
- ✅ Routes GET et POST (`@GetMapping`, `@PostMapping`)
- ✅ Passage de données aux vues (`Model`)
- ✅ Paramètres d'URL (`@PathVariable`, `@RequestParam`)
- ✅ Validation de formulaires (`@Valid`, `BindingResult`)
- ✅ Gestion de sessions (`@SessionAttributes`)
- ✅ Redirections
- ✅ Injection de dépendances
- ✅ Thymeleaf (boucles, conditions, formulaires)

## 🛠️ Technologies Utilisées

- **Spring Boot 4.0.4**
- **Spring MVC**
- **Thymeleaf**
- **Java 21**
- **Maven**

## 📁 Fichiers Importants

| Fichier | Description |
|---------|-------------|
| `EXERCICES.md` | Guide d'exercices progressifs |
| `src/main/java/.../model/` | Modèles de données (Card, Deck) |
| `src/main/java/.../service/` | Services métier (CardService, DeckService) |
| `src/main/resources/templates/` | Vues Thymeleaf |
| `src/main/resources/static/css/style.css` | Styles CSS |

## 💡 Conseils

1. **Lisez d'abord l'exercice entier** avant de coder
2. **Référez-vous au poly** pour les annotations et syntaxes
3. **Testez après chaque exercice** : relancez l'app et vérifiez dans le navigateur
4. **Lisez les erreurs** : Spring affiche des messages clairs
5. **Utilisez des `System.out.println()`** pour débugger
6. **Respectez l'ordre** : les exercices sont progressifs

## 🎴 Cartes Disponibles

Le projet contient 25 cartes Magic pré-configurées :
- Créatures (Grizzlis, Chevalier Blanc, Dragon Shivan, etc.)
- Sorts (Éclair, Contresort, Démembrement, etc.)
- Enchantements (Pacifisme, Folie Sanguinaire)
- Artefacts (Épée de Feu et de Glace, Sol's Ring)
- Terrains (Forêt, Montagne, Plaine, Île, Marais)

## 📞 Besoin d'Aide ?

- Consultez le **poly.pdf**
- Relisez les **commentaires dans le code**
- Vérifiez les **vues Thymeleaf** pour voir comment les données sont attendues

## ✅ Validation

Un deck est valide s'il contient **exactement 30 cartes** (avec max 2 copies de chaque).

---

**Bon courage et amusez-vous bien !** 🎴✨
