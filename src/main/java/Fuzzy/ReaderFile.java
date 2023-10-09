package Fuzzy;

import Fuzzy.Model.BasketballPlayer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Double.parseDouble;

public class ReaderFile {


    public static void readFilePlayers(String path) throws IOException {
        //String excelFilePath = "players_stats_by_season_full_details_to_columns_reduced_weight.xlsx";
        //String excelFilePath = "w.xlsx";
        String excelFilePath = path;
        ArrayList<BasketballPlayer> players = new ArrayList<>();


        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        List<String> cells = new ArrayList<String>();
        Row nextRow = iterator.next();
        while (iterator.hasNext()) {
            nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            //Cell cell;

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String tmp = cell.toString();
                //System.out.prdouble(cell );

                //if(tmp.isEmpty())
                //  cells.add("0");

                cells.add(tmp);

            }

            String league = cells.get(0);
            String season = cells.get(1);
            String playerName = cells.get(2);
            String team = cells.get(3);

            int gamesPlayedR = (int) parseDouble(cells.get(4));
            //System.out.println("4: " + cells.get(4));
            double minutesPlayedR = parseDouble(cells.get(5)); //dd
            int throwsAccurateR = (int) parseDouble(cells.get(6));
            int throwsAttemptR = (int) parseDouble(cells.get(7));

            int threePodoublesMade = (int) parseDouble(cells.get(8));
            int threePodoublesAttempt = (int) parseDouble(cells.get(9));
            int FTM = (int) parseDouble(cells.get(10));
            int FTA = (int) parseDouble(cells.get(11));
            int tournovers = (int) parseDouble(cells.get(12));

            int foulsR = (int) parseDouble(cells.get(13));

            int ORB = (int) parseDouble(cells.get(14));
            int DRB = (int) parseDouble(cells.get(15));
            int REB = (int) parseDouble(cells.get(16));


            int assistsR = (int) parseDouble(cells.get(17));
            int stealsR = (int) parseDouble(cells.get(18));
            int blocksR = (int) parseDouble(cells.get(19));
            int podoublesR = (int) parseDouble(cells.get(20));
            int heightR = (int) parseDouble(cells.get(21));
            int weightR = (int) parseDouble(cells.get(22));
            int birthYearR = (int) parseDouble(cells.get(23));

            String nationality = cells.get(24);
            //int draftRound = (int) parseDouble(cells.get(25));
            // int draftPick = (int) parseDouble(cells.get(26));


            players.add(new BasketballPlayer(league, season, playerName, team, gamesPlayedR, minutesPlayedR, throwsAccurateR, throwsAttemptR,
                    threePodoublesMade, threePodoublesAttempt, FTM, FTA, tournovers, foulsR, ORB, DRB, REB, assistsR, stealsR, blocksR,
                    podoublesR,birthYearR, heightR, weightR, "s", 0, 0));

            //System.out.prdoubleln();


        }

        workbook.close();
        inputStream.close();

        Data.basketballPlayers = players;

    }




}