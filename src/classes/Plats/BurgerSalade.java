package classes.Plats;

import classes.Consommable;
import classes.Inventaire;
import com.company.Main;

public class BurgerSalade extends Consommable {
    public BurgerSalade() {
        super("burger_salade", 12);

        Inventaire.removeItem("pain_burger", 1);
        Inventaire.removeItem("salade", 1);
        Inventaire.removeItem("steak", 1);
    }
}
