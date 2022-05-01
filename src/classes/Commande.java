package classes;

import java.util.ArrayList;
import java.util.List;

public class Commande {
    public List<Integer> listeIdsConsos = new ArrayList<Integer>();
    public int id;
    public boolean platsReady = false, boissonsReady = false;

    public Commande(int id){
        this.id = id;
    }
}
