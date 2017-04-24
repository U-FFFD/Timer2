import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;


public class JUnitTests {
	 ChronoTimer timer = new ChronoTimer();
	 Queue<Racer> testWaitingQueue = new LinkedList<Racer>();
	 Queue<Racer> testRacingQueue = new LinkedList<Racer>();
	 Queue<Racer> testRacingQueue2 = new LinkedList<Racer>();
	 ArrayList<Racer> testFinishedList = new ArrayList<Racer>();

	  @Test
	  public void testPower(){
		  assertEquals(timer.running, false);
		  timer.power();
		  assertEquals(timer.running, true);
	  }
	  
	  @Test
	  public void testAddRacerIND(){
		  timer.power();
		 
		  Racer r = new Racer(711);
		  testWaitingQueue.add(r);
		  
		  timer.setMode("IND");
		  timer.addRacer(711);
		  assertEquals(testWaitingQueue.toString(),((IndMode)timer.mode).waitingQueue.toString());
		  
	  }
	  
	  @Test
	  public void testAddRacerPARIND(){
		  timer.power();
		  Racer r = new Racer(711);
		  testWaitingQueue.add(r);
		  
		  timer.setMode("PARIND");
		  timer.addRacer(711);
		  assertEquals(testWaitingQueue.toString(),((ParIndMode)timer.mode).waitingQueue.toString());
		  
	  }
	  
	  @Test
	  public void testAddRacerGRP(){
		  timer.power();
		  Racer r = new Racer(711);
		  testWaitingQueue.add(r);
		  
		  timer.setMode("GRP");
		  timer.addRacer(711);
		  assertEquals(testWaitingQueue.toString(),((GrpMode)timer.mode).waitingQueue.toString());
		  
	  }
	  
	  @Test
	  public void testStartIND(){
		  timer.power();
		  timer.toggleChannel("1");
		  Racer r = new Racer(711);
		  r.startStamp = "00:00:00.0";
		  testRacingQueue.add(r);
		  
		  timer.addRacer(711);
		  timer.triggerChannel("1");
		  assertEquals(testRacingQueue.toString(),((IndMode)timer.mode).racingQueue.toString());
		  
	  }
	  
	  @Test
	  public void testStartPARIND(){
		  timer.power();
		  timer.toggleChannel("1");
		  timer.toggleChannel("3");
		  Racer r = new Racer(711);
		  Racer r2 = new Racer(333);
		  r.startStamp = "00:00:00.0";
		  r2.startStamp = "00:00:00.0";
		  testRacingQueue.add(r);
		  testRacingQueue2.add(r2);
		  
		  timer.setMode("PARIND");
		  timer.addRacer(711);
		  timer.addRacer(333);
		  timer.triggerChannel("1");
		  timer.triggerChannel("3");
		  assertEquals(testRacingQueue.toString(),((ParIndMode)timer.mode).racingQueue1.toString());
		  assertEquals(testRacingQueue2.toString(),((ParIndMode)timer.mode).racingQueue2.toString());
		  
	  }
	  @Test
	  public void testStartGRP(){
		  timer.power();
		  timer.toggleChannel("1");
		  timer.setMode("GRP");
		  Racer r = new Racer(111);
		  Racer r2 = new Racer(222);
		  Racer r3 = new Racer(333);
		  r.startStamp = "00:00:00.0";
		  r2.startStamp = "00:00:00.0";
		  r3.startStamp = "00:00:00.0";
		  testRacingQueue.add(r);
		  testRacingQueue.add(r2);
		  testRacingQueue.add(r3);
		  
		  timer.addRacer(111);
		  timer.addRacer(222);
		  timer.addRacer(333);
		  timer.triggerChannel("1");
		  assertEquals(testRacingQueue.toString(),((GrpMode)timer.mode).racingQueue.toString());	  
	  }
	    
	  @Test
	  public void testFinish(){
		  timer.power();
		  timer.toggleChannel("1");
		  timer.toggleChannel("2");
		  timer.addRacer(711);
		  timer.triggerChannel("1");
		  timer.triggerChannel("2");
		  Racer r = ((IndMode)timer.mode).finishedList.remove(0);
		  assertTrue(r.startTime >= 0);
		  assertTrue(r.endTime >= r.startTime);
	  }
	  
	  @Test
	  public void testSwap(){
		  timer.power();
		  timer.toggleChannel("1");
		  Racer r = new Racer(111);
		  Racer r2 = new Racer(222);
		  r.startStamp = "00:00:00.0";
		  r2.startStamp = "00:00:00.0";
		  testRacingQueue.add(r2);
		  testRacingQueue.add(r);
		  
		  timer.addRacer(111);
		  timer.addRacer(222);
		  timer.triggerChannel("1");
		  timer.triggerChannel("1");
		  timer.swap();
		  assertEquals(testRacingQueue.toString(),((IndMode)timer.mode).racingQueue.toString());
	  }
	  
	  
	  @Test
	  public void testCancel(){
		  timer.power();
		  timer.toggleChannel("1");
		  Racer r = new Racer(111);
		  Racer r2 = new Racer(222);
		  r.startStamp = "00:00:00.0";
		  testWaitingQueue.add(r);
		  testWaitingQueue.add(r2);
		  
		  timer.addRacer(111);
		  timer.addRacer(222);
		  timer.triggerChannel("1");
		  timer.cancel();
		  assertEquals(testWaitingQueue.toString(),((IndMode)timer.mode).waitingQueue.toString());
		  assertEquals(testRacingQueue.toString(),((IndMode)timer.mode).racingQueue.toString());
	  
	  }
	  @Test
	  public void testNewrun(){
		  timer.power();
		// random data just to make sure newRun() clears it
		  timer.toggleChannel("1");
		  timer.addRacer(111);
		  timer.addRacer(222);       	
		  timer.triggerChannel("1");
		  
		  timer.newRun();
		  assertEquals(testWaitingQueue.toString(),((IndMode)timer.mode).waitingQueue.toString());
		  assertEquals(testRacingQueue.toString(),((IndMode)timer.mode).racingQueue.toString());
		  assertEquals(testFinishedList.toString(),((IndMode)timer.mode).finishedList.toString());
	  }
	  @Test
	  public void testDNF(){
		  timer.power();
		  timer.toggleChannel("1");
		  Racer r = new Racer(711);
		  r.startStamp = "00:00:00.0";
		  r.endStamp = "DNF (Did Not Finish)";
		  testFinishedList.add(r);
		  
		  timer.addRacer(711);
		  timer.triggerChannel("1");
		  timer.dnfRacer();
		  assertEquals(testFinishedList.toString(),((IndMode)timer.mode).finishedList.toString());
	  }
	

}
