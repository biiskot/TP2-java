package classes.Plats;

import classes.Consommable;
import classes.Inventaire;

public class FajitasPoulet extends Consommable {

    public FajitasPoulet(){
        super("fajitas_poulet",11);

        Inventaire.removeItem("galette",1);
        Inventaire.removeItem("riz",1);
        Inventaire.removeItem("poulet",1);
    }
}
