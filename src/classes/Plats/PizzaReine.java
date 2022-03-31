package classes.Plats;

import classes.Consommable;
import com.company.Main;

public class PizzaReine extends Consommable {
    public PizzaReine(){
        super("pizza_reine",12);

        Main.stock.removeItem("pate_pizza",1);
        Main.stock.removeItem("tomate",1);
        Main.stock.removeItem("fromage",1);
        Main.stock.removeItem("champignon",1);
    }
}
