package classes.Plats;

import classes.Consommable;
import com.company.Main;

public class PotageTomate extends Consommable {
    public PotageTomate(){
        super("potage_tomate",8);

        Main.stock.removeItem("tomate",3);
    }
}

