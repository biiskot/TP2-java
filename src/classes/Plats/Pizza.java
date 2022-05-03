package classes.Plats;

import classes.Consommable;
import classes.Inventaire;
import com.company.Main;

public class Pizza extends Consommable {
    public Pizza(){
        super("pizza",12);

        Inventaire.removeItem("pate_pizza",1);
        Inventaire.removeItem("tomate",1);
        Inventaire.removeItem("fromage",1);
    }

}
