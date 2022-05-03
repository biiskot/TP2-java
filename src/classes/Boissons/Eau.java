package classes.Boissons;

import classes.Consommable;
import classes.Inventaire;
import com.company.Main;

public class Eau extends Consommable {
    public Eau(){
        super("eau",0);
        Inventaire.removeItem("eau", 1);
    }
}
