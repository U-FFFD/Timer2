public class RacerData {
    private String name;
    private String place;
    private String bibNum;
    private String raceTime;
    private String raceType;

    public RacerData() {}

    public RacerData(String place, String bib, String name, String time, String type) {
        this.name = name;
        this.place = place;
        this.bibNum = bib;
        this.raceTime = time;
        this.raceType = type;
    }

    public String toString() {
        return  place + " " + bibNum + "; " + name + " " + raceTime + " " + raceType + "\n";
    }
}