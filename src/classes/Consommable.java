package classes;

public abstract class Consommable{
    static int prix;
    static String name;

    public Consommable(String name, int prix){
        Consommable.name = name;
        Consommable.prix = prix;
    }
}

