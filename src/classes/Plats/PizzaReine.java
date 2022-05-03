package classes.Plats;

import classes.Consommable;
import classes.Inventaire;
import com.company.Main;

public class PizzaReine extends Consommable {
    public PizzaReine(){
        super("pizza_reine",12);

        Inventaire.removeItem("pate_pizza",1);
        Inventaire.removeItem("tomate",1);
        Inventaire.removeItem("fromage",1);
        Inventaire.removeItem("champignon",1);
    }
}
