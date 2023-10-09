package Fuzzy.Sets;

public class TrapezoidalFuzzySet extends FuzzySet {
    private double a;
    private double b;
    private double m;
    private double n;
    
    public TrapezoidalFuzzySet(double a, double m, double n, double b){
        this.a = a;
        this.b = b;
        this.m = m;
        this.n = n;
    }



    @Override
    public double getValue(double val) {
        if (val <= a || val >= b) {
                return 0.0;
            }
        else if (val > a && val <= m) {
                return (val - a) / (m - a);
            }
        else if (val > m && val <= n) {
                return 1.0;
            }
        else if (val > n && val < b) {
                return (b - val) / (b - n);
            }
        else return 0.0;
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

    public double getN() {
        return n;
    }
}


