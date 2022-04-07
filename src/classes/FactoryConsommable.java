package classes;

import classes.Boissons.*;
import classes.Plats.*;

public class FactoryConsommable{
   public static Consommable Build(String name){
        switch(name) {
            case "pizza" -> {
                return new Pizza();
            }
            case "pizza_reine" -> {
                return new PizzaReine();
            }
            case "pizza_saucisse" -> {
                return new PizzaSaucisse();
            }

            // BURGER :
            case "burger_viande" -> {
                return new BurgerViande();
            }
            case "burger_complet" -> {
                return new BurgerComplet();
            }
            case "burger_salade" -> {
                return new BurgerSalade();
            }

            // SALADE :
            case "salade" -> {
                return new Salade();
            }
            case "salade_tomate" -> {
                return new SaladeTomate();
            }

            // POTAGE :
            case "potage_oignon" -> {
                return new PotageOignon();
            }
            case "potage_tomate" -> {
                return new PotageTomate();
            }
            case "potage_champignon" -> {
                return new PotageChampi();
            }

            //BOISSONS :
            case "limonade" -> {
                return new Limonade();
            }
            case "eau" -> {
                return new Eau();
            }
            case "jus" -> {
                return new Jus();
            }
            case "biere" -> {
                return new Biere();
            }
            case "cidre" -> {
                return new Cidre();
            }
        }
        return null;
    }

    boolean check() {
      return true;
    }
}


