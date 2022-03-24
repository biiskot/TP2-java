package classes;

import java.util.ArrayList;
import java.util.List;

public abstract class Carte {
    List<Consommable> listecons = new ArrayList<>();

    public Carte(List<Consommable> listcons){

        this.listecons = listcons;
    }

    Carte updateCarte(Consommable cons){
        this.listecons.add(cons);
        return this;
    }
}
