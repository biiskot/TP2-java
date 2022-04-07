package classes.Boissons;

import classes.Consommable;
import com.company.Main;

public class Biere extends Consommable {
    public Biere() {
        super("biere", 5);
        Main.stock.removeItem("biere", 1);
    }
}
