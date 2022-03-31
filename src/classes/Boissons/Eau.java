package classes.Boissons;

import classes.Consommable;
import com.company.Main;

public class Eau extends Consommable {
    public Eau(){
        super("eau",0);
        Main.stock.removeItem("eau", 1);
    }
}
