package classes.Boissons;

import classes.Consommable;
import com.company.Main;

public class Limonade extends Consommable {
    public Limonade() {
        super("limonade",4);
        Main.stock.removeItem("limonade", 1);
    }
}
