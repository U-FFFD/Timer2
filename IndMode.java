/** IndMode
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

class IndMode implements RaceMode{

    private Time theTimer = new Time();

    private int runNum = 1;

    protected Queue<Racer> waitingQueue = new LinkedList<Racer>();
    protected Queue<Racer> racingQueue = new LinkedList<Racer>();
    protected ArrayList<Racer> finishedList = new ArrayList<Racer>();

    private boolean toSwap = false;
    private Racer swapHold = null;

    public IndMode(Time t){
        theTimer = t;
    }

    public IndMode(){

    }

    /** Add a racer to the waiting queue */
    public void addRacer(int id){

        System.out.println("Adding" + id );waitingQueue.add(new Racer(id));
    }
    /** Handles a sensor trigger on a channel
     * @param ch  int from 1-8 for which channel is triggered
     */
    public void triggerChannel(int ch){
        System.out.println("Triggering" + ch );
        switch (ch){
            // starts on ch1
            case 1:
                start();
                break;
            // ends on ch2
            case 2:
                finish();
                break;
            // other channels do nothing
            default:
                break;
        }

    }
    /** Returns the current racer to the waiting queue */
    public void cancel(){
        while (!waitingQueue.isEmpty()) {
            racingQueue.add(waitingQueue.remove());
        }
        waitingQueue = racingQueue;
        racingQueue = new LinkedList<Racer>();
    }
    /** Triggers a start event */
    public void start(){
        // moves racer from waitingQueue to the currently racing queue and sets their start time.
        if (!waitingQueue.isEmpty()) {
            Racer tempRacer = waitingQueue.remove();
            tempRacer.startTime = theTimer.getTime();
            tempRacer.startStamp = theTimer.timeStamp();
            racingQueue.add(tempRacer);
        }
    }
    /** Triggers a finish event */
    public void finish(){
        // remove top racer from queue
        if(!racingQueue.isEmpty()) {
            Racer finishedRacer = racingQueue.remove();

            // set their finish time
            finishedRacer.endTime = theTimer.getTime();
            finishedRacer.endStamp = theTimer.timeStamp();
            finishedRacer.raceTime = finishedRacer.endTime - finishedRacer.startTime;

            // Round to 2 decimal places. (Hundredths of second)
            BigDecimal bd = new BigDecimal(finishedRacer.raceTime);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            finishedRacer.raceTime = bd.doubleValue();
            finishedList.add(finishedRacer);
        }
    }

    public void swap(){
        if(racingQueue.size() >= 2){
            Queue<Racer> tempRacingQueue = new LinkedList<Racer>();

            Racer nextToFinish = racingQueue.remove();
            Racer secondToFinish = racingQueue.remove();

            //second racer to finish moves to first
            tempRacingQueue.add(secondToFinish);
            tempRacingQueue.add(nextToFinish);

            for (Racer r : racingQueue) tempRacingQueue.add(r);
            racingQueue.removeAll(racingQueue);
            racingQueue.addAll(tempRacingQueue);
        }
    }


    public String print(){
        String s = "";
        for (Racer r : finishedList)  {
            s =  s + "\n" + r.toString();
        }
        return s;
    }

    public void newRun(){
        // set up a new run with empty queues
        waitingQueue = new LinkedList<Racer>();
        racingQueue = new LinkedList<Racer>();
        finishedList = new ArrayList<Racer>();
    }

    public void endRun(){
        export();
        ++runNum;
        // ends the run, clearing memory n stuff
        waitingQueue    = new LinkedList<Racer>();
        racingQueue     = new LinkedList<Racer>();
        finishedList    = new ArrayList<Racer>();


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

    public String format() {
        String output = "Racers: ";
        if (!waitingQueue.isEmpty()) {
            Object[] objects = waitingQueue.toArray();
            Racer[] racers = new Racer[objects.length];
            for (int i = 0; i < objects.length; i++) {
                racers[i] = (Racer) objects[i];
            }
            if (racers.length >= 3)
                output = "\n" + racers[racers.length - 3].id + "            " + "00:00:00.00" + "\n" + racers[racers.length - 2].id + "             " + "00:00:00.00" + "\n" + racers[racers.length - 1].id + "             " + "00:00:00.00";
            else if (racers.length == 2) {
                output = "\n" + racers[racers.length - 2].id + "             " + "00:00:00.00" + "\n" + racers[racers.length - 1].id + "             " + "00:00:00.00";
            } else
                output = "\n" + racers[0].id + "         " + "00:00:00.00";
            output += "\n";
        }
        // That handles the queue.
        // Now current racers.

        if(!racingQueue.isEmpty()) {
            Object[] racingObjects = racingQueue.toArray();
            Racer[] curRacers = new Racer[racingObjects.length];
            for (int i = 0; i < racingObjects.length; i++) {
                curRacers[i] = (Racer) racingObjects[i];
            }
            for (Racer r : curRacers) {
                BigDecimal bd = new BigDecimal((theTimer.getTime() - r.startTime));
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                output += "\n" + r.id + "               " + theTimer.timeConversion(bd.doubleValue()) + "    R";
            }
        }

        // Now just the last racer to finish. "
        if(!finishedList.isEmpty())
            output += "\n\n" + finishedList.get(finishedList.size()-1) .id + "              " + finishedList.get(finishedList.size()-1).raceTime + "   F";

        return output;
    }

  public void dnf(){
    if(!racingQueue.isEmpty()) {
        Racer dnfRacer = racingQueue.remove();

            // set end time and race time to negative values
            dnfRacer.endTime = 0;
            dnfRacer.endStamp = "DNF (Did Not Finish)";
            dnfRacer.raceTime = 0;

            // add DNF racer to finished list
            finishedList.add(dnfRacer);
        }
    }
}
