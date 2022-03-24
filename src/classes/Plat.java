package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Plat extends Consommable {
    List<Ingredient> ingredients_recette = new ArrayList<>();
    Map<String,Integer> quantite_ingredients_recette = new TreeMap<String,Ingredient>();


    public Plat(String name) {
        super();

        switch (name) {
            // PIZZA :
            case "pizza" -> {
                    ingredients_recette.add(stock.stockIngredients.get("pate_pizza"));
                    ingredients_recette.add(stock.stockIngredients.get("tomate"));
                    ingredients_recette.add(stock.stockIngredients.get("fromage"));
                this.prix = 12;
            }
            case "pizza_reine" -> {
                ingredients_recette.add();
                ingredients_recette.add();
                ingredients_recette.add();
                ingredients_recette.add();
                this.prix = 12;
            }
            case "pizza_saucisse" -> {
                ingredients_recette.add();
                ingredients_recette.add();
                ingredients_recette.add();
                ingredients_recette.add();
                this.prix = 12;
            }

            // BURGER :
            case "burger_chaimae" -> {
                ingredients_recette.add();
                ingredients_recette.add();
                this.prix = 15;
            }
            case "burger_complet" -> {
                ingredients_recette.add();
                ingredients_recette.add();
                ingredients_recette.add();
                ingredients_recette.add();
                this.prix = 15;
            }
            case "burger_st" -> {
                ingredients_recette.add();
                ingredients_recette.add();
                ingredients_recette.add();
                this.prix = 15;
            }

            // SALADE :
            case "salade" -> {
                ingredients_recette.add();
                this.prix = 9;
            }
            case "salade_tomate" -> {
                ingredients_recette.add();
                ingredients_recette.add();
                this.prix = 9;
            }

            // POTAGE :
            case "potage_oignon" -> {
                ingredients_recette.add(oignon, 3);
                this.prix = 8;
            }
            case "potage_tomate" -> {
                ingredients_recette.add(tomate, 3);
                this.prix = 8;
            }
            case "potage_champignon" -> {
                ingredients_recette.add(champignon, 3);
                this.prix = 8;
            }
        }
    }

    boolean check(Ingredient ingr, int quantity) {
        if (quantity >= ingr.quantite)
            return true;
        else
            return false;
    }
}
