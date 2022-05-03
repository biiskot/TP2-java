package classes.Boissons;

import classes.Consommable;
import classes.Inventaire;
import com.company.Main;

public class Jus extends Consommable {
    public Jus(){
        super("jus",1);
        Inventaire.removeItem("jus", 1);
    }
}
