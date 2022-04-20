package classes;

public abstract class Consommable{
     public int prix;
     public String name;
     private int tempsPrepa;

    public Consommable(String name, int prix){
        this.name = name;
        this.prix = prix;
    }
}

