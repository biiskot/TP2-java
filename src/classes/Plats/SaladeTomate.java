package classes.Plats;

import classes.Consommable;
import classes.Inventaire;
import com.company.Main;

public class SaladeTomate extends Consommable {
    public SaladeTomate(){
        super("salade_tomate",12);

        Inventaire.removeItem("salade",1);
        Inventaire.removeItem("tomate",1);
    }
}
