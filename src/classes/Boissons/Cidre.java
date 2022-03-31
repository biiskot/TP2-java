package classes.Boissons;

import classes.Consommable;
import com.company.Main;

public class Cidre extends Consommable {
    public Cidre() {
        super("cidre",5);
        Main.stock.removeItem("cidre", 1);
    }
}
