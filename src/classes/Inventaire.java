package classes;

import java.util.*;
import java.util.Map;

public class Inventaire {

    //Map qui associe un ingrédient à sa quantité:
    public Map<String,Integer> stockIngredients = new TreeMap<String,Integer>();

    public Inventaire(){
        int baseQtte = 10;
        stockIngredients.put("tomate", baseQtte*2);
        stockIngredients.put("salade",baseQtte*2);
        stockIngredients.put("oignon",baseQtte*2);
        stockIngredients.put("champignon",baseQtte*2);
        stockIngredients.put("pain_burger",baseQtte);
        stockIngredients.put("steak",baseQtte);
        stockIngredients.put("pate_pizza",baseQtte);
        stockIngredients.put("fromage",baseQtte*2);
        stockIngredients.put("saucisse",baseQtte);
        stockIngredients.put("biere",baseQtte*2);
        stockIngredients.put("cidre",baseQtte*2);
        stockIngredients.put("eau",baseQtte*2);
        stockIngredients.put("jus",baseQtte*2);
        stockIngredients.put("limonade",baseQtte*2);
    }

    public void addItem(String name,int qty) {
        //On vérifie si l'ingrédient n'existe pas déjà dans le stock :
        if(!stockIngredients.containsKey(name)) {
            stockIngredients.put(name,qty);
        }
        //Sinon on ajoute simplement le nombre d'item
        else {
            int newQty =  stockIngredients.get(name)+qty;

            if(newQty < 0) {
                newQty = 0;
                //Empêche d'avoir une quantité négative
            }
            else{
                //On remplace par la bonne quantité :
                stockIngredients.replace(name,newQty);
            }
        }
    }
    public void removeItem(String name,int qty){

        //Check si on a assez de l'ingrédient pour le plat et si l'ingredient existe
        if(stockIngredients.get(name) >= qty && stockIngredients.containsKey(name)) {
            int newQty = stockIngredients.get(name) - qty;

            if (newQty >= 0) {
                //On remplace par la bonne quantité :
                stockIngredients.replace(name, newQty);

            } else {
                newQty = 0; // On met à 0 si quantité négative
                stockIngredients.replace(name, newQty);
                //Ne devrait pas être lu puisque la quantité est check au-dessus
            }
        }
        else{
            System.out.println("Quantité de " + name + " insuffisante pour préparer le plat, ou "+name+" n'est pas un ingrédient");
        }
    }
    public void afficherStock(){
        System.out.println("Stock actuel :");
        for(Map.Entry<String, Integer> n : stockIngredients.entrySet()){
            System.out.println(n.getKey() + ":" + n.getValue());
        }
    }
}
