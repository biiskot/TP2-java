package classes.Plats;

import classes.Consommable;
import com.company.Main;

public class PotageChampi extends Consommable {
    public PotageChampi(){
        super("potage_champi",8);

        Main.stock.removeItem("champignon",3);
    }
}
