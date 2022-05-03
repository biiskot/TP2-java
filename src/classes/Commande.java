package classes;

import java.util.ArrayList;
import java.util.List;

public class Commande {
    public List<Integer> listeIdsConsos = new ArrayList<>();
    public List<String>ticketCaisse = new ArrayList<>();
    public int id;
    public int addition;
    public boolean platsReady = false, boissonsReady = false, containsBoisson = false, containsPlat = false;
    public Commande(int id){
        this.id = id;
    }

    public void servirBoissons(CarteBoissons cb){
        Consommable obj;
        String conso;
        //On build
        for(int n : this.listeIdsConsos){
          if (n >= 11){
              obj = FactoryConsommable.Build(cb.listeboissons.get(n-11));
              conso = obj.getClass().getSimpleName()+" --- "+obj.prix+" €";
              this.addition+=obj.prix;
              ticketCaisse.add(conso);
          }
        }
        System.out.println("Boissons servies");
    }
public void servirPlats(CartePlats cp){
        Consommable obj;
        String conso;
        //On build
        for(int n : this.listeIdsConsos){
          if (n < 11){
                obj = FactoryConsommable.Build(cp.listeplats.get(n));
                conso = obj.getClass().getSimpleName()+" --- "+obj.prix+" €";
                this.addition+=obj.prix;
                ticketCaisse.add(conso);
            }
        }
        System.out.println("Plats servis");
    }

}
