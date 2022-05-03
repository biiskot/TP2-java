package classes.Plats;

import classes.Consommable;
import classes.Inventaire;
import com.company.Main;

public class PotageTomate extends Consommable {
    public PotageTomate(){
        super("potage_tomate",8);

        Inventaire.removeItem("tomate",3);
    }
}

