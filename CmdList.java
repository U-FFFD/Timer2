/**
 * Created by Emmett on 4/12/2017.
 */
public class CmdList {
    String[] cmds = {"NEWRUN","ENDRUN" ,"NUM" ,"PRINT" ,"FILE","EVENT", "RESET","TIME", "DNF" ,"CANCEL" };
    int i;
    public CmdList() {
        this.i = -1;
    }
    public String left() {
        if (i-1 < 0) {
            i = cmds.length-1;
            return cmds[i];
        }
        i--;
        return cmds[i];
    }
    public String right() {
        if (i+1 == cmds.length) {
            i = 0;
            return cmds[i];
        }
        i++;
        return cmds[i];
    }
}
