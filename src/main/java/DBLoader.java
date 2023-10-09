import Fuzzy.Data;
import Fuzzy.Model.BasketballPlayer;

import java.sql.*;
import java.util.ArrayList;

public class DBLoader {
    public void load(){
        ArrayList<BasketballPlayer> basketballPlayers = new ArrayList<>();
       /* String connectionUrl =
                "jdbc:sqlserver://localhost\\SQLFULL:1433;"
                        + "database=BasketPlayers;"
                        + "user=test11;"
                        + "password=test11;"
                        + "encrypt=true;"
                        + "trustServerCertificate=true;"
                        + "loginTimeout=15;";*/

        String connectionUrl = "jdbc:sqlserver://DESKTOP-1PTGG58;Database=BasketPlayers";
        String user="test11";
        String password="test11";

        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(connectionUrl, user, password);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * from [players]";
            resultSet = statement.executeQuery(selectSql);
            int x=0;
            // Print results from select statement

            while (resultSet.next()) {
                BasketballPlayer basketballPlayer = new BasketballPlayer();
                basketballPlayer.league = resultSet.getString(1);
                basketballPlayer.season = resultSet.getString(2);
                basketballPlayer.playerName = resultSet.getString(3);
                basketballPlayer.team = resultSet.getString(4);
                basketballPlayer.gamesPlayedR = resultSet.getDouble(5);
                basketballPlayer.minutesPlayedR = resultSet.getDouble(6);
                basketballPlayer.throwsAccurateR = resultSet.getDouble(7);
                basketballPlayer.throwsAttemptR = resultSet.getDouble(8);
                basketballPlayer.threePointsMade = resultSet.getDouble(9);
                basketballPlayer.threePointsAttempt = resultSet.getDouble(10);
                basketballPlayer.FTM = resultSet.getDouble(11);
                basketballPlayer.FTA = resultSet.getDouble(12);
                basketballPlayer.tournovers = resultSet.getDouble(13);
                basketballPlayer.foulsR = resultSet.getDouble(14);
                basketballPlayer.ORB = resultSet.getDouble(15);
                basketballPlayer.DRB = resultSet.getDouble(16);
                basketballPlayer.REB = resultSet.getDouble(17);
                basketballPlayer.assistsR = resultSet.getDouble(18);
                basketballPlayer.stealsR = resultSet.getDouble(19);
                basketballPlayer.blocksR = resultSet.getDouble(20);
                basketballPlayer.pointsR = resultSet.getDouble(21);
                basketballPlayer.heightR = resultSet.getDouble(24);
                basketballPlayer.weightR = resultSet.getDouble(23);
                basketballPlayer.birthYearR = resultSet.getDouble(22);
                basketballPlayer.nationality = resultSet.getString(25);
                basketballPlayer.draftRound = resultSet.getDouble(26);
                basketballPlayer.draftPick = resultSet.getDouble(27);

                basketballPlayer.resB.add(basketballPlayer.gamesPlayedR);
                basketballPlayer.resB.add(basketballPlayer.throwsAccurateR);
                basketballPlayer.resB.add(basketballPlayer.throwsAttemptR);
                basketballPlayer.resB.add(basketballPlayer.foulsR);
                basketballPlayer.resB.add(basketballPlayer.assistsR);
                basketballPlayer.resB.add(basketballPlayer.stealsR);
                basketballPlayer.resB.add(basketballPlayer.blocksR);
                basketballPlayer.resB.add(basketballPlayer.pointsR);
                basketballPlayer.resB.add(basketballPlayer.birthYearR);
                basketballPlayer.resB.add(basketballPlayer.weightR);
                basketballPlayer.resB.add(basketballPlayer.heightR);






                basketballPlayers.add(basketballPlayer);

                System.out.println("Koszykarz: " + basketballPlayer.toString());
                System.out.println(x++);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        Data.basketballPlayers=basketballPlayers;

    }



}
