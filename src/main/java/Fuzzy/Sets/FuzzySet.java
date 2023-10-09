package Fuzzy.Sets;

import java.util.List;
import java.util.stream.Collectors;

public class FuzzySet {

    public String name;

    public double getValue(double val) {
        return 0;
    }

    public String getName() {
        return name;
    }

    //dopełnienie
    public double complement(List<Double> raports) {
        return 0;
    }

    //suma
    public double sum(FuzzySet fuzzySet) {
        return 0;
    }

    //iloczyn
    public double product(FuzzySet fuzzySet) {
        return 0;
    }

    //wysokość
    public double height(List<Double> raports) {
        //maksymalna wartosc funkcji przynaleznosci
        return 1;
    }
    //zbiór rozmyty pusty,wypukły,normalny - o co chodzi xD

    //nośnik
    public List<Double> support(List<Double> player) {
        return player.stream().filter(xx -> getValue(xx) > 0.0).collect(Collectors.toList());
    }

    //alfa-przekrój
    public List<Double> alphaCut(List<Double> player, double a) {
        return player.stream().filter(xx-> getValue(xx) >= a).collect(Collectors.toList());
    }

    public boolean isConvex(){
        return true;
    }

    public boolean isNormalized(List<Double> player) {
        //if height == 1
        return true;
    }

    public boolean isEmpty(List<Double> player) {
        return false;
    }

    public double degreeOfFuzziness(List<Double> player){
        return support(player).size() / (double) player.size();
    }

    public double cardinality(List<Double> player){
        return player.stream().mapToDouble(this::getValue).sum();
    }


}
