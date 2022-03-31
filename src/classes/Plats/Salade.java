package classes.Plats;

import classes.Consommable;
import com.company.Main;

public class Salade extends Consommable {
    public Salade(){
        super("salade",12);

        Main.stock.removeItem("salade",1);
    }
}
