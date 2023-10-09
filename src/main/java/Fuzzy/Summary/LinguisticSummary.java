package Fuzzy.Summary;

import Fuzzy.Data;
import Fuzzy.Model.BasketballPlayer;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class LinguisticSummary {
    private LinguisticQuantifier quantifier;
    private Label qualifier;
    private ArrayList<BasketballPlayer> objects;
    private ArrayList<BasketballPlayer> objects2;
    private ArrayList<Label> summarizers;
    private int multiForm = 0;

    public LinguisticSummary(LinguisticQuantifier quantifier, Label qualifier, ArrayList<Label> summarizers) {
        this.quantifier = quantifier;
        this.qualifier = qualifier;
        this.objects = Data.basketballPlayers;
        this.summarizers = summarizers;
    }

    public LinguisticSummary(LinguisticQuantifier quantifier, Label qualifier, String objects, String objects2, ArrayList<Label> summarizers, int multiForm) {
        this.quantifier = quantifier;
        this.qualifier = qualifier;
        this.objects = Data.basketballPlayers.stream().filter(player -> player.league.equals(objects)).collect(Collectors.toCollection(ArrayList::new));
        this.objects2 = Data.basketballPlayers.stream().filter(player -> player.league.equals(objects2)).collect(Collectors.toCollection(ArrayList::new));
        this.summarizers = summarizers;
        this.multiForm = multiForm;
    }

    public String generate(){
        StringBuilder sb = new StringBuilder();
        switch (multiForm){
            case 0:{
                sb.append(quantifier.name);
                sb.append(" Koszykarzy ");
                if(qualifier != null){
                    sb.append(", którzy są/mają ");
                    sb.append(humanFriendlyLabels(qualifier));
                }
                sb.append(connectSummarizers(summarizers));
                break;
            }
            case 1: {
                sb.append(quantifier.name);
                sb.append(" Koszykarzy, którzy z ligii ");
                sb.append(objects.get(0).league);
                sb.append(", w porównaniu do Koszykarzy z ligii ");
                sb.append(objects2.get(0).league);
                sb.append(connectSummarizers(summarizers));
                break;
            }
            case 2:{
                sb.append(quantifier.name);
                sb.append(" Koszykarzy, którzy z ligii ");
                sb.append(objects.get(0).league);
                sb.append(", w porównaniu do koszykarzy z ligii ");
                sb.append(objects2.get(0).league);
                sb.append(", którzy są/mają ");
                sb.append(humanFriendlyLabels(qualifier));
                sb.append(connectSummarizers(summarizers));
                break;
            }
            case 3:{
                sb.append(quantifier.name);
                sb.append(" Koszykarzy, którzy z ligii  ");
                sb.append(objects.get(0).league);
                sb.append(", które sa/mają ");
                sb.append(humanFriendlyLabels(qualifier));
                sb.append(", w porównaniu do koszykarzy z ligii ");
                sb.append(objects2.get(0).league);
                sb.append(connectSummarizers(summarizers));
                break;
            }
            case 4:{
                sb.append("Więcej koszykarzy, z ligii ");
                sb.append(objects.get(0).league);
                sb.append(", niż koszykarzy, z ligii ");
                sb.append(objects2.get(1).league);
                sb.append(connectSummarizers(summarizers));
            }
        }

        return sb.toString();

    }

    private String humanFriendlyLabels(Label label){
    /*switch (label.getID()){
        case 0:{
            return label.getName().toLowerCase() + " do zagrania";
        }
        case 2:{
            return label.getName().toLowerCase() + label.getParentName().substring(5).toLowerCase();
        }
        case 3:{
            return label.getName().toLowerCase() + label.getParentName().substring(13).toLowerCase();
        }
        default:{
            return label.getName().toLowerCase() +" "+ label.getParentName().toLowerCase();
        }
    }*/
        return label.getName().toLowerCase();
    }

    private String connectSummarizers(ArrayList<Label> summarizers){
        StringBuilder sb = new StringBuilder();
        sb.append(" są/ma ");
        sb.append(humanFriendlyLabels(summarizers.get(0)));

        for(int i = 1; i< summarizers.size(); i++){
            sb.append(" i są/ma ");
            sb.append(humanFriendlyLabels(summarizers.get(i)));
        }
        return sb.toString();
    }
    public LinguisticQuantifier getQuantifier() {
        return quantifier;
    }

    public Label getQualifier() {
        return qualifier;
    }

    public ArrayList<BasketballPlayer> getObjects() {
        return objects;
    }

    public ArrayList<BasketballPlayer> getObjects2() {
        return objects2;
    }

    public ArrayList<Label> getSummarizers() {
        return summarizers;
    }

    public int getMultiForm() {
        return multiForm;
    }
}
