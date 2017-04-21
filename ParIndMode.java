/** ParIndMode
  *
  */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.math.BigDecimal;
import java.math.RoundingMode;

class ParIndMode implements RaceMode{

  private Time theTimer;

  private int runNum = 1;

  private int startedQueue;

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
        startedQueue = 1;
        start();
        break;
      case 3:
        startedQueue = 2;
        start();
        break;
      // ends on ch2 and 4
      case 2:
        finish();
        break;
      case 4:
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
        if (startedQueue == 1){
          racingQueue1.add(tempRacer);
        } else {
          racingQueue2.add(tempRacer);
        }

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

        //store finished racer in finished list
        finishedList.add(finishedRacer);
    }
  }

  public void print(){
    for (Racer r : finishedList) System.out.println(r.toString());
  }

  public void newRun(){
    // set up a new run with empty queues
    waitingQueue = new LinkedList<Racer>();
    racingQueue = new LinkedList<Racer>();
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
    racingQueue     = new LinkedList<Racer>();
    finishedList    = new ArrayList<Racer>();
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
