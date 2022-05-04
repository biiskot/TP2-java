package classes;

import java.util.ArrayList;
import java.util.List;

public class Commande {
    public List<Integer> listeIdsConsos = new ArrayList<>();
    public List<String>ticketCaisse = new ArrayList<>();
    public int id, addition;
    public boolean platsReady = false, boissonsReady = false, containsBoisson = false, containsPlat = false, menuCentenaire = false;
    public Commande(int id){
        this.id = id;
    }

    public void servirBoissons(){
        Consommable obj;
        String conso;
        //On build
        for(int n : this.listeIdsConsos){
          if (n >= CartePlats.listeplats.size()){
              obj = FactoryConsommable.Build(CarteBoissons.listeboissons.get(n-CartePlats.listeplats.size()));
              conso = obj.getClass().getSimpleName()+" --- "+obj.prix+" €";//String avec le nom de la conso et son prix
              //Si menu centenaire, on fixe l'addition à 100
              if(this.menuCentenaire){
                  addition = 100;
              }
              else {
                  this.addition+=obj.prix;
              }
              ticketCaisse.add(conso);
          }
        }
        System.out.println("Boissons servies");
    }
public void servirPlats(){
        Consommable obj;
        String conso;
        //On build
        for(int n : this.listeIdsConsos){
          if (n < CartePlats.listeplats.size()){
                obj = FactoryConsommable.Build(CartePlats.listeplats.get(n));
                conso = obj.getClass().getSimpleName()+" --- "+obj.prix+" €";//String avec le nom de la conso et son prix
                  //Si menu centenaire, on fixe l'addition à 100
                  if(this.menuCentenaire){
                      addition = 100;
                  }
                  else {
                      this.addition+=obj.prix;
                  }
                ticketCaisse.add(conso);
            }
        }
        System.out.println("Plats servis");
    }

}
