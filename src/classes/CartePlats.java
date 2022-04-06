package classes;

import classes.Plats.*;


import java.util.ArrayList;
import java.util.List;

public class CartePlats{
    public List<Consommable> listeplats = new ArrayList<>();
    public CartePlats(){
        listeplats.add(new BurgerComplet());
        listeplats.add(new BurgerSalade());
        listeplats.add(new BurgerViande());
        listeplats.add(new Pizza());
        listeplats.add(new PizzaReine());
        listeplats.add(new PizzaSaucisse());
        listeplats.add(new PotageChampi());
        listeplats.add(new PotageOignon());
        listeplats.add(new PotageTomate());
        listeplats.add(new Salade());
        listeplats.add(new SaladeTomate());
    }
}
