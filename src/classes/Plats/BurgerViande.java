package classes.Plats;

import classes.Consommable;
import classes.Inventaire;
import com.company.Main;

public class BurgerViande extends Consommable {
    public BurgerViande() {
        super("burger_viande", 12);

        Inventaire.removeItem("pain_burger", 1);
        Inventaire.removeItem("steak", 1);
    }
}
