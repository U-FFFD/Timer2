/*
  CS361
  ChronoTimer
  Sprint 2
  Team U+FFFD:
  * AJ
  * Emmett
  * Kerstin
  * Kody
  * Owen
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ChronoTimer{

    protected boolean running = false;
    protected int     runNum = 0;
    protected static Time    theTimer = new Time();
    protected RaceMode mode = new IndMode(theTimer);

    protected int[]               lastTrig        = new int[2];
    protected boolean[]           channels        = new boolean[8];       // tracks whether channels are enabled
    protected Queue<Racer> 		waitingQueue 	= new LinkedList<Racer>();
    protected Queue<Racer> 		racingQueue     = new LinkedList<Racer>();
    protected ArrayList<Racer> 	finishedList    = new ArrayList<Racer>();


    public ChronoTimer(){

    }

    // Used by simulator to pass in events
    public void sendEvent(Event e, String arg)
    {
        handleEvent(e, arg);
    }

    protected void handleEvent(Event e, String arg){
        switch (e){
            case FILE:
                break;
            case EVENT:
                setMode(arg);
                break;
            case POWER:
                power();
                break;
            case EXIT:
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
                start();
                break;
            case FINISH:
                finish();
                break;
            case PRINT:
                print();
                break;
            default:
                System.out.println("This command not supported yet");
                break;
        }
    }

    protected void parIndStart() {
        if(lastTrig[0] == 0)
            triggerChannel("1");//start 1
        else if(lastTrig[0] == 1)
            triggerChannel("3");//start 3
        else {
            triggerChannel("1");//start 1
        }
    }

    protected void parIndEnd() {
        if(lastTrig[1] == 0)
            triggerChannel("2");//start 1
        else if(lastTrig[1] == 2)
            triggerChannel("4");//start 3
        else {
            triggerChannel("2");//start 1
        }
    }

    protected void power() {
        if (!running){
            running = true;
            reset();
        }
        else{
            running = false;
            theTimer.stop();
        }
    }

    protected void reset() {
        running = false;
        theTimer = new Time();
        mode = new IndMode(theTimer);
        channels = new boolean[8];
        waitingQueue = new LinkedList<Racer>();
        racingQueue = new LinkedList<Racer>();
        finishedList = new ArrayList<Racer>();
        theTimer.stop();
        theTimer.start();

    }

    protected void cancel() {
        while (!waitingQueue.isEmpty()) {
            racingQueue.add(waitingQueue.remove());
        }
        waitingQueue = racingQueue;
        racingQueue = new LinkedList<Racer>();
    }

    protected void setMode(String inmode){
        if (inmode != null){
          switch (inmode){
            case "IND":
              mode = new IndMode(theTimer);
              //mode = new IndMode();
              break;
            case "PARIND":
              //mode = new ParIndMode();
              break;
            case "GRP":
              //mode = new GrpMode();
              break;
            case "PARGRP":
              //mode = new ParGrpMode();
          }
        }
    }

    protected void setTime(String hms) {
        theTimer.stop();
        // may need to check form!!
        theTimer.setTime(hms);
        theTimer.start();
    }

    protected void addRacer(int id) {
      mode.addRacer(id);
    }

    protected void toggleChannel(String ch){
        // parse string to int, converts range 1-8 to 0-7
        int channel = Integer.parseInt(ch) - 1;
        // toggles that channel
        channels[channel] = !channels[channel];
    }

    protected void triggerChannel(String ch){
        int channel = Integer.parseInt(ch);

        // if channel is active, trigger it in mode
        if (channels[channel - 1]) {
          mode.triggerChannel(channel);
        }
    }

    protected void start(){
      mode.start();
    }

    protected void dnfRacer() {
      mode.dnf();
    }

    protected void newRun(){
      mode.newRun();
    }

    protected void endRun(){
      mode.endRun();
    }

    protected void finish(){
      mode.finish();
    }

    public String print(){
      return mode.print();
    }
}
