package classes;

public class Boisson extends Consommable {
    String nom;
    Boisson (String name, int price) {
        super();
        this.nom = name;

        switch (name) {
            case "limonade" -> this.prix = 4;
            case "cidre" -> this.prix = 5;
            case "biere" -> this.prix = 5;
            case "jus" -> this.prix = 1;
            case "eau" -> this.prix = 0;
        }
    }
}
