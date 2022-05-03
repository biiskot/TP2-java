package classes.Plats;

import classes.Consommable;
import classes.Inventaire;
import com.company.Main;

public class BurgerComplet extends Consommable {
    public BurgerComplet(){
            super("burger_complet",12);

            Inventaire.removeItem("pain_burger",1);
            Inventaire.removeItem("tomate",1);
            Inventaire.removeItem("salade",1);
            Inventaire.removeItem("steak",1);
        }
}
