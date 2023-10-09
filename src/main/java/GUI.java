import Fuzzy.Data;
import Fuzzy.Measures.Measures;
import Fuzzy.Sets.GaussianFuzzySet;
import Fuzzy.Sets.TrapezoidalFuzzySet;
import Fuzzy.Sets.TriangularFuzzySet;
import Fuzzy.Summary.Label;
import Fuzzy.Summary.LinguisticQuantifier;
import Fuzzy.Summary.LinguisticSummary;
import Fuzzy.Summary.LinguisticVariable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;


public class GUI extends Application {
    private boolean withoutAbs = false;

    public ArrayList<Double> weights = new ArrayList<>();
    public ComboBox<String> firstSubjectCombo;
    public ComboBox<String> secondSubjectCombo;

    public RadioButton radioPierwszaPierwsza;
    public RadioButton radioPierwszaDruga;
    public RadioButton radioDrugaPierwsza;
    public RadioButton radioDrugaDruga;
    public RadioButton radioDrugaTrzecia;
    public RadioButton radioDrugaCzwarta;

    public TextArea podsText;
    public TextArea kwanText;
    public TextArea kwalText;
    public TextArea sumText;
    public TextField kwanIDText;
    public TextField kwalIDText;
    public TextField sumIDText;

    public CheckBox isAbsChckbx;

    public javafx.scene.control.Label firstLabel;
    public javafx.scene.control.Label secondLabel;
    public javafx.scene.control.Label thirdLabel;
    public javafx.scene.control.Label fourthLabel;


    public TextField T1Text;
    public TextField T2Text;
    public TextField T3Text;
    public TextField T4Text;
    public TextField T5Text;
    public TextField T6Text;
    public TextField T7Text;
    public TextField T8Text;
    public TextField T9Text;
    public TextField T10Text;
    public TextField T11Text;

    public Button WyczyśćBttn;
    public Button zapiszBttn;
    public Button generujBttn;
    public TextField ostatniePodsText;

    public RadioButton kwanRadio;
    public RadioButton kwalSumRadio;

    public ComboBox<String> wybierzZmiennaCombo;

    public RadioButton gaussowskaRadio;
    public RadioButton trapezoidalnaRadio;
    public RadioButton trojkatnaRadio;
    public RadioButton sigmoidalRadio;

    public TextField NazwaText;

    public TextField firstText;
    public TextField secondText;
    public TextField thirdText;
    public TextField FourthText;

    public Button dodajBttn;
    public Button aktualizujBttn;
    public ComboBox<String> miaryCombo;

    private ArrayList<Measures> summaries = new ArrayList<>();
    private HashMap<String, Comparator<Measures>> comparatorHashMap = new HashMap<>();

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Komputerowe Systemy Rozpoznawania: Zadanie 2");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void reloadgui() {
        kwanText.setText("");
        kwalText.setText("");
        sumText.setText("");

