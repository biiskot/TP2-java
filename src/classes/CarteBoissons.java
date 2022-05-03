package classes;

import java.util.ArrayList;
import java.util.List;

public class CarteBoissons {
    public static final List<String> listeboissons = new ArrayList<>(){
        {
            add("biere");
            add("cidre");
            add("eau");
            add("jus");
            add("limonade");
        }
    };
}
