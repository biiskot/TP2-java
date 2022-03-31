package classes.Plats;

import classes.Consommable;
import com.company.Main;

public class BurgerViande extends Consommable {
    public BurgerViande() {
        super("burger_viande", 12);

        Main.stock.removeItem("pain_burger", 1);
        Main.stock.removeItem("steak", 1);
    }
}
