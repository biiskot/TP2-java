package classes.Plats;

import classes.Consommable;
import classes.Inventaire;
import com.company.Main;

public class Salade extends Consommable {
    public Salade(){
        super("salade",12);

        Inventaire.removeItem("salade",1);
    }
}
