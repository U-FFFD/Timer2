/*
  CS361
  ChronoTimer
  Sprint 1
  Team U+FFFD:
  * AJ
  * Emmett
  * Kerstin
  * Kody
  * Owen
*/

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ChronoTimer{

    public enum Mode{
        IND,
        PARIND,
        GRP,
        PARGRP
    }
    // enum defines the event commands
    private boolean running = false;
    private Time theTimer;
    // default to IND mode
    private Mode mode = Mode.IND;
    // tracks whether channels are enabled
    private boolean[] channels = new boolean[8];

    private Queue<Racer> 		waitingQueue 	 = new LinkedList<Racer>();
    private Queue<Racer> 		racingQueue = new LinkedList<Racer>();
    private ArrayList<Racer> 	finishedList = new ArrayList<Racer>();
    private int[]               lastTrig = new int[2];


    public ChronoTimer(){
        theTimer = new Time();
    }

    // Used by simulator to pass in events
    public void sendEvent(Event e, String arg)
    {
        // TODO: Check here if the Event needs an arg?
        handleEvent(e, arg);
    }

    private void handleEvent(Event e, String arg){
        switch (e){
            case EVENT:
                setMode(arg);
                break;
            case POWER:
                power();
                break;
            case RESET:
                reset();
                break;
            case TIME:
                setTime(arg);
                break;
            case NEWRUN:
                newRun();
                break;
            case ENDRUN:
                endRun();
                break;
            case NUM:
                addRacer(Integer.parseInt(arg));
                break;
            case DNF:
                dnfRacer();
                break;
            case CANCEL:
                cancel();
                break;
            case TOG:
                toggleChannel(arg);
                break;
            case TRIG:
                triggerChannel(arg);
                break;
            case START:
                if (this.mode.name().equals("PARIND")) {
                    parIndStart();
                }
                else
                    triggerChannel("1");
                break;
            case FINISH:
                if (this.mode.name().equals("PARIND")) {
                    parIndEnd();
                }
                else
                    triggerChannel("2");
                break;
            case PRINT:
                print();
                break;
            default:
                System.out.println("This command not supported yet");
                break;
        }
    }

    private void parIndStart() {
        if(lastTrig[0] == 0)
            triggerChannel("1");//start 1
        else if(lastTrig[0] == 1)
            triggerChannel("3");//start 3
        else {
            triggerChannel("1");//start 1
        }
    }

    private void parIndEnd() {
        if(lastTrig[1] == 0)
            triggerChannel("2");//start 1
        else if(lastTrig[1] == 2)
            triggerChannel("4");//start 3
        else {
            triggerChannel("2");//start 1
        }
    }

    private void power() {
        if (!running){
            running = true;
            reset();
        }
        else{
            running = false;
            theTimer.stop();
        }
    }

    private void reset() {
        running = false;
        theTimer = new Time();
        mode = Mode.IND;
        channels = new boolean[8];
        waitingQueue = new LinkedList<Racer>();
        racingQueue = new LinkedList<Racer>();
        finishedList = new ArrayList<Racer>();
        theTimer.stop();
        theTimer.start();

    }

    private void cancel() {
        while (!waitingQueue.isEmpty()) {
            racingQueue.add(waitingQueue.remove());
        }
        waitingQueue = racingQueue;
        racingQueue = new LinkedList<Racer>();
    }

    private void setMode(String mode){
        if (mode != null){
            for (Mode m : Mode.values()){
                if (m.name().equals(mode)){
                    System.out.println("Event: " + m.name());
                    this.mode = m;
                }
            }
        }
    }

    public void setTime(String hms) {
        theTimer.stop();
        // may need to check form!!
        theTimer.setTime(hms);
        theTimer.start();
    }

    public void addRacer(int id) {
        waitingQueue.add(new Racer(id));
    }

    private void toggleChannel(String ch){
        // parse string to int, converts range 1-8 to 0-7
        int channel = Integer.parseInt(ch) - 1;
        // toggles that channel
        channels[channel] = !channels[channel];
    }

    private void triggerChannel(String ch){
        // parse string to int, converts range 1-8 to 0-7
        int channel = Integer.parseInt(ch) - 1;

        // checks if the channel is active
        if (channels[channel]) {
            channel = channel+1; // must add one back for %2 to work
            //starts a racer if the channel is odd
            if (channel%2 == 1){
                if(waitingQueue.isEmpty()){return;}
                startRacer();
                lastTrig[0] = channel;
                System.out.println("STARTED ON CHANNEL: " + channel);
            }
            //ends a racer if the channel is even
            else{
                if(racingQueue.isEmpty()){return;}
                finishRacer();
                lastTrig[1] = channel;
                System.out.println("FINISHED ON CHANNEL: " + channel);
            }
        }
    }

    private void startRacer(){
        // moves racer from waitingQueue to the currently racing queue and sets their start time.
        if (!waitingQueue.isEmpty()) {
            Racer tempRacer = waitingQueue.remove();
            tempRacer.startTime = theTimer.getTime();
            tempRacer.startStamp = theTimer.timeStamp();
            racingQueue.add(tempRacer);
        }
    }

    public void dnfRacer() {
        // remove top racer from queue
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

    private void newRun(){
        // set up a new run with empty queues
        waitingQueue = new LinkedList<Racer>();
        racingQueue = new LinkedList<Racer>();
        finishedList = new ArrayList<Racer>();
    }

    private void endRun(){
        // ends the run, clearing memory n stuff
        waitingQueue = new LinkedList<Racer>();
        racingQueue = new LinkedList<Racer>();
        finishedList = new ArrayList<Racer>();
    }

    private void finishRacer(){
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
        for (Racer r : finishedList){
            System.out.println(r.toString());
        }
    }

    // inner class for encapsulating a racer's data
    private class Racer{
        public double startTime;
        public String startStamp;
        public String endStamp;
        public double endTime;
        public double raceTime;
        public String timeStamp;
        public int id;

        // TODO create state of racer.

        public Racer(int idNum) {
            id = idNum;
        }

        private String timeConversion(double exactSeconds) {
            double modSeconds = exactSeconds % 60;
            int intSec = (int) exactSeconds;
            int mins = (intSec/60)%60;
            int hours = (mins/60) %24;

            return hours + ":" + mins + ":" + modSeconds;
        }

        public String toString(){
            return ("Racer " + id + ":\n  Start: " + startStamp + "\n " + "\n  End:   " + endStamp + "\n" + ("\n  Time of Race: " + timeConversion(raceTime)));
        }
    }
}