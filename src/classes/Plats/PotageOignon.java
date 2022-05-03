package classes.Plats;

import classes.Consommable;
import classes.Inventaire;
import com.company.Main;

public class PotageOignon extends Consommable {
    public PotageOignon(){
        super("potage_oignon",8);

        Inventaire.removeItem("oignon",3);
    }
}
