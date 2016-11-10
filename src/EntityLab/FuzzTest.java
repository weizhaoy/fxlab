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
    private static int SEED_TOTAL;
    private static int OP_TOTAL;
    private static int ITER_NUM;//Times of Iterations

    public FuzzTest(List<File> seedFiles, File opInterFile, List<File> opImplFiles){
        seeds = new ArrayList<Seed>();
        for (File seedFile : seedFiles) {
            seeds.add(new Seed(seedFile));
        }

        operation = new Operation(opInterFile, opImplFiles);

        SEED_TOTAL = seeds.size();
        OP_TOTAL = opImplFiles.size();
        ITER_NUM = 0;
    }

    public void addSeed(File seedFile){
        seeds.add(new Seed(seedFile));
    }

    public static List<Seed> getSeeds() {
        return seeds;
    }

    public static void setSeeds(List<Seed> seeds) {
        FuzzTest.seeds = seeds;
    }

    public static Operation getOperation() {
        return operation;
    }

    public static void setOperation(Operation operation) {
        FuzzTest.operation = operation;
    }

    public static int getSeedTotal() {
        return SEED_TOTAL;
    }

    public static void setSeedTotal(int seedTotal) {
        SEED_TOTAL = seedTotal;
    }

    public static int getOpTotal() {
        return OP_TOTAL;
    }

    public static void setOpTotal(int opTotal) {
        OP_TOTAL = opTotal;
    }

    public void run() {
        System.out.println("FuzzTest Run!");
    }
}
