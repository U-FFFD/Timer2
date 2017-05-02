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

    public String toTable() {
        return "<tr>\n"
                + "<td>" + place + "</td>\n"
                + "<td>" + bibNum + "</td>\n"
                + "<td>" + name + "</td>\n"
                + "<td>" + raceTime + "</td>\n"
                + "<td>" + raceType + "</td>\n"
                + "</tr>";
    }
}