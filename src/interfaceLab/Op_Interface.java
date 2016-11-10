package InterfaceLab;

import java.io.File;

/**
 * Created by weizhaoy on 16/10/25.
 */
public interface Op_Interface {

    File Op_mut_seed_num_1(File seed1);
    File Op_mut_seed_num_2(File seed1, File seed2);
    File Op_mut_seed_num_3(File seed1, File seed2, File seed3);

    int Op_mut_2(int seed1_as_int, int seed2_as_int);

    String Op_mut_3(String seed1_as_string, String seed2_as_string);


}
