package InterfaceLab;

import java.io.File;

/**
 * Created by weizhaoy on 16/10/25.
 */
public class Op_Interface_Implementation_1 implements Op_Interface {


    @Override
    public File Op_mut_seed_num_1(File seed1) {
        System.out.println("Op_Interface_Implementation_1->Op_mut_seed_num_1(File seed1)");
        return seed1;
    }

    @Override
    public File Op_mut_seed_num_2(File seed1, File seed2) {
        System.out.println("Op_Interface_Implementation_1->Op_mut_seed_num_2(File seed1, File seed2)");
        return seed2;
    }

    @Override
    public File Op_mut_seed_num_3(File seed1, File seed2, File seed3) {
        System.out.println("Op_Interface_Implementation_1->Op_mut_seed_num_3(File seed1, File seed2, File seed3)");
        return seed3;
    }

    @Override
    public int Op_mut_2(int seed1_as_int, int seed2_as_int) {
        int result = seed1_as_int + seed2_as_int;
        return result;
    }

    @Override
    public String Op_mut_3(String seed1_as_string, String seed2_as_string) {

        return seed1_as_string+seed2_as_string;
    }
}
