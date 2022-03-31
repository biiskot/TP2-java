package classes.Plats;

import classes.Consommable;
import com.company.Main;

public class SaladeTomate extends Consommable {
    public SaladeTomate(){
        super("salade_tomate",12);

        Main.stock.removeItem("salade",1);
        Main.stock.removeItem("tomate",1);
    }
}
