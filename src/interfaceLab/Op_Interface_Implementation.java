package interfaceLab;

import java.io.File;

/**
 * Created by weizhaoy on 16/10/25.
 */
public class Op_Interface_Implementation implements Op_Interface {
    @Override
    public void Op_mut_1(File seed1, File seed2) {
        if(seed1 != null && seed2 != null){
            System.out.println("Mutate seed1 and seed2");
        }else{
            System.out.println("Invalid input.");
        }
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
