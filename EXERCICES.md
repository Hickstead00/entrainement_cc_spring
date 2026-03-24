# Gestionnaire de Deck Magic: The Gathering - Exercices Progressifs

Bienvenue dans votre entraînement Spring MVC ! Ce projet vous permettra de créer un gestionnaire de decks pour Magic: The Gathering.

## 📋 Structure du Projet

**Ce qui est déjà fait (ne pas toucher) :**
- ✅ Modèles : `Card`, `Deck`, `CardType`, `ManaColor`, `Rarity`
- ✅ Services : `CardService`, `DeckService`
- ✅ Vues Thymeleaf de base (dans `src/main/resources/templates/`)

**Ce que VOUS allez coder :**
- 🎯 Les contrôleurs (`CardController`, `DeckController`, `DeckBuilderController`)
- 🎯 Compléter certaines vues Thymeleaf

---

## 🎓 Niveau 1 : Affichage de Base (GET simple)

### Exercice 1.1 : Page d'Accueil
**Objectif** : Créer une page d'accueil simple

**Fichier à créer** : `src/main/java/com/bibliotheque/entrainement/controller/HomeController.java`

**Ce que vous devez faire :**
1. Créer un contrôleur avec `@Controller`
2. Créer une méthode qui répond à la route `/`
3. Passer au modèle :
   - Un message de bienvenue : `"Bienvenue dans le Gestionnaire de Decks Magic !"`
   - Le nombre total de cartes disponibles (utiliser `CardService`)
4. Retourner la vue `home`

**Concepts utilisés :** `@Controller`, `@GetMapping`, `Model`, injection de dépendances

**Vue fournie :** `home.html` (déjà créée)

---

### Exercice 1.2 : Liste de Toutes les Cartes
**Objectif** : Afficher toutes les cartes Magic disponibles

**Fichier à créer** : `CardController.java`

**Ce que vous devez faire :**
1. Créer un contrôleur `CardController`
2. Injecter `CardService` via le constructeur
3. Créer une méthode pour la route `/cards`
4. Récupérer toutes les cartes avec `cardService.getAllCards()`
5. Passer la liste au modèle avec l'attribut `"cards"`
6. Retourner la vue `cards/list`

**Concepts utilisés :** Injection de dépendances, `Model.addAttribute()`

**Vue fournie :** `cards/list.html`

---

### Exercice 1.3 : Détails d'une Carte
**Objectif** : Afficher les détails d'une carte spécifique

**Fichier** : `CardController.java` (ajouter une méthode)

**Ce que vous devez faire :**
1. Créer une méthode pour la route `/cards/{id}`
2. Utiliser `@PathVariable` pour récupérer l'ID
3. Récupérer la carte avec `cardService.getCardById(id)`
4. Si la carte n'existe pas, rediriger vers `/cards`
5. Sinon, passer la carte au modèle et retourner la vue `cards/detail`

**Concepts utilisés :** `@PathVariable`, `Optional<T>`, redirections

**Vue fournie :** `cards/detail.html`

---

## 🎓 Niveau 2 : Filtres et Recherche (GET avec paramètres)

### Exercice 2.1 : Recherche de Cartes par Nom
**Objectif** : Ajouter une barre de recherche

**Fichier** : `CardController.java` (modifier la méthode `/cards`)

**Ce que vous devez faire :**
1. Ajouter un paramètre optionnel `search` avec `@RequestParam(required = false)`
2. Si `search` est présent, utiliser `cardService.searchByName(search)`
3. Sinon, utiliser `cardService.getAllCards()`
4. Passer le terme de recherche au modèle pour l'afficher dans le formulaire

**Concepts utilisés :** `@RequestParam`, paramètres optionnels

**Exemple d'URL :** `/cards?search=dragon`

---

### Exercice 2.2 : Filtre par Type de Carte
**Objectif** : Filtrer les cartes par type (Créature, Sort, etc.)

**Fichier** : `CardController.java`

**Ce que vous devez faire :**
1. Créer une route `/cards/filter`
2. Accepter un paramètre `type` (peut être `null`)
3. Utiliser `cardService.filterByType(type)`
4. Passer au modèle :
   - La liste de cartes filtrées
   - Tous les types disponibles : `CardType.values()`
   - Le type sélectionné
5. Retourner la vue `cards/filter`

**Concepts utilisés :** Enums, filtrage

**Vue fournie :** `cards/filter.html`

---

## 🎓 Niveau 3 : Gestion de Decks (POST et Sessions)

### Exercice 3.1 : Liste des Decks
**Objectif** : Afficher tous les decks créés

**Fichier à créer** : `DeckController.java`

