package classes.Plats;

import classes.Consommable;
import classes.Inventaire;
import com.company.Main;

public class PizzaSaucisse extends Consommable {
    public PizzaSaucisse(){
        super("pizza_saucisse",12);

        Inventaire.removeItem("pate_pizza",1);
        Inventaire.removeItem("tomate",1);
        Inventaire.removeItem("fromage",1);
        Inventaire.removeItem("saucisse",1);
    }
}
