package Fuzzy.Sets;

public class SigmoidalFuzzySet extends FuzzySet{

    double a;
    double b;


    public SigmoidalFuzzySet(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getValue(double value){
        return 1/(1+Math.exp(-a*((double) value-b)));
    }

}
