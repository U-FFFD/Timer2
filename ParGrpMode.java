/** ParGrpMode
 *
 */

import com.google.gson.Gson;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

class ParGrpMode implements RaceMode{

    private Time theTimer = new Time();

    private int runNum = 1;

    // This array keeps up to 8 racing racers
    Racer[] racerLanes = new Racer[8];
    // finished list for exported file
    protected ArrayList<Racer> finishedList = new ArrayList<Racer>();
    // Tracks how many initialized racers
    int numRacers = 0;
    boolean racing = false;

    public ParGrpMode(Time t){
        theTimer = t;
    }

    public ParGrpMode(){

    }

    /** Add a racer to the waiting queue */
    public void addRacer(int id){
        if (numRacers < 8){
            racerLanes[numRacers] = new Racer(id);
            numRacers++;
        }
    }

    /** Handles a sensor trigger on a channel
     * @param ch  int from 1-8 for which channel is triggered
     */
    public void triggerChannel(int ch){
        System.out.println("Triggering" + ch );
        if (!racing) {
            if (ch == 1){
                start();
            }
        } else {
            finishLane(ch);
        }
    }
    /** Returns all currently racing to the waiting queue */
    public void cancel(){

    }

    /** Triggers a start event */
    public void start(){
        double startTime = theTimer.getTime();
        String startStamp = theTimer.timeStamp();
        for (int i = 0; i < numRacers; i++){
            racerLanes[i].startTime = startTime;
            racerLanes[i].startStamp = startStamp;
        }
        racing = true;
    }

    /** Triggers a finish event */
    public void finish(){

    }

    public void finishLane(int lane){
        // check that racer exists
        if (lane <= numRacers){
            Racer tmp = racerLanes[lane - 1];
            // check that racer has not finished yet
            if (tmp.endTime == 0.0){
                tmp.endTime = theTimer.getTime();
                tmp.endStamp = theTimer.timeStamp();

                tmp.calcRaceTime();

                BigDecimal bd = new BigDecimal(tmp.raceTime);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                tmp.raceTime = bd.doubleValue();
                finishedList.add(tmp);
            }

        }
    }

    public void dnf(){
        // Implementation for Group?
    }

    public void newRun(){
        theTimer = new Time();
        runNum = 1;
        Racer[] racerLanes = new Racer[8];
        numRacers = 0;
        racing = false;
        finishedList = new ArrayList<>();
    }

    private Racer[] sort(Racer[] racers) {
        Racer[] sorted = racers.clone();
        for (int i = 0; i < sorted.length; i++) {
            for ( int j = 0; j < sorted.length; j++) {
                if((sorted[i].endTime < sorted[j].endTime) && (sorted[i].endTime != sorted[j].endTime)) {
                    Racer temp = sorted[j];
                    sorted[j] = sorted[i];
                    sorted[i] = temp;
                }
            }
        }
        return sorted;
    }

    public void endRun() {
        int place = 0;
        racerLanes = sort(racerLanes);
        ArrayList<Racer> racerList = new ArrayList<Racer>();
        for (Racer r : racerLanes) {
            if (!(r.endTime == 0)) {
                racerList.add(r);
            }
        }
        if (!racerList.isEmpty()) {
            int i = 0;
            for (Racer r : racerList) {
                Gson g = new Gson();
                if (i == 0) {
                    RacerData data = new RacerData(++place + "", r.id + "", "Jeev Sobs", Time.timeConversion(r.raceTime), "Group");
                    ChronoTimer.sendDataToServer(g.toJson(data));
                    i++;
                } else if (i == 7) {
                    RacerData data = new RacerData(++place + "", r.id + "", "Gill Bates", Time.timeConversion(r.raceTime), "Group");
                    ChronoTimer.sendDataToServer(g.toJson(data));
                    i++;
                } else if (i == 5) {
                    RacerData data = new RacerData(++place + "", r.id + "", "Tinus Lorvalds", Time.timeConversion(r.raceTime), "Group");
                    ChronoTimer.sendDataToServer(g.toJson(data));
                    i++;
                } else if (i == 6) {
                    RacerData data = new RacerData(++place + "", r.id + "", "Rennis Ditchie", Time.timeConversion(r.raceTime), "Group");
                    ChronoTimer.sendDataToServer(g.toJson(data));
                    i++;
                } else if (i == 2) {
                    RacerData data = new RacerData(++place + "", r.id + "", "Emmett Wesolowski", Time.timeConversion(r.raceTime), "Group");
                    ChronoTimer.sendDataToServer(g.toJson(data));
                    i++;
                } else if (i == 3) {
                    RacerData data = new RacerData(++place + "", r.id + "", "Kody Fitch", Time.timeConversion(r.raceTime), "Group");
                    ChronoTimer.sendDataToServer(g.toJson(data));
                    i++;
                } else if (i == 4) {
                    RacerData data = new RacerData(++place + "", r.id + "", "AJ Seager", Time.timeConversion(r.raceTime), "Group");
                    ChronoTimer.sendDataToServer(g.toJson(data));
                    i++;
                } else if (i == 1) {
                    RacerData data = new RacerData(++place + "", r.id + "", "Owen Monsma", Time.timeConversion(r.raceTime), "Group");
                    ChronoTimer.sendDataToServer(g.toJson(data));
                    i++;
                } else {
                    RacerData data = new RacerData(++place + "", r.id + "", "", Time.timeConversion(r.raceTime), "Group");
                    ChronoTimer.sendDataToServer(g.toJson(data));
                    i++;
                }
            }
            export();
        }
        finishedList = new ArrayList<Racer>();
    }

    public String print(){
        String s = "";
        for (int i = 0; i < numRacers; i++){
            if (racerLanes[i] != null){
                s += racerLanes[i].toString();
                s += "\n";
            }
        }
        return s;
    }

    public void swap() { }


    public String format() {
        String s = "\n\n---------------\n";

        for (int i = 0; i < numRacers; i++){
            if (racerLanes[i] != null){
                Racer tmp = racerLanes[i];
                if (tmp.endTime == 0){
                    if (!racing){
                        s += "Lane " + (i + 1) + " Racer " + tmp.id + " waiting to race\n";
                    } else {
                        s += "Lane " + (i + 1) + " Racer " + tmp.id + " IS racing\n";
                    }
                } else {
                    s += "Lane " + (i + 1) + " Racer " + tmp.id + " time: " + tmp.raceTime + "\n";
                }
            }
        }

        return s;
    }

    public void export(){
        // SAVE HERE
        Gson g = new Gson();
        String out = g.toJson(finishedList);
        Path file = Paths.get(("RUN00" + runNum + ".txt"));
        ++runNum;
        try {
            Files.write(file, out.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}