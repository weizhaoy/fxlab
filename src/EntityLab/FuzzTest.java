package EntityLab;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by weizhaoy on 16/11/2.
 */
public class FuzzTest {
    private  List<Seed> seeds;
    private  Operation operation;
    private  int SEED_TOTAL;
    private  int OP_TOTAL;
    private  double METRIC;
    /**
     * Metric, double or others?
     **/
    private static String STRATEGY = "Random";
    private static int ITER_NUM;//Times of Iterations

    public FuzzTest(List<File> seedFiles, File opInterFile, List<File> opImplFiles) {
        seeds = new ArrayList<Seed>();
        for (File seedFile : seedFiles) {
            seeds.add(new Seed(seedFile));
        }

        operation = new Operation(opInterFile, opImplFiles);

        SEED_TOTAL = seeds.size();
        OP_TOTAL = opImplFiles.size();
        ITER_NUM = 0;
    }

    public void addSeed(File seedFile) {
        seeds.add(new Seed(seedFile));
    }

    public  List<Seed> getSeeds() {
        return seeds;
    }

    public  void setSeeds(List<Seed> seeds) {
        this.seeds = seeds;
    }

    public  Operation getOperation() {
        return operation;
    }

    public  void setOperation(Operation operation) {
        this.operation = operation;
    }

    public  int getSeedTotal() {
        return SEED_TOTAL;
    }

    public  void setSeedTotal(int seedTotal) {
        SEED_TOTAL = seedTotal;
    }

    public  int getOpTotal() {
        return OP_TOTAL;
    }

    public  void setOpTotal(int opTotal) {
        OP_TOTAL = opTotal;
    }

    public void run() {
        System.out.println("FuzzTest Run!");
        ITER_NUM += 1;

        OpImplementation opImplementation;
        Method opMethod;
        Seed[] seeds;
        File[] seedFiles;
        if (STRATEGY.equals("XXXX")) {

        } else {
            /**Default: Random**/
            opImplementation = getRandomOpImpl();
            opMethod = getMethodFromOpImpl(opImplementation);
            int seedNum = opMethod.getParameterCount();
            seeds = getRandomSeed(seedNum);
            seedFiles = getFilesFromSeeds(seeds);



            try {
                Object opImplementationInstance = opImplementation.getOpClass().newInstance();//create instance
                File newSeedFile = (File) opMethod.invoke(opImplementationInstance,seedFiles);//invoke method
                if(newSeedFile.createNewFile()){
                    System.out.println("New Seed "+newSeedFile.getAbsolutePath()+" generated.");
                }else{
                    System.out.println("New Seed "+newSeedFile.getAbsolutePath()+" failed.");
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    private File[] getFilesFromSeeds(Seed[] seeds) {
        List<File> files = new ArrayList<File>();
        for (Seed seed : seeds) {
            /**increase seed frequency number by one**/
            seed.increaseTimesUsed();
            files.add(seed.getSeedFile());
        }
        return (File[]) files.toArray();
    }

    private Method getMethodFromOpImpl(OpImplementation opImplementation) {
        //todo: currently select the first one

        /**increase opImplementation frequency number by one**/
        opImplementation.increaseTimesUsed();

        return opImplementation.getOpMethods().get(0);
    }

    private Seed[] getRandomSeed(int seedNum) {
        List<Seed> seeds = new ArrayList<Seed>();
        if(seedNum <= this.seeds.size()){
            //todo: select seeds
        }else{
            seeds.addAll(this.seeds);
            Collections.addAll(seeds, getRandomSeed(seedNum-this.seeds.size()));
        }
        return (Seed[]) seeds.toArray();
    }

    private OpImplementation getRandomOpImpl() {
        List<OpImplementation> opImpls = this.operation.getOpImplementations();
        //todo: select OpImplementation
        return null;
    }

    


}
