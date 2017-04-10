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

public class ChronoTimer{

    private boolean running = false;
    private int     runNum = 0;
    private static Time    theTimer = new Time();
    private RaceMode mode = new IndMode(theTimer);

    private int[]               lastTrig        = new int[2];
    private boolean[]           channels        = new boolean[8];       // tracks whether channels are enabled
    private Queue<Racer> 		waitingQueue 	= new LinkedList<Racer>();
    private Queue<Racer> 		racingQueue     = new LinkedList<Racer>();
    private ArrayList<Racer> 	finishedList    = new ArrayList<Racer>();


    public ChronoTimer(){

    }

    // Used by simulator to pass in events
    public void sendEvent(Event e, String arg)
    {
        handleEvent(e, arg);
    }

    private void handleEvent(Event e, String arg){
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
        mode = new IndMode(theTimer);
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

    private void setMode(String inmode){
        if (inmode != null){
          switch (inmode){
            case "IND":
              mode = new IndMode(theTimer);
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

    private void setTime(String hms) {
        theTimer.stop();
        // may need to check form!!
        theTimer.setTime(hms);
        theTimer.start();
    }

    private void addRacer(int id) {
      mode.addRacer(id);
    }

    private void toggleChannel(String ch){
        // parse string to int, converts range 1-8 to 0-7
        int channel = Integer.parseInt(ch) - 1;
        // toggles that channel
        channels[channel] = !channels[channel];
    }

    private void triggerChannel(String ch){
        int channel = Integer.parseInt(ch);

        // if channel is active, trigger it in mode
        if (channels[channel - 1]) {
          mode.triggerChannel(channel);
        }
    }

    private void start(){
      mode.start();
    }

    private void dnfRacer() {
      mode.dnf();
    }

    private void newRun(){
      mode.newRun();
    }

    private void endRun(){
      mode.endRun();
    }

    private void finish(){
      mode.finish();
    }

    public void print(){
      mode.print();
    }
}
