package classes.Boissons;

import classes.Consommable;
import com.company.Main;

public class Jus extends Consommable {
    public Jus(){
        super("jus",1);
        Main.stock.removeItem("biere", 1);
    }
}
