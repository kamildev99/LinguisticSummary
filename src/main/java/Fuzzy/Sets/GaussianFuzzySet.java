package Fuzzy.Sets;

public class GaussianFuzzySet extends FuzzySet {
    private double center;
    private double width;

    public GaussianFuzzySet(double center, double width){
        this.center = center;
        this.width = width;
    }

    @Override
    public double getValue(double value) {
        //return Math.exp(-(center - value) * (center - value) / (2 * width * width));
        return Math.exp(-Math.pow((value-center)/width, 2));
    }

    public double getCenter() {
        return center;
    }

    public double getWidth() {
        return width;
    }
}
