package Fuzzy.Sets;

public class TriangularFuzzySet extends FuzzySet {

    private double a;
    private double b;
    private double m;

    public TriangularFuzzySet(double a, double m, double b){
        this.a = a;
        this.b = b;
        this.m = m;
    }



    @Override
    public double getValue(double val) {
        if (val <= a || val >= b) {
            return 0.0;
        } else if (val > a && val <= m) {
            return (val - a) / (m - a);
        } else if (val > m && val < b) {
            return (b - val) / (b - m);
        } else return 0.0;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getM() {
        return m;
    }
}
