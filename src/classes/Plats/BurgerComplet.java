package classes.Plats;

import classes.Consommable;
import com.company.Main;

public class BurgerComplet extends Consommable {
    public BurgerComplet(){
            super("burger_complet",12);

            Main.stock.removeItem("pain_burger",1);
            Main.stock.removeItem("tomate",1);
            Main.stock.removeItem("salade",1);
            Main.stock.removeItem("steak",1);
        }
}