**Ce que vous devez faire :**
1. Créer un contrôleur `DeckController` avec `@RequestMapping("/decks")`
2. Injecter `DeckService`
3. Créer une méthode GET pour `/decks` (route de base)
4. Récupérer tous les decks avec `deckService.getAllDecks()`
5. Passer la liste au modèle
6. Retourner la vue `decks/list`

**Vue fournie :** `decks/list.html`

---

### Exercice 3.2 : Formulaire de Création de Deck
**Objectif** : Afficher un formulaire pour créer un nouveau deck

**Fichier** : `DeckController.java`

**Ce que vous devez faire :**
1. Créer une méthode GET pour `/decks/new`
2. Créer un objet `Deck` vide
3. Le passer au modèle avec `model.addAttribute("deck", new Deck())`
4. Retourner la vue `decks/form`

**Concepts utilisés :** Préparation de formulaire

**Vue fournie :** `decks/form.html`

---

### Exercice 3.3 : Traitement du Formulaire de Création
**Objectif** : Enregistrer le nouveau deck

**Fichier** : `DeckController.java`

**Ce que vous devez faire :**
1. Créer une méthode POST pour `/decks/new`
2. Utiliser `@Valid @ModelAttribute("deck") Deck deck, BindingResult result`
3. Si `result.hasErrors()`, retourner `"decks/form"`
4. Sinon, sauvegarder avec `deckService.saveDeck(deck)`
5. Rediriger vers `/decks` avec `return "redirect:/decks"`

**Concepts utilisés :** `@PostMapping`, `@Valid`, `@ModelAttribute`, `BindingResult`, validation

---

### Exercice 3.4 : Détails d'un Deck
**Objectif** : Afficher le contenu d'un deck

**Fichier** : `DeckController.java`

**Ce que vous devez faire :**
1. Créer une méthode GET pour `/decks/{id}`
2. Récupérer le deck avec `deckService.getDeckById(id)`
3. Si le deck n'existe pas, rediriger vers `/decks`
4. Passer au modèle :
   - Le deck
   - Les statistiques avec `deckService.getDeckStats(id, cardService)`
5. Retourner la vue `decks/detail`

**Note :** Vous aurez besoin d'injecter `CardService` dans `DeckController`

**Vue fournie :** `decks/detail.html`

---

## 🎓 Niveau 4 : Construction de Deck (Sessions avancées)

### Exercice 4.1 : Deck Builder - Affichage
**Objectif** : Interface pour ajouter des cartes à un deck

**Fichier à créer** : `DeckBuilderController.java`

**Ce que vous devez faire :**
1. Créer un contrôleur avec `@Controller` et `@RequestMapping("/deck-builder")`
2. Utiliser `@SessionAttributes("currentDeck")` pour gérer le deck en session
3. Injecter `CardService` et `DeckService`
4. Créer une méthode GET pour `/deck-builder/{deckId}`
5. Récupérer le deck et le stocker en session avec le nom `"currentDeck"`
6. Récupérer toutes les cartes disponibles
7. Passer au modèle :
   - Le deck en cours de construction
   - La liste des cartes disponibles
8. Retourner la vue `deck-builder/builder`

**Concepts utilisés :** `@SessionAttributes`, gestion de session

**Vue fournie :** `deck-builder/builder.html`

---

### Exercice 4.2 : Ajouter une Carte au Deck
**Objectif** : Ajouter une carte depuis le deck builder

**Fichier** : `DeckBuilderController.java`

**Ce que vous devez faire :**
1. Créer une méthode POST pour `/deck-builder/add`
2. Accepter deux paramètres :
   - `@RequestParam Long deckId`
   - `@RequestParam Long cardId`
3. Récupérer le deck en session avec `@ModelAttribute("currentDeck") Deck deck`
4. Ajouter la carte avec `deck.addCard(cardId)`
5. Sauvegarder avec `deckService.saveDeck(deck)`
6. Rediriger vers `/deck-builder/{deckId}`

**Concepts utilisés :** Modification d'objet en session, `@RequestParam` multiple

---

### Exercice 4.3 : Retirer une Carte du Deck
**Objectif** : Retirer une carte depuis le deck builder

**Fichier** : `DeckBuilderController.java`

**Ce que vous devez faire :**
1. Créer une méthode POST pour `/deck-builder/remove`
2. Accepter `deckId` et `cardId`
3. Récupérer le deck en session
4. Retirer la carte avec `deck.removeCard(cardId)`
5. Sauvegarder
6. Rediriger vers `/deck-builder/{deckId}`

---

### Exercice 4.4 : Valider le Deck
**Objectif** : Vérifier si le deck est valide (30 cartes exactement)

