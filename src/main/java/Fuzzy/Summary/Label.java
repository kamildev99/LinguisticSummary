package Fuzzy.Summary;

import Fuzzy.Sets.FuzzySet;

public class Label {
    private int ID;
    private String name;
    private String parentName;
    private FuzzySet set;

    public Label(int ID, String name, String parentName, FuzzySet set) {
        this.ID = ID;
        this.parentName = parentName;
        this.name = name;
        this.set = set;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }
    public String getParentName() {
        return parentName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FuzzySet getSet() {
        return set;
    }

    public void setSet(FuzzySet set) {
        this.set = set;
    }
}
