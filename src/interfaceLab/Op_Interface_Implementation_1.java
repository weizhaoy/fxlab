package InterfaceLab;

import ClassLoaderLab.Utils;

import java.io.File;

/**
 * Created by weizhaoy on 16/10/25.
 */
public class Op_Interface_Implementation_1 implements Op_Interface {


    @Override
    public File Op_mut_seed_num_1(File seed1) {
        System.out.println("Op_Interface_Implementation_1->Op_mut_seed_num_1(File seed1)");
        File seed = new File(seed1.getAbsolutePath()+".tmpseed");
        seed = Utils.renameFile(seed,"Op_Interface_Implementation_1"+"-"+"Op_mut_seed_num_1"+"-"+seed1.getName());

        return seed;
    }

    @Override
    public File Op_mut_seed_num_2(File seed1, File seed2) {
        System.out.println("Op_Interface_Implementation_1->Op_mut_seed_num_2(File seed1, File seed2)");
        File seed = new File(seed2.getAbsolutePath()+".tmpseed");
        seed = Utils.renameFile(seed,"Op_Interface_Implementation_1"+"-"+"Op_mut_seed_num_2"+"-"+seed1.getName()+"-"+seed2.getName());

        return seed;
    }

    @Override
    public File Op_mut_seed_num_3(File seed1, File seed2, File seed3) {
        System.out.println("Op_Interface_Implementation_1->Op_mut_seed_num_3(File seed1, File seed2, File seed3)");
        File seed = new File(seed3.getAbsolutePath()+".tmpseed");
        seed = Utils.renameFile(seed,"Op_Interface_Implementation_1"+"-"+"Op_mut_seed_num_3"+"-"+seed1.getName()+"-"+seed2.getName()+"-"+seed3.getName());

        return seed;
    }

    /*
    @Override
    public int Op_mut_2(int seed1_as_int, int seed2_as_int) {
        int result = seed1_as_int + seed2_as_int;
        return result;
    }
    */

    /*
    @Override
    public String Op_mut_3(String seed1_as_string, String seed2_as_string) {

        return seed1_as_string+seed2_as_string;
    }
    */
}
