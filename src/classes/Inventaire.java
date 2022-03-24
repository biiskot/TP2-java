package classes;

import java.util.*;
import java.util.Map;

public class Inventaire {
    Map<String,Ingredient> stockIngredients = new TreeMap<String,Ingredient>();

    public Inventaire(){
        stockIngredients.put("tomate",new Ingredient("tomate", 10));
        stockIngredients.put("salade",new Ingredient("salade", 10));
        stockIngredients.put("oignon",new Ingredient("oignon", 10));
        stockIngredients.put("champignon",new Ingredient("champignon", 10));
        stockIngredients.put("pain_burger",new Ingredient("pain_burger", 10));
        stockIngredients.put("steak",new Ingredient("steak", 10));
        stockIngredients.put("pate_pizza",new Ingredient("pate_pizza", 10));
        stockIngredients.put("fromage",new Ingredient("fromage", 10));
        stockIngredients.put("saucisse",new Ingredient("saucisse", 10));
    }

    void addItem(String name,int qtty) {
        //On vérifie si l'ingrédient n'existe pas déjà dans le stock :
        if(!stockIngredients.containsKey(name)) {
            stockIngredients.put(name, new Ingredient(name, qtty));
        }
        else {
          stockIngredients.get(name).quantite += qtty;
        }
    }
    void removeItem(String name){
        stockIngredients.get(name).quantite = 0;
    }
}