        int i = 0;
        for (LinguisticQuantifier lq : Data.lq) {
            if (withoutAbs){
                if (!lq.isabsolute){
                    kwanText.appendText("[" + i + "]" + " - " + lq.name + "\n");
                }
            }
            else{
                kwanText.appendText("[" + i + "]" + " - " + lq.name + "\n");
            }
            i++;
        }
        i = 0;
        for (LinguisticVariable lv : Data.lv) {
            for (Label label : lv.labels) {
                kwalText.appendText("[" + i + "] - " + lv.name + " - " + label.getName() + "\n");
                i++;
            }
        }
        i = 0;
        for (LinguisticVariable lv : Data.lv) {
            for (Label label : lv.labels) {
                sumText.appendText("[" + i + "] - " + lv.name + " - " + label.getName() + "\n");
                i++;
            }
        }
    }

    public void initialize() throws IOException {
        lockelem();
        for (int i =0; i<=11; i++){
            weights.add(0.0);
        }
        comparatorHashMap.put("T1", Comparator.comparingDouble(Measures::degreeOfTruth).reversed());
        comparatorHashMap.put("T2", Comparator.comparingDouble(Measures::degreeOfImprecision).reversed());
        comparatorHashMap.put("T3", Comparator.comparingDouble(Measures::degreeOfCovering).reversed());
        comparatorHashMap.put("T4", Comparator.comparingDouble(Measures::degreeOfAppropriateness).reversed());
        comparatorHashMap.put("T5", Comparator.comparingDouble(Measures::lengthOfSummary).reversed());
        comparatorHashMap.put("T6", Comparator.comparingDouble(Measures::degreeOfQuantifierImprecision).reversed());
        comparatorHashMap.put("T7", Comparator.comparingDouble(Measures::degreeOfQuantifierCardinality).reversed());
        comparatorHashMap.put("T8", Comparator.comparingDouble(Measures::degreeOfSummarizerCardinality).reversed());
        comparatorHashMap.put("T9", Comparator.comparingDouble(Measures::degreeOfQualifierImprecision).reversed());
        comparatorHashMap.put("T10", Comparator.comparingDouble(Measures::degreeOfQualifierCardinality).reversed());
        comparatorHashMap.put("T11", Comparator.comparingDouble(Measures::lengthOfQualifier).reversed());
        comparatorHashMap.put("optymalna", Comparator.comparingDouble(Measures::goodnessOfTheSummary).reversed());


        firstSubjectCombo.getItems().add("NBA");
        firstSubjectCombo.getItems().add("Euroleague");
        firstSubjectCombo.getItems().add("Eurocup");
        firstSubjectCombo.getItems().add("Australian-NBL");
        firstSubjectCombo.getSelectionModel().selectFirst();

        secondSubjectCombo.getItems().add("NBA");
        secondSubjectCombo.getItems().add("Euroleague");
        secondSubjectCombo.getItems().add("Eurocup");
        secondSubjectCombo.getItems().add("Australian-NBL");
        secondSubjectCombo.getSelectionModel().select(1);

        miaryCombo.getItems().add("T1");
        miaryCombo.getItems().add("T2");
        miaryCombo.getItems().add("T3");
        miaryCombo.getItems().add("T4");
        miaryCombo.getItems().add("T5");
        miaryCombo.getItems().add("T6");
        miaryCombo.getItems().add("T7");
        miaryCombo.getItems().add("T8");
        miaryCombo.getItems().add("T9");
        miaryCombo.getItems().add("T10");
        miaryCombo.getItems().add("T11");
        miaryCombo.getItems().add("optymalna");
        miaryCombo.getSelectionModel().selectedItemProperty()
                .addListener((observableValue, oldV, newValue) -> displaySorted(newValue));
        miaryCombo.getSelectionModel().selectFirst();
        updateWeight();
        radioLock();
        //ReaderFile.readFilePlayers("w.xlsx");
        DBLoader dbl = new DBLoader();
        dbl.load();
        Default.def();



        for (LinguisticVariable lv : Data.lv) {
            wybierzZmiennaCombo.getItems().add(lv.name);
        }
        wybierzZmiennaCombo.getSelectionModel().selectFirst();
        reloadgui();
    }

    public void lockelem() {
        if (kwanRadio.isSelected()) {
            wybierzZmiennaCombo.setDisable(true);
            isAbsChckbx.setOpacity(1);
        }
        if (kwalSumRadio.isSelected()) {
            isAbsChckbx.setOpacity(0);
            wybierzZmiennaCombo.setDisable(false);
        }
        if (trojkatnaRadio.isSelected()) {
            firstLabel.setText("a");
            secondLabel.setText("b");
            thirdLabel.setText("c");
            fourthLabel.setText("");
            //thirdText.setOpacity(1);
           // FourthText.setOpacity(0);

        }
        if (trapezoidalnaRadio.isSelected()) {
            firstLabel.setText("a");
            secondLabel.setText("b");
            thirdLabel.setText("c");
            fourthLabel.setText("d");
            //FourthText.setOpacity(1);
            //thirdText.setOpacity(1);
        }
        if (gaussowskaRadio.isSelected()) {
            firstLabel.setText("center");
            secondLabel.setText("width");
            thirdLabel.setText("");
            //thirdText.setOpacity(0);
            fourthLabel.setText("");
            //FourthText.setOpacity(0);
        }

        if (sigmoidalRadio.isSelected()) {
            firstLabel.setText("a");
            secondLabel.setText("b");
            thirdLabel.setText("");
            //thirdText.setOpacity(0);
            fourthLabel.setText("");
            //FourthText.setOpacity(0);
        }

    }

    public void addNewVariable() {
        if (verifyDataOfNewElement()){
            if (kwanRadio.isSelected()) {
                if (trojkatnaRadio.isSelected()) {
                    Data.lq.add(new LinguisticQuantifier(NazwaText.getText(), new TriangularFuzzySet(Double.parseDouble(firstText.getText()), Double.parseDouble(secondText.getText()), Double.parseDouble(thirdText.getText())), isAbsChckbx.isSelected()));
                }
                if (trapezoidalnaRadio.isSelected()) {
                    Data.lq.add(new LinguisticQuantifier(NazwaText.getText(), new TrapezoidalFuzzySet(Double.parseDouble(firstText.getText()), Double.parseDouble(secondText.getText()), Double.parseDouble(thirdText.getText()), Double.parseDouble(FourthText.getText())), isAbsChckbx.isSelected()));
                }
                if (gaussowskaRadio.isSelected()) {
                    Data.lq.add(new LinguisticQuantifier(NazwaText.getText(), new GaussianFuzzySet(Double.parseDouble(firstText.getText()), Double.parseDouble(secondText.getText())), isAbsChckbx.isSelected()));
                }
            }
            if (kwalSumRadio.isSelected()) {
                if (trojkatnaRadio.isSelected()) {
                    Data.lv.get(wybierzZmiennaCombo.getSelectionModel().getSelectedIndex()).labels.add(new Label(Data.lv.get(wybierzZmiennaCombo.getSelectionModel().getSelectedIndex()).ID, NazwaText.getText(), Data.lv.get(wybierzZmiennaCombo.getSelectionModel().getSelectedIndex()).name, new TriangularFuzzySet(Double.parseDouble(firstText.getText()), Double.parseDouble(secondText.getText()), Double.parseDouble(thirdText.getText()))));
                }
                if (trapezoidalnaRadio.isSelected()) {
                    Data.lv.get(wybierzZmiennaCombo.getSelectionModel().getSelectedIndex()).labels.add(new Label(Data.lv.get(wybierzZmiennaCombo.getSelectionModel().getSelectedIndex()).ID, NazwaText.getText(), Data.lv.get(wybierzZmiennaCombo.getSelectionModel().getSelectedIndex()).name, new TrapezoidalFuzzySet(Double.parseDouble(firstText.getText()), Double.parseDouble(secondText.getText()), Double.parseDouble(thirdText.getText()), Double.parseDouble(FourthText.getText()))));

                }
                if (gaussowskaRadio.isSelected()) {
                    Data.lv.get(wybierzZmiennaCombo.getSelectionModel().getSelectedIndex()).labels.add(new Label(Data.lv.get(wybierzZmiennaCombo.getSelectionModel().getSelectedIndex()).ID, NazwaText.getText(), Data.lv.get(wybierzZmiennaCombo.getSelectionModel().getSelectedIndex()).name, new GaussianFuzzySet(Double.parseDouble(firstText.getText()), Double.parseDouble(secondText.getText()))));

                }
            }
        }

        reloadgui();
    }

    public void updateWeight() {

        weights.set(0, Double.parseDouble(T1Text.getText()));
        weights.set(1, Double.parseDouble(T2Text.getText()));
        weights.set(2, Double.parseDouble(T3Text.getText()));
        weights.set(3, Double.parseDouble(T4Text.getText()));
        weights.set(4, Double.parseDouble(T5Text.getText()));
        weights.set(5, Double.parseDouble(T6Text.getText()));
        weights.set(6, Double.parseDouble(T7Text.getText()));
        weights.set(7, Double.parseDouble(T8Text.getText()));
        weights.set(8, Double.parseDouble(T9Text.getText()));
        weights.set(9, Double.parseDouble(T10Text.getText()));
        weights.set(10, Double.parseDouble(T11Text.getText()));
        double sum = weights.stream().reduce((double) 0, Double::sum);
        if (sum>0.99999 && sum < 1.00001){
            Data.weights = weights;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Wagi muszą się sumować do 1. Aktualna suma: " + String.format("%.2f",sum));
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    //System.out.println("Pressed OK.");
                }
            });
        }
        System.out.println("weights sum: "+sum);
    }

    public void radioLock() {
        if (radioPierwszaPierwsza.isSelected()) {
           // kwanIDText.setDisable(false);
            kwalIDText.setText("");
            //kwalIDText.setDisable(true);
            firstSubjectCombo.setDisable(true);
            secondSubjectCombo.setDisable(true);
            withoutAbs = false;
            reloadgui();
        } else if (radioPierwszaDruga.isSelected()) {
           // kwanIDText.setDisable(false);
           // kwalIDText.setDisable(false);
            firstSubjectCombo.setDisable(true);
            secondSubjectCombo.setDisable(true);
            withoutAbs = false;
            reloadgui();
        } else if (radioDrugaPierwsza.isSelected()) {
           // kwanIDText.setDisable(false);
            kwalIDText.setText("");
           // kwalIDText.setDisable(true);
            firstSubjectCombo.setDisable(false);
            secondSubjectCombo.setDisable(false);
            withoutAbs = true;
            reloadgui();
        } else if (radioDrugaDruga.isSelected()) {
           // kwanIDText.setDisable(false);
           // kwalIDText.setDisable(false);
            firstSubjectCombo.setDisable(false);
            secondSubjectCombo.setDisable(false);
            withoutAbs = true;
            reloadgui();
        } else if (radioDrugaTrzecia.isSelected()) {
          //  kwanIDText.setDisable(false);
           // kwalIDText.setDisable(false);
            firstSubjectCombo.setDisable(false);
            secondSubjectCombo.setDisable(false);
            withoutAbs = true;
            reloadgui();
        } else if (radioDrugaCzwarta.isSelected()) {
          //  kwanIDText.setDisable(true);
            kwanIDText.setText("");
            kwalIDText.setText("");
          //  kwalIDText.setDisable(true);
            firstSubjectCombo.setDisable(false);
            secondSubjectCombo.setDisable(false);
            withoutAbs = true;
            reloadgui();
        }
    }

    public boolean verifyDataOfNewElement() {
        if (!NazwaText.getText().equals("")) {
            if (gaussowskaRadio.isSelected()) {
                if (!firstText.getText().equals("") && !secondText.getText().equals("")) {
                    if (Double.parseDouble(firstText.getText()) >= 0) {
                        if (Double.parseDouble(secondText.getText()) > 0) {
                            return true;
                        }
                    }
                }
            } else if (trapezoidalnaRadio.isSelected()) {
                if (!firstText.getText().equals("") && !secondText.getText().equals("") && !thirdText.getText().equals("") && !FourthText.getText().equals("")) {
                    if (Double.parseDouble(firstText.getText()) <= Double.parseDouble(thirdText.getText())) {
                        if (Double.parseDouble(thirdText.getText()) <= Double.parseDouble(FourthText.getText())) {
                            if (Double.parseDouble(FourthText.getText()) <= Double.parseDouble(secondText.getText())) {
                                return true;
                            }
                        }
                    }
                }
            } else if (trojkatnaRadio.isSelected()) {
                if (!firstText.getText().equals("") && !secondText.getText().equals("") && !thirdText.getText().equals("")) {
                    if (Double.parseDouble(firstText.getText()) <= Double.parseDouble(thirdText.getText())) {
                        if (Double.parseDouble(thirdText.getText()) <= Double.parseDouble(secondText.getText())) {
                            return true;
                        }
                    }
                }
            }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Wprowadzono niepoprawne dane.");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    //System.out.println("Pressed OK.");
                }
            });

            return false;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText("Nazwa nowego elementu nie może być pusta.");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                //System.out.println("Pressed OK.");
            }
        });
        return false;
    }

    public void generate() {

        if (!radioDrugaCzwarta.isSelected()){
            if (kwanIDText.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText("Należy podać ID kwantyfikatora.");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        //System.out.println("Pressed OK.");
                    }
                });
                return;
            }
        }

        if (sumIDText.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Należy podać ID sumaryzatora.");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    //System.out.println("Pressed OK.");
                }
            });
            return;
        }



        String[] kwans = kwanIDText.getText().split(",");
        int[] kwanIDs = new int[kwans.length];
        for (int i = 0; i < kwans.length; i++) {
            if (kwans[i] != null && !kwans[i].equals("")) {
                kwanIDs[i] = Integer.parseInt(kwans[i]);
            }
        }
        String[] kwals = kwalIDText.getText().split(",");

        int[] kwalIDs = new int[kwals.length];
        for (int i = 0; i < kwals.length; i++) {
            if (kwals[i] != null && !kwals[i].equals("")) {
                kwalIDs[i] = Integer.parseInt(kwals[i]);
            }

        }
        String[] sums = sumIDText.getText().split(",");
        int[] sumIDs = new int[sums.length];
        for (int i = 0; i < sums.length; i++) {
            sumIDs[i] = Integer.parseInt(sums[i]);
        }
        Arrays.sort(kwanIDs);
        Arrays.sort(kwalIDs);
        Arrays.sort(sumIDs);
        LinguisticQuantifier quan = null;
        for (int i : kwanIDs) {
            quan = Data.lq.get(i);
        }

        if (withoutAbs && quan.isabsolute)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Należy podać poprawny kwantyfikator.");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    //System.out.println("Pressed OK.");
                }
            });
            return;
        }

        ArrayList<Label> sum = new ArrayList<Label>();
        Label kwal = null;
        //podsText.appendText("Kwalifikator: \n");

        for (int i : kwalIDs) {
            int j = 0;
            for (LinguisticVariable lv : Data.lv) {
                for (Label label : lv.labels) {
                    if (i == j) {
                        kwal = label;
                    }
                    j++;
                }
            }
        }
        if (kwals[0] == null || kwals[0].equals("")) {
            kwal = null;
        }
        //podsText.appendText("Sumaryzator: \n");
        for (int i : sumIDs) {
            int j = 0;
            for (LinguisticVariable lv : Data.lv) {
                for (Label label : lv.labels) {
                    if (i == j) {
                        sum.add(label);
                    }
                    j++;
                }
            }
        }
        if (radioPierwszaPierwsza.isSelected() || radioPierwszaDruga.isSelected()) {
            LinguisticSummary ls = new LinguisticSummary(quan, kwal, sum);
            summaries.add(new Measures(ls, Data.weights));
        } else {
            int multiform = 0;
            if (radioDrugaPierwsza.isSelected()) multiform = 1;
            if (radioDrugaDruga.isSelected()) multiform = 2;
            if (radioDrugaTrzecia.isSelected()) multiform = 3;
            if (radioDrugaCzwarta.isSelected()){
                multiform = 4;
                quan = null;
            }
            LinguisticSummary ls = new LinguisticSummary(
                    quan,
                    kwal,
                    firstSubjectCombo.getSelectionModel().getSelectedItem(),
                    secondSubjectCombo.getSelectionModel().getSelectedItem(),
                    sum,
                    multiform
            );
            summaries.add(new Measures(ls, Data.weights));
        }
        displaySorted(miaryCombo.getSelectionModel().getSelectedItem());
        T1Text.setText(Data.weights.get(0).toString());
        T2Text.setText(Data.weights.get(1).toString());
        T3Text.setText(Data.weights.get(2).toString());
        T4Text.setText(Data.weights.get(3).toString());
        T5Text.setText(Data.weights.get(4).toString());
        T6Text.setText(Data.weights.get(5).toString());
        T7Text.setText(Data.weights.get(6).toString());
        T8Text.setText(Data.weights.get(7).toString());
        T9Text.setText(Data.weights.get(8).toString());
        T10Text.setText(Data.weights.get(9).toString());
        T11Text.setText(Data.weights.get(10).toString());

    }
    private void displaySorted(String measure){
       // podsText.clear();
        if(summaries.size() > 1) {
            summaries.sort(comparatorHashMap.get(measure));
        }
        System.out.println("----------------------------------------------");
        for(Measures measures : summaries){
            podsText.appendText(measures.getLinguisticSummary().generate() + measures.toString() + "\n");
        }
    }

    public void clear(){
       // podsText.clear();
        summaries.clear();
    }

    public void export() throws IOException {
        FileWriter fw = new FileWriter("results.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        int count = Integer.parseInt(ostatniePodsText.getText());
        long val = podsText.getText().chars().filter(ch -> ch == '\n').count();
        if (val<count){
            count = (int) val;
        }
        String[] podsumowania = podsText.getText().split("\n");
        for (int i=0; i<count; i++){

            bw.write(podsumowania[i]);
            bw.newLine();
        }
        bw.close();
    }

}
