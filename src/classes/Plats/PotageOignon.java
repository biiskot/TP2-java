package classes.Plats;

import classes.Consommable;
import com.company.Main;

public class PotageOignon extends Consommable {
    public PotageOignon(){
        super("potage_oignon",8);

        Main.stock.removeItem("oignon",3);
    }
}
