package Fuzzy.Summary;

import java.util.ArrayList;

public class LinguisticVariable {
    public int ID;
    public String name;
    public ArrayList<Label> labels = new ArrayList<Label>();
    public LinguisticVariable(String name, int ID){
        this.name = name;
        this.ID = ID;
    }


}
