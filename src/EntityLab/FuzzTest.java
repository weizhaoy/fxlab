package EntityLab;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

    public int getSEED_TOTAL() {
        return SEED_TOTAL;
    }

    public void setSEED_TOTAL(int SEED_TOTAL) {
        this.SEED_TOTAL = SEED_TOTAL;
    }

    public int getOP_TOTAL() {
        return OP_TOTAL;
    }

    public void setOP_TOTAL(int OP_TOTAL) {
        this.OP_TOTAL = OP_TOTAL;
    }

    public double getMETRIC() {
        return METRIC;
    }

    public void setMETRIC(double METRIC) {
        this.METRIC = METRIC;
    }

    public static String getSTRATEGY() {
        return STRATEGY;
    }

    public static void setSTRATEGY(String STRATEGY) {
        FuzzTest.STRATEGY = STRATEGY;
    }

    public static int getIterNum() {
        return ITER_NUM;
    }

    public static void setIterNum(int iterNum) {
        ITER_NUM = iterNum;
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
        File[] filesArr = new File[seeds.length];
        for (Seed seed : seeds) {
            /**increase seed frequency number by one**/
            seed.increaseTimesUsed();
            files.add(seed.getSeedFile());
        }

        files.toArray(filesArr);
        return filesArr;
    }

    private Method getMethodFromOpImpl(OpImplementation opImplementation) {
        //todo: currently select the first one

        /**increase opImplementation frequency number by one**/
        opImplementation.increaseTimesUsed();

        return opImplementation.getOpMethods().get(0);
    }

    private Seed[] getRandomSeed(int seedNum) {
        List<Seed> randomSeeds = new ArrayList<Seed>();
        Seed[] seedsArr = new Seed[seedNum];

//        if(seedNum <= this.seeds.size()){
            //todo: select seeds
            Random random = new Random();
            for(int i =0;i <seedNum;i++){
                //todo: assuming seed is duplicatable
                Seed s = seeds.get(random.nextInt(seeds.size()));
                randomSeeds.add(s);//potential bug
                System.out.println(s.getSeedFilePath());
            }
//        }else{
//            randomSeeds.addAll(this.seeds);
//            Collections.addAll(randomSeeds, getRandomSeed(seedNum-this.seeds.size()));
//        }
        randomSeeds.toArray(seedsArr);
        return seedsArr;
    }

    private OpImplementation getRandomOpImpl() {
        List<OpImplementation> opImpls = this.operation.getOpImplementations();
        //todo: select OpImplementation
        Random random = new Random();
        int idx = random.nextInt(opImpls.size());
        System.out.println("random op impl: "+idx);
        return opImpls.get(idx);
    }




}
