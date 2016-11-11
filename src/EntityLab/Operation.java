package EntityLab;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by weizhaoy on 16/11/2.
 */
public class Operation {
    private OpInterface opInterface;
    private List<OpImplementation> opImplementations;

    public OpInterface getOpInterface() {
        return opInterface;
    }

    public void setOpInterface(OpInterface opInterface) {
        this.opInterface = opInterface;
    }

    public List<OpImplementation> getOpImplementations() {
        return opImplementations;
    }

    public void setOpImplementations(List<OpImplementation> opImplementations) {
        this.opImplementations = opImplementations;
    }

    public Operation(File opInterFile, List<File> opImplFiles){
        opInterface = new OpInterface(opInterFile);
        opImplementations = new ArrayList<OpImplementation>();
        for (File opImplFile : opImplFiles) {
            opImplementations.add(new OpImplementation(opImplFile));
        }
    }
}
