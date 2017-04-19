import java.util.ArrayList;

/**
 * Created by Emmett on 4/12/2017.
 */
public class CmdList {
    ArrayList<String> cmds = new ArrayList<String>();
    String state;
    boolean isCmd = false;
    int i;
    public CmdList() {
        state = "BASE";
        this.i = -1;
        cmds.add("EVENT");
        cmds.add("NEW RUN");
        cmds.add("END RUN");
        cmds.add("ADD RACERS");
        cmds.add("PRINT");
        cmds.add("FILE");
        cmds.add("RESET");
        cmds.add("SET TIME");
        cmds.add("DNF");
        cmds.add("CANCEL");
    }
    public String left() {
        if (i-1 < 0) {
            i = cmds.size()-1;
            return cmds.get(i);
        }
        i--;
        return cmds.get(i);
    }
    public String right() {
        if (i+1 == cmds.size()) {
            i = 0;
            return cmds.get(i);
        }
        i++;
        return cmds.get(i);
    }
    public String getCmd() {
        if(i == -1)
            return "";
        return cmds.get(i);
    }
    public boolean isCmd() {
        return isCmd;
    }

    public void setMode(String s) {
        cmds.removeAll(cmds);
        if(s.equals("RACE_TYPE")) {
            state = "MODE";
            cmds.add("GROUP");
            cmds.add("IND");
            cmds.add("PAR IND");
        } else if(s.equals("BASE")) {
            state = "BASE";
            cmds.add("EVENT");
            cmds.add("ADD RACERS");
            cmds.add("NEW RUN");
            cmds.add("END RUN");
            cmds.add("PRINT");
            cmds.add("FILE");
            cmds.add("RESET");
            cmds.add("SET TIME");
            cmds.add("DNF");
            cmds.add("CANCEL");
        } else{}
    }
}
