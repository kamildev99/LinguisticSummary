package Fuzzy.Measures;

import Fuzzy.Model.BasketballPlayer;
import Fuzzy.Sets.GaussianFuzzySet;
import Fuzzy.Sets.SigmoidalFuzzySet;
import Fuzzy.Sets.TrapezoidalFuzzySet;
import Fuzzy.Sets.TriangularFuzzySet;
import Fuzzy.Summary.Label;
import Fuzzy.Summary.LinguisticSummary;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Measures {
    private LinguisticSummary linguisticSummary;
    private List<Double> weights;

    public Measures(LinguisticSummary linguisticSummary, List<Double> weights) {
        this.linguisticSummary = linguisticSummary;
        this.weights = weights;
    }

    //Podsumowanie złozone
    private double compoundSummarizer(ArrayList<Label> labels, BasketballPlayer player){
      return labels.stream().mapToDouble( x -> x.getSet().getValue(player.resB.get(x.getID()))).min().orElse(0);
    }



//T1
    public double degreeOfTruth() {
        double u = 0;
        double m = 0;

        switch (linguisticSummary.getMultiForm()){
            case 0:{
                if (linguisticSummary.getQualifier() == null) {
                    //pierwsza forma jednopodmiotowego
                    for (BasketballPlayer player : linguisticSummary.getObjects()) {
                        u += compoundSummarizer(linguisticSummary.getSummarizers(), player);
                    }
                    m = linguisticSummary.getObjects().size();
                } else {
                    //druga forma jednopodmiotowe (z kwalifikatorem)
                    ArrayList<Label> allLabels = new ArrayList<>(linguisticSummary.getSummarizers());
                    allLabels.add(linguisticSummary.getQualifier());

                    for (BasketballPlayer player : linguisticSummary.getObjects()) {
                        u += compoundSummarizer(allLabels, player);
                        m += linguisticSummary.getQualifier().getSet()
                                .getValue( player.resB.get(linguisticSummary.getQualifier().getID()));
                    }
                }
                if (linguisticSummary.getQuantifier().isabsolute) {
                    return linguisticSummary.getQuantifier().set.getValue(u);
                } else {
                    return linguisticSummary.getQuantifier().set.getValue(u / m);
                }
            }
            case 1:{
                double p1 = 0.0;
                double p2 = 0.0;
                for(BasketballPlayer player: linguisticSummary.getObjects()){
                    p1 += compoundSummarizer(linguisticSummary.getSummarizers(), player);
                }
                for(BasketballPlayer player: linguisticSummary.getObjects2()){
                    p2 += compoundSummarizer(linguisticSummary.getSummarizers(), player);
                }
                p1 /= linguisticSummary.getObjects().size();
                p2 /= linguisticSummary.getObjects2().size();
                return linguisticSummary.getQuantifier().set.getValue(p1 / ( p1 + p2));
            }
            case 2:{
                double p1 = 0.0;
                double p2 = 0.0;
                ArrayList<Label> allLabels = new ArrayList<>(linguisticSummary.getSummarizers());
                allLabels.add(linguisticSummary.getQualifier());

                for(BasketballPlayer player: linguisticSummary.getObjects()){
                    p1 += compoundSummarizer(linguisticSummary.getSummarizers(), player);
                }
                for(BasketballPlayer player: linguisticSummary.getObjects2()){
                    p2 += compoundSummarizer(allLabels, player);
                }
                p1 /= linguisticSummary.getObjects().size();
                p2 /= linguisticSummary.getObjects2().size();
                return linguisticSummary.getQuantifier().set.getValue(p1 / ( p1 + p2));
            }
            case 3:{
                double p1 = 0.0;
                double p2 = 0.0;
                ArrayList<Label> allLabels = new ArrayList<>(linguisticSummary.getSummarizers());
                allLabels.add(linguisticSummary.getQualifier());

                for(BasketballPlayer player: linguisticSummary.getObjects()){
                    p1 += compoundSummarizer(allLabels, player);
                }
                for(BasketballPlayer player: linguisticSummary.getObjects2()){
                    p2 += compoundSummarizer(linguisticSummary.getSummarizers(), player);
                }
                System.out.println(linguisticSummary.getObjects().toString());
                System.out.println(p1);
                System.out.println(p2);
                p1 /= linguisticSummary.getObjects().size();
                p2 /= linguisticSummary.getObjects2().size();
                return linguisticSummary.getQuantifier().set.getValue(p1 / ( p1 + p2));
            }
            case 4:{
                double p1 = 0.0;
                double p2 = 0.0;
                for(BasketballPlayer player: linguisticSummary.getObjects()){
                    p1 += compoundSummarizer(linguisticSummary.getSummarizers(), player);
                }
                for(BasketballPlayer player: linguisticSummary.getObjects2()){
                    p2 += compoundSummarizer(linguisticSummary.getSummarizers(), player);
                }
                p1 /= linguisticSummary.getObjects().size();
                p2 /= linguisticSummary.getObjects2().size();
                return 1.0 - Math.min(1 - p1 + p2, 1);
            }
        }
        return 0.0;
    }

    // T2zalezy od sredniej geometrycznej stopni rozmytosci (supp / size) zbiorów rozmytych
    public double degreeOfImprecision() {
        if(linguisticSummary.getMultiForm() != 0) return 0.0;
        double result = 1.0;
        for (Label label : linguisticSummary.getSummarizers()) {
            result *= label.getSet().degreeOfFuzziness(linguisticSummary.getObjects().stream().map(player -> player.resB.get(label.getID())).collect(Collectors.toList()));
        }

        return 1 - Math.pow(result, 1.0 / linguisticSummary.getSummarizers().size());
    }

    // T3jak duzo rekordow z bazy jest uwzglednionych przez kwalifikator
    public double degreeOfCovering() {
        if(linguisticSummary.getMultiForm() != 0) return 0.0;
        if (linguisticSummary.getQualifier() == null) {
            return linguisticSummary.getSummarizers().get(0).getSet().degreeOfFuzziness(
                    linguisticSummary.getObjects().stream()
                            .map(player ->  player.throwsAttemptR).collect(Collectors.toList())
            );
        }
        double t = linguisticSummary.getObjects().stream()
                .map(player -> Math.min(
                        linguisticSummary.getQualifier().getSet()
                                .getValue(player.throwsAttemptR),
                        linguisticSummary.getSummarizers().get(0).getSet()
                                .getValue(player.throwsAttemptR)
                )).filter(x -> x > 0).count();

        double h = linguisticSummary.getQualifier().getSet()
                .support(linguisticSummary.getObjects().stream().map(player -> player.resB.get(linguisticSummary.getSummarizers().get(0).getID())).collect(Collectors.toList()))
                .size();

        return t / h;
    }

    //T4
    public double degreeOfAppropriateness() {
        if(linguisticSummary.getMultiForm() != 0) return 0.0;
        double res = linguisticSummary.getSummarizers().stream().mapToDouble(summ -> summ.getSet().support(
                linguisticSummary.getObjects().stream()
                        .map(player ->  player.resB.get(summ.getID())).collect(Collectors.toList())).size()
                / (double) linguisticSummary.getObjects().size()).reduce(1.0, (a, b) -> a * b);
        double T3 = degreeOfCovering();
        return Math.abs(res - T3);
    }

    //T5
    public double lengthOfSummary() {
        if(linguisticSummary.getMultiForm() != 0) return 0.0;
        return 2.0 * Math.pow(0.5, linguisticSummary.getSummarizers().size());
    }

    //T6
    public double degreeOfQuantifierImprecision() {
        if(linguisticSummary.getMultiForm() != 0) return 0.0;
        if (linguisticSummary.getQuantifier().set instanceof TrapezoidalFuzzySet) {
            TrapezoidalFuzzySet fuzzySet = (TrapezoidalFuzzySet) linguisticSummary.getQuantifier().set;
            double supp = fuzzySet.getB() - fuzzySet.getA();
            return 1 - supp / (double) linguisticSummary.getObjects().size();
        }else if(linguisticSummary.getQuantifier().set instanceof TriangularFuzzySet){
            TriangularFuzzySet fuzzySet = (TriangularFuzzySet) linguisticSummary.getQuantifier().set;
            double supp = fuzzySet.getB() - fuzzySet.getA();
            return 1 - supp / (double) linguisticSummary.getObjects().size();
        }else if(linguisticSummary.getQuantifier().set instanceof SigmoidalFuzzySet){
            SigmoidalFuzzySet fuzzySet = (SigmoidalFuzzySet) linguisticSummary.getQuantifier().set;
            double integral = 0.0;
            for (double i = 0.0; i <= 1.0; i += 0.01) {
                integral += fuzzySet.getValue(i) * 0.01;
            }
            return 1.0 - integral;
        }
        else {
            GaussianFuzzySet fuzzySet = (GaussianFuzzySet) linguisticSummary.getQuantifier().set;
            double integral = 0.0;
            for (double i = 0.0; i <= 1.0; i += 0.01) {
                integral += fuzzySet.getValue(i) * 0.01;
            }
            return 1.0 - integral;
        }
    }

    //T7
    public double degreeOfQuantifierCardinality() {
        if(linguisticSummary.getMultiForm() != 0) return 0.0;
        double card = 0.0;

            for(int i=0; i< linguisticSummary.getObjects().size(); i++){
                card += linguisticSummary.getQuantifier().set.getValue(i * ((linguisticSummary.getQuantifier().isabsolute)? 1.0 : (1.0 / linguisticSummary.getObjects().size())));
            }

        return 1 - card / (double) linguisticSummary.getObjects().size();
    }

    //T8
    public double degreeOfSummarizerCardinality() {
        if(linguisticSummary.getMultiForm() != 0) return 0.0;
        double result = 1.0;
        for (Label label : linguisticSummary.getSummarizers()) {
            result *= (label.getSet().cardinality(linguisticSummary.getObjects().stream()
                    .map(player ->  player.resB.get(label.getID())).collect(Collectors.toList())) / linguisticSummary.getObjects().size());
        }

        return 1 - Math.pow(result, 1.0 / linguisticSummary.getSummarizers().size());
    }

    //T9
    public double degreeOfQualifierImprecision() {
        if(linguisticSummary.getMultiForm() != 0) return 0.0;
        if(linguisticSummary.getQualifier() == null){
            return 0.0;
        }
        return 1.0 - linguisticSummary.getQualifier().getSet().degreeOfFuzziness(linguisticSummary.getObjects().stream()
                .map(player ->  player.resB.get(linguisticSummary.getQualifier().getID())).collect(Collectors.toList()));
    }

    //T10
    public double degreeOfQualifierCardinality() {
        if(linguisticSummary.getMultiForm() != 0) return 0.0;
        if(linguisticSummary.getQualifier() == null){
            return 0.0;
        }
        return 1.0 - linguisticSummary.getQualifier().getSet().cardinality(linguisticSummary.getObjects().stream()
                .map(x-> x.resB.get(linguisticSummary.getQualifier().getID())).collect(Collectors.toList()))
                / (double) linguisticSummary.getObjects().size();
    }

    //T11
    public double lengthOfQualifier() {
        if(linguisticSummary.getMultiForm() != 0) return 0.0;
        return 2.0 * Math.pow(0.5, 1);
    }

    //ogólna jakosc
    public double goodnessOfTheSummary() {
        if(linguisticSummary.getMultiForm() != 0) return degreeOfTruth();
        return degreeOfTruth() * weights.get(0)
                + degreeOfImprecision() * weights.get(1)
                + degreeOfCovering() * weights.get(2)
                + degreeOfAppropriateness() * weights.get(3)
                + lengthOfSummary() * weights.get(4)
                + degreeOfQuantifierImprecision() * weights.get(5)
                + degreeOfQuantifierCardinality() * weights.get(6)
                + degreeOfSummarizerCardinality() * weights.get(7)
                + degreeOfQualifierImprecision() * weights.get(8)
                + degreeOfQualifierCardinality() * weights.get(9)
                + lengthOfQualifier() * weights.get(10);
    }

    @Override
    public String toString() {
        if(linguisticSummary.getMultiForm() == 0){
            System.out.println(linguisticSummary.generate() +';'+
                            String.format("%.2f", degreeOfTruth()) +';'+
                            String.format("%.2f", degreeOfImprecision()) +';'+
                            String.format("%.2f", degreeOfCovering()) +';'+
                            String.format("%.2f", degreeOfAppropriateness()) +';'+
                            String.format("%.2f", lengthOfSummary()) +';'+
                            String.format("%.2f", degreeOfQuantifierImprecision()) +';'+
                            String.format("%.2f", degreeOfQuantifierCardinality()) +';'+
                            String.format("%.2f", degreeOfSummarizerCardinality()) +';'+
                            String.format("%.2f", degreeOfQualifierImprecision()) +';'+
                            String.format("%.2f", degreeOfQualifierCardinality()) +';'+
                            String.format("%.2f", lengthOfQualifier()) +';'+
                            String.format("%.2f", goodnessOfTheSummary())
                    );
            return String.format(" T1[%.2f] T2[%.2f] T3[%.2f] T4[%.2f] T5[%.2f] T6[%.2f] T7[%.2f] T8[%.2f] T9[%.2f] T10[%.2f] T11[%.2f] optymalna[%.2f] ",
                    degreeOfTruth(), degreeOfImprecision(), degreeOfCovering(), degreeOfAppropriateness(), lengthOfSummary(),
                    degreeOfQuantifierImprecision(),
                    degreeOfQuantifierCardinality(),
                    degreeOfSummarizerCardinality(),
                    degreeOfQualifierImprecision(),
                    degreeOfQualifierCardinality(),
                    lengthOfQualifier(),
                    goodnessOfTheSummary()
            );
        } else {
            System.out.println(linguisticSummary.generate() +';'+
                            String.format("%.2f", degreeOfTruth())
                    );
            return String.format(" [%.2f]", degreeOfTruth());
        }

    }

    public LinguisticSummary getLinguisticSummary() {
        return linguisticSummary;
    }
}
