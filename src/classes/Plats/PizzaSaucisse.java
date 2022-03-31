package classes.Plats;

import classes.Consommable;
import com.company.Main;

public class PizzaSaucisse extends Consommable {
    public PizzaSaucisse(){
        super("pizza_saucisse",12);

        Main.stock.removeItem("pate_pizza",1);
        Main.stock.removeItem("tomate",1);
        Main.stock.removeItem("fromage",1);
        Main.stock.removeItem("saucisse",1);
    }
}
