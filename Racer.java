class Racer{
    public double startTime;
    public double endTime;
    public double raceTime;
    public String startStamp;
    public String endStamp;
    public int id;

    public Racer(int idNum) {
        id = idNum;
    }

    public String toString(){
        return ("Racer " + id + ":\n  Start: " + startStamp + "\n  End:   " + endStamp + ("\n  Time of Race: " + Time.timeConversion(raceTime)));
    }

    public void calcRaceTime(){
      if (startTime != 0 && endTime != 0){
        raceTime = endTime - startTime;
      }
    }
}
