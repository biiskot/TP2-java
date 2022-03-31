package classes.Plats;

import classes.Consommable;
import com.company.Main;

public class BurgerSalade extends Consommable {
    public BurgerSalade() {
        super("burger_salade", 12);

        Main.stock.removeItem("pain_burger", 1);
        Main.stock.removeItem("salade", 1);
        Main.stock.removeItem("steak", 1);
    }
}