**Fichier** : `DeckBuilderController.java`

**Ce que vous devez faire :**
1. Créer une méthode GET pour `/deck-builder/{deckId}/validate`
2. Récupérer le deck
3. Vérifier avec `deck.isValid()`
4. Passer le résultat au modèle avec un message approprié :
   - Si valide : "Deck valide ! Prêt à jouer."
   - Sinon : "Deck invalide : [X]/30 cartes"
5. Retourner la vue `deck-builder/validation`

**Vue fournie :** `deck-builder/validation.html`

---

## 🎓 Niveau 5 : Fonctionnalités Avancées (Bonus)

### Exercice 5.1 : Supprimer un Deck
**Objectif** : Ajouter la suppression de deck

**Fichier** : `DeckController.java`

**Ce que vous devez faire :**
1. Créer une méthode POST pour `/decks/{id}/delete`
2. Supprimer avec `deckService.deleteDeck(id)`
3. Rediriger vers `/decks`

**Astuce :** Dans Thymeleaf, utiliser un formulaire avec `method="post"`

---

### Exercice 5.2 : Filtres Combinés de Cartes
**Objectif** : Filtrer par type ET couleur ET rareté simultanément

**Fichier** : `CardController.java`

**Ce que vous devez faire :**
1. Créer une route `/cards/advanced-filter`
2. Accepter plusieurs paramètres optionnels : `type`, `color`, `rarity`, `minCost`, `maxCost`
3. Utiliser `cardService.filterCards(...)`
4. Passer tous les filtres disponibles au modèle
5. Retourner une vue avec formulaire de filtres multiples

---

### Exercice 5.3 : Dupliquer un Deck
**Objectif** : Créer une copie d'un deck existant

**Fichier** : `DeckController.java`

**Ce que vous devez faire :**
1. Créer une méthode POST pour `/decks/{id}/duplicate`
2. Récupérer le deck source
3. Créer un nouveau deck avec le même nom + " (Copie)"
4. Copier toutes les cartes
5. Sauvegarder le nouveau deck
6. Rediriger vers `/decks`

---

## 📝 Checklist de Progression

Cochez au fur et à mesure :

**Niveau 1 - Affichage de base**
- [ ] Exercice 1.1 : Page d'accueil
- [ ] Exercice 1.2 : Liste des cartes
- [ ] Exercice 1.3 : Détails d'une carte

**Niveau 2 - Filtres et recherche**
- [ ] Exercice 2.1 : Recherche par nom
- [ ] Exercice 2.2 : Filtre par type

**Niveau 3 - Gestion de decks**
- [ ] Exercice 3.1 : Liste des decks
- [ ] Exercice 3.2 : Formulaire de création
- [ ] Exercice 3.3 : Traitement du formulaire
- [ ] Exercice 3.4 : Détails d'un deck

**Niveau 4 - Construction de deck**
- [ ] Exercice 4.1 : Deck builder - Affichage
- [ ] Exercice 4.2 : Ajouter une carte
- [ ] Exercice 4.3 : Retirer une carte
- [ ] Exercice 4.4 : Valider le deck

**Niveau 5 - Bonus**
- [ ] Exercice 5.1 : Supprimer un deck
- [ ] Exercice 5.2 : Filtres combinés
- [ ] Exercice 5.3 : Dupliquer un deck

---

## 🚀 Conseils

1. **Testez après chaque exercice** : Lancez l'application avec `mvn spring-boot:run` et vérifiez dans le navigateur
2. **Lisez les erreurs** : Spring affiche des messages d'erreur clairs, lisez-les attentivement
3. **Utilisez le poly** : Référez-vous au poly pour les annotations et syntaxes
4. **Debuggez avec des prints** : Ajoutez `System.out.println()` pour voir ce qui se passe
5. **Respectez l'ordre** : Les exercices sont progressifs, ne sautez pas d'étapes

---

## 🎯 Objectifs Pédagogiques

À la fin de ces exercices, vous maîtriserez :
- ✅ Les contrôleurs Spring MVC (`@Controller`, `@GetMapping`, `@PostMapping`)
- ✅ Le passage de données aux vues (`Model`)
- ✅ Les paramètres d'URL (`@PathVariable`, `@RequestParam`)
- ✅ La validation de formulaires (`@Valid`, `BindingResult`)
- ✅ La gestion de sessions (`@SessionAttributes`)
- ✅ Les redirections
- ✅ L'injection de dépendances
- ✅ Thymeleaf (affichage, boucles, conditions, formulaires)

Bon courage et amusez-vous bien ! 🎴✨
