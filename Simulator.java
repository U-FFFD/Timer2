import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Simulator{

    private boolean running = true;
    private ChronoTimer theTimer = new ChronoTimer();

    public void listen(){
        Scanner sc = new Scanner(System.in);
        String[] split = new String[2];
        Event inputEvent = null;
        int arg = -1;

        System.out.println("Welcome to ChronoTimer Simulator\nEnter FILE <filename> to load, or type commands for manual mode");

        while (running){
            // wait for a valid input
            while (inputEvent == null){
                split = parse(sc.nextLine());

                // convert the string to an event
                inputEvent = strToEvent(split[0]);
            }
            if (inputEvent == Event.EXIT){
                System.exit(0);
            }
            if (inputEvent == Event.FILE){
                runFile(split[1]);
            }
            // if there is no arg value, was giving array out of bounds
            if(split.length == 2){
                theTimer.sendEvent(inputEvent, split[1]);
            } else {
                theTimer.sendEvent(inputEvent, null);
            }
            inputEvent = null;
        }
    }

    private String[] parse(String input){
        // splits line at spaces
        return input.split("\\s+");
    }

    private Event strToEvent(String s){
        // checks if string is in event enum
        for (Event e : Event.values()){
            if (e.name().equals(s)){
                return e;
            }
        }

        return null;
    }

    private void runFile(String filename){
        long currTime = 0;
        long nextTime = 0;
        String path = "testfiles/";
        String[] split = new String[3];
        try{
            File file = new File(path + filename);
            Scanner sc = new Scanner(file);
            estimatedCompletionTime(file);
            // handle each line of the file
            while (sc.hasNextLine()){
                String currLine = sc.nextLine();
                System.out.println(currLine);
                split = parse(currLine);
                // for realtime execution, waits length of each timestamp
                nextTime = parseHMS(split[0]);
                // if its the first line, no delay
                if(currTime == 0){
                    currTime = nextTime;
                }
                try{
                    Thread.sleep((nextTime - currTime));
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }
                Event e = strToEvent(split[1]);
                // System.out.println(e.name());
                if (e == Event.EXIT){
                    System.exit(0);
                }
                if(split.length == 3){
                    theTimer.sendEvent(e, split[2]);
                } else {
                    theTimer.sendEvent(e, null);
                }

                // advance the time tracker
                currTime = nextTime;
            }
        }catch(FileNotFoundException ex){
            //ex.printStackTrace();
            System.out.println("File Not Found.");
        }
        System.out.println("File finished executing");
        return;
    }

    private void estimatedCompletionTime(File f){
        try{
            Scanner sc = new Scanner(f);
            String firstLine = sc.nextLine();
            String lastLine = sc.nextLine();

            while (sc.hasNextLine()){
                lastLine = sc.nextLine();
            }

            String[] splitFirstLine = parse(firstLine);
            String[] splitLastLine = parse(lastLine);

            long estTimeSec = parseHMS(splitLastLine[0]) - parseHMS(splitFirstLine[0]);

            float estTimeHrs = estTimeSec / 3600;

            System.out.println("Estimated completion time: " + estTimeHrs + " hour(s)");
        } catch(FileNotFoundException ex){
            ex.printStackTrace();
        }

    }

    private long parseHMS(String hms){
        //TODO: parses a timestamp in the format of hh:mm:ss(.0)
        String[] split = hms.split("[:.]");
        int[] times = new int[split.length];
        // converts strings to ints
        for (int i = 0; i < split.length; i++){
            times[i] = Integer.parseInt(split[i]);
        }
        return 3600 * times[0] + 60 * times[1] + times[2];
    }

   // public static void main(String[] args){
   //     Simulator sim = new Simulator();
   //    sim.listen();
   // }
}
