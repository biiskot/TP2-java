package classes.Boissons;

import classes.Consommable;
import classes.Inventaire;
import com.company.Main;

public class Biere extends Consommable {
    public Biere() {
        super("biere", 5);
        Inventaire.removeItem("biere", 1);
    }
}
