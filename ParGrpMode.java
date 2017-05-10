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
import java.util.LinkedList;
import java.util.Queue;
import java.util.Deque;

class ParGrpMode implements RaceMode{

    private Time theTimer = new Time();

    private int runNum = 1;

    // This array keeps up to 8 racing racers
    Racer[] racerLanes = new Racer[8];
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
        }

      }
    }

    public void dnf(){
        // Implementation for Group?
    }

    public void newRun(){

    }

    public void endRun(){

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
                s += "Lane " + (i + 1) + " Racer " + tmp.id + " still racing\n";
              }
            } else {
              s += "Lane " + (i + 1) + " Racer " + tmp.id + " time: " + tmp.raceTime + "\n";
            }
          }
        }

        return s;
    }


    public void export(){

    }
}
