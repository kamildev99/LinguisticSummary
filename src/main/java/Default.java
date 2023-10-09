import Fuzzy.Data;
import Fuzzy.Sets.GaussianFuzzySet;
import Fuzzy.Sets.SigmoidalFuzzySet;
import Fuzzy.Sets.TrapezoidalFuzzySet;
import Fuzzy.Sets.TriangularFuzzySet;
import Fuzzy.Summary.Label;
import Fuzzy.Summary.LinguisticQuantifier;
import Fuzzy.Summary.LinguisticVariable;

public class Default {

    public static void def(){

        Data.lq.add(new LinguisticQuantifier("poniżej 2000", new TriangularFuzzySet(0,0,5000), true));
        Data.lq.add(new LinguisticQuantifier("powyżej 4000", new TrapezoidalFuzzySet(1950,4000,11966, 11966), true));
        Data.lq.add(new LinguisticQuantifier("około 6000", new GaussianFuzzySet(800,6000), true));
        Data.lq.add(new LinguisticQuantifier("prawie 8000", new GaussianFuzzySet(700,8000), true));
        Data.lq.add(new LinguisticQuantifier("około 11000", new TriangularFuzzySet(9000,11000,14000), true));


        Data.lq.add(new LinguisticQuantifier("żaden", new TriangularFuzzySet(0,0,0.2), false));
        Data.lq.add(new LinguisticQuantifier("kilku", new TriangularFuzzySet(0.1,0.22,0.46), false));
        Data.lq.add(new LinguisticQuantifier("połowa", new GaussianFuzzySet(0.1,0.5), false));
        Data.lq.add(new LinguisticQuantifier("większość", new TriangularFuzzySet(0.64,0.72, 0.88), false));
        Data.lq.add(new LinguisticQuantifier("wszyscy", new SigmoidalFuzzySet(45, 0.9), false));


        LinguisticVariable rozegraneGry = new LinguisticVariable("rozegrane gry", 0);
        rozegraneGry.labels.add(new Label(0,"początkujący", "rozegrane gry", new TrapezoidalFuzzySet(1, 1, 15, 25)));
        rozegraneGry.labels.add(new Label(0,"obeznani", "rozegrane gry", new TriangularFuzzySet(15, 25, 35)));
        rozegraneGry.labels.add(new Label(0,"doświadczeni", "rozegrane gry", new GaussianFuzzySet(10, 42)));
        rozegraneGry.labels.add(new Label(0,"zaawansowani", "rozegrane gry", new TrapezoidalFuzzySet(51, 53, 60, 70)));
        rozegraneGry.labels.add(new Label(0,"seniorzy", "rozegrane gry", new TrapezoidalFuzzySet(59, 70, 85, 85)));
        Data.lv.add(rozegraneGry);


        LinguisticVariable celneTrafienia = new LinguisticVariable("celne trafienia", 1);
        celneTrafienia.labels.add(new Label(1,"niedokladni", "celne trafienia", new TrapezoidalFuzzySet(0, 0, 120, 150)));
        celneTrafienia.labels.add(new Label(1,"malo celni", "celne trafienia", new TriangularFuzzySet(120, 210, 330)));
        celneTrafienia.labels.add(new Label(1,"przecietni", "celne trafienia",new TrapezoidalFuzzySet(270, 420, 540, 630)));
        celneTrafienia.labels.add(new Label(1,"celni", "celne trafienia", new TrapezoidalFuzzySet(510, 570, 660, 810)));
        celneTrafienia.labels.add(new Label(1,"precyzyjni", "celne trafienia", new SigmoidalFuzzySet(0.2, 810)));
        Data.lv.add(celneTrafienia);




        LinguisticVariable probneTrafienia = new LinguisticVariable("próbne trafienia", 2);
        probneTrafienia.labels.add(new Label(2,"defensywni", "próbne trafienia", new TrapezoidalFuzzySet(0, 0, 60, 230)));
        probneTrafienia.labels.add(new Label(2,"pasywni", "próbne trafienia", new TriangularFuzzySet(180, 490, 590)));
        probneTrafienia.labels.add(new Label(2,"atakujący", "próbne trafienia",new TrapezoidalFuzzySet(550, 730, 925, 925)));
        Data.lv.add(probneTrafienia);





        LinguisticVariable nieprzepisoweZagrania = new LinguisticVariable("nieprzepisowe_zagrania", 3);
        nieprzepisoweZagrania.labels.add(new Label(3,"spokojni", "nieprzepisowe_zagrania", new TrapezoidalFuzzySet(0, 0, 60, 105)));
        nieprzepisoweZagrania.labels.add(new Label(3,"utemperowani", "nieprzepisowe_zagrania", new TrapezoidalFuzzySet(75, 120, 150, 180)));
        nieprzepisoweZagrania.labels.add(new Label(3,"zrownoważeni", "nieprzepisowe_zagrania",new TriangularFuzzySet(150, 195, 225)));
        nieprzepisoweZagrania.labels.add(new Label(3,"pobudliwi", "nieprzepisowe_zagrania", new TriangularFuzzySet(20, 255, 1)));
        nieprzepisoweZagrania.labels.add(new Label(3,"agresywni", "nieprzepisowe_zagrania", new GaussianFuzzySet(0.28, 300)));
        Data.lv.add(nieprzepisoweZagrania);




        LinguisticVariable asysty = new LinguisticVariable("asysty", 4);
        asysty.labels.add(new Label(4,"egoistycznyi", "asysty", new TrapezoidalFuzzySet(0, 0, 60, 230)));
        asysty.labels.add(new Label(4,"wspierający", "asysty", new TriangularFuzzySet(180, 490, 590)));
        asysty.labels.add(new Label(4,"pomocni", "asysty",new TrapezoidalFuzzySet(550, 730, 925, 925)));
        Data.lv.add(asysty);





        LinguisticVariable odebranePiłki = new LinguisticVariable("odebrane piłki", 5);
        odebranePiłki.labels.add(new Label(5,"słabi w odbiorze", "odebrane piłki", new TrapezoidalFuzzySet(0, 0, 80, 100)));
        odebranePiłki.labels.add(new Label(5,"zwykli w odbiorze", "odebrane piłki",new TrapezoidalFuzzySet(90, 130, 150, 190)));
        odebranePiłki.labels.add(new Label(5,"sprytni", "odebrane piłki",new TrapezoidalFuzzySet(170, 200, 225, 225)));
        Data.lv.add(odebranePiłki);


        LinguisticVariable blokowania = new LinguisticVariable("blokowania", 6);
        blokowania.labels.add(new Label(6,"latwi w objesciu", "blokowania", new TrapezoidalFuzzySet( 0, 0, 50, 80)));
        blokowania.labels.add(new Label(6,"przeszkadzajacy", "blokowania",new TrapezoidalFuzzySet(70, 125, 180, 225)));
        blokowania.labels.add(new Label(6,"trudni w objesciu", "blokowania",new TrapezoidalFuzzySet(210, 260, 307, 307)));
        Data.lv.add(blokowania);



        LinguisticVariable punkty = new LinguisticVariable("punkty", 7);
        punkty.labels.add(new Label(7,"kiepscy", "punkty", new TrapezoidalFuzzySet(0, 0, 275, 500)));
        punkty.labels.add(new Label(7,"słabi", "punkty", new GaussianFuzzySet(200, 750)));
        punkty.labels.add(new Label(7,"nie wyróżniajacy sie", "punkty",new TriangularFuzzySet(925, 1450, 1675)));
        punkty.labels.add(new Label(7,"zdolni", "punkty", new TriangularFuzzySet(1450, 1800, 2175)));
        punkty.labels.add(new Label(7,"utalentowani", "punkty", new TrapezoidalFuzzySet(1975, 2200, 2832, 2832)));
        Data.lv.add(punkty);




        LinguisticVariable rokUrodzenia = new LinguisticVariable("rok urodzenia", 8);
        rokUrodzenia.labels.add(new Label(8,"młodzi", "rok urodzenia", new TrapezoidalFuzzySet(  1961, 1961, 1970, 1979)));
        rokUrodzenia.labels.add(new Label(8,"dojrzali", "rok urodzenia",new TrapezoidalFuzzySet(1977, 1979, 1988, 1990)));
        rokUrodzenia.labels.add(new Label(8,"u kresu kariery", "rok urodzenia",new SigmoidalFuzzySet( 0.5, 1993)));
        Data.lv.add(rokUrodzenia);


        LinguisticVariable waga = new LinguisticVariable("waga", 9);
        waga.labels.add(new Label(9,"wagi piórkowej", "waga", new TrapezoidalFuzzySet(57, 57, 77, 84)));
        waga.labels.add(new Label(9,"leccy", "waga",new GaussianFuzzySet( 8, 93)));
        waga.labels.add(new Label(9,"sredniej wagi", "waga",new TriangularFuzzySet( 99, 111, 121)));
        waga.labels.add(new Label(9,"ciężcy", "waga", new TriangularFuzzySet(120, 127, 142)));
        waga.labels.add(new Label(9,"super ciężcy", "waga", new TrapezoidalFuzzySet( 135, 150, 170, 170)));
        Data.lv.add(waga);

        LinguisticVariable wysokosc = new LinguisticVariable("wysokość", 10);
        wysokosc.labels.add(new Label(10,"mali", "wysokość", new SigmoidalFuzzySet(-1, 172)));
        wysokosc.labels.add(new Label(10,"niewielcy", "wysokość", new TriangularFuzzySet(173, 182, 190)));
        wysokosc.labels.add(new Label(10,"średniego wzrostu", "wysokość",new TrapezoidalFuzzySet( 186, 190, 195, 206)));
        wysokosc.labels.add(new Label(10,"wysocy", "wysokość", new GaussianFuzzySet(6, 210)));
        wysokosc.labels.add(new Label(10,"ogromni", "wysokość", new SigmoidalFuzzySet( 1, 220)));
        Data.lv.add(wysokosc);








    }


}
