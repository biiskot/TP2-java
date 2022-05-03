package classes.Boissons;

import classes.Consommable;
import classes.Inventaire;
import com.company.Main;

public class Cidre extends Consommable {
    public Cidre() {
        super("cidre",5);
        Inventaire.removeItem("cidre", 1);
    }
}
