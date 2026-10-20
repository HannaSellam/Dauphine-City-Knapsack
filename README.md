# Dauphine City : choix de projets sous contraintes de budget

Projet Java-Objet, 2025-2026.

## Sommaire

- [A. Idée générale](#a-idée-générale)
- [B. Structure du code](#b-structure-du-code)
- [C. Lancer le programme](#c-lancer-le-programme)
- [D. Utilisation du menu console](#d-utilisation-du-menu-console)
- [E. Exemple d'utilisation](#e-exemple-dutilisation)
- [F. Tests unitaires](#f-tests-unitaires)


---

## A. Idée générale

On se place dans la mairie de Dauphine City. Une équipe municipale propose des projets dans plusieurs secteurs (sport, santé, éducation, culture, attractivité économique). Ces projets sont évalués selon plusieurs critères : le bénéfice pour la ville et les coûts (économique, social, environnemental).

On transforme ensuite ces projets en une instance du **sac à dos multidimensionnel** : on veut sélectionner l'ensemble de projets qui maximise l'utilité (le bénéfice) tout en respectant plusieurs contraintes de budget.

Le programme permet de tester plusieurs méthodes de résolution :

- **Méthodes gloutonnes** (à ajout ou à retrait, avec différents critères d'ordre)
- **Hill Climbing** (et quelques variantes)

## B. Structure du code

Le projet est organisé en plusieurs packages :

| Package | Rôle |
|---|---|
| `equipe` | Simulation de l'équipe municipale |
| `sacADos` | Représentation du sac à dos multidimensionnel et génération d'instances via `VersSacADos` |
| `solveur.glouton` | Solveurs gloutons à ajout et à retrait, et comparateurs d'ordre |
| `solveur.hillclimbing` | Solveur Hill Climbing et ses variantes |
| `main` | Classe `Main` avec le menu console (point d'entrée du programme) |

## C. Lancer le programme

Prérequis : un JDK Java installé.

### Avec un IDE (Eclipse)

1. Ouvrir le projet dans Eclipse.
2. Ouvrir `src/main/Main.java`.
3. Cliquer sur **Run**.

### Sans IDE (terminal)

Se placer dans le dossier `src` du projet, puis compiler et exécuter :

```bash
cd chemin/ProjetJava/src

# Compilation
javac main/Main.java

# Exécution
java main.Main
```

## D. Utilisation du menu console

Au lancement, le programme propose trois choix.

## E. Exemple d'utilisation

Un fichier `exemple.txt` est fourni à la racine du dépôt.
Il contient 5 objets, 3 contraintes et les budgets suivants : 9, 7 et 7.

### Lancement depuis le terminal

Depuis le dossier principal du projet :

```bash
cd src
javac -encoding UTF-8 main/Main.java
java main.Main
```

### Choix dans les menus

Saisir successivement, en validant chaque ligne avec Entrée :

1. `2` : générer un sac à dos à partir d'un fichier.
2. `../exemple.txt` : charger le fichier placé à la racine du projet.
3. `1` : utiliser la méthode gloutonne à ajout.
4. `1` : sélectionner l'ordre Somme.

### Résultat attendu

Lors d'un nouveau lancement du programme, l'extrait final attendu est :

```text
¤¤ Solution contient les objets :
~ Objet numero 1
Utilite : 60

~ Objet numero 3
Utilite : 40

~ Objet numero 2
Utilite : 50

¤¤ Utilité de la solution 150
```

Les objets 1, 3 et 2 sont sélectionnés.
Leurs coûts cumulés sont de 9, 7 et 7 : les trois budgets sont respectés.

Pour cette petite instance, l'utilité obtenue est égale à l'optimum : 150.
Cela ne signifie pas que la méthode gloutonne trouve toujours l'optimum.

Saisir ensuite `3` dans le menu principal pour quitter.

## F. Tests unitaires

Les tests utilisent l'API JUnit Jupiter et se trouvent dans
`src/testJUnit/TestProjet.java`.

### 1. Simulation de l'équipe municipale

1. Les experts proposent des projets.
2. Chaque évaluateur attribue ses coûts (économique, social, environnemental).
3. L'élu attribue le bénéfice.
4. On obtient une liste de projets.

Ensuite, l'utilisateur choisit comment créer le sac à dos (2 modes) :

- **Budgets par types de coûts** : budget économique, social, environnemental.
- **Budgets par secteurs** : sport, santé, éducation, culture, attractivité économique.

Puis il choisit une méthode de résolution :

- **Gloutonne à ajout** (ordre Somme ou ordre Max)
- **Gloutonne à retrait**
- **Hill Climbing** (choix de la variante)

Le programme affiche enfin la liste des projets sélectionnés et leur utilité totale.

### 2. Génération d'une instance depuis un fichier

Au lieu de générer les projets aléatoirement, on construit le sac à dos directement à partir d'un fichier (utile pour tester sur des cas plus difficiles).

- Si le fichier est dans le dossier `src`, il suffit de saisir son nom.
- Sinon, saisir son chemin absolu.

Format du fichier :

1. `n k opt` : nombre d'objets, nombre de budgets, valeur optimale (ou `0` si inconnue).
2. Les utilités `u_i` des `n` objets.
3. La matrice de contraintes, de taille `k × n`.
4. Les budgets `B_l`, pour `l = 1…k`.

Après lecture du fichier, on retrouve le même menu de résolution (méthodes gloutonnes ou Hill Climbing).

### 3. Quitter

Arrêt du programme.
