package Fuzzy.Model;


import java.util.ArrayList;
import java.util.List;

public class BasketballPlayer {

    public String league;
    public String season;
    public String playerName;
    public String team;

   public double gamesPlayedR;
   public double minutesPlayedR;
   public double throwsAccurateR;
   public double throwsAttemptR;

   public double threePointsMade;
   public double threePointsAttempt;
   public double FTM;
   public double FTA;
   public double tournovers;

    public double foulsR;

    public double ORB;
    public double DRB;
    public double REB;


    public double assistsR;
    public double stealsR;
    public double blocksR;
    public double pointsR;
    public double heightR;
    public double weightR;
    public double birthYearR;

    public String nationality;
    public double draftRound;
    public double draftPick;
    public List<Double> resB = new ArrayList<>();

    public BasketballPlayer(){

    };
    public BasketballPlayer(String league, String season, String playerName, String team, double gamesPlayedR, double minutesPlayedR, double throwsAccurateR, double throwsAttemptR, double threePointsMade, double threePointsAttempt, double FTM, double FTA, double tournovers, double foulsR, double ORB, double DRB, double REB, double assistsR, double stealsR, double blocksR, double pointsR, double heightR, double weightR, double birthYearR, String nationality, double draftRound, double draftPick) {
        this.league = league;
        this.season = season;
        this.playerName = playerName;
        this.team = team;
        this.gamesPlayedR = gamesPlayedR;
        this.minutesPlayedR = minutesPlayedR;
        this.throwsAccurateR = throwsAccurateR;
        this.throwsAttemptR = throwsAttemptR;
        this.threePointsMade = threePointsMade;
        this.threePointsAttempt = threePointsAttempt;
        this.FTM = FTM;
        this.FTA = FTA;
        this.tournovers = tournovers;
        this.foulsR = foulsR;
        this.ORB = ORB;
        this.DRB = DRB;
        this.REB = REB;
        this.assistsR = assistsR;
        this.stealsR = stealsR;
        this.blocksR = blocksR;
        this.pointsR = pointsR;
        this.heightR = heightR;
        this.weightR = weightR;
        this.birthYearR = birthYearR;
        this.nationality = nationality;
        this.draftRound = draftRound;
        this.draftPick = draftPick;
    }



   /* public BasketballPlayer(String row) {
        this.league = league;
        this.season = season;
        this.playerName = playerName;
        this.team = team;
        this.gamesPlayedR = gamesPlayedR;
        this.minutesPlayedR = minutesPlayedR;
        this.throwsAccurateR = throwsAccurateR;
        this.throwsAttemptR = throwsAttemptR;
        this.threePointsMade = threePointsMade;
        this.threePointsAttempt = threePointsAttempt;
        this.FTM = FTM;
        this.FTA = FTA;
        this.tournovers = tournovers;
        this.foulsR = foulsR;
        this.ORB = ORB;
        this.DRB = DRB;
        this.REB = REB;
        this.assistsR = assistsR;
        this.stealsR = stealsR;
        this.blocksR = blocksR;
        this.pointsR = pointsR;
        this.heightR = heightR;
        this.weightR = weightR;
        this.birthYearR = birthYearR;
        this.nationality = nationality;
        this.draftRound = draftRound;
        this.draftPick = draftPick;
    }
*/

    public String getLeague() {
        return league;
    }

    public String getSeason() {
        return season;
    }

    public String getPlayerName() {
        return playerName;
    }

    public double getGamesPlayedR() {
        return gamesPlayedR;
    }

    public double getMinutesPlayedR() {
        return minutesPlayedR;
    }

    public double getThrowsAccurateR() {
        return throwsAccurateR;
    }

    public double getThrowsAttemptR() {
        return throwsAttemptR;
    }

    public double getThreePointsMade() {
        return threePointsMade;
    }

    public double getThreePointsAttempt() {
        return threePointsAttempt;
    }

    public double getFTM() {
        return FTM;
    }

    public double getFTA() {
        return FTA;
    }

    public double getTournovers() {
        return tournovers;
    }

    public double getFoulsR() {
        return foulsR;
    }

    public double getORB() {
        return ORB;
    }

    public double getDRB() {
        return DRB;
    }

    public double getREB() {
        return REB;
    }

    public double getAssistsR() {
        return assistsR;
    }

    public double getStealsR() {
        return stealsR;
    }

    public double getBlocksR() {
        return blocksR;
    }

    public double getPointsR() {
        return pointsR;
    }

    public double getHeightR() {
        return heightR;
    }

    public double getWeightR() {
        return weightR;
    }

    public double getBirthYearR() {
        return birthYearR;
    }

    public String getNationality() {
        return nationality;
    }

    public double getDraftRound() {
        return draftRound;
    }

    public double getDraftPick() {
        return draftPick;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "BasketballPlayer{" +
                "league='" + league + '\'' +
                ", season='" + season + '\'' +
                ", playerName='" + playerName + '\'' +
                ", team='" + team + '\'' +
                ", gamesPlayedR=" + gamesPlayedR +
                ", minutesPlayedR=" + minutesPlayedR +
                ", throwsAccurateR=" + throwsAccurateR +
                ", throwsAttemptR=" + throwsAttemptR +
                ", threePointsMade=" + threePointsMade +
                ", threePointsAttempt=" + threePointsAttempt +
                ", FTM=" + FTM +
                ", FTA=" + FTA +
                ", tournovers=" + tournovers +
                ", foulsR=" + foulsR +
                ", ORB=" + ORB +
                ", DRB=" + DRB +
                ", REB=" + REB +
                ", assistsR=" + assistsR +
                ", stealsR=" + stealsR +
                ", blocksR=" + blocksR +
                ", pointsR=" + pointsR +
                ", heightR=" + heightR +
                ", weightR=" + weightR +
                ", birthYearR=" + birthYearR +
                ", nationality='" + nationality + '\'' +
                ", draftRound=" + draftRound +
                ", draftPick=" + draftPick +
                '}';
    }
}

