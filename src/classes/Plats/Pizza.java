package classes.Plats;

import classes.Consommable;
import com.company.Main;

public class Pizza extends Consommable {
    public Pizza(){
        super("pizza",12);

        Main.stock.removeItem("pate_pizza",1);
        Main.stock.removeItem("tomate",1);
        Main.stock.removeItem("fromage",1);
    }

}
