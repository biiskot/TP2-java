package classes;

import java.util.ArrayList;
import java.util.List;

public class Clients {
    int nombre = 0;
    List<Consommable> commande = new ArrayList<>();

    Clients(int nombre) {
        this.nombre = nombre;

        //Boucle forEach
        for (Consommable c : commande) {

        }
    }

}

