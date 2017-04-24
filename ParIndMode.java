/** ParIndMode
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
import java.util.LinkedList;
import java.util.Queue;

class ParIndMode implements RaceMode{

    private Time theTimer;

    private int runNum = 1;

    private enum Track{
        TRACK_ONE,
        TRACK_TWO
    }

    private Track startedQueue;
    private Track finishedQueue;

    private Queue<Racer> waitingQueue = new LinkedList<Racer>();
    private Queue<Racer> racingQueue1 = new LinkedList<Racer>();
    private Queue<Racer> racingQueue2 = new LinkedList<Racer>();
    private ArrayList<Racer> finishedList = new ArrayList<Racer>();

    public ParIndMode(Time t){
        theTimer = t;
    }

    /** Add a racer to the waiting queue */
    public void addRacer(int id){
        waitingQueue.add(new Racer(id));
    }
    /** Handles a sensor trigger on a channel
     * @param ch  int from 1-8 for which channel is triggered
     */
    public void triggerChannel(int ch){
        switch (ch){
            // starts on ch1 and 3
            case 1:
                startedQueue = Track.TRACK_ONE;
                start();
                break;
            case 3:
                startedQueue = Track.TRACK_TWO;
                start();
                break;
            // ends on ch2 and 4
            case 2:
                finishedQueue = Track.TRACK_ONE;
                finish();
                break;
            case 4:
                finishedQueue = Track.TRACK_TWO;
                finish();
                break;
            // other channels do nothing
            default:
                break;
        }

    }
    /** Returns the current racer to the waiting queue */
    public void cancel(){
        // cancels current racers in both tracks
        // put all the people into the same racing queue
        while (!racingQueue2.isEmpty()) {
            racingQueue1.add(racingQueue2.remove());
        }
        while (!waitingQueue.isEmpty()) {
            racingQueue1.add(waitingQueue.remove());
        }
        // convert that full racing queue to the waiting queue
        waitingQueue = racingQueue1;
        racingQueue1 = new LinkedList<Racer>();
        racingQueue2 = new LinkedList<Racer>();
    }

    /** Triggers a start event */
    public void start(){
        // moves racer from waitingQueue to the currently racing queue and sets their start time.
        if (!waitingQueue.isEmpty()) {
            Racer tempRacer = waitingQueue.remove();
            tempRacer.startTime = theTimer.getTime();
            tempRacer.startStamp = theTimer.timeStamp();
            switch (startedQueue){
                case TRACK_ONE:
                    racingQueue1.add(tempRacer);
                case TRACK_TWO:
                    racingQueue2.add(tempRacer);
                default:
                    break;
            }
        }
    }

    /** Triggers a finish event */
    public void finish(){
        Racer finishedRacer;
        // remove top racer from whatever queue finish was on
        switch (finishedQueue) {
            case TRACK_ONE:
                if (!racingQueue1.isEmpty()){
                    finishedRacer = racingQueue1.remove();
                } else {
                    return;
                }
                break;
            case TRACK_TWO:
                if (!racingQueue2.isEmpty()){
                    finishedRacer = racingQueue2.remove();
                } else {
                    return;
                }
                break;
            default:
                return;
        }
        // set their finish time
        finishedRacer.endTime = theTimer.getTime();
        finishedRacer.endStamp = theTimer.timeStamp();
        finishedRacer.raceTime = finishedRacer.endTime - finishedRacer.startTime;

        // Round to 2 decimal places. (Hundredths of second)
        BigDecimal bd = new BigDecimal(finishedRacer.raceTime);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        finishedRacer.raceTime = bd.doubleValue();

        //store finished racer in finished list
        finishedList.add(finishedRacer);
    }

    public String print(){
        String s = "";
        for (Racer r : finishedList){
            s = s + "\n" + r.toString();
        }
        return s;
    }

    public String format() {


        return "";
    }

    public void export(){
        // SAVE HERE
        Gson g = new Gson();
        String out = g.toJson(finishedList);

        Path file = Paths.get(("RUN00" + runNum + ".txt"));
        try {
            Files.write(file, out.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newRun(){
        // set up a new run with empty queues
        waitingQueue = new LinkedList<Racer>();
        racingQueue1 = new LinkedList<Racer>();
        racingQueue2 = new LinkedList<Racer>();
        finishedList = new ArrayList<Racer>();
    }

    public void endRun(){
        // SAVE HERE
        ++runNum;
        Gson g = new Gson();
        String out = g.toJson(finishedList);

        Path file = Paths.get(("RUN00" + runNum + ".txt"));
        try {
            Files.write(file, out.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ends the run, clearing memory n stuff
        waitingQueue    = new LinkedList<Racer>();
        racingQueue1    = new LinkedList<Racer>();
        racingQueue2    = new LinkedList<Racer>();
        finishedList    = new ArrayList<Racer>();
    }

    /** Which queue should I DNF from? */
    public void dnf(){
        if(!racingQueue1.isEmpty()) {
            Racer dnfRacer = racingQueue1.remove();

            // set end time and race time to negative values
            dnfRacer.endTime = 0;
            dnfRacer.endStamp = "DNF (Did Not Finish)";
            dnfRacer.raceTime = 0;

            // add DNF racer to finished list
            finishedList.add(dnfRacer);
        }
    }
}