package EntityLab;

import java.io.File;

/**
 * Created by weizhaoy on 16/11/2.
 */
public class OpImplementation extends OpBase {

    private int timesUsed;

    public OpImplementation(File file) {
        super(file);
        timesUsed = 0;
    }

    public int getTimesUsed() {
        return timesUsed;
    }

    public void setTimesUsed(int timesUsed) {
        this.timesUsed = timesUsed;
    }

    public void increaseTimesUsed(){
        timesUsed+=1;
    }
}
