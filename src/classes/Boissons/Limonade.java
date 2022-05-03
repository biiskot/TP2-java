package classes.Boissons;

import classes.Consommable;
import classes.Inventaire;
import com.company.Main;

public class Limonade extends Consommable {
    public Limonade() {
        super("limonade",4);
        Inventaire.removeItem("limonade", 1);
    }
}
