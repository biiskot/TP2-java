package classes;

import com.company.Main;

public abstract class Consommable{
     public int prix;
     public String name;
     private int tempsPrepa;

    public Consommable(String name, int prix){
        this.name = name;
        this.prix = prix;
        Main.chiffreAffaire += prix; //A chaque fois qu'un consommable est build (factory) le chiffre d'affaire augmente
    }
}

