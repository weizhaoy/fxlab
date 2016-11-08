package EntityLab;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by weizhaoy on 16/11/2.
 */
public class FuzzTest {
    private static List<Seed> seeds;
    private static Operation operation;

    public FuzzTest(List<File> seedFiles, File opInterFile, List<File> opImplFiles){
        seeds = new ArrayList<Seed>();
        for (File seedFile : seedFiles) {
            seeds.add(new Seed(seedFile));
        }

        operation = new Operation(opInterFile, opImplFiles);
    }
}
