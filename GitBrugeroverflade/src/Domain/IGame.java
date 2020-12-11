package Domain;

import java.util.ArrayList;

public interface IGame {

    public void addInventory(String name);
    public void removeInventory(String name);
    public ArrayList<String> getInventory();


}



