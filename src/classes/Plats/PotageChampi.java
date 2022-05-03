package classes.Plats;

import classes.Consommable;
import classes.Inventaire;
import com.company.Main;

public class PotageChampi extends Consommable {
    public PotageChampi(){
        super("potage_champi",8);

        Inventaire.removeItem("champignon",3);
    }
}
