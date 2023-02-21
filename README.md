<center>

# Programmation Fonctionnelle

## Mini Projet
</center>

## Description

Ce projet est un mini-projet de programmation fonctionnelle. Il met en application les 
notions de programmation fonctionnelle de java, telles que les fonctions lambda, les
interfaces fonctionnelles, les streams, les collections, etc.

## Objectif

L'objectif de ce projet est de créer une application Java qui permet de manipuler un jeu de données 
de type XML contenant des recettes de cuisine en s'appuyant sur un raisonnement fonctionnel
à travers l'API Stream de Java.

## Entités

### Recipe

La classe Recette représente une recette de cuisine. Elle contient les attributs suivants:

* **id** : identifiant de la recette
* **title** : titre de la recette
* **comment** : commentaire de la recette
* **date** : date de création de la recette
* **nutrition** : informations nutritionnelles de la recette
* **ingredients** : liste des ingrédients de la recette
* **steps** : liste des étapes de la recette

### Ingredient

La classe Ingrédient est une classe abstraite qui représente un ingrédient de cuisine. Elle contient les attributs suivants:

* **name** : nom de l'ingrédient

#### SimpleIngredient

La classe SimpleIngredient est une classe qui hérite de la classe Ingredient. Elle représente un ingrédient simple de cuisine. Elle contient les attributs suivants:

* **name** : nom de l'ingrédient
* **amount** : quantité de l'ingrédient
* **unit** : unité de l'ingrédient

#### CompositeIngredient

La classe CompositeIngredient est une classe qui hérite de la classe Ingredient. Elle représente un ingrédient composé de cuisine. Elle contient les attributs suivants:

* **name** : nom de l'ingrédient
* **ingredients** : liste des ingrédients composant l'ingrédient
* **steps** : liste des étapes de la recette

### Step

La classe Step représente une étape de la recette. Elle contient les attributs suivants:

* **description** : description de l'étape

### Nutrition

La classe Nutrition représente les informations nutritionnelles d'une recette. Elle contient les attributs suivants:

* **calories** : nombre de calories de la recette
* **fats** : nombre de graisses de la recette
* **carbohydrates** : nombre de glucides de la recette
* **proteins** : nombre de protéines de la recette

### UnitsKinds

La classe énumérée UnitsKinds représente les unités de mesure des ingrédients. Elle contient les valeurs suivantes:

* **POUND** : livre
* **OUNCE** : once
* **CUP** : tasse
* **TEASPOON** : cuillère à café
* **JAR** : pot

## Fonctionnalités

### Parser

Le parser est une classe qui permet de parser un fichier XML contenant des recettes de cuisine. Il permet de créer une liste de recettes à partir d'un fichier XML.

### Builder

Certaines entités ont une classe statique qui permet de faciliter la création de ces entités.

### RecipeRepository

La classe RecipeRepository est une classe qui permet de manipuler une liste de recettes. Elle contient les méthodes à implémenter pour répondre aux questions du mini-projet.

## Auteurs

* **Hokanosekai** - *Initial work* - [Hokanosekai](https://github.com/Hokanosekai)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE) file for details