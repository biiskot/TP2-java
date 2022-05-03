package classes.Plats;

import classes.Consommable;
import classes.Inventaire;
import com.company.Main;

public class FajitasBoeuf extends Consommable {
    public FajitasBoeuf(){
        super("fajitas_boeuf",11);

        Inventaire.removeItem("galette",1);
        Inventaire.removeItem("riz",1);
        Inventaire.removeItem("steak",1);
    }
}
