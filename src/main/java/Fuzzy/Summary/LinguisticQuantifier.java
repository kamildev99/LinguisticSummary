package Fuzzy.Summary;

import Fuzzy.Sets.FuzzySet;

public class LinguisticQuantifier {
    public String name;
    public FuzzySet set;
    public boolean isabsolute;

    public LinguisticQuantifier(String name, FuzzySet set, boolean isabsolute){
        this.name = name;
        this.set = set;
        this.isabsolute = isabsolute;
    }
}
