/** GrpMode
 *
 */

import com.google.gson.Gson;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Deque;

class GrpMode implements RaceMode{

    private Time theTimer = new Time();

    private int runNum = 1;

    private int finishedCount = 1;

    protected Queue<Racer> waitingQueue = new LinkedList<Racer>();
    protected Queue<Racer> racingQueue = new LinkedList<Racer>();
    // keeps the finishes racers in queues to preserve finished order
    protected Deque<Racer> finishedList = new LinkedList<Racer>();
    protected Queue<Racer> finishedNumbered = new LinkedList<Racer>();

    public GrpMode(Time t){
        theTimer = t;
    }

    public GrpMode(){

    }

    /** Add a racer to the waiting queue */
    public void addRacer(int id){
        waitingQueue.add(new Racer(id));
    }
    /** Handles a sensor trigger on a channel
     * @param ch  int from 1-8 for which channel is triggered
     */
    public void triggerChannel(int ch){
        System.out.println("Triggering" + ch );
        switch(ch){
            case 1:
                start();
                break;
            case 2:
                finish();
                break;
            default:
                break;
        }

    }
    /** Returns all currently racing to the waiting queue */
    public void cancel(){
        while (!waitingQueue.isEmpty()) {
            racingQueue.add(waitingQueue.remove());
        }
        waitingQueue = racingQueue;
        racingQueue = new LinkedList<Racer>();
    }
    /** Triggers a start event */
    public void start(){
        double startTime = theTimer.getTime();
        String startStamp = theTimer.timeStamp();
        // starts every racer in the waiting queue
        for (Racer r : waitingQueue){
            r.startTime = startTime;
            r.startStamp = startStamp;
            racingQueue.add(r);
            System.out.println("starting: " + r.toString());
        }
        waitingQueue.clear();
    }
    /** Triggers a finish event */
    public void finish(){
        if (!racingQueue.isEmpty()) {
            Racer finished = racingQueue.remove();
            finished.endTime = theTimer.getTime();
            finished.endStamp = theTimer.timeStamp();

            finished.calcRaceTime();

            // Round to 2 decimal places
            BigDecimal bd = new BigDecimal(finished.raceTime);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            finished.raceTime = bd.doubleValue();

            // temporarily assigns racer ID to their place.
            // this can get overwritten when bib# assigned
            finished.id = finishedCount++;

            finishedList.add(finished);
        }
    }

    public void dnf(){
        // Implementation for Group?
    }

    /** Assigns this number to a finished racer */
    public void assignNumToRacer(int id){
        if (!finishedList.isEmpty()){
            Racer r = finishedList.remove();
            r.id = id;
            finishedNumbered.add(r);
        }
    }

    public void newRun(){
        finishedCount = 1;
        waitingQueue = new LinkedList<Racer>();
        racingQueue = new LinkedList<Racer>();
        finishedList = new LinkedList<Racer>();
        finishedNumbered = new LinkedList<Racer>();
    }

    public void endRun(){
        // Put on server here
        int place = 0;
        if(!finishedNumbered.isEmpty()) {
            int i = 0;
            for (Racer r : finishedNumbered) {
                Gson g = new Gson();
                if(i == 0) {
                    RacerData data = new RacerData(++place + "", r.id + "", "Jeev Sobs", Time.timeConversion(r.raceTime), "Group");
                    ChronoTimer.sendDataToServer(g.toJson(data));
                    i++;
                }
                else if(i == 1) {
                    RacerData data = new RacerData(++place + "", r.id + "", "Gill Bates", Time.timeConversion(r.raceTime), "Group");
                    ChronoTimer.sendDataToServer(g.toJson(data));
                    i++;
                }
                else if(i == 2) {
                    RacerData data = new RacerData(++place + "", r.id + "", "Tinus Lorvalds", Time.timeConversion(r.raceTime), "Group");
                    ChronoTimer.sendDataToServer(g.toJson(data));
                    i++;
                }
                else if(i == 3) {
                    RacerData data = new RacerData(++place + "", r.id + "", "Rennis Ditchie", Time.timeConversion(r.raceTime), "Group");
                    ChronoTimer.sendDataToServer(g.toJson(data));
                    i++;
                }
                else {
                    RacerData data = new RacerData(++place + "", r.id + "", "", Time.timeConversion(r.raceTime), "Group");
                    ChronoTimer.sendDataToServer(g.toJson(data));
                    i++;
                }
            }
            export();
        }
        finishedCount = 1;
        waitingQueue = new LinkedList<Racer>();
        racingQueue = new LinkedList<Racer>();
        finishedList = new LinkedList<Racer>();
        finishedNumbered = new LinkedList<Racer>();
    }

    public String print(){
        String s = "";
        // Prints all racers, whether they have assigned num or not
        // First merge numbered and un-numbered
        Queue<Racer> merged = new LinkedList<Racer>();
        for (Racer r : finishedNumbered){
            merged.add(r);
        }
        for (Racer r : finishedList){
            merged.add(r);
        }
        for (Racer r : merged){
            s = s + "\n" + r.toString();
        }
        return s;
    }

    public void swap() { }


    public String format() {
        String s = "\n\n---------------\n";

        // prints currently racing
        if (!racingQueue.isEmpty()){
            for (Racer r : racingQueue) {
                BigDecimal bd = new BigDecimal(theTimer.getTime() - r.startTime);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                s += r.id + " " + Time.timeConversion(bd.doubleValue()) + " R\n";
            }
        }
        // prints last finished.
        if (!finishedList.isEmpty()){
            s += "\n" + finishedList.peekLast().id;
            s += " " + Time.timeConversion(finishedList.peekLast().raceTime) + " F\n";
        }
        return s;
    }

    public boolean finishedIsEmpty() {
        return finishedList.isEmpty();
    }


    public boolean allFinished(){
        return (waitingQueue.isEmpty() && racingQueue.isEmpty());
    }


    public void export(){
        // merge all together
        Queue<Racer> merged = new LinkedList<Racer>();
        for (Racer r : finishedNumbered){
            merged.add(r);
        }
        for (Racer r : finishedList){
            merged.add(r);
        }
        // SAVE HERE
        Gson g = new Gson();
        String out = g.toJson(merged);

        Path file = Paths.get(("RUN00" + runNum + ".txt"));
        runNum++;
        try{
            Files.write(file, out.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


